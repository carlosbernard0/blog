package com.carlos.blog_api.entity;

import com.carlos.blog_api.enums.PostStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private Integer idComment;

    @Column(name = "content_comment")
    private String contentComment;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "id_user", insertable = false, updatable = false)
    private Integer idUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private UserEntity user;

    @Column(name = "id_post", insertable = false, updatable = false)
    private Integer idPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_post", referencedColumnName = "id_post")
    private PostEntity post;

}
