package com.carlos.blog_api.repository;

import com.carlos.blog_api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsernameAndPasswordUser(String login, String password);
    Optional<UserEntity> findByEmailUserAndPasswordUser(String login, String password);

}
