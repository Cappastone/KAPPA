package com.codeup.kappa.controllers;

import com.codeup.kappa.models.Game;
import com.codeup.kappa.models.Post;
import com.codeup.kappa.models.User;
import com.codeup.kappa.repositories.GameRepository;
import com.codeup.kappa.repositories.PostImageRepository;
import com.codeup.kappa.repositories.PostRepository;
import com.codeup.kappa.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/discover")
public class DiscoverController {

    private final PostRepository postDao;
    private final GameRepository gameDao;
    private final UserRepository userDao;

    public DiscoverController(PostRepository postDao, UserRepository userDao, PostImageRepository postImageDao, GameRepository gameDao) {
        this.postDao = postDao;
        this.gameDao = gameDao;
        this.userDao = userDao;
    }

//    HttpServletRequest session;
//
//    public HttpSession getSession() {
//        try {
//            return session.getSession(false);
//        } catch (NullPointerException e) {
//            System.out.println("User not authenticated");
//
//        }
//
//        return null;
//    }

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
        }
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

}
