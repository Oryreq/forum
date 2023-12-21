package com.undersnowmans.forum.controllers;

import com.undersnowmans.forum.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    MessageRepository messageRepository;
}
