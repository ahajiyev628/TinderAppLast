package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.SignInRequest;
import com.example.registrationlogindemo.repository.UserRepository;
import com.example.registrationlogindemo.service.UserService;
import com.example.registrationlogindemo.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/mainpage")
    public String showMainPage() {
        return "mainpage";
    }


    //    @PostMapping("login")
//    public void handle_login(@RequestBody LoginRequest request) {
//        System.out.println(request);
//        authService.login(request.getUsername(), request.getPassword(), request.getRememberMe())
//                .map(LoginResponse::Ok)
//                .orElse(LoginResponse.Error("something went wrong"));
//    }
//
//    @PostMapping("/login")
//    public RedirectView submitLoginForm(HttpServletRequest request) {
//        System.out.println("girildi posta");
//        String email = request.getParameter("email");
//        System.out.println(request.getParameter("email"));
//        String password = request.getParameter("password");
//
//        // email ve password değerlerini kontrol etmek için if koşullarını kullanabilirsiniz
//        if (email.equals("jamilagarayeva937@gmail.com")
//                && password.equals("123")
//        ) {
//            System.out.println("giriş doğruysa, kullanıcıyı bir sonraki sayfaya yönlendirin");
//            return new RedirectView("/mainpage");
//        } else {
//            System.out.println("yanlış giriş yapıldığını bildiren bir hata mesajı gösterin");
//            return new RedirectView("/login?error=true");
//        }
//    }

    @PostMapping("/login")
    public RedirectView submitLoginForm(SignInRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        log.info("girildi posta");
        boolean isAuthenticated = userServiceImpl.authenticate(email, password);

        System.out.println( userService.findByEmail(email).getEmail());
        System.out.println(password);

        System.out.println(userService.findByEmail(email).getPassword());
//        System.out.println(userServiceImpl.matches(email, password));
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        System.out.println(passwordEncoder.encode(password));
        //"$2a$10$1RhHeYUBT4fd.PlcZVBcCOFNLIpKO89mLunpYovkQ5QkOI8EJj91C"
//        $2a$10$v4RZeelYISPhH4HsZM3PGeH6piMH4CVwGcGuppdn9VaH/ROUyqXrG
        if (isAuthenticated) {
            System.out.println("giriş doğruysa, kullanıcıyı bir sonraki sayfaya yönlendirin");
            // doğruysa, kullanıcıyı bir sonraki sayfaya yönlendirin
            return new RedirectView("/mainpage");
        } else {
            // yanlış giriş yapıldığını bildiren bir hata mesajı gösterin
            System.out.println("yanlış giriş yapıldığını bildiren bir hata mesajı gösterin");
            return new RedirectView("/login?error=true");
        }
    }
//
//
//    @RequestMapping("/login")
//    public String loginForm(@RequestParam String email, @RequestParam String password, HttpSession session) {
//        if (userService.authenticate(email, password)) {
//            System.out.println("111");
//            session.setAttribute("loggedInUser", email);
//            return "redirect:/main-page";
//        } else {
//            System.out.println("112");
//            return "redirect:/login2?error";
//        }
//    }

}
