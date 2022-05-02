package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "Welcome");

        return "index";
    }






}
