package com.csc340.jpademo.goal;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author sunny
 */
@RequestMapping("/goal")
@Controller
public class GoalController {

    @Autowired
    private GoalService service;

    @GetMapping("/all")
    public String getGoals(Model model) {
        model.addAttribute("goalList", service.getAllGoals());
        return "goal/list-goals";
    }
    
     @GetMapping("/week")
    public String getWeekGoals(Model model) {
        model.addAttribute("goalList", service.getWeekGoals());
         model.addAttribute("isWeekly", true);
        return "goal/list-goals";
    }

    @GetMapping("/id={goalId}")
    public String getGoal(@PathVariable long goalId, Model model) {
        model.addAttribute("goal", service.getGoalById(goalId));
        return "goal/goal-detail";
    }

    @GetMapping("/delete/id={goalId}")
    public String deleteGoal(@PathVariable long goalId, Model model) {
        service.deleteGoalByIdLazy(goalId);
        return "redirect:/goal/all";
    }

    @GetMapping("/delete/eager/id={goalId}")
    public String deleteGoalEager(@PathVariable long goalId, Model model) {
        service.deleteGoalByIdEager(goalId);
        return "redirect:/goal/all";
    }

    @PostMapping("/create")
    public String createGoal(Goal goal) {
        service.saveGoal(goal);
        return "redirect:/goal/all";
    }

    @PostMapping("/update/id={goalId}")
    public String updateGoal(@PathVariable long goalId, Goal goal) {       
        service.saveGoal(goal);
        return "redirect:/goal/all";
    }

    @GetMapping("/update-status/id={goalId}&status={status}")
    public String updateGoalStatus(@PathVariable long goalId,
            @PathVariable String status) {
        Goal existing = service.getGoalById(goalId);
        existing.setStatus(status);
        service.saveGoal(existing);
        return "redirect:/goal/all";
    }

    @GetMapping("/create")
    public String newGoalForm(Model model) {
        Goal goal = new Goal();
        model.addAttribute("goal", goal);
        model.addAttribute("isUpdate", false);
        return "goal/update-goal";
    }

    @GetMapping("/update/id={goalId}")
    public String updateGoalForm(@PathVariable long goalId, Model model) {
        model.addAttribute("goal", service.getGoalById(goalId));
        model.addAttribute("isUpdate", true);
        model.addAttribute("goalList", service.getAllGoals());
        return "goal/update-goal";
    }
    
    @InitBinder
    public void dateBinder(WebDataBinder binder) {
        // 2023-04-19 02:14:25
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,
                false));
    }
}