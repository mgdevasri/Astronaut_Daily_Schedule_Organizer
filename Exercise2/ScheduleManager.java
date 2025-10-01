import java.util.*;

public class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks = new ArrayList<>();
    private List<TaskObserver> observers = new ArrayList<>();

    private ScheduleManager() {}

    public static ScheduleManager getInstance() {
        if (instance == null) instance = new ScheduleManager();
        return instance;
    }

    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String msg) {
        for(TaskObserver o : observers) o.notify(msg);
    }

    public boolean addTask(Task task) {
        for(Task t : tasks) {
            if(t.getDay().equalsIgnoreCase(task.getDay()) &&
               task.getStartTime().isBefore(t.getEndTime()) &&
               task.getEndTime().isAfter(t.getStartTime())) {
                notifyObservers("[WARNING] Conflict with task: " + t.getDescription());
                return false;
            }
        }
        tasks.add(task);
        tasks.sort(Comparator.comparing(Task::getStartTime));
        notifyObservers("[INFO] Task added: " + task.getDescription());
        return true;
    }

    public boolean removeTask(String desc) {
        Iterator<Task> it = tasks.iterator();
        while(it.hasNext()) {
            Task t = it.next();
            if(t.getDescription().equalsIgnoreCase(desc)) {
                it.remove();
                notifyObservers("[INFO] Task removed: " + desc);
                return true;
            }
        }
        notifyObservers("[ERROR] Task not found: " + desc);
        return false;
    }

    public void editTask(String oldDesc, String newDesc, String newDay, String newStart, String newEnd, String newPriority) {
        for(Task t : tasks) {
            if(t.getDescription().equalsIgnoreCase(oldDesc)) {
                t.setDescription(newDesc);
                t.setDay(newDay);
                t.setStartTime(newStart);
                t.setEndTime(newEnd);
                t.setPriority(newPriority);
                notifyObservers("[INFO] Task edited: " + newDesc);
                return;
            }
        }
        notifyObservers("[ERROR] Task not found: " + oldDesc);
    }

    public void markTaskCompleted(String desc) {
        for(Task t : tasks) {
            if(t.getDescription().equalsIgnoreCase(desc)) {
                t.setCompleted(true);
                notifyObservers("[INFO] Task completed: " + desc);
                return;
            }
        }
        notifyObservers("[ERROR] Task not found: " + desc);
    }

    public void viewTasks() {
        if(tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
            return;
        }
        System.out.println("----- Daily Tasks -----");
        for(Task t : tasks) System.out.println(t);

        double completed = tasks.stream().filter(Task::isCompleted).count();
        double percentage = (completed / tasks.size()) * 100;
        System.out.printf("Progress: %.2f%% completed%n", percentage);
    }
}
