package com.yandex.app.test;

import com.yandex.app.model.Task;
import com.yandex.app.model.Status;
import com.yandex.app.service.HistoryManager;
import com.yandex.app.service.Managers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InMemoryHistoryManagerTest {
    private HistoryManager historyManager;

    @BeforeEach
    void setUp() {
        historyManager = Managers.getDefaultHistory();
    }

    @Test
    void addTaskToHistory() {
        Task task = new Task(1, "test task", "test dis", Status.NEW);
        historyManager.add(task);
        assertFalse(historyManager.getHistory().isEmpty());
        assertEquals(1, historyManager.getHistory().size());
        assertEquals(task, historyManager.getHistory().get(0));
    }

    @Test
    void historyKeepsPreviousTaskVersion() {
        Task task = new Task(1, "test task", "test dis", Status.NEW);
        historyManager.add(task);
        Task updatedTask = new Task(1, "Updated task", "Updated dis", Status.DONE);
        historyManager.add(updatedTask);
        assertEquals(2, historyManager.getHistory().size());
        assertEquals(task, historyManager.getHistory().get(0));
        assertEquals(updatedTask, historyManager.getHistory().get(1));
    }

    @Test
    void historyLimitedToTenTasks() {
        for (int i = 1; i <= 11; i++) {
            historyManager.add(new Task(i, "task " + i, "dis " + i, Status.NEW));
        }
        assertEquals(10, historyManager.getHistory().size());
        assertEquals(2, historyManager.getHistory().get(0).getId());
    }
}