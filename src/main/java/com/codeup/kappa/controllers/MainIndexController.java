package com.codeup.kappa.controllers;

import com.codeup.kappa.models.Comment;
import com.codeup.kappa.models.Post;
import com.codeup.kappa.models.PostImage;
import com.codeup.kappa.models.User;
import com.codeup.kappa.repositories.CommentRepository;
import com.codeup.kappa.repositories.PostRepository;
import com.codeup.kappa.repositories.UserRepository;
import com.codeup.kappa.services.DateFormatter;
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
        long user_id = user.getId();

        if(user_id == 0){
            return "redirect:/discover";
        }

        List<Long> followingIds = userDao.followingList(user_id);

//        List<User> following = userDao.findAllById(followingIds);

        model.addAttribute("user", userDao.getById(user_id));
        model.addAttribute("following", userDao.findAllById(followingIds));
//        model.addAttribute("posts", postDao.findAll());
        model.addAttribute("followingPosts", postDao.findPostsByUserIds(followingIds));
        model.addAttribute("sessionUserId", user_id);
        model.addAttribute("ListPostIdLikedByUserId", userDao.findPostIdLikedByUserId(user_id));
        model.addAttribute("findCommentIdsByUserId", commentDao.findCommentIdsByUserId(user_id));

        model.addAttribute("newPost", new Post());
//        model.addAttribute("newComment", new Comment());

        //        get array list of dates in desired string format =>
        DateFormatter dateFormatter = new DateFormatter();
        List<Post> posts = postDao.findPostsByUserIds(followingIds);
        List<Date> postCreationDateObjs = dateFormatter.getPostDateObjs(posts);
//        List<Date> postCreationDateObjs = getPostDateObjs(posts);
//        List<String> postDates = getDates(postCreationDateObjs);
        List<String> postDates = dateFormatter.getDates(postCreationDateObjs);

        model.addAttribute("postCreationDates", postDates);

        return "index/main";
    }

    @PostMapping("main/create-post")
    public String addPost(@ModelAttribute Post post, @RequestParam(required = false, name = "post-image-upload") List <String> postImageUrls){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(postImageUrls.size() != 0) {

            List<PostImage> postImages1 = new ArrayList<>();

            if (postImageUrls.size() == 1) {
                PostImage postImages = new PostImage("hello", postImageUrls.get(0), post);
                postImages1.add(postImages);
            } else if (postImageUrls.size() == 2) {
                PostImage postImages = new PostImage("hello", postImageUrls.get(0), post);
                PostImage postImages2 = new PostImage("hello", postImageUrls.get(1), post);
                postImages1.add(postImages);
                postImages1.add(postImages2);
            } else if (postImageUrls.size() == 3) {
                PostImage postImages = new PostImage("hello", postImageUrls.get(0), post);
                PostImage postImages2 = new PostImage("hello", postImageUrls.get(1), post);
                PostImage postImages3 = new PostImage("hello", postImageUrls.get(2), post);
                postImages1.add(postImages);
                postImages1.add(postImages2);
                postImages1.add(postImages3);
            }

            post.setPostImages(postImages1);

        }

        post.setUser(user);

        postDao.save(post);

        return "redirect:/main";
    }

    public static void main(String[] args) {
            LocalDate date = LocalDate.now();
            System.out.println(date);
    }

}
