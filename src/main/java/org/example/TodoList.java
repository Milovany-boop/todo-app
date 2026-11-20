package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TodoList {

    public static final class Task {
        private final String text;
        private boolean done;

        public Task(String text, boolean done) {
            this.text = text;
            this.done = done;
        }

        public String getText() { return text; }
        public boolean isDone() { return done; }
        public void setDone(boolean value) { this.done = value; }

        @Override
        public String toString() {
            return (done ? "[x] " : "[ ] ") + text;
        }
    }

    private final List<Task> items = new ArrayList<>();

    public void add(String item) {
        if (item == null) return;
        String trimmed = item.trim();
        if (!trimmed.isEmpty()) {
            items.add(new Task(trimmed, false));
        }
    }

    public boolean remove(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            return true;
        }
        return false;
    }

    public List<String> getAll() {
        List<String> result = new ArrayList<>(items.size());
        for (Task t : items) result.add(t.toString());
        return result;
    }

    public int size() { return items.size(); }

    public void clear() { items.clear(); }

    public boolean done(int index) {
        if (index >= 0 && index < items.size()) {
            items.get(index).setDone(true);
            return true;
        }
        return false;
    }

    public List<String> search(String query) {
        List<String> result = new ArrayList<>();
        if (query == null) return result;
        String q = query.trim().toLowerCase(Locale.ROOT);
        if (q.isEmpty()) return result;
        for (Task t : items) {
            if (t.getText().toLowerCase(Locale.ROOT).contains(q)) {
                result.add(t.toString());
            }
        }
        return result;
    }
}