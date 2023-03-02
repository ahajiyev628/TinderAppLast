//package com.example.registrationlogindemo.service.impl;
//
//import com.example.registrationlogindemo.dto.SignInRequest;
//import com.example.registrationlogindemo.entity.User;
//import com.example.registrationlogindemo.repository.UserRepository;
//import com.example.registrationlogindemo.service.AuthService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.util.ObjectUtils;
//
//import static org.springframework.util.ObjectUtils.isEmpty;
//
//@RequiredArgsConstructor
//public class AuthServiceImpl implements AuthService {
//
//    private UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public void signIn(SignInRequest request) {
//         User userEntity = this.userRepository.findByEmail(request.getEmail());
//         if (!isEmpty(userEntity)) {
//             if (this.passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
//
//             }
//         }
//    }
//}
