package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.service.UserService;
import com.example.registrationlogindemo.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class MyProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/my-profile")
    public String showProfileForm(HttpSession session, Model model) {
        log.info("get myprofile");
        String username = (String) session.getAttribute("username"); // retrieve the username from the session
        model.addAttribute("username", username);
        String firstname = (String) session.getAttribute("firstname"); // retrieve the username from the session
        model.addAttribute("firstname", firstname);
        String surname = (String) session.getAttribute("surname"); // retrieve the username from the session
        model.addAttribute("surname", surname);
        String nickname = (String) session.getAttribute("nickname"); // retrieve the username from the session
        model.addAttribute("nickname", nickname);
        String location = (String) session.getAttribute("location"); // retrieve the username from the session
        model.addAttribute("location", location);
        return "my-profile";
    }

    @PostMapping("/my-profile/save")
    public RedirectView submitMyProfile(HttpSession session,
                                        @ModelAttribute("user") UserDto user) {
        log.info("posta girdi");
        String email = (String) session.getAttribute("sesEmail");
        userServiceImpl.saveUserDetails(user,email);
        return new RedirectView("/my-profile");
    }
}
