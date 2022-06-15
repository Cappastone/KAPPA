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
        List<Post> posts = postDao.findPostsByUserIds(followingIds);
        List<Date> postCreationDateObjs = getPostDateObjs(posts);
        List<String> postDates = getDates(postCreationDateObjs);

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

    public static String getDate(Date date) {

        long diff = System.currentTimeMillis() - date.getTime();
        long hours = Math.round(diff / (60 * 60 * 1000));

        if(hours < 12) {
            return "less than a day ago";
        } else {
            long days = Math.round(diff / (24.0 * 60 * 60 * 1000));
            if (days == 0)
                return "today";
            else if (days == 1)
                return "yesterday";
            else if (days == 7)
                return ((int) (days / 7)) + " week ago";
            else if (days < 14)
                return days + " days ago";
            else if (days <= 27)
                return ((int) (days / 7)) + " weeks ago";
            if (days == 28 || days == 29 || days == 30 || days == 31)
                return ((int) (days / 30)) + " month ago";
            else if (days < 365)
                return ((int) (days / 30)) + " months ago";
            else if (days == 365)
                return ((int) (days / 365)) + " year ago";
            else
                return ((int) (days / 365)) + " years ago";
        }
    }

    public static List<String> getDates(List<Date> dates){

        List<String> dateStrings = new ArrayList<>();

        for(Date date : dates){

            dateStrings.add(getDate(date));
            System.out.println("CHECK " + (getDate(date)));
        }

        return dateStrings;
    }

    public static List<Date> getPostDateObjs(List<Post> dates){

        List<Date> dateObjs = new ArrayList<>();

        for (Post post : dates){
            dateObjs.add(post.getCreationDate());
        }
        return dateObjs;
    }

    public static void main(String[] args) {
            LocalDate date = LocalDate.now();
            System.out.println(date);
    }

}
