package com.undersnowmans.forum.controllers;

import com.undersnowmans.forum.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String getAuthPage(Model model) {
        return "auth/auth";
    }
}
