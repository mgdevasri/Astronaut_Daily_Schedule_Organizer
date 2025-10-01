import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ScheduleManager manager = ScheduleManager.getInstance();

        // Observer for notifications
        manager.addObserver(msg -> System.out.println(msg));

        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("\nCommands: add, remove, edit, complete, view, exit");
            System.out.print("Enter command: ");
            String cmd = sc.nextLine();

            switch(cmd.toLowerCase()) {
                case "add":
                    System.out.print("Title: "); String title = sc.nextLine();
                    System.out.print("Day: "); String day = sc.nextLine();
                    System.out.print("Start (HH:mm): "); String start = sc.nextLine();
                    System.out.print("End (HH:mm): "); String end = sc.nextLine();
                    System.out.print("Priority (High/Medium/Low): "); String prio = sc.nextLine();
                    manager.addTask(TaskFactory.createTask(title, day, start, end, prio));
                    break;

                case "remove":
                    System.out.print("Title to remove: "); manager.removeTask(sc.nextLine());
                    break;

                case "edit":
                    System.out.print("Task to edit: "); String oldDesc = sc.nextLine();
                    System.out.print("New title: "); String newDesc = sc.nextLine();
                    System.out.print("New day: "); String newDay = sc.nextLine();
                    System.out.print("New start: "); String newStart = sc.nextLine();
                    System.out.print("New end: "); String newEnd = sc.nextLine();
                    System.out.print("New priority: "); String newPrio = sc.nextLine();
                    manager.editTask(oldDesc, newDesc, newDay, newStart, newEnd, newPrio);
                    break;

                case "complete":
                    System.out.print("Task to complete: "); manager.markTaskCompleted(sc.nextLine());
                    break;

                case "view":
                    manager.viewTasks();
                    break;

                case "exit":
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid command!");
            }
        }
    }
}
