package dev.adonogtx.model;

import java.time.LocalDate;
import java.util.Objects;


public class Task implements Comparable<Task> {


    public enum TASK_PRIORITY{
        LOW,
        MEDIUM,
        HIGH
    }

    public enum TASK_STATUS{
        PENDING,
        COMPLETE
    }

    private final Long id;
    String title;
    String description;
    TASK_PRIORITY priority;
    LocalDate date;
    TASK_STATUS status;

    public Task(Long id, String title, String description, TASK_PRIORITY priority, LocalDate date, TASK_STATUS status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.date = date;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TASK_PRIORITY getPriority() {
        return priority;
    }

    public LocalDate getDate() {
        return date;
    }

    public TASK_STATUS getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format("%-5s - %-20s - %-30s - %-12s - %-10s - %-10s",
                id, title, description, date, priority, status);
    }

    @Override
    public int compareTo(Task o) {
        return this.id.compareTo(o.id);
    }
}
