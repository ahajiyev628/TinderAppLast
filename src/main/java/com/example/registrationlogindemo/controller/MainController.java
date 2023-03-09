package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.SignInRequest;
import com.example.registrationlogindemo.entity.Role;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.UserRepository;
import com.example.registrationlogindemo.service.UserService;
import com.sun.security.auth.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.validation.Valid;
//import java.util.ArrayList;
//import java.util.List;
@Controller
@Slf4j
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

//    @GetMapping("/mainpage")
//    public String showMainPage(HttpSession session, Model model) {
//        String username = (String) session.getAttribute("username"); // retrieve the username from the session
//        model.addAttribute("username", username);
//        return "mainpage";
//    }


//    @GetMapping("/mainpage")
//    public String showHomePage(Model model) {
//
//        return "mainpage";
//    }

    @GetMapping("/mainpage")
    public String showMainPage(HttpSession session, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            String username = (String) session.getAttribute("username"); // retrieve the username from the session
            model.addAttribute("username", username);
            List<User> users = userRepository.findAll();
            model.addAttribute("users", users);
        }
        return "mainpage";
    }

}