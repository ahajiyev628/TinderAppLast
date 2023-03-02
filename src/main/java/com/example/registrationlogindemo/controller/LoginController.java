package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.SignInRequest;
import com.example.registrationlogindemo.entity.Role;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.UserRepository;
import com.example.registrationlogindemo.service.UserService;
import com.example.registrationlogindemo.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private PasswordEncoder passwordEncoder;

    SignInRequest request;
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public RedirectView submitLoginForm(SignInRequest request,  HttpSession session) {
        String email = request.getEmail();
        String password = request.getPassword();
        log.info("girildi post");
        boolean isAuthenticated = passwordEncoder.matches(request.getPassword(),userService.findByEmail(email).getPassword());
        log.info(userService.findByEmail(email).getEmail());
        log.info(password);
        log.info(userService.findByEmail(email).getPassword());
//        log.info("girildi post " +  passwordEncoder.encode(password));
        if (isAuthenticated) {
            String username = userService.findByEmail(email).getName();
            log.info("giriş doğruysa, kullanıcıyı bir sonraki sayfaya yönlendirin");
            session.setAttribute("username", username); // store the username in the session
            return new RedirectView("/mainpage");

        } else {
            log.info("yanlış giriş yapıldığını bildiren bir hata mesajı gösterin");
            return new RedirectView("login");
    //           "/login?error=true"
        }
    }

    @GetMapping("/mainpage")
    public String showMainPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username"); // retrieve the username from the session
        model.addAttribute("username", username);
        return "mainpage";
    }


//    @GetMapping("/mainpage")
//    public String showMyPage(Model model, Authentication authentication) {
//        log.info("tapilmadi");
//
//        User user = new User(7L,"Jhon", "jhon84@gmail.com","123",
//                (List<Role>) new ArrayList<Role>());
//
//        //        getCurrentUsername(authentication)
//        model.addAttribute("currentUsername", user);
//        log.info(getCurrentUsername(authentication));
//        log.info(user.getName());
//        log.info();
////        log.info(userService.findByEmail("jama93@gmial.com").getName());
//        return "mainpage";
//    }
//    private String getCurrentUsername(Authentication authentication) {
//        if (authentication != null && authentication.isAuthenticated()) {
//            Object principal = authentication.getPrincipal();
//            if (principal instanceof UserDetails) {
//                return ((UserDetails)principal).getUsername();
//            } else {
//                return principal.toString();
//            }
//        } else {
//            return null;
//        }
//    }


}
