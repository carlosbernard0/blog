package com.carlos.blog_api.entity;

import com.carlos.blog_api.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public String getPassword() {
        return passwordUser;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

