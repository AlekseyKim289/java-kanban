package com.yandex.app.test;

import com.yandex.app.model.Epic;
import com.yandex.app.model.Subtask;
import com.yandex.app.model.Task;
import com.yandex.app.model.Status;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    void tasksWithSameIdShouldBeEqual() {
        Task task1 = new Task(1, "task 1", "dis 1", Status.NEW);
        Task task2 = new Task(1, "task 2", "dis 2", Status.IN_PROGRESS);
        assertEquals(task1, task2);
    }

    @Test
    void epicAndSubtaskWithSameIdShouldBeEqual() {
        Epic epic = new Epic(1, "Epic 1", "dis 1");
        Subtask subtask = new Subtask(1, "subtask 1", "dis 1", Status.NEW, 2);
        assertEquals(epic, subtask);
    }
}