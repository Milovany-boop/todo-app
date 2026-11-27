package com.example.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TodoApp {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(String description) {
        tasks.add(new Task(description));
    }


    public void clearTasks() {
        tasks.clear();
    }


    public boolean markTaskAsDone(int index) {
        if (index > 0 && index <= tasks.size()) {
            tasks.get(index - 1).markCompleted();
            return true;
        }
        return false;
    }


    public List<Task> searchTasks(String query) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(query.toLowerCase())) {
                result.add(task);
            }
        }
        return result;
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public static void main(String[] args) {
        TodoApp app = new TodoApp();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Todo App started. Commands: add <text>, clear, done <index>, search <text>, exit");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) break;

            String[] parts = input.split(" ", 2);
            String command = parts[0].toLowerCase();

            switch (command) {
                case "add":
                    if (parts.length > 1) {
                        app.addTask(parts[1]);
                        System.out.println("Added: " + parts[1]);
                    } else {
                        System.out.println("Usage: add <text>");
                    }
                    break;
                case "clear":
                    app.clearTasks();
                    System.out.println("All tasks cleared.");
                    break;
                case "done":
                    if (parts.length > 1) {
                        try {
                            int idx = Integer.parseInt(parts[1]);
                            if (app.markTaskAsDone(idx)) {
                                System.out.println("Task " + idx + " marked as done.");
                            } else {
                                System.out.println("Invalid index.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Index must be a number.");
                        }
                    } else {
                        System.out.println("Usage: done <index>");
                    }
                    break;
                case "search":
                    if (parts.length > 1) {
                        List<Task> found = app.searchTasks(parts[1]);
                        System.out.println("Found " + found.size() + " tasks:");
                        for (Task t : found) {
                            System.out.println("- " + t.getDescription() + (t.isCompleted() ? " [DONE]" : ""));
                        }
                    } else {
                        System.out.println("Usage: search <text>");
                    }
                    break;
                default:
                    System.out.println("Unknown command.");
            }
        }
        scanner.close();
    }
}