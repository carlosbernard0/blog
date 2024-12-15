package com.carlos.blog_api.repository;

import com.carlos.blog_api.entity.PostEntity;
import com.carlos.blog_api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {



}
