package com.codeup.kappa.controllers;

import com.codeup.kappa.models.Post;
import com.codeup.kappa.models.PostImage;
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

    //    FORM-MODEL BINDING NEEDS WORK BECAUSE OF DATE FORMAT COMPLICATION
    @PostMapping("/edit-post")
    public String updatePost(@RequestParam("postId") long id, @RequestParam("body") String body) {

        Post post = postDao.getPostById(id);
        post.setBody(body);

        postDao.save(post);

        return "redirect:/post/" + id;
    }

    @PostMapping("delete-image")
    public String updatePostImages(@RequestParam("image-id") long imageId, @RequestParam("post-id") long postId){

        postImageDao.deleteById(imageId);

        return "redirect:/post/" + postId;
    }

    @PostMapping("/add-image")
    public String addImage(@RequestParam(name="img-title")String title, @RequestParam(name="url")String url, @RequestParam(name="post-id") long postId){

        Post post = postDao.getById(postId);
        PostImage postImage = new PostImage(title, url, post);

        List<PostImage> images = post.getPostImages();
        images.add(postImage);
        post.setPostImages(images);
        postDao.save(post);

        return "redirect:/post/" + postId;
    }

//    NEEDS WORK
//    @PostMapping("edit-image")
//    public String updatePostImages(@RequestParam("imageId") long imageId, @RequestParam("postId") long postId, @RequestParam("title") String title, @RequestParam("url") String url){
//
//        PostImage image = postImageDao.getById(imageId);
//        image.setTitle(title);
//        image.setUrl(url);
//
//        return "redirect:/post/" + postId;
//    }

    public static void main(String[] args) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd, uuuu HH:mm");
        ZonedDateTime date = ZonedDateTime.now();
        System.out.println(format.format(date));
    }

}
