package com.codeup.kappa.controllers;

import com.codeup.kappa.models.User;
import com.codeup.kappa.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/{id}")
    public String viewUserProfile(
            @PathVariable long id, Model model){

        model.addAttribute("user", userDao.getById(id));
        return "/users/profile";
    }

    @GetMapping("/profile")
    public String viewProfile(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        return "redirect:/user/" + id;
    }

    @GetMapping("/register")
    public String createUser(Model model){

        model.addAttribute("user", new User());

        return "/users/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user){

        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);

        System.out.println(user);
        userDao.save(user);


        return "redirect:/login";
    }


}
