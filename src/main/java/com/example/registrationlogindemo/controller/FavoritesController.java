package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.Favorites;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.FavoritesRepository;
import com.example.registrationlogindemo.repository.UserRepository;
import com.example.registrationlogindemo.service.FavoritesService;
import com.example.registrationlogindemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@AllArgsConstructor
public class FavoritesController {

    private final UserService userService;

    private final FavoritesService favoritesService;

    @GetMapping("/favorites")
    public String getFavorites(Model model) {
        List<User> likedUsers = new ArrayList<>();
        List<User> dislikedUsers = new ArrayList<>();

        List<Favorites> likes = favoritesService.findByStatus("like");
        List<Favorites> dislikes = favoritesService.findByStatus("dislike");

        for (Favorites f : likes) {
            likedUsers.addAll(f.getUsers());
        }

        for (Favorites f : dislikes) {
            dislikedUsers.addAll(f.getUsers());
        }

        model.addAttribute("likedUsers", likedUsers);
        model.addAttribute("dislikedUsers", dislikedUsers);
        return "favorites";
    }
    @PostMapping("/favorites/{status}")
    public String updateFavorites(@RequestParam("userIds[]") Long[] userIds, @PathVariable String status) {
        Set<User> users = new HashSet<>();
        for (Long userId : userIds) {
            Optional<User> user = userService.findById(userId);
            if (user.isPresent()) {
                users.add(user.get());
            }
        }
        Favorites favorites = new Favorites(users, status);
        favoritesService.saveFavorites(favorites);
        for (User user : users) {
            user.getFavorites().add(favorites);
            userService.saveUser(user);
        }
        return "redirect:/favorites";
    }

}


