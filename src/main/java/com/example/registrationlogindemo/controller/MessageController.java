package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.Favorites;
import com.example.registrationlogindemo.entity.Message;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.MessageRepository;
import com.example.registrationlogindemo.repository.UserRepository;
import com.example.registrationlogindemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;
@Controller
public class MessageController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/chat")
    public String showMessageForm(@RequestParam("receiverId") Long receiverId, Model model, HttpSession session) {
        Optional<User> receiver = userRepository.findById(receiverId);
        if (receiver.isPresent()) {
            Long currentUserId = (Long) session.getAttribute("userId");
            User sender = userService.findById(currentUserId).get();
            System.out.println("RECEIVER : " + receiver.get());
            System.out.println("SENDER : " + sender);
            if (sender != null) {
                model.addAttribute("sender", sender);
                model.addAttribute("receiver", receiver.get());
                return "chat";
            }
        }
        return "redirect:/favorites";
    }

    @PostMapping("/chat")
    public String sendMessage(@RequestParam Long receiverId, @RequestParam String msg_text, HttpSession session) {

        // create a new Message object
        Message message = new Message();

        // set the sender and receiver of the message
        Long currentUserId = (Long) session.getAttribute("userId");
        User sender = userService.findById(currentUserId).orElseThrow();
        User receiver = userService.findById(receiverId).orElseThrow();

        message.setSender(sender);
        message.setReceiver(receiver);

        // set the message text
        message.setMsg_text(msg_text);

        // save the message to the database
        messageRepository.save(message);

        // redirect back to the chat page
        return "redirect:/chat?receiverId=" + receiverId;
    }
}
