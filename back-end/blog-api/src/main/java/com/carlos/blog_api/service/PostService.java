package com.carlos.blog_api.service;

import com.carlos.blog_api.dto.PostDTO;
import com.carlos.blog_api.dto.PostDTOCreateUpdate;
import com.carlos.blog_api.dto.UserDTO;
import com.carlos.blog_api.entity.PostEntity;
import com.carlos.blog_api.entity.UserEntity;
import com.carlos.blog_api.enums.PostStatus;
import com.carlos.blog_api.mapper.PostMapper;
import com.carlos.blog_api.repository.PostRepository;
import com.carlos.blog_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public PostDTO createPost(PostDTOCreateUpdate postDTO){
        UserDTO userRecover = userService.recoverUser();
        Optional<UserEntity> user = userRepository.findById(userRecover.getIdUser());
        PostEntity postEntity = postMapper.convertToEntityCreate(postDTO);
        postEntity.setUser(user.get());
        postEntity.setIdUser(user.get().getIdUser());
        postEntity.setStatusPost(PostStatus.PENDING);
        postEntity.setCreateDate(new Date());
        postEntity.setUpdateDate(postEntity.getCreateDate());
        postRepository.save(postEntity);
        return postMapper.convertToDTO(postEntity);
    }

    public PostDTO getPost(Integer postId){
        Optional<PostEntity> optionalPostEntity = postRepository.findById(postId);
        if(optionalPostEntity.isPresent()){
            return postMapper.convertToDTO(optionalPostEntity.get());
        }else{
            throw new RuntimeException("User not found!");
        }
    }

    public List<PostDTO> getAllPosts(){
        return postRepository.findAll().stream().map(postMapper::convertToDTO).toList();
    }

    public PostDTO updatePost(PostDTOCreateUpdate postDTO, Integer postId){
        Optional<PostEntity> optionalPostEntity = postRepository.findById(postId);
        if(optionalPostEntity.isPresent()){
            PostEntity postUpdated = optionalPostEntity.get();
            postUpdated.setTitlePost(postDTO.getTitlePost());
            postUpdated.setContentPost(postDTO.getContentPost());
            postUpdated.setUpdateDate(new Date());
            postRepository.save(postUpdated);
            return postMapper.convertToDTO(postUpdated);
        }else {
            throw new RuntimeException("User not found!");
        }
    }

    public void deletePost(Integer idPost){
        postRepository.deleteById(idPost);
    }

    public PostDTO approvePost(Integer idPost){
        Optional<PostEntity> postEntityOptional = postRepository.findById(idPost);
        if(postEntityOptional.isPresent()){
            PostEntity post = postEntityOptional.get();
            post.setStatusPost(PostStatus.APPROVED);
            postRepository.save(post);
            return postMapper.convertToDTO(post);
        }else {
            throw new RuntimeException("Post not found!");
        }
    }
}
