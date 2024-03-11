package com.csc340.jpademo.goal;

import com.csc340.jpademo.task.Task;
import com.csc340.jpademo.task.TaskRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sunny
 */
@Service
public class GoalService {

    @Autowired
    private GoalRepository repo;

    @Autowired
    private TaskRepository taskRepo;

    public Goal getGoalById(long id) {
        Goal goal = repo.getReferenceById(id);
        goal.setTasks(taskRepo.findTasksByGoalNumber(id));
        return goal;
    }

    public List<Goal> getAllGoals() {
        List<Goal> goalList = repo.findAll();
        for (Goal goal : goalList) {
            goal.setTasks(taskRepo.findTasksByGoalNumber(goal.getGoalId()));
        }
        return goalList;
    }

    public void deleteGoalByIdLazy(long id) {
        repo.deleteById(id);
    }

    public void deleteGoalByIdEager(long goalId) {
        List<Task> tasks = taskRepo.findTasksByGoalNumber(goalId);
        for (Task task : tasks) {
            taskRepo.deleteById(task.getTaskId());
        }
        repo.deleteById(goalId);
    }

    public void saveGoal(Goal goal) {
        repo.save(goal);
    }
    
    public List<Goal> getWeekGoals(){
        return repo.getWeekGoals();
    }
}
