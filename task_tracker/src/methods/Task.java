package methods;

import java.util.ArrayList;
import java.util.List;

public class Task {
    protected  String taskName;
    protected  String taskDescription;
    protected  String taskStatus;
    protected  int taskID;

    public Task(String taskName, String taskDescription, String taskStatus, int taskID) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return String.format("Задача {Название: %s; Описание: %s; Статус: %s; ID: %d}",
               taskName,
               taskDescription,
               taskStatus,
               taskID);
    }
}

class TaskEpic extends Task{
    private List<Integer> subtaskIds;

    public TaskEpic(String taskName, String taskDescription, String taskStatus, int taskID) {
        super(taskName, taskDescription, taskStatus, taskID);
        this.subtaskIds = new ArrayList<>();
    }


    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void addSubtaskId(int subtaskId) {
        subtaskIds.add(subtaskId);
    }

    public void removeSubtaskId(int subtaskId) {
        subtaskIds.remove(Integer.valueOf(subtaskId));
    }

    public void updateStatus(List<TaskSubtask> subtasks) {
        if (subtaskIds.isEmpty()) {
            this.taskStatus = "NEW";
            return;
        }

        boolean allNew = true;
        boolean allDone = true;

        for (int id : subtaskIds) {
            for (TaskSubtask subtask : subtasks) {
                if (subtask.getTaskID() == id) {
                    if (!subtask.getTaskStatus().equals("NEW")) {
                        allNew = false;
                    }
                    if (!subtask.getTaskStatus().equals("DONE")) {
                        allDone = false;
                    }
                    break;
                }
            }
        }

        if (allNew) {
            this.taskStatus = "NEW";
        } else if (allDone) {
            this.taskStatus = "DONE";
        } else {
            this.taskStatus = "IN_PROGRESS";
        }
    }
    @Override
    public String toString() {
        return String.format("Задача {Название: %s; Описание: %s; Статус: %s; ID: %d; Подзадачи: %s}",
                taskName,
                taskDescription,
                taskStatus,
                taskID,
                subtaskIds
        );
    }
}

class TaskSubtask extends Task{
    private int epicId;
    private String epicName;

    public TaskSubtask(String taskName, String taskDescription, String taskStatus, int taskID, int epicId, String epicName) {
        super(taskName, taskDescription, taskStatus, taskID);
        this.epicId = epicId;
        this.epicName = epicName;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return String.format("Задача {Название: %s; Описание: %s; Статус: %s; ID: %d; Глобальная задача: %s (%d)}",
                taskName,
                taskDescription,
                taskStatus,
                taskID,
                epicName,
                epicId);
    }
}