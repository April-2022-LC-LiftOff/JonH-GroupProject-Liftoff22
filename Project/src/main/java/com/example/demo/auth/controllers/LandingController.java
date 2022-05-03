package com.example.demo.auth.controllers;

import com.example.demo.auth.web.bind.annotation.*;
import com.example.demo.auth.ui.Model;
import com.example.demo.auth.stereotype.Controller;


@Controller
public class LandingController {
    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title", "Greetings");
        return "index";
    }

    @RequestMapping("/login")
    public String loginLink(Model model) {
        model.addAttribute("login", "Log In");
        return "login";
    }

    @RequestMapping("/register")
    public String registerLink(Model model) {
        model.addAttribute("register", "Register");
        return "register";
    }
}