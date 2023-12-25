package com.undersnowmans.forum.controllers;

import com.undersnowmans.forum.models.User;
import com.undersnowmans.forum.repos.UserRepository;
import com.undersnowmans.forum.service.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String getRegisterPage(Model model) {
        return "register";
    }

    @PostMapping("/send")
    public String register(@RequestParam String username, @RequestParam String password) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isPresent()) {
            return "register";
        }

        var user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRank(UserRoles.STANDARD_ROLE);
        userRepository.save(user);

        return "redirect:/";
    }
}
