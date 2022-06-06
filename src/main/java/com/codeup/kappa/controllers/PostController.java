package com.codeup.kappa.controllers;

import com.codeup.kappa.models.Post;
import com.codeup.kappa.repositories.PostRepository;
import com.codeup.kappa.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/index")
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping
    public String topPosts(Model model) {

        List<String> mostLiked = postDao.findByMostLiked();

        long id1 = Long.parseLong(mostLiked.get(0));

        long postId = postDao.getPostById(id1).getId();

        long likes = postDao.numberOfLikes(postId);

        model.addAttribute("likes1", likes);
        model.addAttribute("top1", postDao.getPostById(id1));

        long id2 = Long.parseLong(mostLiked.get(1));
        model.addAttribute("likes2", likes);
        model.addAttribute("top2", postDao.getPostById(id2));
        Post bob = postDao.getPostById(id2);
        System.out.println("yoooooooooooo" + bob);
        System.out.println("yoooooooooooo" + bob.getBody());



        long id3 = Long.parseLong(mostLiked.get(2));
        model.addAttribute("likes3", likes);
        model.addAttribute("top3", postDao.getPostById(id3));

        long id4 = Long.parseLong(mostLiked.get(3));
        model.addAttribute("likes4", likes);
        model.addAttribute("top4", postDao.getPostById(id4));

        return "/games/index";
    }

}
