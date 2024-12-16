package com.carlos.blog_api.service;

import com.carlos.blog_api.dto.CommentDTO;
import com.carlos.blog_api.dto.CommentDTOCreateUpdate;
import com.carlos.blog_api.entity.CommentEntity;
import com.carlos.blog_api.entity.PostEntity;
import com.carlos.blog_api.entity.UserEntity;
import com.carlos.blog_api.mapper.CommentMapper;
import com.carlos.blog_api.repository.CommentRepository;
import com.carlos.blog_api.repository.PostRepository;
import com.carlos.blog_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public CommentDTO createComment(CommentDTOCreateUpdate commentDTOCreateUpdate){
        CommentEntity commentEntity = commentMapper.convertToEntityCreate(commentDTOCreateUpdate);
        Optional<UserEntity> userEntityOptional = userRepository.findById(commentEntity.getIdUser());
        if(!userEntityOptional.isPresent()){
            throw new RuntimeException("User not found in CreateComment");
        }
        Optional<PostEntity> postEntityOptional = postRepository.findById(commentEntity.getIdPost());
        if(!postEntityOptional.isPresent()){
            throw new RuntimeException("Post not found in CreateComment");
        }
        commentEntity.setUser(userEntityOptional.get());
        commentEntity.setPost(postEntityOptional.get());
        commentEntity.setCreateDate(new Date());
        commentEntity.setUpdateDate(commentEntity.getCreateDate());
        commentRepository.save(commentEntity);
        return commentMapper.convertToDTO(commentEntity);
    }


    public List<CommentDTO> getAllCommentsOfPost(Integer idPost){
        Optional<PostEntity> postEntityOptinal = postRepository.findById(idPost);
        if(!postEntityOptinal.isPresent()){
            throw new RuntimeException("Post not found!");
        }
        List<CommentDTO> commentDTOList = postEntityOptinal.get()
                .getComments()
                .stream()
                .map(commentMapper::convertToDTO)
                .toList();
        return commentDTOList;
    }

    public List<CommentDTO> getAllCommentsOfUser(Integer idUser){
        Optional<UserEntity> entityOptional = userRepository.findById(idUser);
        if(!entityOptional.isPresent()){
            throw new RuntimeException("Post not found!");
        }
        List<CommentDTO> commentDTOList = entityOptional.get()
                .getComments()
                .stream()
                .map(commentMapper::convertToDTO)
                .toList();
        return commentDTOList;
    }

    public CommentDTO updateComment(CommentDTOCreateUpdate commentDTOCreateUpdate, Integer idComment){
        Optional<CommentEntity> optionalCommentEntity = commentRepository.findById(idComment);
        if(optionalCommentEntity.isPresent()){
            CommentEntity commentEntity = optionalCommentEntity.get();
            commentEntity.setContentComment(commentDTOCreateUpdate.getContentComment());
            commentEntity.setUpdateDate(new Date());
            commentRepository.save(commentEntity);
            return commentMapper.convertToDTO(commentEntity);
        }else {
            throw new RuntimeException("Comment not found!");
        }
    }

    public void deleteComment(Integer idComment){
        commentRepository.deleteById(idComment);
    }


}
