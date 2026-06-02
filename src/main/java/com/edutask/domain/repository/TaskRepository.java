package com.edutask.domain.repository;

import com.edutask.domain.model.Task;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findByListId(Long listId);
    Optional<Task> findById(Long id);
    Task save(Task task);
    void delete(Long id);
    List<Task> searchByTitle(String query, String userId);
    List<Task> findDueToday(String userId);
}