package com.edutask.application.usecase.list;

import com.edutask.domain.model.TaskList;
import com.edutask.domain.repository.TaskListRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class GetListsUseCase {

    @Inject
    TaskListRepository taskListRepository;

    public List<TaskList> execute(String userId) {
        return taskListRepository.findByUserId(userId);
    }
}