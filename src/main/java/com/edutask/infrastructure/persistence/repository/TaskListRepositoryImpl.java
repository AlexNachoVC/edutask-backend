package com.edutask.infrastructure.persistence.repository;

import com.edutask.domain.model.TaskList;
import com.edutask.domain.repository.TaskListRepository;
import com.edutask.infrastructure.persistence.entity.TaskListEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TaskListRepositoryImpl implements TaskListRepository {

    @Override
    public List<TaskList> findByUserId(String userId) {
        return TaskListEntity.findByUserId(userId)
                .stream().map(this::toDomain).toList();
    }

    @Override
    public Optional<TaskList> findById(Long id) {
        return TaskListEntity.<TaskListEntity>findByIdOptional(id)
                .map(this::toDomain);
    }

    @Override
    @Transactional
    public TaskList save(TaskList taskList) {
        TaskListEntity entity;
        if (taskList.getId() != null) {
            entity = TaskListEntity.findById(taskList.getId());
            entity.title = taskList.getTitle();
            entity.description = taskList.getDescription();
            entity.accentColor = taskList.getAccentColor();
            entity.icon = taskList.getIcon();
        } else {
            entity = new TaskListEntity();
            entity.title = taskList.getTitle();
            entity.description = taskList.getDescription();
            entity.accentColor = taskList.getAccentColor();
            entity.icon = taskList.getIcon();
            entity.userId = taskList.getUserId();
            entity.persist();
        }
        return toDomain(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        TaskListEntity.deleteById(id);
    }

    @Override
    public List<TaskList> searchByTitle(String query, String userId) {
        return TaskListEntity.searchByTitle(query, userId)
                .stream().map(this::toDomain).toList();
    }

    private TaskList toDomain(TaskListEntity entity) {
        TaskList list = new TaskList();
        list.setId(entity.id);
        list.setTitle(entity.title);
        list.setDescription(entity.description);
        list.setAccentColor(entity.accentColor);
        list.setIcon(entity.icon);
        list.setUserId(entity.userId);
        return list;
    }
}