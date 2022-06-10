package com.codeup.kappa.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.attribute.standard.Media;

@Controller
public class KeysController {
    @Value("token")
    private String token;

    @GetMapping(path = "/keys", produces = "application/javascript")
    @ResponseBody
    public String keys() {
        String rawg = "const token =" + token;
        return rawg;
    }

}
