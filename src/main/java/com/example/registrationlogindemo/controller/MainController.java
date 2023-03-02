package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.SignInRequest;
import com.example.registrationlogindemo.entity.Role;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.service.UserService;
import com.sun.security.auth.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

//    @GetMapping("/mainpage")
//    public String showMainPage() {
//        return "mainpage";
//    }

//    @GetMapping("/mainpage")
//    public ModelAndView getUser() {
//
//        User user = new User(7L,"Jhon", "jhon84@gmail.com","123",
//                (List<Role>) new ArrayList<Role>());
//
//        ModelAndView modelAndView = new ModelAndView("user");
//        modelAndView.addObject("user", user);
//        log.info("bitdi");
//        return modelAndView;
//    }

//    @GetMapping("/mainpage")
//    public String showMainPage(Authentication authentication, Model model) {
//        String currentUserName = authentication.getName();
//        model.addAttribute("userName", currentUserName);
//        System.out.println(currentUserName);
//        return "mainpage";
//    }


}