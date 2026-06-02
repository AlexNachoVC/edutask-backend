package com.edutask.infrastructure.persistence.repository;

import com.edutask.domain.model.Task;
import com.edutask.domain.repository.TaskRepository;
import com.edutask.infrastructure.persistence.entity.TaskEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TaskRepositoryImpl implements TaskRepository {

    @Override
    public List<Task> findByListId(Long listId) {
        return TaskEntity.findByListId(listId)
                .stream().map(this::toDomain).toList();
    }

    @Override
    public Optional<Task> findById(Long id) {
        return TaskEntity.<TaskEntity>findByIdOptional(id)
                .map(this::toDomain);
    }

    @Override
    @Transactional
    public Task save(Task task) {
        TaskEntity entity;
        if (task.getId() != null) {
            entity = TaskEntity.findById(task.getId());
            entity.title = task.getTitle();
            entity.description = task.getDescription();
            entity.completed = task.isCompleted();
            entity.dueDate = task.getDueDate();
            entity.priority = task.getPriority();
        } else {
            entity = new TaskEntity();
            entity.title = task.getTitle();
            entity.description = task.getDescription();
            entity.completed = task.isCompleted();
            entity.dueDate = task.getDueDate();
            entity.priority = task.getPriority();
            entity.listId = task.getListId();
            entity.persist();
        }
        return toDomain(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        TaskEntity.deleteById(id);
    }

    @Override
    public List<Task> searchByTitle(String query, String userId) {
        return TaskEntity.searchByTitle(query, userId)
                .stream().map(this::toDomain).toList();
    }

    @Override
    public List<Task> findDueToday(String userId) {
        return TaskEntity.findDueToday(userId)
                .stream().map(this::toDomain).toList();
    }

    private Task toDomain(TaskEntity entity) {
        Task task = new Task();
        task.setId(entity.id);
        task.setTitle(entity.title);
        task.setDescription(entity.description);
        task.setCompleted(entity.completed);
        task.setDueDate(entity.dueDate);
        task.setPriority(entity.priority);
        task.setListId(entity.listId);
        return task;
    }
}