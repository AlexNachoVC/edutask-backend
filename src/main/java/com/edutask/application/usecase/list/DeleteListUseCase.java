package com.edutask.application.usecase.list;

import com.edutask.domain.repository.TaskListRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteListUseCase {

    @Inject
    TaskListRepository taskListRepository;

    public void execute(Long id) {
        taskListRepository.delete(id);
    }
}