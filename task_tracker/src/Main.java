public class Main {
    public static void main(String[] args) {
        // Используем Managers для получения менеджера задач
        TaskManager taskManager = Managers.getDefault();

        // Проверяем, что мы получили правильный тип
        System.out.println("Получен менеджер задач: " + taskManager.getClass().getSimpleName());

        // Запускаем менеджер (только если это InMemoryTaskManager)
        if (taskManager instanceof InMemoryTaskManager) {
            InMemoryTaskManager manager = (InMemoryTaskManager) taskManager;
            manager.start();
        } else {
            System.out.println("Ошибка: неизвестный тип менеджера");
        }
    }
}
