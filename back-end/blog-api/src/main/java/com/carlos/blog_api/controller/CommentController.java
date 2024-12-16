package com.carlos.blog_api.controller;

import com.carlos.blog_api.dto.CommentDTO;
import com.carlos.blog_api.dto.CommentDTOCreateUpdate;
import com.carlos.blog_api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/post/{idPost}")
    public List<CommentDTO> getAllCommentsOfPost(@PathVariable Integer idPost){
        return commentService.getAllCommentsOfPost(idPost);
    }
    @GetMapping("/user/{idUser}")
    public List<CommentDTO> getAllCommentsOfUser(@PathVariable Integer idUser){
        return commentService.getAllCommentsOfUser(idUser);
    }

    @PostMapping
    public CommentDTO createComment(@RequestBody CommentDTOCreateUpdate commentDTO){
        return commentService.createComment(commentDTO);
    }
    @PutMapping("/{idComment}")
    public CommentDTO updatePost(@RequestBody CommentDTOCreateUpdate commentDTO, @PathVariable Integer idComment){
        return commentService.updateComment(commentDTO, idComment);
    }

    @DeleteMapping("/{idComment}")
    public void deleteComment(@PathVariable Integer idComment){
        commentService.deleteComment(idComment);
    }

}
