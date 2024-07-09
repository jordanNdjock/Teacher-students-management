package com.springboot.bootstrap.controller;


import com.springboot.bootstrap.model.Role;
import com.springboot.bootstrap.model.Ue;
import com.springboot.bootstrap.model.Users;
import com.springboot.bootstrap.repositories.UeRepository;
import com.springboot.bootstrap.service.UeService;
import com.springboot.bootstrap.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UeService ueService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/login")
    public String loginPage(){
        return "auth-login";
    }

    @GetMapping("/uepage")
    public String uepage(){
        return "/pages/ue_page";
    }
    @GetMapping("/ues")
    public String viewUePage(Model model) {
        List<Ue> ues = ueService.getAllUes();
        model.addAttribute("ues", ues);
        return "pages/ue_page"; // Le nom de votre template Thymeleaf
    }

    @PostMapping("/addUe")
    public  String addU(@ModelAttribute("ue") Ue ue, Model model){
        System.out.println("hola " + ue.getNiv() + ue.getUser());
        ueService.saveUe(ue);
        model.addAttribute("message", "Registered Successfuly!");
        return "/pages/ue_page";
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
        Users users = userService.validateUser(user);
        if (users != null) {
            session.setAttribute("user", users);
            session.setAttribute("email", users.getEmail());
            session.setAttribute("role", users.getRole().getName());

            if ("Enseignant".equals(users.getRole().getName())) {
                return "/pages/landing_page";
            } else if ("Etudiant".equals(users.getRole().getName())) {
                return "/pages/student_page";
            }
        }

        model.addAttribute("error", "Invalid email or password");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalider la session
        return "login"; // Rediriger vers la page de login
    }

}

