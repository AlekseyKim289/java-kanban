package com.yandex.app.test;

import com.yandex.app.model.Epic;
import com.yandex.app.model.Subtask;
import com.yandex.app.model.Task;
import com.yandex.app.model.Status;
import com.yandex.app.service.Managers;
import com.yandex.app.service.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InMemoryTaskManagerTest {
    private TaskManager manager;

    @BeforeEach
    void setUp() {
        manager = Managers.getDefault();
    }

    @Test
    void addNewTask() {
        Task task = new Task(0, "test task", "test dis", Status.NEW);
        Task createdTask = manager.createTask(task);
        Task savedTask = manager.getTaskById(createdTask.getId());
        assertNotNull(savedTask);
        assertEquals(createdTask, savedTask);
        assertEquals(1, manager.getAllTasks().size());
        assertEquals(createdTask, manager.getAllTasks().get(0));
    }

    @Test
    void addNewEpic() {
        Epic epic = new Epic(0, "Test Epic", "test dis");
        Epic createdEpic = manager.createEpic(epic);
        Epic savedEpic = manager.getEpicById(createdEpic.getId());
        assertNotNull(savedEpic);
        assertEquals(createdEpic, savedEpic);
        assertEquals(1, manager.getAllEpics().size());
        assertEquals(createdEpic, manager.getAllEpics().get(0));
    }

    @Test
    void addNewSubtask() {
        Epic epic = manager.createEpic(new Epic(0, "Test Epic", "test dis"));
        Subtask subtask = new Subtask(0, "test subtask", "test dis", Status.NEW, epic.getId());
        Subtask createdSubtask = manager.createSubtask(subtask);
        Subtask savedSubtask = manager.getSubtaskById(createdSubtask.getId());
        assertNotNull(savedSubtask);
        assertEquals(createdSubtask, savedSubtask);
        assertEquals(1, manager.getAllSubtasks().size());
        assertEquals(createdSubtask, manager.getAllSubtasks().get(0));
    }

    @Test
    void epicCannotBeAddedAsSubtask() {
        Epic epic = manager.createEpic(new Epic(0, "Test Epic", "test dis"));
        Subtask subtask = new Subtask(epic.getId(), "test subtask", "test dis", Status.NEW, epic.getId());
        assertNull(manager.createSubtask(subtask));
    }

    @Test
    void subtaskCannotBeItsOwnEpic() {
        Epic epic = manager.createEpic(new Epic(0, "Test Epic", "test dis"));
        Subtask subtask = new Subtask(epic.getId(), "test subtask", "test dis", Status.NEW, epic.getId());
        assertNull(manager.createSubtask(subtask));
    }

    @Test
    void tasksWithGeneratedAndCustomIdsDoNotConflict() {
        Task task1 = manager.createTask(new Task(0, "task 1", "dis 1", Status.NEW));
        Task task2 = manager.createTask(new Task(0, "task 2", "dis 2", Status.IN_PROGRESS));
        assertNotEquals(task1.getId(), task2.getId());
        assertEquals(2, manager.getAllTasks().size());
    }

    @Test
    void taskFieldsRemainUnchangedAfterAdding() {
        Task task = new Task(0, "test task", "test dis", Status.NEW);
        Task createdTask = manager.createTask(task);
        assertEquals("test task", createdTask.getName());
        assertEquals("test dis", createdTask.getDescription());
        assertEquals(Status.NEW, createdTask.getStatus());
    }
}