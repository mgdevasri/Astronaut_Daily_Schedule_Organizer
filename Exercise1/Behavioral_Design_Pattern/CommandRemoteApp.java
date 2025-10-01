import java.util.*; // command 1: Scanner, ArrayList

// Command interface
interface Command {
    void execute();
    void undo();
}

// Receiver classes: Devices
class Light {
    private String name;
    public Light(String name) { this.name = name; }
    public void on() { System.out.println(name + " is ON"); }
    public void off() { System.out.println(name + " is OFF"); }
}

class Fan {
    private String name;
    public Fan(String name) { this.name = name; }
    public void on() { System.out.println(name + " is ON"); }
    public void off() { System.out.println(name + " is OFF"); }
}

// Concrete Commands
class LightOnCommand implements Command {
    private Light light;
    public LightOnCommand(Light light) { this.light = light; }
    public void execute() { light.on(); }
    public void undo() { light.off(); }
}

class LightOffCommand implements Command {
    private Light light;
    public LightOffCommand(Light light) { this.light = light; }
    public void execute() { light.off(); }
    public void undo() { light.on(); }
}

class FanOnCommand implements Command {
    private Fan fan;
    public FanOnCommand(Fan fan) { this.fan = fan; }
    public void execute() { fan.on(); }
    public void undo() { fan.off(); }
}

class FanOffCommand implements Command {
    private Fan fan;
    public FanOffCommand(Fan fan) { this.fan = fan; }
    public void execute() { fan.off(); }
    public void undo() { fan.on(); }
}

// Invoker class: Remote Control
class RemoteControl {
    private Stack<Command> history = new Stack<>();
    public void pressButton(Command command) {
        command.execute();
        history.push(command);
    }
    public void pressUndo() {
        if(!history.isEmpty()) {
            Command last = history.pop();
            last.undo();
        } else {
            System.out.println("No command to undo!");
        }
    }
}

// Client: Console App
public class CommandRemoteApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Light livingRoomLight = new Light("Living Room Light");
        Fan ceilingFan = new Fan("Ceiling Fan");

        RemoteControl remote = new RemoteControl();

        boolean exit = false;
        while(!exit) {
            System.out.println("\nRemote Control Options:");
            System.out.println("1 - Turn Light ON");
            System.out.println("2 - Turn Light OFF");
            System.out.println("3 - Turn Fan ON");
            System.out.println("4 - Turn Fan OFF");
            System.out.println("5 - Undo Last Command");
            System.out.println("6 - Exit");
            System.out.print("Enter choice: ");

            String choice = sc.nextLine();
            switch(choice) {
                case "1":
                    remote.pressButton(new LightOnCommand(livingRoomLight));
                    break;
                case "2":
                    remote.pressButton(new LightOffCommand(livingRoomLight));
                    break;
                case "3":
                    remote.pressButton(new FanOnCommand(ceilingFan));
                    break;
                case "4":
                    remote.pressButton(new FanOffCommand(ceilingFan));
                    break;
                case "5":
                    remote.pressUndo();
                    break;
                case "6":
                    exit = true;
                    System.out.println("Exiting Remote Control App...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }

        sc.close();
    }
}
