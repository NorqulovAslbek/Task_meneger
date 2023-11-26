package service;

import dto.Task;
import enums.Type;
import repository.TaskRepository;

import java.util.List;

public class TaskService {
    private TaskRepository taskRepository = new TaskRepository();

    public void createTaskService(Task task) {
        if (taskRepository.createTaskRepository(task)) {
            System.out.println("Success");
        } else {
            System.out.println("error service");
        }
    }

    public List<Task> activeTasks() {
        return taskRepository.getActiveTask();
    }

    public List<Task> finishedTask() {
        return taskRepository.getFinishedTask();
    }

    public void updateTask(int id, Task task) {
        if (taskRepository.updateRepository(id, task)) {
            System.out.println("Success.");
        } else {
            System.out.println("error task service");
        }
    }

    public void deleteTask(int id) {
        if (taskRepository.delete(id)) {
            System.out.println("deleted task");
        } else {
            System.out.println("error task deleted");
        }
    }

    public void doneTask(int id, Type type) {
        if (taskRepository.doneTaskRepository(id, type)) {
            System.out.println("Success");
        }

    }

}
