package dev.adonogtx.service;

import dev.adonogtx.model.Task;
import dev.adonogtx.model.TaskDomainException;
import dev.adonogtx.model.TaskErrorCode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


class FilterByPriority implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        return o2.getPriority().compareTo(o1.getPriority());
    }
}

class FilterByDate implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}

public class SchedulerService {

    List<Task> taskList = new ArrayList<>();
    private Long id = 1L;

    public void addTask(String title, String description, String priority, String date) {


        if (title == null || title.isBlank()) {
            throw new TaskDomainException(TaskErrorCode.TITLE_REQUIRED);
        }

        if (description == null) {
            throw new TaskDomainException(TaskErrorCode.DESCRIPTION_REQUIRED);
        }


        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new TaskDomainException(TaskErrorCode.INVALID_DATE_FORMAT, date);
        }


        Task.TASK_PRIORITY taskPriority = retrievePriority(priority);


        taskList.add(new Task(this.id, title, description, taskPriority, localDate, Task.TASK_STATUS.PENDING));
        this.id += 1;
    }

    private Task.TASK_PRIORITY retrievePriority(String priority) {
        try {
            return Task.TASK_PRIORITY.valueOf(priority.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new TaskDomainException(TaskErrorCode.INVALID_PRIORITY, priority);
        }
    }


    public List<Task> listTasksByPriority() {
        List<Task> tasks = new ArrayList<>(taskList);
        tasks.sort(new FilterByPriority());
        return tasks;
    }

    public List<Task> listTasksByDate() {
        List<Task> tasks = new ArrayList<>(taskList);
        tasks.sort(new FilterByDate());
        return tasks;
    }


    public List<Task> searchTask(String search) {

        List<Task> matchingTasks = new ArrayList<>();

        for (Task task : taskList) {

            boolean idMatches = task.getId().toString().equals(search);

            boolean titleMatches = task.getTitle()
                    .toLowerCase()
                    .contains(search.toLowerCase());

            boolean descriptionMatches = task.getDescription()
                    .toLowerCase()
                    .contains(search.toLowerCase());

            if (idMatches || titleMatches || descriptionMatches) {
                matchingTasks.add(task);
            }

        }

        return matchingTasks;
    }

    public boolean removeTask(String id) {

        boolean removed = false;


        Iterator<Task> taskIterator = taskList.iterator();

        while (taskIterator.hasNext()) {
            if (taskIterator.next().getId().toString().equals(id)) {
                taskIterator.remove();
                removed = true;
                break;
            }
        }


        return removed;
    }

    public void completeTask(String taskId) {


        for (Task task : taskList) {
            if (task.getId().toString().equals(taskId)){
                if(task.getStatus() == Task.TASK_STATUS.PENDING)
                {
                    task.setStatus(Task.TASK_STATUS.COMPLETE);
                }
                else{
                    throw new TaskDomainException(TaskErrorCode.ALREADY_COMPLETED);
                }
                return;
            }
        }

        throw new TaskDomainException(TaskErrorCode.TASK_NOT_FOUND, taskId);

    }

    public boolean executeNextTask() {

        return false;
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


    public void printLastTaskId() {
        System.out.println("ID: " + taskList.getLast().getId());
    }



}
