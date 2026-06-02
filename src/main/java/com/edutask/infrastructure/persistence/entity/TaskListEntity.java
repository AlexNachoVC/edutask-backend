package com.edutask.infrastructure.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "task_lists")
public class TaskListEntity extends PanacheEntity {

    @Column(nullable = false)
    public String title;

    public String description;
    public String accentColor;
    public String icon;

    @Column(nullable = false)
    public String userId;

    public static List<TaskListEntity> findByUserId(String userId) {
        return list("userId", userId);
    }

    public static List<TaskListEntity> searchByTitle(String query, String userId) {
        return list("userId = ?1 and lower(title) like ?2", userId, "%" + query.toLowerCase() + "%");
    }
}