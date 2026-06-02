package com.edutask.application.usecase.task;

import com.edutask.domain.repository.TaskRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteTaskUseCase {

    @Inject
    TaskRepository taskRepository;

    public void execute(Long id) {
        taskRepository.delete(id);
    }
}