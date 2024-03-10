package com.csc340.jpademo.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sunny
 */
@Repository
public class TaskRepository {

    @Autowired
    NamedParameterJdbcTemplate template;

    List<Task> findAll() {
        String query = "select task_id, title, detail, status, goal_number from task";
        return template.query(query,
                (result, rowNum)
                -> new Task(result.getLong("task_id"),
                        result.getString("title"),
                        result.getString("detail"),
                        result.getString("status"),
                        result.getLong("goal_number")));
    }

    public List<Task> findTasksByGoalNumber(long goalNumber) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(
                "goalNumber", goalNumber);
        String query = "select task_id, title, detail, status, goal_number from task "
                + "where goal_number=:goalNumber";
        return template.query(query, namedParameters,
                (result, rowNum)
                -> new Task(result.getLong("task_id"),
                        result.getString("title"),
                        result.getString("detail"),
                        result.getString("status"),
                        result.getLong("goal_number")));
    }

    public Task getTaskById(long taskId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("taskId", taskId);
        String query = "select * from task where task_id=:taskId ";
        return template.queryForObject(query, namedParameters,
                BeanPropertyRowMapper.newInstance(Task.class));
    }

    public int saveTask(Task task) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("title", task.getTitle());
        paramMap.put("detail", task.getDetail());
        paramMap.put("status", task.getStatus());
        paramMap.put("goal_number", task.getGoalNumber());
        String query = "INSERT INTO task(title,detail,status,goal_number) "
                + "VALUES(:title, :detail, :status, :goal_number)";
        return template.update(query, paramMap);
    }

    public void deleteTaskByID(long taskId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("taskId", taskId);
        String query = "delete from task where task_id=:taskId";
        template.update(query, namedParameters);
    }

    public void updateTask(Task task) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("task_id", task.getTaskId());
        paramMap.put("title", task.getTitle());
        paramMap.put("detail", task.getDetail());
        paramMap.put("status", task.getStatus());
        paramMap.put("goal_number", task.getGoalNumber());
        String query = "update task set title=:title, detail=:detail, "
                + "status=:status, goal_number=:goal_number where task_id=:task_id ";
        template.update(query, paramMap);
    }
}
