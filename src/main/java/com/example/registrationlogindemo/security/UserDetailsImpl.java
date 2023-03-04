package com.example.registrationlogindemo.security;
//
//import com.example.registrationlogindemo.entity.Role;
//import com.example.registrationlogindemo.entity.User;
//import com.example.registrationlogindemo.repository.UserRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//@AllArgsConstructor
//@Service
//public class UserDetailsImpl implements UserDetailsService {
//    private UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email);
//
//        if (user != null) {
//            return new org.springframework.security.core.userdetails.User(
//                    user.getEmail(),
//                    user.getPassword(),
//                    mapRolesToAuthorities(user.getRoles()));
//
//        }else{
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//    }
//    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
//        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//        return mapRoles;
//    }
//
//}
//

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserDetailsImpl(String username, String password, List<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
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

