package com.example.demo.auth.controllers;

import com.example.demo.auth.data.UserRepository;
import com.example.demo.auth.models.Reminder;
import com.example.demo.auth.models.User;
import com.example.demo.auth.models.dto.LoginFormDTO;
import com.example.demo.auth.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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
                                                          Errors errors, HttpServletRequest request) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body("Has Errors");
        }

        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());

        if (existingUser != null) {
            User nullUser = new User();
            return ResponseEntity.ok(nullUser);
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            return ResponseEntity.badRequest().body("Password mismatch");
        }

        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getEmail(), registerFormDTO.getPassword());
        if (registerFormDTO.getMobile()!=null) {
            newUser.setMobile(registerFormDTO.getMobile());
        }
        if (registerFormDTO.getCarrier()!=null) {
            newUser.setCarrier(registerFormDTO.getCarrier());
        }
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
            User nullUser = new User();
            return ResponseEntity.ok(nullUser);
        }

        String password = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            User badPasswordUser = new User("error");
            return ResponseEntity.ok(badPasswordUser);
        }

        setUserInSession(request.getSession(), theUser);

        return ResponseEntity.ok(theUser);
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request){
        request.getSession().invalidate();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/profile")
    public ResponseEntity<Object> updateUser(@RequestBody User user, HttpServletRequest request, RegisterFormDTO registerFormDTO) {

        HttpSession session = request.getSession();
        User oldUserInfo = getUserFromSession(session);

        try {
            Files.write(Paths.get("user.txt"), Arrays.asList("User Info Input: \n" + user.getMobile()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (user!=null) {
            User _user = user;
            _user.setUsername(user.getUsername());
            _user.setEmail(user.getEmail());
            _user.setMobile(user.getMobile());
            _user.setCarrier(user.getCarrier());
            if(registerFormDTO.getPassword()!=null) {
                String password = registerFormDTO.getPassword();
                String verifyPassword = registerFormDTO.getVerifyPassword();
                if (!password.equals(verifyPassword)) {
                    return ResponseEntity.badRequest().body("Password mismatch");
                }
                _user.setPassword(registerFormDTO.getPassword());
            } else if(registerFormDTO.getPassword() == null) {
                _user.setPwHash(oldUserInfo.getPwHash());
            }
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = getUserFromSession(session);

        if (user!=null) {
            return ResponseEntity.ok(user);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}