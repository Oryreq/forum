package com.undersnowmans.forum.repos;

import com.undersnowmans.forum.models.Thread;
import org.springframework.data.repository.CrudRepository;

public interface ThreadRepository extends CrudRepository<Thread, Long> {
}
