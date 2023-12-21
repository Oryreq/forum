package com.undersnowmans.forum.controllers;

import com.undersnowmans.forum.models.Thread;
import com.undersnowmans.forum.repos.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/threads")
public class ThreadController {

    @Autowired
    ThreadRepository threadRepository;

    @GetMapping
    public String getThreads(Model model) {
        return "threads";
    }
}
