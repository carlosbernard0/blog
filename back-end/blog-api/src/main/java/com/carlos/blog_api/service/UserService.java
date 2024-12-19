package com.carlos.blog_api.service;

import com.carlos.blog_api.dto.AuthenticationDTO;
import com.carlos.blog_api.dto.RegisterDTO;
import com.carlos.blog_api.dto.UserDTO;
import com.carlos.blog_api.entity.UserEntity;
import com.carlos.blog_api.enums.UserRole;
import com.carlos.blog_api.mapper.UserMapper;
import com.carlos.blog_api.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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

    @Value("${api.security.token.secret}")
    private String secret;
    @Value("${api.security.token.validate}")
    private String validateJWT;

    public UserService(@Lazy AuthenticationManager authenticationManager, UserMapper userMapper,@Lazy UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
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

            Date dateNow = new Date();
            Date dateExpiration = new Date(dateNow.getTime() + Long.parseLong(validateJWT));

            String jwt = Jwts.builder()
                    .setIssuer("blog-api")
                    .setSubject(userEntity.getIdUser().toString())
                    .setIssuedAt(new Date())
                    .setExpiration(dateExpiration)
                    .signWith(SignatureAlgorithm.HS256,secret)
                    .compact();

            return jwt;

        }catch (AuthenticationException ex){
            System.out.println(ex);
            throw new RuntimeException("User or Password is invalid!");

        }
    }

    public UserEntity validateToken(String token) throws Exception {
        if(token == null){
            throw  new Exception("Token not found!");
        }

        String tokenClean = token.replace("Bearer ", "");

        Claims claims = Jwts.parser()
                .setSigningKey(secret) // acesso com a secret
                .parseClaimsJws(tokenClean)  //valida o token e decriptografa
                .getBody(); //recupera o payload o token

        String subject = claims.getSubject(); //id do usuario que foi utilizado para fazer o login

        Optional<UserEntity> user = userRepository.findById(Integer.parseInt(subject));

        return user.orElse(null);
    }

    public Optional<UserEntity> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    
    public UserDTO register(RegisterDTO dto) throws Exception {
        if(userRepository.findByUsername(dto.getUsername()).isPresent()){
            throw new Exception("This username is registered!");
        }
        if(userRepository.findByEmailUser(dto.getUsername()).isPresent()){
            throw new Exception("This email is registered!");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String passEncrypted = bCryptPasswordEncoder.encode(dto.getPassword());
        
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
}
