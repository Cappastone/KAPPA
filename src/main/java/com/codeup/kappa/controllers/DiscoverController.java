package com.codeup.kappa.controllers;

import com.codeup.kappa.models.Comment;
import com.codeup.kappa.models.Game;
import com.codeup.kappa.models.Post;
import com.codeup.kappa.models.User;
import com.codeup.kappa.repositories.*;
import com.codeup.kappa.services.DateFormatter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/discover")
public class DiscoverController {

    private final PostRepository postDao;
    private final GameRepository gameDao;
    private final UserRepository userDao;
    private final CommentRepository commentDao;

    public DiscoverController(PostRepository postDao, UserRepository userDao, PostImageRepository postImageDao, GameRepository gameDao, CommentRepository commentDao) {
        this.postDao = postDao;
        this.gameDao = gameDao;
        this.userDao = userDao;
        this.commentDao = commentDao;
    }

    @GetMapping
    public String topPostsAndGames(Model model) {

//        calls method to retrieve the list of mostLiked Posts
        List<String> mostLikedPostsAsStrings = postDao.findPostByMostLiked();
        List<Post> mostLikedPosts = convertPosts(mostLikedPostsAsStrings);

        List<String> mostLikedGamesAsStrings = gameDao.findGameByMostLiked();
        List<Game> mostLikedGames = convertGames(mostLikedGamesAsStrings);

//        sends object info to user view
        model.addAttribute("posts", mostLikedPosts);
        model.addAttribute("games", (mostLikedGames));

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        boolean validSession = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
//        System.out.println(validSession);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        if (principal instanceof UserDetails) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            long user_id = user.getId();
            model.addAttribute("sessionUserId", user_id);
            model.addAttribute("ListPostIdLikedByUserId", userDao.findPostIdLikedByUserId(user_id));
            model.addAttribute("findCommentIdsByUserId", commentDao.findCommentIdsByUserId(user_id));

            model.addAttribute("newComment", new Comment());
        }

        //        get array list of dates in desired string format =>
        DateFormatter dateFormatter = new DateFormatter();
        List<Date> postCreationDateObjs = dateFormatter.getPostDateObjs(mostLikedPosts);
        List<String> postDates = dateFormatter.getDates(postCreationDateObjs);

        model.addAttribute("postCreationDates", postDates);

        return "index/discover";
    }

//    loops through list of Post id's in string and converts data type to long and retrieves list of Post objects in the original index order
    public List<Post> convertPosts(List<String> list){

        List<Post> posts = new ArrayList<>();

        for (String s : list) {
            posts.add(postDao.getPostById(Long.parseLong(s)));
        }
        return posts;
    }

    //    loops through list of Game id's in string and converts data type to long and retrieves list of Post objects in the original index order
    public List<Game> convertGames(List<String> list){

        List<Game> games = new ArrayList<>();

        for (String s : list) {

            games.add(gameDao.getGameById(Long.parseLong(s)));
        }
        return games;
    }

    @PostMapping("/comment")
    public String comment(
            @ModelAttribute Comment newComment,
//            @RequestParam("commentBody") String body,
            @RequestParam("postId") long postId){

//        Comment comment = new Comment();

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        newComment.setUser(user);
        newComment.setPost(postDao.getPostById(postId));
//        comment.setComment(body);

        Date date = new Date();
        newComment.setCreationDate(date);

        commentDao.save(newComment);

        return "redirect:/discover";
    }


}
