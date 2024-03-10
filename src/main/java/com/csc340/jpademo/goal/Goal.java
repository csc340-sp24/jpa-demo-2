package com.csc340.jpademo.goal;

import com.csc340.jpademo.task.Task;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sunny
 */
@Entity
@Table(name = "goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long goalId;

    private String title;
    private String detail;
    private String status;
    
    @Temporal(TemporalType.DATE)
    private Date deadline;
    
    @Transient
    private List<Task> tasks;

    public Goal() {
    }

    public Goal(String title, String detail, String status, Date deadline) {
        this.title = title;
        this.detail = detail;
        this.status = status;
        this.deadline = deadline;
    }

    public Goal(String title, String detail, Date deadline, String status,
            List<Task> tasks) {
        this.title = title;
        this.detail = detail;
        this.status = status;
        this.deadline = deadline;
        this.tasks = tasks;
    }

    

    public long getGoalId() {
        return goalId;
    }

    public void setGoalId(long goalId) {
        this.goalId = goalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
      public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
