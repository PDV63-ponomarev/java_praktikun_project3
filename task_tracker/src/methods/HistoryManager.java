package methods;

import java.util.LinkedList;
import java.util.List;

public class HistoryManager {
    private static final int MAX_HISTORY_SIZE = 10;
    private List<Task> historyTasks = new LinkedList<>();
    private CreatTask createTask;

    public HistoryManager(CreatTask createTask) {
        this.createTask = createTask;
    }

    public void addToHistory(Task task) {
        if (task == null) {
            return;
        }

        historyTasks.add(task);

        if (historyTasks.size() > MAX_HISTORY_SIZE) {
            historyTasks.remove(0);
        }
    }

    public void addToHistory(int taskId) {
        Task task = findTaskById(taskId);
        if (task != null) {
            addToHistory(task);
        }
    }

    public List<Task> history() {
        return new LinkedList<>(historyTasks); // Возвращаем копию списка
    }

    public void showHistory() {
        List<Task> history = history();

        if (history.isEmpty()) {
            System.out.println("История просмотров пуста");
            return;
        }

        System.out.println("История просмотров (последние " + history.size() + " из " + MAX_HISTORY_SIZE + "):");

        int count = 1;
        for (int i = history.size() - 1; i >= 0; i--) {
            Task task  = history.get(i);
            String taskInfo = getTaskInfo(task);
            System.out.println(count++ + ") " + taskInfo);
        }
    }
    private Task findTaskById(int id) {
        // Проверяем обычные задачи
        if (createTask.getTasks().containsKey(id)) {
            return createTask.getTasks().get(id);
        }

        // Проверяем эпики
        if (createTask.getEpics().containsKey(id)) {
            return createTask.getEpics().get(id);
        }

        // Проверяем подзадачи
        if (createTask.getSubtasks().containsKey(id)) {
            return createTask.getSubtasks().get(id);
        }

        return null; // Задача не найдена
    }

    private String getTaskInfo(Task task) {
        if (task instanceof TaskEpic) {
            TaskEpic epic = (TaskEpic) task;
            return String.format("[Большая задача] ID: %d | Название: %s | Подзадач: %d",
                    task.getTaskID(), task.getTaskName(), epic.getSubtaskIds().size());
        }

        if (task instanceof TaskSubtask) {
            TaskSubtask subtask = (TaskSubtask) task;
            TaskEpic epic = createTask.getEpics().get(subtask.getEpicId());
            return String.format("[Подзадача] ID: %d | Название: %s | Большая задача: %s",
                    task.getTaskID(), task.getTaskName(), epic.getTaskName());
        }

        return String.format("[Задача] ID: %d | Название: %s | Статус: %s",
                task.getTaskID(), task.getTaskName(), task.getTaskStatus());
    }
}

