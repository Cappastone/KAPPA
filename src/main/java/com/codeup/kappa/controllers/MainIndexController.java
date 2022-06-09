package com.codeup.kappa.controllers;

import com.codeup.kappa.models.Post;
import com.codeup.kappa.models.User;
import com.codeup.kappa.repositories.PostRepository;
import com.codeup.kappa.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainIndexController {

    private final UserRepository userDao;
    private final PostRepository postDao;

    public MainIndexController(UserRepository userDao, PostRepository postDao){
        this.userDao = userDao;
        this.postDao = postDao;
    }

    @GetMapping
    public String home() {
      
        return "index/discover";
    }

    @GetMapping("main")
    public String index(Model model){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();

        List<Long> followingIds = userDao.followingList(userId);
        List<User> following = userDao.findAllById(followingIds);
//        List<Post> followingPosts = postDao.findAllById(followingIds);


//        List<Long> fPostIds = loop(following);


//        Collections.sort();

//        NEEDS WORK =>

        model.addAttribute("user", user);
        model.addAttribute("following", following);
        model.addAttribute("posts", postDao.findAll());
        model.addAttribute("followingPosts", following);

//        model.addAttribute("followingPosts", findAllById(followingIds));

        return "index/main";
    }

    public static void main(String[] args) {
            LocalDate date = LocalDate.now();

            System.out.println(date);
    }

//    public List<Post> findAllById(List<Long> ids) {
//        List<Post> results = new ArrayList<>();
//        for (Long id : ids) {
//            results.add(postDao.findPostByUserId(id));
//        }
//        return results;
//    }

    public List<Post> findAllById(List<Long> ids) {

        List<Post> results = new ArrayList<>();

        for (Long id : ids) {
            results.add(postDao.findPostByUserId(id));
        }
        return results;
    }


//    public List<Long> loop(List<User> list){
//
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        long userId = user.getId();
//
//        List<Long> followingIds = userDao.followingList(userId);
//        List<User> following = userDao.findAllById(followingIds);
//
//        List<Long> postIds = new ArrayList<>();
//
//        for(int i = 0; i < list.size(); i++){
//
//            postIds.add(following.get(i).getId());
//        }
//        return postIds;
//    }

//    public List<Post> loop2(List<Long> list){
//
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        long userId = user.getId();
//
//        List<Long> followingIds = userDao.followingList(userId);
//        List<User> following = userDao.findAllById(followingIds);
//
//        List<Long> postIds = new ArrayList<>();
//
//        for(int i = 0; i < list.size(); i++){
//
//            postIds.add(following.get(i).getId());
//        }
//        return postIds;
//    }



}
