package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.Favorites;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.FavoritesRepository;
import com.example.registrationlogindemo.repository.UserRepository;
import com.example.registrationlogindemo.service.FavoritesService;
import com.example.registrationlogindemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class FavoritesController {
    @Autowired
    private final FavoritesService favoritesService;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final FavoritesRepository favoritesRepository;


    @Autowired
    private final UserService userService;

    @GetMapping("/favorites")
    public String getFavorites(Model model) {
        Set<User> users = userService.findAll();
        Set<Long> likedUserIds = favoritesService.findByStatus("like").stream()
                .map(f -> f.getLikedUser().getId())
                .collect(Collectors.toSet());
        Set<Long> dislikedUserIds = favoritesService.findByStatus("dislike").stream()
                .map(f -> f.getLikedUser().getId())
                .collect(Collectors.toSet());
        Set<User> likedUsers = favoritesService.findByStatus("like").stream()
                .map(f -> f.getLikedUser())
                .collect(Collectors.toSet());
        Set<User> dislikedUsers = favoritesService.findByStatus("dislike").stream()
                .map(f -> f.getLikedUser())
                .collect(Collectors.toSet());
        System.out.println(likedUserIds);
        System.out.println(dislikedUserIds);
        model.addAttribute("users", users);
        model.addAttribute("likedUserIds", likedUserIds);
        model.addAttribute("dislikedUserIds", dislikedUserIds);
       // System.out.println(favoritesRepository.findByLikedByAndLikedUser(1L,2L)); // works
        return "favorites";
    }

    @PostMapping("/favorites/{status}")
    public String updateFavorites(@RequestParam("userIds[]") List<Long> userIds, @PathVariable String status, Model model) {
        User likedBy = userRepository.findByUsername("Nizami"); //userService.getCurrentUser(); //Optional.ofNullable(userService.getCurrentUser());
        System.out.println(userRepository.findByUsername("Nizami"));
        System.out.println(userService.getCurrentUser());   // return null
//        if (likedBy==null) {
//            model.addAttribute("error", "You must be logged in to update your favorites.");
//            return "favorites";
//        }
        for (Long userId : userIds) {
            System.out.println(userId);
            Optional<User> likedUser = userService.findById(userId);
            System.out.println(userService.findById(userId));
//            if (!likedUser.isPresent()) {
//                model.addAttribute("error", "User not found.");
//                return "favorites";
//            }
            System.out.println(new Favorites(status,likedBy, likedUser.get()));
            favoritesRepository.save(new Favorites(status, likedBy, likedUser.get()));
            //favoritesService.saveFavorites(likedBy.getId(), likedUser.get().getId(), status);
        }
        model.addAttribute("success", "Favorites updated successfully.");
        return "redirect:/favorites";
    }
}
