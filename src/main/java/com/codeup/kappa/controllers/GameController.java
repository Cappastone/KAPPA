package com.codeup.kappa.controllers;

import com.codeup.kappa.models.Game;
import com.codeup.kappa.repositories.GameRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/game")
public class GameController {

    private final GameRepository gameDao;

    public GameController(GameRepository GameDao) {
        this.gameDao = GameDao;
    }

    @GetMapping
    public String gamesIndex(Model model, @RequestParam("gameID")long api_id){

        List<Long> allApiIds = gameDao.findAllApiIds();


        if (allApiIds.contains(api_id)) {
            model.addAttribute("game", gameDao.findGameByGamesApiId(api_id));
        }


//        long game = gameDao.findGameByGamesApiId(api_id);
//        System.out.println("YOOOOOOOOOOOOOOOO!!!!!!! " + game);
//
//        model.addAttribute("game", gameDao.getGameById(game));

        return "games/game";
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
        return "games/game";
    }



}
