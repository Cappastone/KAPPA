package com.codeup.kappa.controllers;

import com.codeup.kappa.models.Game;
import com.codeup.kappa.models.User;
import com.codeup.kappa.repositories.GameRepository;
import com.codeup.kappa.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final UserRepository userDao;

    public GameController(GameRepository GameDao,UserRepository userDao) {
        this.gameDao = GameDao;
        this.userDao = userDao;
    }

    @GetMapping
    public String gamesIndex(Model model, @RequestParam("gameID")long api_id){

        List<Long> allApiIds = gameDao.findAllApiIds();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        if (allApiIds.contains(api_id)) {
            model.addAttribute("game", gameDao.findGameByGamesApiId(api_id));
            if(principal instanceof UserDetails) {
                User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                long user_id = user.getId();
                model.addAttribute("sessionUserId", user_id);
                model.addAttribute("ListGameIdFavoriteByUserId", userDao.findGameIdFavoriteByUserId(user_id));
            }
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
            @RequestParam(name = "platforms") String platforms,
            @RequestParam(name = "genres") String genres,
            @RequestParam(name = "rating", defaultValue = "N/A") String ageRating) {

        long parsedId = Long.parseLong(id);

        Game game = new Game(parsedId, title, description, backgroundUrl, platforms, genres, developer, ageRating);
        gameDao.save(game);
        return "redirect:game?gameID=" + id;
    }



}
