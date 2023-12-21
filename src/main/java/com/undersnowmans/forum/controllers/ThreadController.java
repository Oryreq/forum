package com.undersnowmans.forum.controllers;

import com.undersnowmans.forum.models.Message;
import com.undersnowmans.forum.models.Thread;
import com.undersnowmans.forum.repos.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/threads")
public class ThreadController {

    @Autowired
    ThreadRepository threadRepository;

    @GetMapping
    public String getThreadsPage(Model model) {
        var threads = List.of(new Thread(1L, "Sex", 1L, "23.02.2022"),
                new Thread(2L, "IT", 2L, "24.08.2005"));

        // TO-DO: Отправлять еще и данные об авторе треда (засунуть их в threads, можно через добавление связи с пользователем, как будто бы даже логичнее на самом деле)
        model.addAttribute("threads", threads);
        return "threads";
    }

    @GetMapping("/{id}")
    public String getMessagesPage(Model model, @PathVariable(value= "id" )Long id) {
        Optional<Thread> threadOptional = threadRepository.findById(id);

        /**
         * Требует thread
         */

        if (threadOptional.isEmpty()) {
            ArrayList<Message> messages = new ArrayList<>();

            messages.add(new Message());

            Thread thread = new Thread(id, "Test Title", 1L, "12.23.1220", messages);
            model.addAttribute("thread", thread);
        } else {
            model.addAttribute("thread", threadOptional.get());
        }

        return "thread";
    }

    @GetMapping("/create")
    public String createThreadPage(Model model) {
        return "create-thread";
    }

    @PostMapping("/create") // TO-DO: СДЕЛАТЬ ОПРЕДЕЛЕНИЕ ПОЛЬЗОВАТЕЛЯ ПО ТОКЕНУ ( @CookieValue(value = "token")String token если ты будешь хранить токен в бд а не в сессии)
    public String createThread(Model model, @RequestParam String title, @RequestParam String message) {

        // TO-DO: Создание треда и первого сообщения

        return "index";
    }
}
