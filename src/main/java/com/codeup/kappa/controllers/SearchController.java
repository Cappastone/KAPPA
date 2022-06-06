package com.codeup.kappa.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/results")
public class SearchController {

    @GetMapping
    public String gamesIndex(){
        return "games/search-results";
    }

}
