package dev.adonogtx.service;

import dev.adonogtx.model.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class FilterByPriority implements Comparator<Task>{
    @Override
    public int compare(Task o1, Task o2) {
        return o1.getPriority().compareTo(o2.getPriority());
    }
}

class FilterByDate implements Comparator<Task>{
    @Override
    public int compare(Task o1, Task o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}

public class SchedulerService {

    List<Task> taskList = new ArrayList<>();


    public boolean addTask( String title, String description, String priority, String date) {

        Long newId = ThreadLocalRandom.current().nextLong(1, 100_000_000);

        if (!isIdAvailable(newId)) return false;

        if (!isValidDate(date)) return false;

        Task.TASK_PRIORITY taskPriority = retrievePriority(priority);

        LocalDate localDate = LocalDate.parse(date);

        if (title == null || title.isBlank() || description == null || description.isBlank()) {
            return false;
        }

        taskList.add(new Task(newId, title, description, taskPriority, localDate, Task.TASK_STATUS.PENDING));

        return true;
    }

    public void listTasksByPriority() {

        taskList.sort(new FilterByPriority());
        for (Task task : taskList) {
            System.out.println(task);
            // System.out.println(task.getId() + " " + task.getTitle() + " " + task.getDescription() + " " + task.getDate() + " " + task.getPriority() + " " + task.getStatus());
        }

    }

    public void listTasksByDate() {

        taskList.sort(new FilterByDate());
        for (Task task : taskList) {
            System.out.println(task);
        }

    }

    public boolean searchTask() {
        return false;
    }

    public boolean removeTask() {
        return false;
    }

    public boolean completeTask() {
        return false;
    }

    public boolean executeNextTask() {
        return false;
    }

    public boolean isIdAvailable(Long createdId) {

        for (Task task : taskList) {
            if (task.getId().equals(createdId)) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidDate(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date");
            return false;
        }

        return true;
    }

    private Task.TASK_PRIORITY retrievePriority(String priority){

        switch (priority.toLowerCase()){
            case "low" -> {
                return Task.TASK_PRIORITY.LOW;
            }
            case "medium" -> {
                return Task.TASK_PRIORITY.MEDIUM;
            }
            case "high" -> {
                return Task.TASK_PRIORITY.HIGH;
            }
        }

        return  Task.TASK_PRIORITY.LOW;

    }


}
