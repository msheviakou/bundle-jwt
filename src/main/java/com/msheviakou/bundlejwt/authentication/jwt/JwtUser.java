package com.msheviakou.bundlejwt.authentication.jwt;

import com.msheviakou.bundlejwt.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class JwtUser implements UserDetails {

    private User user;
    private List<GrantedAuthority> authorities;

    JwtUser(User user, List<GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    private static JwtUser create(User user) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return new JwtUser(user, authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    @Override
    public String getPassword() { return user.getPassword(); }

    @Override
    public String getUsername() { return user.getEmail(); }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    public User getUser() { return user; }
}
