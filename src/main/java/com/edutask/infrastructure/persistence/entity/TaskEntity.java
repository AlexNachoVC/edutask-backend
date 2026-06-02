package com.edutask.infrastructure.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tasks")
public class TaskEntity extends PanacheEntity {

    @Column(nullable = false)
    public String title;

    public String description;
    public boolean completed = false;
    public LocalDate dueDate;
    public String priority = "MEDIUM";

    @Column(nullable = false)
    public Long listId;

    public static List<TaskEntity> findByListId(Long listId) {
        return list("listId", listId);
    }

    public static List<TaskEntity> searchByTitle(String query, String userId) {
        return list("""
            listId in (select tl.id from TaskListEntity tl where tl.userId = ?1)
            and lower(title) like ?2
            """, userId, "%" + query.toLowerCase() + "%");
    }

    public static List<TaskEntity> findDueToday(String userId) {
        return list("""
            listId in (select tl.id from TaskListEntity tl where tl.userId = ?1)
            and dueDate = ?2 and completed = false
            """, userId, LocalDate.now());
    }
}