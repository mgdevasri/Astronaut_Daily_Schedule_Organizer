public class TaskFactory {
    public static Task createTask(String description, String day, String start, String end, String priority) {
        return new Task(description, day, start, end, priority);
    }
}
