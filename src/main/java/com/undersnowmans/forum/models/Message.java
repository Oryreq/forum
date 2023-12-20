package com.undersnowmans.forum.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Thread.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "thread_id")
    private Thread thread;

    private String text;
}
