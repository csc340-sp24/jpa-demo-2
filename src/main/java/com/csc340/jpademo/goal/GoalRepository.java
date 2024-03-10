package com.csc340.jpademo.goal;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author sunny
 */
public interface GoalRepository extends JpaRepository<Goal, Long> {

    @Query(value = "select * from Goal g where g.deadline "
            + "between CURDATE() and DATE_ADD(CURDATE(), INTERVAL 7 DAY)",
            nativeQuery = true)
    public List<Goal> getWeekGoals();
}
