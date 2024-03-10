package com.csc340.jpademo.task;

import com.csc340.jpademo.goal.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author sunny
 */
@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private GoalService goalService;

    @GetMapping("/all")
    public String getTasks(Model model) {
        model.addAttribute("taskList", taskService.getAllTasks());
        return "task/list-tasks";
    }

    @GetMapping("/id={taskId}")
    public String getTask(@PathVariable long taskId, Model model) {
        model.addAttribute("task", taskService.getTaskById(taskId));
        return "task/task-detail";
    }

    @GetMapping("/goal={goalId}")
    public String getTasksByGoal(@PathVariable long goalId, Model model) {
        model.addAttribute("tasks", taskService.getTasksByGoalNumber(goalId));
        return "task/list-tasks";
    }

    @GetMapping("/delete/id={taskId}")
    public String deleteTask(@PathVariable long taskId, Model model) {
        taskService.deleteTaskById(taskId);
        return "redirect:/goal/all";
    }

    @PostMapping("/create")
    public String createTask(Task task) {
        taskService.saveTask(task);
        return "redirect:/goal/all";
    }

    @PostMapping("/update/id={taskId}")
    public String updateTask(@PathVariable long taskId, Task task) {
        taskService.updateTask(task);
        return "redirect:/goal/all";
    }

    @GetMapping("/create")
    public String newTaskForm(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        model.addAttribute("isUpdate", false);
        model.addAttribute("goalList", goalService.getAllGoals());
        return "task/update-task";
    }

    @GetMapping("/update/id={taskId}")
    public String updateTaskForm(@PathVariable long taskId, Model model) {
        model.addAttribute("task", taskService.getTaskById(taskId));
        model.addAttribute("isUpdate", true);
        model.addAttribute("goalList", goalService.getAllGoals());
        return "task/update-task";
    }
}
