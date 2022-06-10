package com.codeup.kappa.controllers;

import com.codeup.kappa.models.Game;
import com.codeup.kappa.models.Post;
import com.codeup.kappa.repositories.GameRepository;
import com.codeup.kappa.repositories.PostImageRepository;
import com.codeup.kappa.repositories.PostRepository;
import com.codeup.kappa.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/discover")
public class DiscoverController {

    private final PostRepository postDao;
    private final GameRepository gameDao;

    public DiscoverController(PostRepository postDao, UserRepository userDao, PostImageRepository postImageDao, GameRepository gameDao) {
        this.postDao = postDao;
        this.gameDao = gameDao;
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
