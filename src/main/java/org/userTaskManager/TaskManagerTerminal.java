package org.userTaskManager;

import org.userTaskManager.dao.TaskDAO;
import org.userTaskManager.entity.Task;

import java.util.Scanner;

public class TaskManagerTerminal {

    private final TaskDAO taskDAO;
    private final Scanner sc;
    private boolean quit;

    public TaskManagerTerminal() {
        taskDAO = new TaskDAO();
        sc = new Scanner(System.in);
        quit = false;
    }

    public void mainScreen() {
        while (!quit) {
            System.out.println("1 to add a task, 2 to mark as complete by id, 3 to remove by id, \n" +
                    "4 to get task by id, 5 to get a list, 6 to quit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> createTask();
                case 2 -> markAsComplete();
                case 3 -> removeTask();
                case 4 -> getTaskWithId();
                case 5 -> getTaskList();
                case 6 -> quit = true;
                default -> System.out.println("Invalid choice! Try again:");
            }
        }
        System.exit(0);
    }

    public void createTask() {
        System.out.println("Enter task title:");
        String title = sc.nextLine();
        System.out.println("Enter task description: (!s to skip)");
        String description = sc.nextLine();
        if (description.strip().equalsIgnoreCase("!s")) {
            description = "";
        }
        Task newTask = new Task(title.strip(), description.strip());
        taskDAO.addTask(newTask);
        System.out.println("Task id is: " + newTask.getId());
    }

    public void markAsComplete() {
        System.out.println("Enter ID to mark task as complete");
        long id = sc.nextLong();
        taskDAO.markAsComplete(id);
        System.out.println("Marked as complete");
    }

    public void removeTask() {
        System.out.println("Enter ID to remove task with:");
        long id = sc.nextLong();
        taskDAO.removeTaskById(id);
        System.out.println("Task removed");
    }

    public void getTaskWithId() {
        System.out.println("Enter ID to get task with:");
        long id = sc.nextLong();
        Task task = taskDAO.getTaskById(id);
        System.out.println(task.getTitle());
        System.out.println(task.getDescription());
        System.out.println(task.isCompleted());
    }

    public void getTaskList() {
        taskDAO.getTaskList().forEach(System.out::println);
    }

    public void init() {
        System.out.println("Welcome!");
        mainScreen();
    }

}
