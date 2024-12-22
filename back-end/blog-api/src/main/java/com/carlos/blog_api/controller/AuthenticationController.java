package com.carlos.blog_api.controller;

import com.carlos.blog_api.dto.AuthenticationDTO;
import com.carlos.blog_api.dto.RegisterDTO;
import com.carlos.blog_api.dto.UserDTO;
import com.carlos.blog_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(AuthenticationDTO authDTO){
        return userService.loginUser(authDTO);
    }

    @PostMapping("/register")
    public UserDTO register(RegisterDTO dto) throws Exception{
        return userService.register(dto);
    }

    //envio de email para recuperacao de senha
    @PostMapping("/sendEmail/{email}")
    public String sendEmailForChangePassword(@PathVariable String email){
        return userService.sendEmailForChangePass(email);
    }
    //troca de senha
    @PostMapping("{token}/changePassword/{newPass}")
    public String changePassword(@PathVariable String token, @PathVariable String newPass) throws Exception {
        return userService.changePassword(token,newPass);
    }


}
