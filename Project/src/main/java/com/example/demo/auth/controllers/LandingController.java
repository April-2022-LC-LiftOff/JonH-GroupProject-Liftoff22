package com.example.demo.auth.controllers;

@Controller
public class LandingController {
    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }


}