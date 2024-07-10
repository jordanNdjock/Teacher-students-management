package com.springboot.bootstrap.controller;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.springboot.bootstrap.model.*;
import com.springboot.bootstrap.repositories.UeRepository;
import com.springboot.bootstrap.service.CategoryService;
import com.springboot.bootstrap.service.CoursService;
import com.springboot.bootstrap.service.UeService;
import com.springboot.bootstrap.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UeService ueService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CoursService coursService;

    private UeRepository ueRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    /* première page */
    @RequestMapping("/login")
    public String loginPage(){
        return "auth-login";
    }

    /*  Page d'accueil affichant l'ensemble des Ue  */
    @GetMapping("/uepage")
    public String viewUePage(Model model) {
        List<Ue> ues = ueService.getAllUes();
        System.out.println("Liste des Ue " + ues);
        model.addAttribute("ues", ues);
        return "pages/Ue/ue_page"; // Le nom de votre template Thymeleaf
    }

    /* Récupération de l'ensemble des Ue*/

    /* Ajout d'une Ue*/
    @PostMapping("/addUe")
    public  String addU(@ModelAttribute("ue") Ue ue,HttpSession session, Model model){
        Users currentUser = (Users) session.getAttribute("user");

        if (currentUser == null || !"Enseignant".equals(session.getAttribute("role"))) {
            model.addAttribute("error", "Vous n'avez pas les permissions nécessaires pour ajouter une UE.");
            return "addUe";
        }

        // Vérifier si l'enseignant enseigne déjà une UE à ce niveau
        if (ueService.existsByUserAndNiv(currentUser, ue.getNiv())) {
            model.addAttribute("error", "Vous ne pouvez enseigner qu'une seule UE par niveau.");
            return "addUe";
        }

        // Créer et sauvegarder la nouvelle UE
        Ue ues = new Ue(ue.getCode(), ue.getIntitul(), ue.getNiv(), currentUser);
        ueService.saveUe(ues);
        model.addAttribute("ues", ueService.getAllUes());
        return "/pages/Ue/ue_page";
    }

    /*  Delete Ue*/
    @GetMapping("/delete/{id}")
    public String deleteUe(@PathVariable() long id,Model model) {
        ueService.deleteById(id);
        List<Ue> ues = ueService.getAllUes();
        model.addAttribute("ues", ues);
        return "pages/Ue/ue_page"; // Le nom de votre template Thymeleaf
    }

    @RequestMapping("/register")
    public String registerPage(){
        return "auth-register";
    }

    /* Dash Enseignant */
    @RequestMapping("/home")
    public String loginSubmit(){
        return "/pages/enseignant/landing_page";
    }

    /* Registre */
    @PostMapping("/saveUser")
    public String registerUser(@ModelAttribute("users") Users user, Model model) {
        userService.saveUser(user);
        model.addAttribute("message", "Registered Successfuly!");
        System.out.println("Welcome toi" + user);
        return "auth-login";
    }

    /* Login */
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
                return "/pages/enseignant/landing_page";
            } else if ("Etudiant".equals(users.getRole().getName())) {
                return "/pages/student/student_page";
            }
        }

        model.addAttribute("error", "Invalid email or password");
        return "auth-login";
    }

    /* Deconnexion */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalider la session
        return "auh-login"; // Rediriger vers la page de login
    }

    /* Gestion des cours et de leur catégories */

    /* première page */
    @RequestMapping("/categorieHome")
    public String showAddUeForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        List<Ue> ues = ueService.getAllUes();
        model.addAttribute("categories", categories);
        model.addAttribute("ues", ues);
        return "/pages/cours/cour";
    }
    
    @PostMapping("/cours/add")

    public String registerUser(@ModelAttribute("cours") Cours cour, Model model, @RequestParam("file") MultipartFile file) {

        coursService.saveCours(cour);
        model.addAttribute("message", "Registered Successfuly!");
        return "/pages/cours/cour";
    }
}

