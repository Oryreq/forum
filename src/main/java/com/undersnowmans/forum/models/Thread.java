package com.undersnowmans.forum.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "threads")
@Data
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long threadThemeId;
}
