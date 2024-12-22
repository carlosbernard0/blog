package com.carlos.blog_api.security;


import com.carlos.blog_api.entity.UserEntity;
import com.carlos.blog_api.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //recuperar o token da request
        String tokenBearer = recoverToken(request);

        //validar o token
        try {
            UserEntity userEntity = tokenService.validateToken(tokenBearer);
            if(userEntity == null){
                throw new RuntimeException("Error! User is Null.");
            }
            UsernamePasswordAuthenticationToken tokenSpring = new UsernamePasswordAuthenticationToken(
                    userEntity, null, userEntity.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(tokenSpring);

        } catch (Exception e) {
            SecurityContextHolder.getContext().setAuthentication(null);

        }
        //continua o proximo filtro
        filterChain.doFilter(request, response);

    }

    //metodo para recuperar o token
    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ","");
    }
}
