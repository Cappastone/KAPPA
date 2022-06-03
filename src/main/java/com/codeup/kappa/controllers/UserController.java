package com.codeup.kappa.controllers;

import com.codeup.kappa.models.Post;
import com.codeup.kappa.repositories.PostRepository;
import com.codeup.kappa.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    private final UserRepository userDao;
    private final PostRepository postDao;

    public UserController(UserRepository userDao, PostRepository postDao) {
        this.userDao = userDao;
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    @ResponseBody
    public String test (Model model) {

        List<String> mostLiked = userDao.findByMostLiked();
        long id = Long.parseLong(mostLiked.get(0));
        model.addAttribute("top1", postDao.findById(id));
       

        return postDao.findById(id).toString();
    }


}
