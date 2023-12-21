package com.undersnowmans.forum.repos;

import com.undersnowmans.forum.models.Thread;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ThreadRepository extends CrudRepository<Thread, Long> {

    @Override
    List<Thread> findAll();
}
