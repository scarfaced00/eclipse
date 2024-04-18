package todo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private String title;
    private String description;
    private boolean completed;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Description: " + description + ", Completed: " + completed;
    }
}

class ToDoList {
    private List<Task> tasks;
    private static final String FILENAME = "tasks.txt";

    public ToDoList() {
        tasks = new ArrayList<>();
        loadTasksFromFile();
    }

    private void loadTasksFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String title = parts[0].trim();
                String description = parts[1].trim();
                boolean completed = Boolean.parseBoolean(parts[2].trim());
                tasks.add(new Task(title, description));
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    private void saveTasksToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            for (Task task : tasks) {
                bw.write(task.getTitle() + ", " + task.getDescription() + ", " + task.isCompleted());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        saveTasksToFile();
    }

    public void viewTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("Task " + (i + 1) + ": " + tasks.get(i));
        }
    }

    public void markTaskAsCompleted(int taskId) {
        if (taskId >= 1 && taskId <= tasks.size()) {
            tasks.get(taskId - 1).markAsCompleted();
            saveTasksToFile();
        } else {
            System.out.println("Invalid task ID.");
        }
    }

    public void deleteTask(int taskId) {
        if (taskId >= 1 && taskId <= tasks.size()) {
            tasks.remove(taskId - 1);
            saveTasksToFile();
        } else {
            System.out.println("Invalid task ID.");
        }
    }
}

