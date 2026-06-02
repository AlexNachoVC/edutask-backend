package com.edutask.application.usecase.list;

import com.edutask.domain.model.TaskList;
import com.edutask.domain.repository.TaskListRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateListUseCase {

    @Inject
    TaskListRepository taskListRepository;

    public record Input(String title, String description,
                        String accentColor, String icon, String userId) {}

    public TaskList execute(Input input) {
        TaskList list = new TaskList();
        list.setTitle(input.title());
        list.setDescription(input.description());
        list.setAccentColor(input.accentColor());
        list.setIcon(input.icon());
        list.setUserId(input.userId());
        return taskListRepository.save(list);
    }
}