package com.example.todo;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodoAppTest {
    private TodoApp app;

    @BeforeEach
    void setUp() {
        app = new TodoApp();
    }

    @Test
    void shouldAddTaskAndIncreaseCount() {
        assertEquals(0, app.getTaskCount());
        app.addTask("Тестовая задача");
        assertEquals(1, app.getTaskCount());
    }

    @Test
    void shouldReturnTaskDescription() {
        app.addTask("Купить молоко");
        assertEquals("Купить молоко", app.getTasks().get(0).getDescription());
    }

    @Test
    void newTaskShouldNotBeCompleted() {
        app.addTask("Задача");
        assertFalse(app.getTasks().get(0).isCompleted());
    }
    @Test
    void shouldClearAllTasks() {
        app.addTask("Задача 1");
        app.addTask("Задача 2");
        assertEquals(2, app.getTaskCount());
        app.clearTasks();
        assertEquals(0, app.getTaskCount());
    }

    @Test
    void shouldMarkTaskAsDone() {
        app.addTask("Задача 1");
        app.addTask("Задача 2");
        assertTrue(app.markTaskAsDone(1));
        assertTrue(app.getTasks().get(0).isCompleted());
        assertFalse(app.getTasks().get(1).isCompleted());
    }

    @Test
    void shouldNotMarkTaskAsDoneWithInvalidIndex() {
        app.addTask("Задача 1");
        assertFalse(app.markTaskAsDone(5));
        assertFalse(app.markTaskAsDone(-1));
        assertFalse(app.getTasks().get(0).isCompleted());
    }

    @Test
    void shouldSearchTasksBySubstring() {
        app.addTask("Купить молоко");
        app.addTask("Купить хлеб");
        app.addTask("Помыть посуду");

        List<Task> result = app.searchTasks("купить");
        assertEquals(2, result.size());

        List<Task> result2 = app.searchTasks("ПОСУДУ");
        assertEquals(1, result2.size());
    }
}
