package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TodoListTest {

    @Test
    void addAndNormalize() {
        TodoList t = new TodoList();
        t.add("  task1  ");
        assertEquals(1, t.size());
        assertEquals("[ ] task1", t.getAll().getFirst());
    }

    @Test
    void addEmptyIgnored() {
        TodoList t = new TodoList();
        t.add("   ");
        t.add(null);
        assertEquals(0, t.size());
    }

    @Test
    void removeValid() {
        TodoList t = new TodoList();
        t.add("a");
        t.add("b");
        assertTrue(t.remove(0));
        assertEquals(1, t.size());
        assertEquals("[ ] b", t.getAll().getFirst());
    }

    @Test
    void removeOutOfRange() {
        TodoList t = new TodoList();
        t.add("a");
        assertFalse(t.remove(10));
        assertFalse(t.remove(-1));
        assertEquals(1, t.size());
    }

    @Test
    void clearEmptiesList() {
        TodoList t = new TodoList();
        t.add("a");
        t.add("b");
        t.clear();
        assertEquals(0, t.size());
        assertTrue(t.getAll().isEmpty());
    }

    @Test
    void doneMarksTask() {
        TodoList t = new TodoList();
        t.add("a");
        assertTrue(t.done(0));
        assertEquals("[x] a", t.getAll().getFirst());
    }

    @Test
    void doneOutOfRange() {
        TodoList t = new TodoList();
        t.add("a");
        assertFalse(t.done(5));
        assertFalse(t.done(-1));
        assertEquals("[ ] a", t.getAll().getFirst());
    }

    @Test
    void searchFindsSubstring() {
        TodoList t = new TodoList();
        t.add("Buy milk");
        t.add("Buy bread");
        t.add("Call mom");
        List<String> found = t.search("buy");
        assertEquals(2, found.size());
    }

    @Test
    void searchEmptyOrNull() {
        TodoList t = new TodoList();
        t.add("a");
        assertTrue(t.search("").isEmpty());
        assertTrue(t.search(null).isEmpty());
        assertTrue(t.search("zzz").isEmpty());
    }
    @Test
    void doneCanBeCalledRepeatedly() {
        TodoList t = new TodoList();
        t.add("a");

        assertTrue(t.done(0));
        assertTrue(t.done(0));
        assertEquals("[x] a", t.getAll().getFirst());
    }
}