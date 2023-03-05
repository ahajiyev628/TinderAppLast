package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.Favorites;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.service.FavoritesService;
import com.example.registrationlogindemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@AllArgsConstructor
public class FavoritesController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final FavoritesService favoritesService;

    @GetMapping("/favorites")
    public String getFavorites(Model model) {
        List<Optional<User>> likedUsers = new ArrayList<>();
        List<Optional<User>> dislikedUsers = new ArrayList<>();

        List<Favorites> likes = favoritesService.findByStatus("like");
        List<Favorites> dislikes = favoritesService.findByStatus("dislike");

        for (Favorites f : likes) {
            Optional<User> likedUser = userService.findById(f.getLikedById());
            if (likedUser.isPresent()) {
                likedUsers.add(likedUser);
            }
        }

        for (Favorites f : dislikes) {
            Optional<User> dislikedUser = userService.findById(f.getLikedById());
            if (dislikedUser.isPresent()) {
                dislikedUsers.add(dislikedUser);
            }
        }

        model.addAttribute("likedUsers", likedUsers);
        System.out.println(likedUsers);
        model.addAttribute("dislikedUsers", dislikedUsers);
        System.out.println(dislikedUsers);
        return "favorites";
    }

    @PostMapping("/favorites/{status}")
    public String updateFavorites(@RequestParam("userIds[]") Long[] userIds, @PathVariable String status, Model model) {
        Set<User> users = new HashSet<>();
        for (Long userId : userIds) {
            Optional<User> user = userService.findById(userId);
            if (user.isPresent()) {
                users.add(user.get());
            }
        }
        if (users.size() < 2) {
            model.addAttribute("error", "Not enough users selected.");
            List<User> updatedUsers = userService.findAll(); // fetch updated list of users
            model.addAttribute("users", updatedUsers); // add updated list to the model
            return "favorites";
        }
        Iterator<User> it = users.iterator();
        User user1 = it.next();
        User user2 = it.next();
        Favorites favorites = new Favorites(user1.getId(), user2.getId(), status);
//        favorites.setLikedById(user1.getId());
//        favorites.setLikedUserId(user2.getId());
//        System.out.println("Before saving favorites: " + favorites);
//        favoritesService.saveFavorites(favorites);
        favoritesService.saveFavorites(user1.getId(), user2.getId(), status);
        System.out.println("After saving favorites: " + favorites);
        for (User user : users) {
            user.getLikedUsers().add(favorites);
            userService.saveUser(user);
        }
        List<User> updatedUsers = userService.findAll(); // fetch updated list of users
        model.addAttribute("users", updatedUsers); // add updated list to the model
        return "redirect:/favorites";
    }



}
