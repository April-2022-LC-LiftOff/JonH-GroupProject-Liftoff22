package com.example.demo.auth.controllers;

import com.example.demo.auth.data.UserRepository;
import com.example.demo.auth.models.User;
import com.example.demo.auth.models.dto.LoginFormDTO;
import com.example.demo.auth.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }


    @PostMapping("/register")
    public ResponseEntity<Object> processRegistrationForm(@RequestBody @Valid RegisterFormDTO registerFormDTO,
                                                        Errors errors, HttpServletRequest request,
                                                        Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return ResponseEntity.badRequest().body("Has Errors");
        }

        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());

        if (existingUser != null) {
            return ResponseEntity.badRequest().body("Already Exists");
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            return ResponseEntity.badRequest().body("Password mismatch");
        }

        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getEmail(), registerFormDTO.getPassword());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return ResponseEntity.ok(newUser);
    }


    @PostMapping("/login")
    public ResponseEntity<Object> processLoginForm(@RequestBody @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body("Log in");
        }

        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        if (theUser == null) {
            return ResponseEntity.badRequest().body("The given username does not exist");
        }

        String password = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            return ResponseEntity.badRequest().body("Password mismatch");
        }

        setUserInSession(request.getSession(), theUser);

        return ResponseEntity.ok(theUser);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }

}
