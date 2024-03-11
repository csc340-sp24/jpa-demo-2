package com.csc340.jpademo.task;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sunny
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    public List<Task> findTasksByGoalNumber(long goalNumber);

}
