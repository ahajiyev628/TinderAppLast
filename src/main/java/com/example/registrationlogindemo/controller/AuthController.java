package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.service.UserService;
import com.example.registrationlogindemo.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
public class AuthController {
    private UserService userService;
    private UserServiceImpl userServiceImpl;

//    @GetMapping("index")
//    public String home(){
//        return "login";
//    }

//    @GetMapping("login")
//    public String loginForm() {
//        // System.out.println(userService.findByEmail("allahverdihajiyev@gmail.com").isPresent());
//        return "login";
//    }
    @RequestMapping("mainpage")
    public String mainpage() {
        return "mainpage";
    }
//    @PostMapping("/user")
//    public String postUser(Model model) {
//        UserDto user = new UserDto();
//        model.addAttribute("user", user);
//        return "user";
//    }



//    @RequestMapping("/user")
//    public ModelAndView getUser() {
//        User user = new User(7L,"Jhon", "jhon84@gmail.com","123",
//                (List<Role>) new ArrayList<Role>());
//        ModelAndView modelAndView = new ModelAndView("user");
//        modelAndView.addObject("user", user);
//        return modelAndView;
//    }



//    @GetMapping("/users")
//    public String listRegisteredUsers(Model model){
//        List<UserDto> users = userService.findAllUsers();
//        model.addAttribute("users", users);
//        return "users";
    }

//    @GetMapping("/users")
//    public String listRegisteredUsers(Model model){
//        List<UserDto> users = userServiceImpl.findAllUsers();
//        model.addAttribute("users", users);
//        return "users";
//    }


