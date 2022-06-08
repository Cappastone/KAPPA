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
@RequestMapping("/index")
public class DiscoverController {
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final PostImageRepository postImageDao;
    private final GameRepository gameDao;

    public DiscoverController(PostRepository postDao, UserRepository userDao, PostImageRepository postImageDao, GameRepository gameDao) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.postImageDao = postImageDao;
        this.gameDao = gameDao;
    }

    @GetMapping
    public String topPostsAndGames(Model model) {

        List<String> mostLikedPosts = postDao.findPostByMostLiked();
        List<Post> mostLikedPosts2 = convertPosts(mostLikedPosts);

        List<String> mostLikedGames = gameDao.findGameByMostLiked();
        List<Game> mostLikedGames2 = convertGames(mostLikedGames);

        model.addAttribute("posts", mostLikedPosts2);
        model.addAttribute("games", (mostLikedGames2));

        return "/games/index";
    }

    public List<Post> convertPosts(List<String> list){

        List<Post> posts = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){

            posts.add(postDao.getPostById(Long.parseLong(list.get(i))));
        }
        return posts;
    }

    public List<Game> convertGames(List<String> list){

        List<Game> games = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){

            games.add(gameDao.getGameById(Long.parseLong(list.get(i))));
        }
        return games;
    }

}
