package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.Favorites;
import com.example.registrationlogindemo.entity.Message;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.FavoritesRepository;
import com.example.registrationlogindemo.repository.MessageRepository;
import com.example.registrationlogindemo.repository.UserRepository;
import com.example.registrationlogindemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;
@Controller
@AllArgsConstructor
public class MessageController {

    private UserRepository userRepository;
    private MessageRepository messageRepository;
    private UserService userService;
    private FavoritesRepository favoritesRepository;

    @GetMapping("/chat")
    public String showMessageForm(@RequestParam("receiverId") Long receiverId, Model model, HttpSession session) {
        System.out.println(receiverId);
        Optional<Favorites> receiver = favoritesRepository.findById(receiverId);
        Long currentUserId = (Long) session.getAttribute("userId");
        User sender = userService.findById(currentUserId).get();

        System.out.println(currentUserId);

        System.out.println(receiver);
        System.out.println(sender);

        if (sender != null && receiver.isPresent()) {
            model.addAttribute("sender", sender);
            model.addAttribute("receiver", receiver.get());
            model.addAttribute("receiverId", receiver.get().getId());
            String username = (String) session.getAttribute("username"); // retrieve the username from the session
            model.addAttribute("username", username);
            return "chat";
        }
        return "redirect:/favorites";
    }

    @PostMapping("/chat")
    public String sendMessage(@RequestParam("receiverId") Long receiverId,
                              @RequestParam("msg_text") String msgText,
                              Principal principal) {

        User sender = userRepository.findByEmail(principal.getName());
        Favorites receiver = favoritesRepository.findById(receiverId).orElseThrow();

        System.out.println(sender);
        System.out.println(receiver);

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setMsg_text(msgText);
        messageRepository.save(message);

        return "redirect:/chat";
    }
}