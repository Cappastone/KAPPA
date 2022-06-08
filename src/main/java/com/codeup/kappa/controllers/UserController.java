package com.codeup.kappa.controllers;

import com.codeup.kappa.models.User;
import com.codeup.kappa.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.List;


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

    @GetMapping("/{user_id}")
    public String viewUserProfile(
            @PathVariable long user_id, Model model){

//        System.out.println("Hellooooooooooooo! " + userDao.followingList(user_id));
        List<Long> followingIds = userDao.followingList(user_id);

        model.addAttribute("following", userDao.findAllById(followingIds));
        model.addAttribute("user", userDao.getById(user_id));
        model.addAttribute("post", postDao.getPostByUserId(user_id));

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

        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user){

        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);

        userDao.save(user);

        return "redirect:/login";
    }

    @GetMapping("/user/{id}")
    public String editUser(@PathVariable long id, Model model) {

        model.addAttribute("user", userDao.getById(id));

        return "users/profile";
    }

    @GetMapping("/account")
    public String editAccount(Model model){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        model.addAttribute("user", userDao.getById(id));

        return "/users/account";
    }

    @PostMapping("/edit-user")
    public String editUser(@RequestParam(name="username")String username, @RequestParam(name="email")String email, @RequestParam(name="id")long id) {
        User user = userDao.getById(id);
        user.setUsername(username);
        user.setEmail(email);

        userDao.save(user);
//        User existingUser = userDao.getById(user.getId());
//        existingUser.setUsername(user.getUsername());
//        existingUser.setEmail(user.getEmail());
//        userDao.save(user);

        return "redirect:/user/" + user.getId();
    }

//    Change Password //
    @PostMapping("edit-password")
    public String editPassword(@RequestParam(name="oldPassword")String oldPassword, @RequestParam(name="newPassword")String newPassword, @RequestParam(name="id")long id, HttpSession session) {
        String message;
        message = "Please enter correct current password";
        User user = userDao.getById(id);

//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);

        if(this.passwordEncoder.matches(oldPassword, user.getPassword()))
        {
            user.setPassword(passwordEncoder.encode(newPassword));
            userDao.save(user);
        } else {
            session.setAttribute("message", message);
        }

        return "redirect:/user/" + user.getId();
    }


}
