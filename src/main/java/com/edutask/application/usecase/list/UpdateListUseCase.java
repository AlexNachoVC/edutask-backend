package com.edutask.application.usecase.list;

import com.edutask.domain.model.TaskList;
import com.edutask.domain.repository.TaskListRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UpdateListUseCase {

    @Inject
    TaskListRepository taskListRepository;

    public record Input(Long id, String title, String description,
                        String accentColor, String icon) {}

    public TaskList execute(Input input) {
        TaskList list = taskListRepository.findById(input.id())
                .orElseThrow(() -> new NotFoundException("Lista no encontrada"));
        list.setTitle(input.title());
        list.setDescription(input.description());
        list.setAccentColor(input.accentColor());
        list.setIcon(input.icon());
        return taskListRepository.save(list);
    }
}