package com.undersnowmans.forum.controllers;

import com.undersnowmans.forum.models.User;
import com.undersnowmans.forum.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String getLoginPage(Model model) {
        return "login";
    }

    @PostMapping("/send")
    public String login(@RequestParam String username, @RequestParam String password) {

        // TO-DO: токены нада
        Optional<User> optionalUser = userRepository.findUserByUsername(username);

        if (optionalUser.isPresent()) {
            if (optionalUser.get().getPassword().equals(password)) {
                return "redirect:/";
            }
        }

        return "login";
    }
}
