package com.example.registrationlogindemo.controller;
import org.springframework.http.ResponseEntity;
import com.example.registrationlogindemo.entity.Favorites;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.FavoritesRepository;
import com.example.registrationlogindemo.repository.UserRepository;
import com.example.registrationlogindemo.service.FavoritesService;
import com.example.registrationlogindemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public String getFavorites(Model model, HttpSession session) {
        // Get the ID of the current user from the session
        Long currentUserId = userRepository.findByUsername("Nizami").getId();

        //Long currentUserId = (Long) session.getAttribute("userId");
        System.out.println(currentUserId);

        Set<User> users = userService.findAll();
        Set<Long> likedUserIds = favoritesService.findByStatusAndLikedBy("like", currentUserId).stream()
                .map(f -> f.getLikedUser().getId())
                .collect(Collectors.toSet());
        Set<Long> dislikedUserIds = favoritesService.findByStatusAndLikedBy("dislike", currentUserId).stream()
                .map(f -> f.getLikedUser().getId())
                .collect(Collectors.toSet());
        Set<User> likedUsers = favoritesService.findByStatusAndLikedBy("like", currentUserId).stream()
                .map(f -> f.getLikedUser())
                .collect(Collectors.toSet());
        Set<User> dislikedUsers = favoritesService.findByStatusAndLikedBy("dislike", currentUserId).stream()
                .map(f -> f.getLikedUser())
                .collect(Collectors.toSet());

        model.addAttribute("users", users);
        model.addAttribute("likedUserIds", likedUserIds);
        model.addAttribute("dislikedUserIds", dislikedUserIds);
        model.addAttribute("likedUsers", likedUsers);
        model.addAttribute("dislikedUsers", dislikedUsers);

        return "favorites";
    }


    @PostMapping("/favorites/{status}")
    public String updateFavorites(@RequestParam("userIds[]") List<Long> userIds, @PathVariable String status, Model model, HttpSession session) {
        User likedBy = userRepository.findByUsername("Nizami"); //userService.getCurrentUser(); //Optional.ofNullable(userService.getCurrentUser());
//        System.out.println(userRepository.findByUsername("Nizami"));
//        System.out.println(userService.getCurrentUser());   // return null

//        Long currentUserId = (Long) session.getAttribute("userId");
//        Optional<User> likedBy = userService.findById(currentUserId);

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

//    @PostMapping("/favorites/remove")
//    @ResponseBody
//    public ResponseEntity<String> removeFavorite(@RequestParam("userId") Long userId,
//                                                 @RequestParam("status") String status,
//                                                 HttpSession session) {
//        Long currentUserId = userRepository.findByUsername("Nizami").getId(); //(Long) session.getAttribute("userId");
//
//        if (currentUserId == null) {
//            return ResponseEntity.badRequest().body("User not logged in");
//        }
//
//        // Check if the current user has already liked or disliked the user with the given ID
//        Favorites favorite = favoritesService.findByLikedUserAndLikedBy(userId, currentUserId);
//        if (favorite == null || !favorite.getStatus().equals(status)) {
//            return ResponseEntity.badRequest().body("User not found in favorites");
//        }
//
//        // Remove the favorite and return a success response
//        favoritesService.delete(favorite);
//        return ResponseEntity.ok("Favorite removed");
//    }


    @PostMapping("/favorites/remove")
    public ResponseEntity<String> removeFavorite(@RequestParam("userId") Long userId,
                                                 @RequestParam("status") String status,  HttpSession session) {


        // Check if the user is logged in
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User currentUser = (User) authentication.getPrincipal();
            session.setAttribute("userId", currentUser.getId());
            Long currentUserId = (Long) session.getAttribute("userId");
            // Long currentUserId = userRepository.findByUsername("Nizami").getId();

            // Check if the current user has already liked or disliked the user with the given ID
            Favorites favorite = favoritesService.findByLikedUserAndLikedBy(userId, currentUserId);
            if (favorite == null || !favorite.getStatus().equals(status)) {
                return ResponseEntity.badRequest().body("User not found in favorites");
            }

            // Remove the favorite and return a success response
            favoritesService.delete(favorite);
            return ResponseEntity.ok("Favorite removed");
        } else {
            return ResponseEntity.badRequest().body("User not logged in");
        }
    }



}
