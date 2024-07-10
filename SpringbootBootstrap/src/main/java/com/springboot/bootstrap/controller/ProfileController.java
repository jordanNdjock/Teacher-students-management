package com.springboot.bootstrap.controller;


import com.springboot.bootstrap.model.Users;
import com.springboot.bootstrap.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profil")
    public String getProfile(Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("user");

        model.addAttribute("user", user);
        return "pages/profil/profil_page";
    }
}
