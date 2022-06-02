package com.codeup.kappa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/games")
public class GameController {

    @GetMapping
    public String gamesIndex(){
        return "games/rawg";
    }

    @PostMapping
    public String SaveGames(){
        return "games/rawg";
    }



}
