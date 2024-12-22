package com.carlos.blog_api.service;

import com.carlos.blog_api.dto.AuthenticationDTO;
import com.carlos.blog_api.dto.RegisterDTO;
import com.carlos.blog_api.dto.UserDTO;
import com.carlos.blog_api.entity.UserEntity;
import com.carlos.blog_api.enums.UserRole;
import com.carlos.blog_api.mapper.UserMapper;
import com.carlos.blog_api.repository.UserRepository;
import com.carlos.blog_api.security.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final TokenService tokenService;

    @Value("${api.security.token.secret}")
    private String secret;
    @Value("${api.security.token.validate}")
    private String validateJWT;

    public UserService(@Lazy AuthenticationManager authenticationManager,
                       UserMapper userMapper,
                       @Lazy UserRepository userRepository,
                       EmailService emailService,
                       @Lazy TokenService tokenService
   ) {
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.tokenService = tokenService;
    }

    public UserDTO createUser(UserDTO userDTO){
        UserEntity userEntity = userMapper.convertToEntity(userDTO);
        userRepository.save(userEntity);
        return userMapper.convertToDTO(userEntity);
    }

    public UserDTO getUser(Integer idUser){
        Optional<UserEntity> userEntityOptional = userRepository.findById(idUser);
        if(userEntityOptional.isPresent()){
            return userMapper.convertToDTO(userEntityOptional.get());
        }else{
            throw new RuntimeException("User not found!");
        }
    }

    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(userMapper::convertToDTO).toList();
    }

    public UserDTO updateUser(UserDTO userDTO, Integer idUser){
        Optional<UserEntity> userEntityOptional = userRepository.findById(idUser);
        if(userEntityOptional.isPresent()){
            userDTO.setIdUser(idUser);
            UserEntity userUpdated = userMapper.convertToEntity(userDTO);
            userRepository.save(userUpdated);
            return userMapper.convertToDTO(userUpdated);
        }else {
            throw new RuntimeException("User not found!");
        }
    }

    public void deleteUser(Integer idUser){
        userRepository.deleteById(idUser);
    }

    public UserDTO changeAdminUser(Integer idUser){
        Optional<UserEntity> userEntityOptional = userRepository.findById(idUser);
        if(!userEntityOptional.isPresent()){
            throw new RuntimeException("User not found!");
        }
        UserEntity userEntity = userEntityOptional.get();
        userEntity.setRoleUser(UserRole.ADMIN);
        userRepository.save(userEntity);
        return userMapper.convertToDTO(userEntity);
    }

    public String loginUser(AuthenticationDTO authDTO){
        UsernamePasswordAuthenticationToken dtoSpring = new UsernamePasswordAuthenticationToken(
                authDTO.getLogin(),
                authDTO.getPassword());

        try{

            System.out.println(dtoSpring);
            Authentication authentication = authenticationManager.authenticate(dtoSpring);

            Object userAuth = authentication.getPrincipal();
            UserEntity userEntity = (UserEntity) userAuth;

            String token = tokenService.createToken(userEntity.getIdUser().toString());

            return token;

        }catch (AuthenticationException ex){
            System.out.println(ex);
            throw new RuntimeException("User or Password is invalid!");

        }
    }

    public Optional<UserEntity> findById(Integer id){
        return userRepository.findById(id);
    }

    public Optional<UserEntity> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<UserEntity> findByEmail(String email){
        return userRepository.findByEmailUser(email);
    }
    public UserDTO register(RegisterDTO dto) throws Exception {
        if(userRepository.findByUsername(dto.getUsername()).isPresent()){
            throw new Exception("This username is registered!");
        }
        if(userRepository.findByEmailUser(dto.getUsername()).isPresent()){
            throw new Exception("This email is registered!");
        }

        String passEncrypted = this.encryptedPass(dto.getPassword());
        
        UserEntity entity = new UserEntity();
        entity.setRoleUser(UserRole.USER);
        entity.setPasswordUser(passEncrypted);
        entity.setEmailUser(dto.getEmail());
        entity.setUsername(dto.getUsername());
        entity.setComments(new HashSet<>());
        entity.setPosts(new HashSet<>());

        userRepository.save(entity);
        return userMapper.convertToDTO(entity);
    }

    public String sendEmailForChangePass(String email){
        Optional<UserEntity> userEntityOptional =userRepository.findByEmailUser(email);
        if(!userEntityOptional.isPresent()){
            throw new RuntimeException("User is not found in DATABASE!");
        }

        String token = tokenService.createToken(email);

        //caminho do para a tela de troca de senha do front;
        String resetLink ="http://localhost:5173/login/password_recovery?token=" + token;

        emailService.sendTextEmail(
                email,
                "Recuperação de Senha",
                "Seu token: " +   token +
                        "\n Clique no link para recuperar sua senha: "
                        + resetLink + "\n O link de recuperação de senha expira em 5 minutos! " +
                        "\n Se você não fez essa solicitação, ignore esse email."
        );
        return "E-mail enviado com sucesso!";


    }

    public String changePassword(String token,String newPassword) throws Exception {

        try{
            UserEntity userEntity = tokenService.validateToken(token);
            String newPasswordEncrypted = this.encryptedPass(newPassword);

            userEntity.setPasswordUser(newPasswordEncrypted);
            userRepository.save(userEntity);
            return "User Updated and Pass: " + newPassword + " is encrypted...";

        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public String encryptedPass(String pass){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        return bCryptPasswordEncoder.encode(pass);
    }

}
