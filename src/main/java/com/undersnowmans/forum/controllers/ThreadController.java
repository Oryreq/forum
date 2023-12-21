package com.undersnowmans.forum.controllers;

import com.undersnowmans.forum.repos.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thread")
public class ThreadController {

    @Autowired
    ThreadRepository threadRepository;
}
