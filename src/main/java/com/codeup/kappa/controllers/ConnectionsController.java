package com.codeup.kappa.controllers;

import com.codeup.kappa.models.User;
import com.codeup.kappa.repositories.CommentRepository;
import com.codeup.kappa.repositories.GameRepository;
import com.codeup.kappa.repositories.PostRepository;
import com.codeup.kappa.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/connections")
public class ConnectionsController {
    private final PostRepository postDao;
    private final GameRepository gameDao;
    private final UserRepository userDao;
    private final CommentRepository commentDao;

    public ConnectionsController(PostRepository postDao, GameRepository gameDao, UserRepository userDao, CommentRepository commentDao){
        this. postDao = postDao;
        this.gameDao = gameDao;
        this.userDao = userDao;
        this.commentDao = commentDao;
    }

    @GetMapping()
    public String connections(Model model){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();

        List<Long> followingIds = userDao.followingList(userId);
        model.addAttribute("following", userDao.findAllById(followingIds));

        model.addAttribute("user", userDao.getById(userId));


        model.addAttribute("sessionUserId", userId);
        model.addAttribute("ListPostIdLikedByUserId", userDao.findPostIdLikedByUserId(userId));
        model.addAttribute("ListUserIdsByFollowerId", userDao.findUserIdsByFollowerId(userId));

        return "/users/connections";
    }

}
