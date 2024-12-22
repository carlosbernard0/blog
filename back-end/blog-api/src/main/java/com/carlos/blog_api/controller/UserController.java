package com.carlos.blog_api.controller;

import com.carlos.blog_api.dto.UserDTO;
import com.carlos.blog_api.entity.UserEntity;
import com.carlos.blog_api.service.EmailService;
import com.carlos.blog_api.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @GetMapping("/{idUser}")
    public UserDTO getUser(@PathVariable Integer idUser){
        return userService.getUser(idUser);
    }

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }


    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO user){
        return userService.createUser(user);
    }
    @PutMapping("/{id}")
    public UserDTO updateUser(@RequestBody UserDTO user, @PathVariable Integer id){
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/{idUser}")
    public void deleteUser(@PathVariable Integer idUser){
        userService.deleteUser(idUser);
    }

    @PatchMapping("/{idUser}/changeAdmin")
    public UserDTO changeAdminUser(@PathVariable Integer idUSer){
        return userService.changeAdminUser(idUSer);
    }



}
