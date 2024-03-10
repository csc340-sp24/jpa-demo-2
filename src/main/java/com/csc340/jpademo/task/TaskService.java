package com.csc340.jpademo.task;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sunny
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository repo;

    public Task getTaskById(long id) {
        return repo.getTaskById(id);
    }

    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    public void saveTask(Task task) {
        repo.saveTask(task);
    }

    public void updateTask(Task task) {       
        repo.updateTask(task);
    }

    public void deleteTaskById(long id) {
        repo.deleteTaskByID(id);
    }

    public List<Task> getTasksByGoalNumber(long goalId) {
        return repo.findTasksByGoalNumber(goalId);
    }
}
