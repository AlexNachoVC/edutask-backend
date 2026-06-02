package com.edutask.application.usecase.task;

import com.edutask.domain.model.Task;
import com.edutask.domain.repository.TaskRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import java.time.LocalDate;

@ApplicationScoped
public class UpdateTaskUseCase {

    @Inject
    TaskRepository taskRepository;

    public record Input(Long id, String title, String description,
                        boolean completed, LocalDate dueDate, String priority) {}

    public Task execute(Input input) {
        Task task = taskRepository.findById(input.id())
                .orElseThrow(() -> new NotFoundException("Tarea no encontrada"));
        task.setTitle(input.title());
        task.setDescription(input.description());
        task.setCompleted(input.completed());
        task.setDueDate(input.dueDate());
        task.setPriority(input.priority());
        return taskRepository.save(task);
    }
}