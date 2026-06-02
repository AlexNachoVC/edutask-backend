package com.edutask.interfaces.rest.dto;

import java.time.LocalDate;

public class TaskDto {
    public String title;
    public String description;
    public boolean completed;
    public LocalDate dueDate;
    public String priority;
}