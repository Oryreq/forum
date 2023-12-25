package com.undersnowmans.forum.controllers;

import com.undersnowmans.forum.models.Message;
import com.undersnowmans.forum.repos.MessageRepository;
import com.undersnowmans.forum.repos.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ThreadRepository threadRepository;

    @PostMapping("/create/{id}")
    public String createMessage(Model model, @PathVariable(value= "id") Long id, @RequestParam String message) {
        // TO-DO: Создание треда и первого сообщения
        var thread = threadRepository.findById(id).get();

        var firstMessage = new Message();
        firstMessage.setText(message);
        firstMessage.setThread(thread);
        messageRepository.save(firstMessage);

        model.addAttribute("thread", thread);
        return "redirect:/threads/" + id;
    }
}
