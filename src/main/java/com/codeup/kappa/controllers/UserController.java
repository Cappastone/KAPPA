package com.codeup.kappa.controllers;

import com.codeup.kappa.models.Game;
import com.codeup.kappa.models.PlatformLink;
import com.codeup.kappa.models.Post;
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
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userDao;
    private final PostRepository postDao;
    private final PasswordEncoder passwordEncoder;
    private final PlatformLinkRepository platformLinkDao;

    public UserController(UserRepository userDao, PostRepository postDao, PasswordEncoder passwordEncoder, PlatformLinkRepository platformLinkDao) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.passwordEncoder = passwordEncoder;
        this.platformLinkDao = platformLinkDao;
    }

    @GetMapping("/{id}")
    public String viewUserProfile(
            @PathVariable long id, Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null) {
//        User user = (User)platformLinkDao SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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

//        get array list of dates in desired string format =>
        User user = userDao.getById(id);
        Date userCreationDate = user.getCreationDate();
        String userDate = getDate(userCreationDate);

        List<Post> posts = postDao.findPostsByUserId(id);
        List<Date> postCreationDateObjs = getPostDateObjs(posts);
        List<String> postDates = getDates(postCreationDateObjs);

        model.addAttribute("userCreationDate", userDate);
        model.addAttribute("postCreationDates", postDates);

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
    public String createUser(Model model) {

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
        user.setLinks(platformLink);
        userDao.save(user);

        return "redirect:/user/account";
    }


    @PostMapping("/edit-platform-links")
    public String editPlatformLinks(@ModelAttribute PlatformLink platformLink, @RequestParam(name = "id") long id) {

        User user = userDao.getById(id);
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

    public static String getDate(Date date) {

        long diff = System.currentTimeMillis() - date.getTime();
        long hours = Math.round(diff / (60 * 60 * 1000));

        if(hours < 12) {
            return "less than a day ago";
        } else {
            long days = Math.round(diff / (24.0 * 60 * 60 * 1000));
            if (days == 0)
                return "today";
            else if (days == 1)
                return "yesterday";
            else if (days == 7)
                return ((int) (days / 7)) + " week ago";
            else if (days < 14)
                return days + " days ago";
            else if (days <= 27)
                return ((int) (days / 7)) + " weeks ago";
            if (days == 28 || days == 29 || days == 30 || days == 31)
                return ((int) (days / 30)) + " month ago";
            else if (days < 365)
                return ((int) (days / 30)) + " months ago";
            else if (days == 365)
                return ((int) (days / 365)) + " year ago";
            else
                return ((int) (days / 365)) + " years ago";
        }
    }

    public static List<String> getDates(List<Date> dates){

        List<String> dateStrings = new ArrayList<>();

        for(Date date : dates){

            dateStrings.add(getDate(date));
            System.out.println("CHECK " + (getDate(date)));
        }

        return dateStrings;
    }

    public static List<Date> getPostDateObjs(List<Post> dates){

        List<Date> dateObjs = new ArrayList<>();

        for (Post post : dates){
            dateObjs.add(post.getCreationDate());
        }
        return dateObjs;
    }


}
