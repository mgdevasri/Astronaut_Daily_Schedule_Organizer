import java.util.Scanner;

// Singleton Volume Controller
class VolumeController {
    private static VolumeController instance;
    private int volume;

    private VolumeController() {
        this.volume = 50; 
        System.out.println("VolumeController instance created!");
    }

    public static VolumeController getInstance() {
        if (instance == null) {
            instance = new VolumeController();
        }
        return instance;
    }

    public void setVolume(int vol) {
        if (vol < 0) vol = 0;
        if (vol > 100) vol = 100;
        this.volume = vol;
        System.out.println("Volume set to: " + volume);
    }

    public void showVolume() {
        System.out.println("Current volume: " + volume);
    }
}

// Base class for apps
abstract class App {
    protected VolumeController vc;

    public App() {
        vc = VolumeController.getInstance();
    }

    public void controlVolume() {
        Scanner sc = new Scanner(System.in);
        System.out.print("--- " + getAppName() + " Volume Control ---\n"
                         + "1. Set Volume\n"
                         + "2. Show Volume\n"
                         + "Choose option: ");
        int choice = sc.nextInt();
        if (choice == 1) {
            System.out.print("Enter volume (0-100): ");
            int vol = sc.nextInt();
            vc.setVolume(vol);
        } else if (choice == 2) {
            vc.showVolume();
        } else {
            System.out.println("Invalid choice!");
        }
    }

    protected abstract String getAppName();
}

// Apps
class MusicApp extends App {
    @Override
    protected String getAppName() {
        return "Music App";
    }
}

class VideoApp extends App {
    @Override
    protected String getAppName() {
        return "Video App";
    }
}

class CallApp extends App {
    @Override
    protected String getAppName() {
        return "Call App";
    }
}

// Main program
public class SingletonVolumeApp {
    public static void main(String[] args) {
        App music = new MusicApp();
        App video = new VideoApp();
        App call = new CallApp();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("\n--- Volume Control System ---\n"
                             + "1. Music App\n"
                             + "2. Video App\n"
                             + "3. Call App\n"
                             + "4. Exit\n"
                             + "Select the app: ");
            int appChoice = sc.nextInt();

            switch (appChoice) {
                case 1:
                    music.controlVolume();
                    break;
                case 2:
                    video.controlVolume();
                    break;
                case 3:
                    call.controlVolume();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
