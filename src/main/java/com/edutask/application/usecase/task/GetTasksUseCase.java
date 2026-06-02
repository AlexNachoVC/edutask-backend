package com.edutask.application.usecase.task;

import com.edutask.domain.model.Task;
import com.edutask.domain.repository.TaskRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class GetTasksUseCase {

    @Inject
    TaskRepository taskRepository;

    public List<Task> execute(Long listId) {
        return taskRepository.findByListId(listId);
    }
}