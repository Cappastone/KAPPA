package com.codeup.kappa.controllers;

import com.codeup.kappa.models.Game;
import com.codeup.kappa.models.PlatformLink;
import com.codeup.kappa.models.Post;
import com.codeup.kappa.models.User;
import com.codeup.kappa.repositories.*;
import com.codeup.kappa.services.DateFormatter;

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
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userDao;
    private final PostRepository postDao;
    private final PasswordEncoder passwordEncoder;
    private final PlatformLinkRepository platformLinkDao;
    private final CommentRepository commentDao;

    public UserController(UserRepository userDao, PostRepository postDao, PasswordEncoder passwordEncoder, PlatformLinkRepository platformLinkDao, CommentRepository commentDao) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.passwordEncoder = passwordEncoder;
        this.platformLinkDao = platformLinkDao;
        this.commentDao = commentDao;
    }

    @GetMapping("/{profile_id}")
    public String viewUserProfile(
            @PathVariable long profile_id, Model model) {

        if (profile_id == 0) {
            return "redirect:/login";
        }

        List<Long> followingIds = userDao.followingList(profile_id);

//        List<Long> postIdLikedByUserId = userDao.findPostIdLikedByUserId(user_id);

        model.addAttribute("following", userDao.findAllById(followingIds));
        model.addAttribute("user", userDao.getById(profile_id));
        model.addAttribute("posts", postDao.findPostsByUserId(profile_id));

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            long user_id = user.getId();
            model.addAttribute("sessionUserId", user_id);
            model.addAttribute("sessionUsername", user.getUsername());
            model.addAttribute("ListPostIdLikedByUserId", userDao.findPostIdLikedByUserId(user_id));
            model.addAttribute("ListUserIdsByFollowerId", userDao.findUserIdsByFollowerId(user_id));
            model.addAttribute("findCommentIdsByUserId", commentDao.findCommentIdsByUserId(user_id));

        }

//        get array list of dates in desired string format =>
        DateFormatter dateFormatter = new DateFormatter();
        User user = userDao.getById(profile_id);
        Date userCreationDate = user.getCreationDate();
        String userDate = dateFormatter.getDate(userCreationDate);

        List<Post> posts = postDao.findPostsByUserId(profile_id);
        List<Date> postCreationDateObjs = dateFormatter.getPostDateObjs(posts);
        List<String> postDates = dateFormatter.getDates(postCreationDateObjs);


//        !!!!!!!!   NEEDS THE CHRIS BERRY SPECIAL  :) !!!!!!!
//        List<Date> commentCreationDateObjs = dateFormatter.getCommentDateObjs(posts);
//        List<String> commentDates = dateFormatter.getDates(commentCreationDateObjs);


        model.addAttribute("userCreationDate", userDate);
        model.addAttribute("postCreationDates", postDates);

        //        !!!!!!!!   NEEDS THE CHRIS BERRY SPECIAL  :) !!!!!!!
//        model.addAttribute("commentCreationDate", commentDates);


        return "users/profile2";
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
    public String createUser(Model model) {

        model.addAttribute("user", new User());

        return "users/register";
    }

//    REFERENCE
    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user, BindingResult result, @RequestParam(name = "confirm") String confirm) {




        String username = user.getUsername().replaceAll("\\s","");
        String email = user.getEmail().replaceAll("\\s","");


        if (userDao.existsByEmail(email)) {
            result.rejectValue("email", "user.email", "This email already exists");
        }

        if (userDao.existsByUsername(username)) {
            result.rejectValue("username", "user.username", "This username already exists");
        }

        if (user.getPassword().length() < 6) {
            result.rejectValue("password", "user.password", "Password must be at least 6 characters!");
        }

        if (username.length() < 3) {
            result.rejectValue("username", "user.username", "Username must be at least 3 characters!");
        }

        if (username.length() > 15) {
            result.rejectValue("username", "user.username", "Username must be less than 15 characters!");
        }

        if (username.contains(" ")) {
            result.rejectValue("username", "user.username", "Username cannot include white space!");
        }

        if (!user.getPassword().equals(confirm)) {
            result.rejectValue("password", "user.password", "Passwords do not match!");
        }



        if (result.hasErrors()) {
            return "users/register";
        }

        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setUsername(username);
        user.setEmail(email);

        userDao.save(user);

        return "redirect:/login";
    }

    @GetMapping("/account")
    public String editAccount(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        User user1 = userDao.getById(id);

        boolean linksExist = user1.getLinks() != null;

        model.addAttribute("user", user1);
        model.addAttribute("links", new PlatformLink());
        if (linksExist) {
            long linksId = userDao.findLinksIdByUserId(id);
            model.addAttribute("existingLinks", platformLinkDao.getById(linksId));

        }

        return "users/account";
    }









//    WORK IN PROGRESS
    @PostMapping("/edit-user")
    public String editUser(
//            @RequestParam(name = "username") String username, @RequestParam(name = "email") String email, @RequestParam(name = "id") long id,
            @ModelAttribute("user") User user) {

        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = sessionUser.getId();

        User user2 = userDao.getById(id);

        String username = user.getUsername().replaceAll("\\s","");




        if (username.length() < 3 || username.contains(" ")) {
            return "redirect:/user/account?user_invalid";
        } else {
            user2.setUsername(username);
        }

        if (username.length() > 15) {
            return "redirect:/user/account?user_invalid2";
        } else {
            user2.setUsername(username);
        }


        if (userDao.existsByUsername(username)) {
            return "redirect:/user/account?user_exists";
        } else {
            user2.setUsername(username);
        }


        userDao.save(user2);

        return "redirect:/user/account?user_success";
    }








    @PostMapping("/edit-email")
    public String editEmail(@ModelAttribute("user") User user) {

        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = sessionUser.getId();

        String email = user.getEmail().replaceAll("\\s","");

        User user2 = userDao.getById(id);


        if (userDao.existsByEmail(email)) {
            return "redirect:/user/account?email_exists";
        } else {
            user2.setEmail(email);
        }


        userDao.save(user2);

        return "redirect:/user/account?email_success";
    }








    @PostMapping("/edit-firstname")
    public String editFirstName(@ModelAttribute("user") User user) {

        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = sessionUser.getId();
        User user2 = userDao.getById(id);
        String firstName = user.getFirstName().replaceAll("\\s","");
        user2.setFirstName(firstName);
        userDao.save(user2);

        return "redirect:/user/account?fn_success";
    }

    @PostMapping("/edit-lastname")
    public String editLastName(@ModelAttribute("user") User user) {

        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = sessionUser.getId();
        User user2 = userDao.getById(id);
        String lastName = user.getLastName().replaceAll("\\s","");

        user2.setLastName(lastName);
        userDao.save(user2);

        return "redirect:/user/account?ln_success";
    }





    @PostMapping("edit-password")
    public String editPassword(@RequestParam(name = "password") String oldPassword, @RequestParam(name = "new-password") String newPassword, @RequestParam(name = "confirm-password") String confirmPassword) {

        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = sessionUser.getId();
        User user2 = userDao.getById(id);

        if (!passwordEncoder.matches(oldPassword, user2.getPassword())){
            return "redirect:/user/account?ps_invalid";
        } else {
            user2.setPassword(passwordEncoder.encode(newPassword));
        }

        if (newPassword.length() < 6 || newPassword.contains(" ")){
            return "redirect:/user/account?ps_error";
        } else {
            user2.setPassword(passwordEncoder.encode(newPassword));
        }

        if (!newPassword.equals(confirmPassword)){
            return "redirect:/user/account?ps_match_error";
        } else {
            user2.setPassword(passwordEncoder.encode(newPassword));
        }

        userDao.save(user2);

        return "redirect:/user/account?ps_success" ;
    }










//    //    Change Password //
//    @PostMapping("edit-password")
//    public String editPassword(@RequestParam(name = "password") String oldPassword, @RequestParam(name = "confirm") String newPassword, @RequestParam(name = "id") long id, HttpSession session) {
//
//        String message;
//        message = "Please enter correct current password";
//        User user = userDao.getById(id);
//
//        if (newPassword.length() < 5){
//            return "redirect:/user/account";
//        }
//
//        if (this.passwordEncoder.matches(oldPassword, user.getPassword())) {
//            user.setPassword(passwordEncoder.encode(newPassword));
//            userDao.save(user);
//        } else {
//            session.setAttribute("message", message);
//        }
//
//        return "redirect:/user/" + user.getId();
//    }

//    @PostMapping("/edit-bio")
//    public String editBio(@RequestParam(name = "bio") String bio, @RequestParam(name = "id") long id) {
//
//        User user = userDao.getById(id);
//        user.setBio(bio);
//
//        userDao.save(user);
//
//        return "redirect:/user/" + user.getId();
//    }

    @PostMapping("/edit-bio")
    public String editBio(@ModelAttribute User user) {

        User existingUser = userDao.getById(user.getId());
        existingUser.setBio(user.getBio());

        userDao.save(existingUser);

        return "redirect:/user/" + user.getId();
    }

    @PostMapping("/edit-profile-pic")
    public String editProfilePic(@RequestParam(name = "profile-picture-url") String profilePictureUrl, @RequestParam(name = "id") long id) {

        User user = userDao.getById(id);
        user.setProfilePictureUrl(profilePictureUrl);

        userDao.save(user);
        return "redirect:/user/" + user.getId();
    }

    @PostMapping("/edit-banner")
    public String editBanner(@RequestParam(name = "banner-url") String bannerUrl, @RequestParam(name = "id") long id) {

        User user = userDao.getById(id);
        user.setBannerUrl(bannerUrl);

        userDao.save(user);
        return "redirect:/user/" + user.getId();
    }


    @PostMapping("/create-platform-links")
    public String createPlatformLinks(@ModelAttribute PlatformLink platformLink, @RequestParam(name = "id") long id) {

        User user = userDao.getById(id);

        String discord = platformLink.getDiscord().replaceAll("\\s","");
        String nintendo = platformLink.getNintendo().replaceAll("\\s","");
        String playstation = platformLink.getPlaystation().replaceAll("\\s","");
        String twitch = platformLink.getTwitch().replaceAll("\\s","");
        String xbox = platformLink.getXbox().replaceAll("\\s","");
        String youtube = platformLink.getYoutube().replaceAll("\\s","");

        if (discord.length() > 22) {
            return "redirect:/user/account?discord";
        }
        if (nintendo.length() > 22) {
            return "redirect:/user/account?nintendo";
        }
        if (playstation.length() > 22) {
            return "redirect:/user/account?playstation";
        }
        if (twitch.length() > 22) {
            return "redirect:/user/account?twitch";
        }
        if (xbox.length() > 22) {
            return "redirect:/user/account?xbox";
        }
        if (youtube.length() > 22) {
            return "redirect:/user/account?youtube";
        }

        platformLink.setDiscord(discord);
        platformLink.setNintendo(nintendo);
        platformLink.setPlaystation(playstation);
        platformLink.setTwitch(twitch);
        platformLink.setXbox(xbox);
        platformLink.setYoutube(youtube);

        user.setLinks(platformLink);
        userDao.save(user);

        return "redirect:/user/account";
    }


    @PostMapping("/edit-platform-links")
    public String editPlatformLinks(@ModelAttribute PlatformLink platformLink, @RequestParam(name = "id") long id) {

        User user = userDao.getById(id);

        String discord = platformLink.getDiscord().replaceAll("\\s","");
        String nintendo = platformLink.getNintendo().replaceAll("\\s","");
        String playstation = platformLink.getPlaystation().replaceAll("\\s","");
        String twitch = platformLink.getTwitch().replaceAll("\\s","");
        String xbox = platformLink.getXbox().replaceAll("\\s","");
        String youtube = platformLink.getYoutube().replaceAll("\\s","");

        if (discord.length() > 22 || discord.isBlank()) {
            return "redirect:/user/account?discord";
        }
        if (nintendo.length() > 22 || nintendo.isBlank()) {
            return "redirect:/user/account?nintendo";
        }
        if (playstation.length() > 22 || playstation.isBlank()) {
            return "redirect:/user/account?playstation";
        }
        if (twitch.length() > 22 || twitch.isBlank()) {
            return "redirect:/user/account?twitch";
        }
        if (xbox.length() > 22 || xbox.isBlank()) {
            return "redirect:/user/account?xbox";
        }
        if (youtube.length() > 22 || youtube.isBlank()) {
            return "redirect:/user/account?youtube";
        }

        platformLink.setDiscord(discord);
        platformLink.setNintendo(nintendo);
        platformLink.setPlaystation(playstation);
        platformLink.setTwitch(twitch);
        platformLink.setXbox(xbox);
        platformLink.setYoutube(youtube);

        user.setLinks(platformLink);
        userDao.save(user);

        return "redirect:/user/account";
    }

//    @PostMapping("/edit-platform-links")
//    public String editPlatformLinks(@RequestParam(name = "discord") String discord, @RequestParam(name = "nintendo") String nintendo, @RequestParam(name = "playstation") String playstation, @RequestParam(name = "twitch") String twitch, @RequestParam(name = "xbox") String xbox, @RequestParam(name = "youtube") String youtube, @RequestParam(name = "id") long id) {
//
//        User user = userDao.getById(id);
//        PlatformLink pl = user.getLinks();
//
//        pl.setDiscord(discord);
//        pl.setNintendo(nintendo);
//        pl.setPlaystation(playstation);
//        pl.setTwitch(twitch);
//        pl.setXbox(xbox);
//        pl.setYoutube(youtube);
//
//        platformLinkDao.save(pl);
//
//        return "redirect:/user/" + user.getId();
//    }



}

