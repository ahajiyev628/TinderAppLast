package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.User;
import javax.servlet.http.HttpSession;

import com.example.registrationlogindemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/mainpage")
    public String showHomePage(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "mainpage";
    }
//    @GetMapping("/home")
//    public String showHomePage(HttpSession session) {
//        User user = (User) session.getAttribute("user");
//        if (user != null) {
//            System.out.println("test4");
//            return "redirect:/mainpage";
//        } else {
//            return "redirect:/login";
//        }
//    }
}
