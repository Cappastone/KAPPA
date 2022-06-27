package com.codeup.kappa.controllers;

import com.codeup.kappa.models.Post;
import com.codeup.kappa.models.PostImage;
import com.codeup.kappa.models.User;
import com.codeup.kappa.repositories.CommentRepository;
import com.codeup.kappa.repositories.PostImageRepository;
import com.codeup.kappa.repositories.PostRepository;
import com.codeup.kappa.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final PostImageRepository postImageDao;
    private final CommentRepository postCommentDao;

    public PostController(PostRepository postDao, UserRepository userDao, PostImageRepository postImageDao, CommentRepository postCommentDao) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.postImageDao = postImageDao;
        this.postCommentDao = postCommentDao;
    }

    @GetMapping("/{postId}")
    public String editPost(@PathVariable long postId, Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Post post = postDao.getById(postId);
        User user2 = post.getUser();

        if (user.getId() != user2.getId()) {
            return "redirect:/user/" + user.getId();
        }

        model.addAttribute("post", postDao.getById(postId));

        return "posts/post2";
    }


//    @PostMapping("/edit-post")
//    public String updatePost(@RequestParam("postId") long id, @RequestParam("body") String body) {
//
//        Post post = postDao.getPostById(id);
//
//        if (body.isBlank()){
//            return "redirect:/post/" + id;
//        }
//
//        post.setBody(body);
//
//        postDao.save(post);
//
//        return "redirect:/post/" + id;
//    }


    @PostMapping("/edit-post")
    public String updatePost(@ModelAttribute Post post) {

        Post existingPost = postDao.getPostById(post.getId());

//        if (body.isBlank()){
//            return "redirect:/post/" + id;
//        }

        existingPost.setBody(post.getBody());

        postDao.save(existingPost);

        return "redirect:/post/" + post.getId();
    }

    @PostMapping("/delete-post")
    public String deletePost(@RequestParam("postId") long id) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Post post = postDao.getPostById(id);

        postCommentDao.deleteAllByPost(post);
        postImageDao.deleteAllByPost(post);
        postDao.deletePostLikeByPostId(id);

        postDao.deleteById(id);

        return "redirect:/main/";
    }

    @PostMapping("delete-image")
    public String updatePostImages(@RequestParam("image-id") long imageId, @RequestParam("post-id") long postId){

        postImageDao.deleteById(imageId);

        return "redirect:/post/" + postId;
    }

    @PostMapping("/add-image")
    public String addImage(@RequestParam(name="img-urls") List <String> imageUrls, @RequestParam(name="post-id") long postId){

        Post post = postDao.getById(postId);


            List<PostImage> addPostImages = new ArrayList<>();

            if (imageUrls.size() == 1) {
                PostImage postImages = new PostImage("hello", imageUrls.get(0), post);
                addPostImages.add(postImages);
            } else if (imageUrls.size() == 2) {
                PostImage postImages = new PostImage("hello", imageUrls.get(0), post);
                PostImage postImages2 = new PostImage("hello", imageUrls.get(1), post);
                addPostImages.add(postImages);
                addPostImages.add(postImages2);
            } else if (imageUrls.size() == 3) {
                PostImage postImages = new PostImage("hello", imageUrls.get(0), post);
                PostImage postImages2 = new PostImage("hello", imageUrls.get(1), post);
                PostImage postImages3 = new PostImage("hello", imageUrls.get(2), post);
                addPostImages.add(postImages);
                addPostImages.add(postImages2);
                addPostImages.add(postImages3);
            }

            post.setPostImages(addPostImages);


        postDao.save(post);

        return "redirect:/post/" + postId;
    }

    public static void main(String[] args) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd, uuuu HH:mm");
        ZonedDateTime date = ZonedDateTime.now();
        System.out.println(format.format(date));
    }

}
