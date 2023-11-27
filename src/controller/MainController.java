package controller;

import dto.Task;
import enums.Type;
import service.TaskService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;


public class MainController {
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
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
        List<Task> taskList = taskService.finishedTask();
        int count = 0;
        System.out.println(BLUE + "---------------------------------------------------------------------------------------------------------------" + ANSI_RESET);
        System.out.print(BLUE + "| " + ANSI_RESET + RED + "id" + ANSI_RESET + BLUE + " |" + ANSI_RESET + RED + "    Content     " + ANSI_RESET + BLUE + "|      " + ANSI_RESET);
        System.out.print(RED + "  Title           " + ANSI_RESET + BLUE + "|      " + ANSI_RESET);
        System.out.print(RED + "   Created_date         " + ANSI_RESET + BLUE + "|        " + ANSI_RESET);
        System.out.println(RED + "Finished_date" + ANSI_RESET + BLUE + "          |   " + ANSI_RESET);
        System.out.print(BLUE + "---------------------------------------------------------------------------------------------------------------\n" + ANSI_RESET);
        for (Task task : taskList) {
            count = task.getId();
            System.out.print(BLUE + "|" + (count <= 9 ? (" " + RED + count + ANSI_RESET + "  ") : (count > 9 & count < 100) ? ("|" + RED + count + ANSI_RESET + " ") : ("|" + RED + count + ANSI_RESET)) + ANSI_RESET);
            System.out.print(BLUE + "|   " + ANSI_RESET + RED + task.getContent() + ANSI_RESET);
            for (int i = 0; i < 20 - ("|    " + task.getContent()).length() - 2; i++) {
                System.out.print(" ");
            }
            System.out.print(BLUE + "|    " + ANSI_RESET + RED + task.getTitle() + ANSI_RESET);
            for (int i = 0; i < 20 - ("|" + task.getTitle()).length() - 5; i++) {
                System.out.print(" ");
            }
            System.out.print(BLUE + "      |  " + ANSI_RESET + RED + task.getCreated_date() + ANSI_RESET);
            for (int i = 0; i < 20 - ("|  " + task.getCreated_date()).length() - 6; i++) {
                System.out.print(" ");
            }
            System.out.print(BLUE + "  |   " + ANSI_RESET + RED +task.getFinished_date() + ANSI_RESET);
            for (int i = 0; i < 20 - ("|  " + task.getCreated_date()).length() - 3; i++) {
                System.out.print(" ");
            }
            System.out.print(BLUE + "  |\n" + ANSI_RESET);
            System.out.println(BLUE + "---------------------------------------------------------------------------------------------------------------" + ANSI_RESET);

        }

    }

    private void activeTaskList() {
        List<Task> taskList = taskService.activeTasks();
        int count = 0;
        System.out.println(BLUE + "-------------------------------------------------------------------------------------------------" + ANSI_RESET);
        System.out.print(BLUE + "| " + ANSI_RESET + RED + "id" + ANSI_RESET + BLUE + " |" + ANSI_RESET + RED + "    Content     " + ANSI_RESET + BLUE + "|      " + ANSI_RESET);
        System.out.print(RED + "  Title           " + ANSI_RESET + BLUE + "|      " + ANSI_RESET);
        System.out.print(RED + "   Created_date         " + ANSI_RESET + BLUE + "|  " + ANSI_RESET);
        System.out.println(RED + "Finished_date" + ANSI_RESET + BLUE + "  |   " + ANSI_RESET);
        System.out.print(BLUE + "-------------------------------------------------------------------------------------------------\n" + ANSI_RESET);
        for (Task task : taskList) {
            count = task.getId();
            System.out.print(BLUE + "|" + (count <= 9 ? (" " + RED + count + ANSI_RESET + "  ") : (count > 9 & count < 100) ? ("|" + RED + count + ANSI_RESET + " ") : ("|" + RED + count + ANSI_RESET)) + ANSI_RESET);
            System.out.print(BLUE + "|   " + ANSI_RESET + RED + task.getContent() + ANSI_RESET);
            for (int i = 0; i < 20 - ("|    " + task.getContent()).length() - 2; i++) {
                System.out.print(" ");
            }
            System.out.print(BLUE + "|    " + ANSI_RESET + RED + task.getTitle() + ANSI_RESET);
            for (int i = 0; i < 20 - ("|" + task.getTitle()).length() - 5; i++) {
                System.out.print(" ");
            }
            System.out.print(BLUE + "      |  " + ANSI_RESET + RED + task.getCreated_date() + ANSI_RESET);
            for (int i = 0; i < 20 - ("|  " + task.getCreated_date()).length() - 6; i++) {
                System.out.print(" ");
            }
            System.out.print(BLUE + "  |   " + ANSI_RESET + RED +"  null" + ANSI_RESET);
            for (int i = 0; i < 10-2; i++) {
                System.out.print(" ");
            }
            System.out.print(BLUE + "|\n" + ANSI_RESET);
            System.out.println(BLUE + "-------------------------------------------------------------------------------------------------" + ANSI_RESET);

        }

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


