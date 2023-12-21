package com.undersnowmans.forum.repos;

import com.undersnowmans.forum.models.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    @Override
    List<Message> findAll();
}
