package com.codeup.kappa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {

        return "index/discover";

    }



    public static void main(String[] args) {
            LocalDate date = LocalDate.now();
            System.out.println(date);

    }

}
