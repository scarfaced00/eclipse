package todo;

import java.util.Scanner;

public class Todo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();

        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    toDoList.addTask(new Task(title, description));
                    System.out.println("Task added successfully!");
                    break;
                case 2:
                    toDoList.viewTasks();
                    break;
                case 3:
                    System.out.print("Enter task ID to mark as completed: ");
                    int taskId = scanner.nextInt();
                    toDoList.markTaskAsCompleted(taskId);
                    System.out.println("Task marked as completed.");
                    break;
                case 4:
                    System.out.print("Enter task ID to delete: ");
                    taskId = scanner.nextInt();
                    toDoList.deleteTask(taskId);
                    System.out.println("Task deleted.");
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}