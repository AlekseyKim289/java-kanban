package com.yandex.app.service;

import com.yandex.app.model.Task;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> history = new ArrayList<>();
    private static final int MAX_HISTORY_SIZE = 10;

    @Override
    public void add(Task task) {
        if (task != null) {
            history.add(task);
            if (history.size() > MAX_HISTORY_SIZE) {
                history.remove(0);
            }
        }
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(history);
    }
}