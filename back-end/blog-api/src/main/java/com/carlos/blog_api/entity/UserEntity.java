package com.carlos.blog_api.entity;

import com.carlos.blog_api.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String emailUser;

    @Column(name = "pass_user")
    private String passwordUser;

    @Column(name = "role_user")
    @Enumerated(EnumType.STRING)
    private UserRole roleUser;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<PostEntity> posts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<CommentEntity> comments;

}

