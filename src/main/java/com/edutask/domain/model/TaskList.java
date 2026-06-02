package com.edutask.domain.model;

public class TaskList {
    private Long id;
    private String title;
    private String description;
    private String accentColor;
    private String icon;
    private String userId;

    public TaskList() {}

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getAccentColor() { return accentColor; }
    public void setAccentColor(String accentColor) { this.accentColor = accentColor; }
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
}