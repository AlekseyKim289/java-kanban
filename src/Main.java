public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();


        Task task1 = manager.createTask(new Task(0, "task 1", "description 1", Status.NEW));
        Task task2 = manager.createTask(new Task(0, "tas 2", "description 2", Status.IN_PROGRESS));

        Epic epic1 = manager.createEpic(new Epic(0, "Epic 1", "description Epic 1"));
        Subtask subtask1 = manager.createSubtask(new Subtask(0, "task 1", "description subtask 1", Status.NEW, epic1.getId()));
        Subtask subtask2 = manager.createSubtask(new Subtask(0, "task 2", "description subtask 2", Status.IN_PROGRESS, epic1.getId()));

        Epic epic2 = manager.createEpic(new Epic(0, "Epic 2", "description Epic 2"));
        Subtask subtask3 = manager.createSubtask(new Subtask(0, "subtask 3", "description subtask 3", Status.DONE, epic2.getId()));

        System.out.println("all tasks: " + manager.getAllTasks());
        System.out.println("all Epics: " + manager.getAllEpics());
        System.out.println("all subtasks: " + manager.getAllSubtasks());

        task1.setStatus(Status.DONE);
        manager.updateTask(task1);

        subtask1.setStatus(Status.DONE);
        manager.updateSubtask(subtask1);

        subtask2.setStatus(Status.DONE);
        manager.updateSubtask(subtask2);

        System.out.println("all tasks: " + manager.getAllTasks());
        System.out.println("all Epics: " + manager.getAllEpics());
        System.out.println("all subtasks: " + manager.getAllSubtasks());


        manager.deleteTaskById(task1.getId());
        manager.deleteEpicById(epic2.getId());

        System.out.println("all tasks: " + manager.getAllTasks());
        System.out.println("all Epics: " + manager.getAllEpics());
        System.out.println("all subtasks: " + manager.getAllSubtasks());
    }
}