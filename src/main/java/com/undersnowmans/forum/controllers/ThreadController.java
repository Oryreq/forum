package com.undersnowmans.forum.controllers;

import com.undersnowmans.forum.models.Message;
import com.undersnowmans.forum.models.Thread;
import com.undersnowmans.forum.models.ThreadWithMessage;
import com.undersnowmans.forum.repos.MessageRepository;
import com.undersnowmans.forum.repos.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/threads")
public class ThreadController {

    @Autowired
    ThreadRepository threadRepository;

    @Autowired
    MessageRepository messageRepository;

    @GetMapping
    public String getThreadsPage(Model model) {
        var threads = threadRepository.findAll();
        var firstMessages = threads.stream()
                                                .map(Thread::getFirstMessageId)
                                                .map(id -> messageRepository.findById(id).get()).toList();

        var threadsWithFirstMessage = IntStream.range(0, threads.size())
                .mapToObj(i -> new ThreadWithMessage(threads.get(i), firstMessages.get(i)))
                .toList();
        model.addAttribute("threads", threadsWithFirstMessage);
        //var threads = List.of(new Thread(1L, "Cars", 1L, "23.02.2022"),
        //new Thread(2L, "IT", 2L, "24.08.2005"));

        // TO-DO: Отправлять еще и данные об авторе треда (засунуть их в threads, можно через добавление связи с пользователем, как будто бы даже логичнее на самом деле)
        //model.addAttribute("threads", threads);
        return "threads";
    }

    @GetMapping("/{id}")
    public String getMessagesPage(Model model, @PathVariable(value= "id") Long id) {
        Optional<Thread> threadOptional = threadRepository.findById(id);

        /**
         * Требует thread
         */

        if (threadOptional.isEmpty()) {
            ArrayList<Message> messages = new ArrayList<>();

            messages.add(new Message());

            Thread thread = new Thread(id, "Test Title", 1L, "12.23.1220", messages);
            model.addAttribute("messages", "");
        } else {
            model.addAttribute("messages", threadOptional.get().getMessages());
            model.addAttribute("createdAt", threadOptional.get().getCreatedTime());
            model.addAttribute("threadTitle", threadOptional.get().getTitle());
        }
        model.addAttribute("id", id);


        return "thread";
    }

    @GetMapping("/create")
    public String createThreadPage(Model model) {
        return "create-thread";
    }

    @PostMapping("/create") // TO-DO: СДЕЛАТЬ ОПРЕДЕЛЕНИЕ ПОЛЬЗОВАТЕЛЯ ПО ТОКЕНУ ( @CookieValue(value = "token")String token если ты будешь хранить токен в бд а не в сессии)
    public String createThread(Model model, @RequestParam String title, @RequestParam String message) {

        // TO-DO: Создание треда и первого сообщения
        var firstMessage = new Message();
        firstMessage.setText(message);

        var thread = new Thread();
        thread.setTitle(title);
        thread.setCreatedTime(Instant.now().toString());
        thread.setFirstMessageId(firstMessage.getId());

        firstMessage.setThread(thread);

        threadRepository.save(thread);
        messageRepository.save(firstMessage);

        thread.setFirstMessageId(new LinkedList<>(messageRepository.findAll()).getLast().getId());
        threadRepository.save(thread);
        return "redirect:/threads";
    }
}
