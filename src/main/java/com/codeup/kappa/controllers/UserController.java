package com.codeup.kappa.controllers;

import com.codeup.kappa.models.Game;
import com.codeup.kappa.models.User;
import com.codeup.kappa.repositories.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.naming.Binding;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userDao;
    private final PostRepository postDao;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PostRepository postDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/{id}")
    public String viewUserProfile(
            @PathVariable long id, Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        long user_id = user.getId();

//        System.out.println(userDao.getById(4L).getFollowers().get(0).getUsername() + "testing!!!!!!!!!!!");


        if (id == 0) {
            return "redirect:/login";
        }

        List<Long> followingIds = userDao.followingList(id);

//        List<Long> postIdLikedByUserId = userDao.findPostIdLikedByUserId(user_id);


        model.addAttribute("following", userDao.findAllById(followingIds));
        model.addAttribute("user", userDao.getById(id));
        model.addAttribute("posts", postDao.findPostsByUserId(id));


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            long user_id = user.getId();
            model.addAttribute("sessionUserId", user_id);
            model.addAttribute("ListPostIdLikedByUserId", userDao.findPostIdLikedByUserId(user_id));
            model.addAttribute("ListUserIdsByFollowerId", userDao.findUserIdsByFollowerId(user_id));
        }


        return "users/profile";
    }

    @GetMapping("/profile")
    public String viewProfile() {


        if ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null) {
            return "redirect:/login";
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();

        return "redirect:/user/" + id;
    }

    @GetMapping("/register")
    public String createUser(Model model
//            , @RequestParam("error")String error
    ) {

        model.addAttribute("usernameError", "That username is already taken!");
        model.addAttribute("emailError", "That email is already taken!");

        model.addAttribute("user", new User());

        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user, BindingResult result) {

        String username = user.getUsername();
        String email = user.getEmail();

        if (userDao.existsByEmail(email)) {
            result.rejectValue("email", "user.email", "This email already exists");
        }

        if (userDao.existsByUsername(username)) {
            result.rejectValue("username", "user.username", "This username already exists");
        }

        if (result.hasErrors()) {
            return "users/register";
        }

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
    public String editAccount(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        model.addAttribute("user", userDao.getById(id));

        return "users/account";
    }

    @PostMapping("/edit-user")
    public String editUser(@RequestParam(name = "username") String username, @RequestParam(name = "email") String email, @RequestParam(name = "id") long id) {
        User user = userDao.getById(id);
        user.setUsername(username);
        user.setEmail(email);

        userDao.save(user);

        return "redirect:/user/" + user.getId();
    }

    //    Change Password //
    @PostMapping("edit-password")
    public String editPassword(@RequestParam(name = "oldPassword") String oldPassword, @RequestParam(name = "newPassword") String newPassword, @RequestParam(name = "id") long id, HttpSession session) {
        String message;
        message = "Please enter correct current password";
        User user = userDao.getById(id);

        if (this.passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userDao.save(user);
        } else {
            session.setAttribute("message", message);
        }

        return "redirect:/user/" + user.getId();
    }

    @PostMapping("/edit-bio")
    public String editBio(@RequestParam(name = "bio") String bio, @RequestParam(name = "id") long id) {
        User user = userDao.getById(id);
        user.setBio(bio);

        userDao.save(user);

        return "redirect:/user/" + user.getId();
    }

    @PostMapping("/edit-profile-pic")
    public String editProfilePic(@RequestParam(name = "profile-picture-url") String profilePictureUrl, @RequestParam(name = "id") long id) {
        User user = userDao.getById(id);
        user.setProfilePictureUrl(profilePictureUrl);

        userDao.save(user);
        return "redirect:/user/" + user.getId();
    }

}
