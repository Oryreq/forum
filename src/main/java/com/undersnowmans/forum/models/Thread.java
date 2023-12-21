package com.undersnowmans.forum.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Thread {

    public Thread(Long id, String title, Long firstMessageId, String createdTime) {
        this.id = id;
        this.title = title;
        this.firstMessageId = firstMessageId;
        this.createdTime = createdTime;
        this.messages = null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Long firstMessageId;

    private String createdTime;

    @OneToMany(targetEntity = Message.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "thread_id")
    private List<Message> messages;

}
