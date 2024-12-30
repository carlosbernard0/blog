package com.carlos.blog_api.controller;

import com.carlos.blog_api.dto.PostDTO;
import com.carlos.blog_api.dto.PostDTOCreateUpdate;
import com.carlos.blog_api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{idPost}")
    public PostDTO getPost(@PathVariable Integer idPost){
        return postService.getPost(idPost);
    }


    @GetMapping
    public List<PostDTO> getAllPosts(){
        return postService.getAllPosts();
    }


    @PostMapping
    public PostDTO createPost(@RequestBody PostDTOCreateUpdate post){
        return postService.createPost(post);
    }
    @PutMapping("/{idPost}")
    public PostDTO updatePost(@RequestBody PostDTOCreateUpdate postDTO, @PathVariable Integer idPost){
        return postService.updatePost(postDTO, idPost);
    }

    @DeleteMapping("/{idPost}")
    public void deletePost(@PathVariable Integer idPost){
        postService.deletePost(idPost);
    }

    @PatchMapping("/{id}/approve")
    public PostDTO approvePost(Integer id){
        return postService.approvePost(id);
    }
}
