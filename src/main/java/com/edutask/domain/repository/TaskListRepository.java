package com.edutask.domain.repository;

import com.edutask.domain.model.TaskList;
import java.util.List;
import java.util.Optional;

public interface TaskListRepository {
    List<TaskList> findByUserId(String userId);
    Optional<TaskList> findById(Long id);
    TaskList save(TaskList taskList);
    void delete(Long id);
    List<TaskList> searchByTitle(String query, String userId);
}