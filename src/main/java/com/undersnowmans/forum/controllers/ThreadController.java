package com.undersnowmans.forum.controllers;

import com.undersnowmans.forum.models.Thread;
import com.undersnowmans.forum.repos.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/threads")
public class ThreadController {

    @Autowired
    ThreadRepository threadRepository;

    @GetMapping
    public String getThreadsPage(Model model) {
        return "threads";
    }

    @GetMapping("/all")
    public List<Thread> getAllThreads() {
        var threads = List.of(new Thread(1L, "Sex", 1L, "23.02.2022"),
                                          new Thread(2L, "IT", 2L, "24.08.2005"));

        return threads;
    }



}
