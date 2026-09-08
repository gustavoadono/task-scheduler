package dev.adonogtx.ui;

import dev.adonogtx.model.Task;
import dev.adonogtx.model.TaskDomainException;
import dev.adonogtx.service.SchedulerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {

    private final Scanner scanner;
    SchedulerService service = new SchedulerService();
    String options;
    int choice;
    boolean isApplicationRunning = true;


    public ConsoleApplication(Scanner scanner) {
        this.scanner = scanner;
    }

    public void runApplication() {

        while (isApplicationRunning) {

            showMenuOptions();

            options = scanner.nextLine();
            choice = Integer.parseInt(options);

            switch (choice) {
                case 0 -> isApplicationRunning = false;
                case 1 -> addTask();
                case 2 -> listTasks();
                case 3 -> searchTask();
                case 4 -> removeTask();
                case 5 -> completeTask();
                case 6 -> executeNextTask();
            }

        }

    }

    void showMenuOptions() {
        System.out.println();
        System.out.println("1. Add task");
        System.out.println("2. List tasks");
        System.out.println("3. Search task");
        System.out.println("4. Remove task");
        System.out.println("5. Complete task");
        System.out.println("6. Execute next task");
        System.out.println("0. Exit");
        System.out.println("Choose an option: ");
    }

    void addTask() {
        System.out.println("Title: ");
        String title = scanner.nextLine();
        System.out.println("Description: ");
        String description = scanner.nextLine();
        System.out.println("Priority: ");
        String priority = scanner.nextLine();
        System.out.println("Scheduled date(yyyy-MM-dd): ");
        String date = scanner.nextLine();


        try {
            service.addTask(title, description, priority, date);
            System.out.println("Task added successfully!");
            service.printLastTaskId();

        } catch (TaskDomainException e) {
            System.err.println("Error Code: " + e.getErrorCode().getCode());
            System.err.println("Message: " + e.getMessage());
        }


    }

    void listTasks() {
        System.out.println("1. List by priority");
        System.out.println("2. List by date");
        options = scanner.nextLine();
        int taskMenu = Integer.parseInt(options);

        List<Task> taskListShow;

        if (taskMenu == 1) {
            taskListShow = service.listTasksByPriority();

        } else if (taskMenu == 2) {
            taskListShow = service.listTasksByDate();
        } else {
            System.out.println("Invalid option");
            return;
        }

        if (taskListShow.isEmpty()) {
            System.out.println("No tasks registered.");
            return;
        }


        for (Task task : taskListShow) {
            printTask(task);
        }

    }

    void searchTask() {

        System.out.println("Search: ");
        String search = scanner.nextLine();

        List<Task> tasks = service.searchTask(search);

        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        for (Task task : tasks) {
            printTask(task);
        }
    }

    void removeTask() {

    }

    void completeTask() {

    }

    void executeNextTask() {

    }

    public static void printTask(Task task) {
        System.out.println("ID: " + task.getId());
        System.out.println("Title: " + task.getTitle());
        System.out.println("Description: " + task.getDescription());
        System.out.println("Priority: " + task.getPriority().name());
        System.out.println("Scheduled date: " + task.getDate());
        System.out.println("Status: " + task.getStatus().name());
    }

}
