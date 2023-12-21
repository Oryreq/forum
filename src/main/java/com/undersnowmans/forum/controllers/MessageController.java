package com.undersnowmans.forum.controllers;

import com.undersnowmans.forum.models.Message;
import com.undersnowmans.forum.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/thread")
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @GetMapping
    public String getMessagesInThread(Model model) {
        return "thread";
    }
}
