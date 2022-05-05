package com.example.demo.auth.controllers;

import com.example.demo.auth.web.bind.annotation.*;
import com.example.demo.auth.ui.Model;
import com.example.demo.auth.stereotype.Controller;


@Controller
public class LandingController {
    @RequestMapping("landing")
    public String landing(Model model) {
        model.addAttribute("title", "Greetings");
        return "./landing.component.html";
    }

    //when login page is created
    @RequestMapping("/login")
    public String loginLink(Model model) {
        model.addAttribute("title", "Log In");
        return "login";
    }

    @RequestMapping("register")
    public String registerLink(Model model) {
        model.addAttribute("title", "Register");
        return "./register.component.html";
    }
}