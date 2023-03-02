package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.UserRepository;
import com.example.registrationlogindemo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
        @Autowired
        private final UserRepository userRepository;
        private BCryptPasswordEncoder passwordEncoder;

        @Autowired
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }



        public User findByEmail(String email) {
            return userRepository.findByEmail(email);
        }

//        public boolean authenticate(String email, String password) {
//            User user = findByEmail(email);
//            if (user == null) {
//                return false;
//            }
//            return passwordEncoder.matches(password, user.getPassword());
//        }
    }


