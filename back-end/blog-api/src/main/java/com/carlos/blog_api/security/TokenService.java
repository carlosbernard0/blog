package com.carlos.blog_api.security;

import com.carlos.blog_api.entity.UserEntity;
import com.carlos.blog_api.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    @Value("${api.security.token.validate}")
    private String validateJWT;

    private UserService userService;

    public TokenService(@Lazy UserService userService) {
        this.userService = userService;
    }

    public String createToken(String subject){
        Date dateNow = new Date();
        Date dateExpiration = new Date(dateNow.getTime() + Long.parseLong(validateJWT));

        //se o subject for email a expiracao do token é de 5 min
        if(subject.contains("@")){
            dateExpiration = new Date(dateNow.getTime() + 5 * 60 * 1000);
        }


        String jwt = Jwts.builder()
                .setIssuer("blog-api")
                .setSubject(subject)
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

        String tokenClean = token.replace("Bearer ", "");

        Claims claims = Jwts.parser()
                .setSigningKey(secret) // acesso com a secret
                .parseClaimsJws(tokenClean)  //valida o token e decriptografa
                .getBody(); //recupera o payload o token

        String subject = claims.getSubject(); //id do usuario que foi utilizado para fazer o login
        Optional<UserEntity> user;
        if(subject.contains("@")){
            user = userService.findByEmail(subject);
        }else{
            user = userService.findById(Integer.parseInt(subject));

        }


        return user.orElse(null);
    }
}
