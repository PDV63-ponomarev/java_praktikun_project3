package methods;

import java.util.LinkedList;
import java.util.List;

public class HistoryManager {

    private static final int MAX_HISTORY_SIZE = 10;
    private List<Integer> historyIds = new LinkedList<>();
    private CreatTask createTask;

    private String instanceId; // Для отладки

    public HistoryManager(CreatTask createTask) {
        this.createTask = createTask;
    }

    // Добавление ID в историю
    public void addToHistory(int taskId) {

        // Добавляем ID в конец списка (самый свежий просмотр)
        historyIds.add(taskId);

        // Если история превышает лимит, удаляем самый старый просмотр
        if (historyIds.size() > MAX_HISTORY_SIZE) {
            historyIds.remove(0);
        }

//        // Если ID уже есть в истории, удаляем старую запись
//        historyIds.remove(Integer.valueOf(taskId));

    }

    public void showHistory() {

        if (historyIds.isEmpty()) {
            System.out.println("История просмотров пуста");
            return;
        }

        System.out.println("История просмотров (последние " + historyIds.size() + " из " + MAX_HISTORY_SIZE + "):");

        for (int i = historyIds.size() - 1; i >= 0; i--) {
            int id = historyIds.get(i);
            String taskInfo = getTaskInfo(id);

            System.out.println((historyIds.size() - i) + ") " + taskInfo);

        }
    }

    // Получить информацию о задаче по ID
    private String getTaskInfo(int id) {
        // Проверяем обычные задачи
        if (createTask.getTasks().containsKey(id)) {
            Task task = createTask.getTasks().get(id);
            return String.format("[Задача] ID: %d | Название: %s | Статус: %s",
                    id, task.getTaskName(), task.getTaskStatus());
        }

        // Проверяем эпики
        if (createTask.getEpics().containsKey(id)) {
            TaskEpic epic = createTask.getEpics().get(id);
            return String.format("[Большая задача] ID: %d | Название: %s | Подзадач: %d",
                    id, epic.getTaskName(), epic.getSubtaskIds().size());
        }

        // Проверяем подзадачи
        if (createTask.getSubtasks().containsKey(id)) {
            TaskSubtask subtask = createTask.getSubtasks().get(id);
            TaskEpic epic = createTask.getEpics().get(subtask.getEpicId());
            return String.format("[Подзадача] ID: %d | Название: %s | Большая задача: %s",
                    id, subtask.getTaskName(), epic.getTaskName());
        }

        return "[Удалена] ID: " + id;
    }

    // Получить последние просмотренные ID (для быстрого доступа)
    public List<Integer> getLastViewedIds() {
        return new LinkedList<>(historyIds);
    }



}

