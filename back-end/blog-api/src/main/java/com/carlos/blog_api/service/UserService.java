package com.carlos.blog_api.service;

import com.carlos.blog_api.dto.UserDTO;
import com.carlos.blog_api.entity.UserEntity;
import com.carlos.blog_api.mapper.UserMapper;
import com.carlos.blog_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

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
}
