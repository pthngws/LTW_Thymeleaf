package com.pthngws.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String index(){
        return "home";
    }
}
