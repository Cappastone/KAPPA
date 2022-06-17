package com.codeup.kappa.controllers;

import com.codeup.kappa.models.User;
import com.codeup.kappa.repositories.CommentRepository;
import com.codeup.kappa.repositories.GameRepository;
import com.codeup.kappa.repositories.PostRepository;
import com.codeup.kappa.repositories.UserRepository;
import com.sun.mail.imap.protocol.Item;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/connections")
public class ConnectionsController {
    private final PostRepository postDao;
    private final GameRepository gameDao;
    private final UserRepository userDao;
    private final CommentRepository commentDao;

    public ConnectionsController(PostRepository postDao, GameRepository gameDao, UserRepository userDao, CommentRepository commentDao) {
        this.postDao = postDao;
        this.gameDao = gameDao;
        this.userDao = userDao;
        this.commentDao = commentDao;
    }

    @GetMapping("/connections")
    public String connections(Model model, @RequestParam(name="search-users", required=false) String qparams) {

        if (qparams != null) {
            model.addAttribute("searchResults", userDao.findUserByName('%' + qparams + '%'));
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();

        List<Long> followingIds = userDao.followingList(userId);
        model.addAttribute("following", userDao.findAllById(followingIds));

        model.addAttribute("user", userDao.getById(userId));


        model.addAttribute("sessionUserId", userId);
        model.addAttribute("ListPostIdLikedByUserId", userDao.findPostIdLikedByUserId(userId));
        model.addAttribute("ListUserIdsByFollowerId", userDao.findUserIdsByFollowerId(userId));



        return "users/connections";
    }

//    @GetMapping(path = {"/user", "/user/{data}"})
//    public void user(@PathVariable(required=false,name="data") String data,
//                     @RequestParam(required=false) Map<String,String> qparams) {
//        qparams.forEach((a,b) -> {
//            System.out.println(String.format("%s -> %s",a,b));
//        }
//
//        if (data != null) {
//            System.out.println(data);
//        }
//    }
//
//    @RequestMapping(value="connections", method = RequestMethod.GET)
//    public @ResponseBody User getItem(@RequestParam("serach-users") String itemid){
//
//        User i = userDao.findByUsername(itemid);
//
//
//        return i;
//    }

//    @PostMapping()
//    public String searchUser(@RequestParam(name = "search-users") String searchedUser, Model model) {
//
//        if (searchedUser != null) {
//            model.addAttribute("searchResults", searchedUser);
//        }
//
//
//        return "redirect:/connections";
//    }

}
