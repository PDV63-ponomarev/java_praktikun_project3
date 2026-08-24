import methods.CreatTask;
import methods.DeleteTask;
import methods.ShowTask;
import methods.UpdateTask;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CreatTask creatTask = new CreatTask();
        ShowTask showTask = new ShowTask(creatTask);
        DeleteTask deleteTask = new DeleteTask(creatTask);
        UpdateTask updateTask = new UpdateTask(creatTask);
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Введите команду:\n" +
                    "1 - Получить список задач\n" +
                    "2 - Удалить все задачи\n" +
                    "3 - Открыть задачу\n" +
                    "4 - Создать новую задачу\n" +
                    "5 - Обновить список задач\n" +
                    "6 - Удалить задачу\n" +
                    "7 - Выход");

            try {
                int userInput = scanner.nextInt();
                switch (userInput) {
                    case 1:
                        showTask.showAllTasks();
                        break;
                    case 2:
                        deleteTask.deleteAllTasks();
                        break;
                    case 3:
                        showTask.showTaskFromID();
                        break;
                    case 4:
                        creatTask.creatingNewTask();
                        break;
                    case 5:
                        updateTask.managerUpdateTask();
                        break;
                    case 6:
                        deleteTask.deleteOneTask();
                        break;
                    case 7:
                        System.out.println("Выход");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Ошибка: введите число от 1 до 7");
                }

            } catch (java.util.InputMismatchException e) {
                System.out.println("Ошибка: введите число от 1 до 7");
                scanner.nextLine();
            }
        }

    }
}
