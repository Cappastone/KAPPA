package com.codeup.kappa.controllers;

import com.codeup.kappa.models.Game;
import com.codeup.kappa.models.Post;
import com.codeup.kappa.repositories.GameRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/games")
public class GameController {

    private final GameRepository gameDao;

    public GameController(GameRepository GameDao) {
        this.gameDao = GameDao;
    }

    @GetMapping
    public String gamesIndex(){

        return "games/rawg";
    }

    @PostMapping
    public String SaveGames(
            @RequestParam(name = "game-id") String id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "background-url") String backgroundUrl,
            @RequestParam(name = "developer") String developer,
            @RequestParam(name = "rating") String rating,
            @RequestParam(name = "platforms") String platforms,
            @RequestParam(name = "genres") String genres) {

        long parsedId = Long.parseLong(id);

        Game game = new Game(parsedId, title, description, backgroundUrl, platforms, rating, genres, developer);
        gameDao.save(game);
        return "games/rawg";
    }

}
