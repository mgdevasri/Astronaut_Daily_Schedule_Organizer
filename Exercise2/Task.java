import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String description;
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String priority;
    private boolean completed;

    public Task(String description, String day, String start, String end, String priority) {
        this.description = description;
        this.day = day;
        this.startTime = LocalTime.parse(start, DateTimeFormatter.ofPattern("HH:mm"));
        this.endTime = LocalTime.parse(end, DateTimeFormatter.ofPattern("HH:mm"));
        this.priority = priority;
        this.completed = false;
    }

    public String getDescription() { return description; }
    public String getDay() { return day; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public String getPriority() { return priority; }
    public boolean isCompleted() { return completed; }

    public void setDescription(String description) { this.description = description; }
    public void setDay(String day) { this.day = day; }
    public void setStartTime(String start) { this.startTime = LocalTime.parse(start, DateTimeFormatter.ofPattern("HH:mm")); }
    public void setEndTime(String end) { this.endTime = LocalTime.parse(end, DateTimeFormatter.ofPattern("HH:mm")); }
    public void setPriority(String priority) { this.priority = priority; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    @Override
    public String toString() {
        String status = completed ? "[✔]" : "[ ]";
        return status + " " + day + " " + startTime + "-" + endTime + " : " + description + " [" + priority + "]";
    }
}
