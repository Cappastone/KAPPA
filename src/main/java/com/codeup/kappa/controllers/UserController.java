package com.codeup.kappa.controllers;

import com.codeup.kappa.models.User;
import com.codeup.kappa.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userDao;
    private final PostRepository postDao;
    private final PostImageRepository postImageDao;
    private final GameRepository gameDao;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PostRepository postDao, PostImageRepository postImageDao, GameRepository gameDao, PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.postDao = postDao;
        this.postImageDao = postImageDao;
        this.gameDao = gameDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/{id}")
    public String viewUserProfile(
            @PathVariable long id, Model model){

//        HELP

//        System.out.println("Hi Justin :]" + userDao.followingList(id));
//        model.addAttribute("following", userDao.followingList(id));

//        HELP

        model.addAttribute("user", userDao.getById(id));
        model.addAttribute("post", postDao.getPostByUserId(id));

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

        userDao.save(user);

        return "redirect:/login";
    }




}
