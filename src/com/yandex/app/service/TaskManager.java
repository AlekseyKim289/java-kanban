package com.yandex.app.service;

import com.yandex.app.model.Epic;
import com.yandex.app.model.Subtask;
import com.yandex.app.model.Task;
import java.util.List;

public interface TaskManager {
    Task createTask(Task task);
    Epic createEpic(Epic epic);
    Subtask createSubtask(Subtask subtask);
    void updateTask(Task task);
    void updateEpic(Epic epic);
    void updateSubtask(Subtask subtask);
    List<Task> getAllTasks();
    List<Epic> getAllEpics();
    List<Subtask> getAllSubtasks();
    Task getTaskById(int id);
    Epic getEpicById(int id);
    Subtask getSubtaskById(int id);
    List<Subtask> getSubtasksByEpicId(int epicId);
    void deleteAllTasks();
    void deleteAllEpics();
    void deleteAllSubtasks();
    void deleteTaskById(int id);
    void deleteEpicById(int id);
    void deleteSubtaskById(int id);
    List<Task> getHistory();
}
