package com.undersnowmans.forum.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "thread_themes")
@Data
public class ThreadTheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String threadTheme;
}
