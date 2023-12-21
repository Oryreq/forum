package com.undersnowmans.forum.controllers;

import com.undersnowmans.forum.models.Message;
import com.undersnowmans.forum.models.Thread;
import com.undersnowmans.forum.repos.MessageRepository;
import com.undersnowmans.forum.repos.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/thread")
public class MessageController {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ThreadRepository threadRepository;

    @GetMapping
    public String getMessagesPage(Model model) {
        return "thread";
    }

    @GetMapping("/all")
    public List<Message> getAllMessagesById(@RequestParam Long id) {
        Optional<Thread> threadOptional = threadRepository.findById(id);
        return threadOptional.map(thread -> messageRepository.findAll()
                .stream()
                .filter(message -> message.getThread().equals(thread))
                .toList()).orElseGet(() -> List.of(new Message(1L, null, null, "Тред не найден (как?)")));

        // хз будет ли работать equals с lombok, вроде подхватил
        // если нет то попробуй @Data в Thread
    }
}
