package com.codeup.kappa.controllers;

import com.codeup.kappa.models.Comment;
import com.codeup.kappa.models.Post;
import com.codeup.kappa.models.PostImage;
import com.codeup.kappa.models.User;
import com.codeup.kappa.repositories.CommentRepository;
import com.codeup.kappa.repositories.PostRepository;
import com.codeup.kappa.repositories.UserRepository;
import com.sun.mail.imap.protocol.ID;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class MainIndexController {

    private final UserRepository userDao;
    private final PostRepository postDao;
    private final CommentRepository commentDao;

    public MainIndexController(UserRepository userDao, PostRepository postDao, CommentRepository commentDao){
        this.userDao = userDao;
        this.postDao = postDao;
        this.commentDao = commentDao;
    }

    @GetMapping
    public String home() {

        return "index/discover";
    }

    @GetMapping("main")
    public String mainIndex(Model model){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();

        if(userId == 0){
            return "redirect:/discover";
        }

        List<Long> followingIds = userDao.followingList(userId);

//        List<User> following = userDao.findAllById(followingIds);

        model.addAttribute("user", userDao.getById(userId));
        model.addAttribute("following", userDao.findAllById(followingIds));
//        model.addAttribute("posts", postDao.findAll());
        model.addAttribute("followingPosts", postDao.findPostsByUserIds(followingIds));
        model.addAttribute("sessionUserId", userId);
        model.addAttribute("ListPostIdLikedByUserId", userDao.findPostIdLikedByUserId(userId));

        model.addAttribute("newPost", new Post());

        model.addAttribute("newComment", new Comment());

        return "index/main";
    }

    @PostMapping("main/create-post")
    public String addPost(@ModelAttribute Post post, @RequestParam(name = "post-image-upload") String postImageUrl){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        PostImage postImages = new PostImage("hello", postImageUrl, post);

        List<PostImage> postImages1 = new ArrayList<>();
        postImages1.add(postImages);

        post.setPostImages(postImages1);
        post.setUser(user);

        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
//        Date newDate = sdf.format(date);

        post.setCreationDate(date);

        postDao.save(post);

        return "redirect:/main";
    }

    @PostMapping("main/comment")
    public String comment(@ModelAttribute Comment newComment, @RequestParam("postId") long postId){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        newComment.setUser(user);
        newComment.setPost(postDao.getPostById(postId));

        Date date = new Date();
        newComment.setCreationDate(date);

        commentDao.save(newComment);

        return "redirect:/main";
    }

    public static void main(String[] args) {
            LocalDate date = LocalDate.now();
            System.out.println(date);
    }

}
