package com.codeup.kappa.controllers;

import com.codeup.kappa.repositories.PostRepository;
import com.codeup.kappa.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/posts")
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
        model.addAttribute("top1", postDao.getPostById(id1));

//        long id2 = Long.parseLong(mostLiked.get(1));
//        model.addAttribute("top2", postDao.findById(id2));
//
//        long id3 = Long.parseLong(mostLiked.get(2));
//        model.addAttribute("top3", postDao.findById(id3));
//
//        long id4 = Long.parseLong(mostLiked.get(3));
//        model.addAttribute("top4", postDao.findById(id4));

        return "posts/post";
    }

}
