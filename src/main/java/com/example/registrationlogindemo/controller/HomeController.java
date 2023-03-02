package com.example.registrationlogindemo.controller;
import com.example.registrationlogindemo.entity.User;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String showHomePage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            System.out.println("test4");
            return "redirect:/mainpage";
        } else {
            return "redirect:/login";
        }
    }
}
