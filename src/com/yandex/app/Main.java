package com.yandex.app;

import com.yandex.app.model.Epic;
import com.yandex.app.model.Subtask;
import com.yandex.app.model.Task;
import com.yandex.app.model.Status;
import com.yandex.app.service.Managers;
import com.yandex.app.service.TaskManager;

public class Main {
    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);
            for (Subtask subtask : manager.getSubtasksByEpicId(epic.getId())) {
                System.out.println(subtask);
            }
        }
        System.out.println("Подзадачи:");
        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }
        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }

    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();

        Task task1 = manager.createTask(new Task(0, "task 1", "description 1", Status.NEW));
        Task task2 = manager.createTask(new Task(0, "tas 2", "description 2", Status.IN_PROGRESS));

        Epic epic1 = manager.createEpic(new Epic(0, "com.yandex.app.model.Epic 1", "description com.yandex.app.model.Epic 1"));
        Subtask subtask1 = manager.createSubtask(new Subtask(0, "task 1", "description subtask 1", Status.NEW, epic1.getId()));
        Subtask subtask2 = manager.createSubtask(new Subtask(0, "task 2", "description subtask 2", Status.IN_PROGRESS, epic1.getId()));

        Epic epic2 = manager.createEpic(new Epic(0, "com.yandex.app.model.Epic 2", "description com.yandex.app.model.Epic 2"));
        Subtask subtask3 = manager.createSubtask(new Subtask(0, "subtask 3", "description subtask 3", Status.DONE, epic2.getId()));

        manager.getTaskById(task1.getId());
        manager.getEpicById(epic1.getId());
        manager.getSubtaskById(subtask1.getId());

        printAllTasks(manager);

        task1.setStatus(Status.DONE);
        manager.updateTask(task1);

        subtask1.setStatus(Status.DONE);
        manager.updateSubtask(subtask1);

        subtask2.setStatus(Status.DONE);
        manager.updateSubtask(subtask2);

        manager.getTaskById(task2.getId());
        manager.getSubtaskById(subtask2.getId());

        printAllTasks(manager);

        manager.deleteTaskById(task1.getId());
        manager.deleteEpicById(epic2.getId());

        manager.getEpicById(epic1.getId());
        manager.getSubtaskById(subtask2.getId());

        printAllTasks(manager);
    }
}