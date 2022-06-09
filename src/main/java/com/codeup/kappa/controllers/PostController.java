package com.codeup.kappa.controllers;

import com.codeup.kappa.models.Post;
import com.codeup.kappa.repositories.PostImageRepository;
import com.codeup.kappa.repositories.PostRepository;
import com.codeup.kappa.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping()
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final PostImageRepository postImageDao;

    public PostController(PostRepository postDao, UserRepository userDao, PostImageRepository postImageDao) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.postImageDao = postImageDao;
    }





//    public List<Post> convert(List<String> list){
//
//        List<Post> posts = new ArrayList<>();
//
//        for(int i = 0; i < list.size(); i++){
//
//            posts.add(postDao.getPostById(Long.parseLong(list.get(i))));
//        }
//        return posts;
//
//    }

    public static void main(String[] args) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd, uuuu HH:mm");
        ZonedDateTime date = ZonedDateTime.now();
        System.out.println(format.format(date));
    }

//    @GetMapping
//    public String topPosts(Model model) {
//
//        List<String> mostLiked = postDao.findPostByMostLiked();
//        List<Post> mostLiked2 = convert(mostLiked);
//
//
//        model.addAttribute("post", (mostLiked2));
//
//        return "/games/index";
//    }

}
