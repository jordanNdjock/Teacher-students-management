package com.springboot.bootstrap.controller;


import com.springboot.bootstrap.model.Role;
import com.springboot.bootstrap.model.Users;
import com.springboot.bootstrap.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/login")
    public String loginPage(){
        return "auth-login";
    }


    @RequestMapping("/register")
    public String registerPage(){
        return "auth-register";
    }

    @RequestMapping("/home")
    public String loginSubmit(){
        return "/pages/landing_page";
    }

    @PostMapping("/saveUser")
    public String registerUser(@ModelAttribute("users") Users user, Model model) {
        userService.saveUser(user);
        model.addAttribute("message", "Registered Successfuly!");
        System.out.println("Welcome toi" + user);
        return "auth-login";
    }

    @PostMapping("/loginUser")
    public String loginUser(@ModelAttribute("users") Users user,
                            HttpSession session,
                            Model model) {
        System.out.println("hey" + user.getPassword() + user.getEmail());
        Users users = userService.validateUser(user);
        if (users != null) {
            System.out.println("Hello " + users);
            session.setAttribute("user", users);
            session.setAttribute("email", users.getEmail());
            session.setAttribute("role", users.getRole().getName());

            if ("Enseignant".equals(users.getRole().getName())) {
                return "/pages/landing_page";
            } else if ("Etudiant".equals(users.getRole().getName())) {
                return "/pages/landing_page";
            }
        }

        model.addAttribute("error", "Invalid email or password");
        return "login";
    }
}

