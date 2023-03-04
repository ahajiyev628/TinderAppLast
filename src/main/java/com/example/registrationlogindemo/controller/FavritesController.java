//package com.example.registrationlogindemo.controller;
//
//import com.example.registrationlogindemo.entity.Profile;
//import com.example.registrationlogindemo.service.UserService;
//import com.example.registrationlogindemo.service.impl.UserServiceImpl;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@AllArgsConstructor
//@Controller
//public class FavritesController {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private UserServiceImpl userServiceImpl;
//
//    @PostMapping("/like")
//    public void likeUser(@RequestParam("name") String name) {
//        // Find the user with the given name and mark them as liked
//        Profile user = userServiceImpl.stream()
//                .filter(u -> u.getName().equals(name))
//                .findFirst().orElse(null);
//        if (user != null) {
//            user.setLiked(true);
//
//            TODO: Save the user's like to the profiles table in the database
//        }
//    }
//
//    @PostMapping("/dislike")
//    public void dislikeUser(@RequestParam("name") String name) {
//        // Find the user with the given name and mark them as disliked
//        Profile user = userList.stream()
//                .filter(u -> u.getName().equals(name))
//                .findFirst().orElse(null);
//        if (user != null) {
//            user.setDisliked(true);
//        }
//    }
//}
