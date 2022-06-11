package com.codeup.kappa.controllers;

import com.codeup.kappa.models.Post;
import com.codeup.kappa.repositories.PostImageRepository;
import com.codeup.kappa.repositories.PostRepository;
import com.codeup.kappa.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final PostImageRepository postImageDao;

    public PostController(PostRepository postDao, UserRepository userDao, PostImageRepository postImageDao) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.postImageDao = postImageDao;
    }

    @GetMapping("/{postId}")
    public String editPost(@PathVariable long postId, Model model) {

        model.addAttribute("post", postDao.getById(postId));

        return "posts/post";
    }

    @PostMapping("/edit-post")
    public String UpdatePost(@ModelAttribute Post post
                             , @RequestParam(name = "date") Date creationDate
//            , @ModelAttribute PostImages images
    ) {


        post.setCreationDate(creationDate);
        postDao.save(post);

        return "redirect:/posts/post";
    }

    public static void main(String[] args) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd, uuuu HH:mm");
        ZonedDateTime date = ZonedDateTime.now();
        System.out.println(format.format(date));
    }

}
