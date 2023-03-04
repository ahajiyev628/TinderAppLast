package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class UserService {
        private final UserRepository userRepository;
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }
        public User findByEmail(String email) {
            return userRepository.findByEmail(email);
        }

        public List<User> findAll() {return userRepository.findAll();}
        public Optional<User> findById(Long id) {return userRepository.findById(id);}

        public void saveUser(User u) {userRepository.save(u);}
    }


