package com.carlos.blog_api.service;

import com.carlos.blog_api.dto.AuthenticationDTO;
import com.carlos.blog_api.dto.UserDTO;
import com.carlos.blog_api.entity.UserEntity;
import com.carlos.blog_api.mapper.UserMapper;
import com.carlos.blog_api.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Value("${api.security.token.secret}")
    private String secret;
    @Value("${api.security.token.validate}")
    private String validateJWT;

    @Autowired
    private UserMapper userMapper;


    public UserService( UserRepository userRepository) {
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

    public String loginUser(AuthenticationDTO authDTO){
        Optional<UserEntity> userEntityOptional;
        if(authDTO.getLogin().contains("@")){
            userEntityOptional = userRepository.findByEmailUserAndPasswordUser(authDTO.getLogin(),authDTO.getPassword());
        }else{
            userEntityOptional = userRepository.findByUsernameAndPasswordUser(authDTO.getLogin(), authDTO.getPassword());
        };
        if(!userEntityOptional.isPresent()){
            throw new RuntimeException("User or Password is invalid!");
        }
        UserEntity user = userEntityOptional.get();

        Date dateNow = new Date();
        Date dateExpiration = new Date(dateNow.getTime() + Long.parseLong(validateJWT));

        String jwt = Jwts.builder()
                .setIssuer("blog-api")
                .setSubject(user.getIdUser().toString())
                .setIssuedAt(new Date())
                .setExpiration(dateExpiration)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();

        return jwt;

    }

    public UserEntity validateToken(String token) throws Exception {
        if(token == null){
            throw  new Exception("Token not found!");
        }
        System.out.println("Token: " + token);

        String tokenClean = token.replace("Bearer ", "");

        Claims claims = Jwts.parser()
                .setSigningKey(secret) // acesso com a secret
                .parseClaimsJws(tokenClean)  //valida o token e decriptografa
                .getBody(); //recupera o payload o token

        String subject = claims.getSubject(); //id do usuario que foi utilizado para fazer o login

        Optional<UserEntity> user = userRepository.findById(Integer.parseInt(subject));

        return user.orElse(null);
    }
}
