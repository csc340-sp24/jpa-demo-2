package com.csc340.jpademo.task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author sunny
 */
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taskId;

    private String title;
    private String detail;
    private String status;
    private long goalNumber;

    public Task() {

    }

    public Task(String title, String detail, String status, long goalNumber) {
        this.title = title;
        this.detail = detail;
        this.status = status;
        this.goalNumber = goalNumber;
    }

    public Task(long taskId, String title, String detail, String status,
            long goalNumber) {
        this.taskId = taskId;
        this.title = title;
        this.detail = detail;
        this.status = status;
        this.goalNumber = goalNumber;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
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

    public long getGoalNumber() {
        return goalNumber;
    }

    public void setGoalNumber(long goalNumber) {
        this.goalNumber = goalNumber;
    }

}
