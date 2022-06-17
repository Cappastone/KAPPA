package com.codeup.kappa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutUsController {

    @GetMapping("/about-us")
    public String getAboutUs() {
        return "/about/about-us";
    }

    @PostMapping("/about-us")
    public String showAboutUs() {
        return "about/about-us";
    }
}