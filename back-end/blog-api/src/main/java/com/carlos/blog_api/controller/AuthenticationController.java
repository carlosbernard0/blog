package com.carlos.blog_api.controller;

import com.carlos.blog_api.dto.AuthenticationDTO;
import com.carlos.blog_api.dto.RegisterDTO;
import com.carlos.blog_api.dto.UserDTO;
import com.carlos.blog_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody AuthenticationDTO authDTO){
        return userService.loginUser(authDTO);
    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody RegisterDTO dto) throws Exception{
        return userService.register(dto);
    }

    //envio de email para recuperacao de senha
    @PostMapping("/sendEmailForChangePass")
    public String sendEmailForChangePassword(@RequestParam String email){
        return userService.sendEmailForChangePass(email);
    }
    //troca de senha
    @PostMapping("{token}/changePassword/{newPass}")
    public String changePassword(@PathVariable String token, @PathVariable String newPass) throws Exception {
        return userService.changePassword(token,newPass);
    }

    @PostMapping("/validateTwoFactor")
    public String validateLoginTwoFactor(@RequestParam String numericCode,@RequestParam String email) throws Exception {
        return userService.generateTokenTwoFactor(numericCode, email);
    }
}
