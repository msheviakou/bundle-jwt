package com.msheviakou.bundlejwt.user;

import com.msheviakou.bundlejwt.common.BaseEntity;
import com.msheviakou.bundlejwt.role.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;
}
