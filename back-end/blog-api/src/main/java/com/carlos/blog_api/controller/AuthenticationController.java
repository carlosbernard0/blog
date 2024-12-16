package com.carlos.blog_api.controller;

import com.carlos.blog_api.dto.AuthenticationDTO;
import com.carlos.blog_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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


}
