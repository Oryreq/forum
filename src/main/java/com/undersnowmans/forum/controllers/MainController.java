package com.undersnowmans.forum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping("/")
    public String userAccess(Principal principal) {
        if (principal == null) {
            return null;
        }
        return "index";
    }
}
