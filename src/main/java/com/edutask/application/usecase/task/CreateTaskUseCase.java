package com.edutask.application.usecase.task;

import com.edutask.domain.model.Task;
import com.edutask.domain.repository.TaskRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.LocalDate;

@ApplicationScoped
public class CreateTaskUseCase {

    @Inject
    TaskRepository taskRepository;

    public record Input(String title, String description,
                        LocalDate dueDate, String priority, Long listId) {}

    public Task execute(Input input) {
        Task task = new Task();
        task.setTitle(input.title());
        task.setDescription(input.description());
        task.setDueDate(input.dueDate());
        task.setPriority(input.priority() != null ? input.priority() : "MEDIUM");
        task.setListId(input.listId());
        task.setCompleted(false);
        return taskRepository.save(task);
    }
}