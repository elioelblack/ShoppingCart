package com.cart.shopping.security;

import com.cart.shopping.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDetails implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public AppUserDetails(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        List<SimpleGrantedAuthority> authorities = new
                ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(Constants.ROLE_DEFAULT));

        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
