
public class Managers {

    // Приватный конструктор, чтобы нельзя было создать экземпляр класса
    private Managers() {
        // Пустой приватный конструктор
    }

    // Единственный публичный метод - возвращает реализацию TaskManager
    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }
}
