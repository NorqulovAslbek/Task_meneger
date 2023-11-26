package controller;

import dto.Task;
import enums.Type;
import service.TaskService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;


public class MainController {
    private Scanner scannerString = new Scanner(System.in);
    private Scanner scannerInt = new Scanner(System.in);
    private TaskService taskService = new TaskService();


    public void start() {
        boolean k = true;
        while (k) {
            showMenu();
            switch (getAction()) {
                case 1 -> createTask();
                case 2 -> activeTaskList();
                case 3 -> finishedTask();
                case 4 -> updateTask();
                case 5 -> deleteTask();
                case 6 -> markAsDone();
                default -> {
                    System.out.println("you have selected another menu!!");
                    k = false;
                }

            }
        }
    }

    private void markAsDone() {
        System.out.println("Enter id:");
        int id=scannerInt.nextInt();
        taskService.doneTask(id,Type.DONE);
    }

    private void deleteTask() {
        System.out.print("Enter by id:");
        int id = scannerInt.nextInt();
        taskService.deleteTask(id);
    }

    private void updateTask() {
        System.out.print("Enter by id:");
        int id = scannerInt.nextInt();
        Task task = new Task();
        System.out.print("Enter content:");
        String content = scannerString.nextLine();
        System.out.print("Enter title:");
        String title = scannerString.nextLine();
        task.setId(id);
        task.setContent(content);
        task.setTitle(title);
        task.setCreated_date(LocalDateTime.now());
        task.setType(Type.ACTIVE);
        taskService.updateTask(id, task);
    }

    private void finishedTask() {
        List<Task> activeTask = taskService.finishedTask();
        System.out.println(activeTask);
    }

    private void activeTaskList() {
        List<Task> activeTask = taskService.activeTasks();
        System.out.println(activeTask);
    }

    private void createTask() {
        Task task = new Task();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter content:");
        String content = scanner.nextLine();
        System.out.print("Enter title:");
        String title = scanner.nextLine();
        task.setContent(content);
        task.setTitle(title);
        task.setType(Type.ACTIVE);
        task.setCreated_date(LocalDateTime.now());
        taskService.createTaskService(task);
    }

    private void showMenu() {
        System.out.print("""
                1.Create task.
                2.Active Task List.
                3.Finished Task List.
                4.Update (by id).
                5.Delete by id.
                6.Mark as Done.
                 """);
    }

    private int getAction() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number:");
        return scanner.nextInt();
    }
}


