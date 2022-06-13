//package com.codeup.kappa.controllers;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.print.attribute.standard.Media;
//
//@Controller
//public class KeysController {
//    @Value("${token}")
//    private String token;
//
//    @Value("${RAPID_API_TOKEN}")
//    private String RAPID_API_TOKEN;
//
//    @Value("${FS_API_KEY}")
//    private String FS_API_TOKEN;
//
//    @GetMapping(path = "/keys", produces = "application/javascript")
//    @ResponseBody
//    public String keys() {
//        String rawg = "const token ='" + token + "'; ";
//        rawg += "const RAPID_API_TOKEN='" + RAPID_API_TOKEN + "'; ";
//        rawg += "const FS_API_TOKEN='" + FS_API_TOKEN + "'; ";
//        return rawg;
//    }
//
//
//}
