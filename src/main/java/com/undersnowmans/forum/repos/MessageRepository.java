package com.undersnowmans.forum.repos;

import com.undersnowmans.forum.models.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
