import java.util.Scanner;

interface Transport {
    void deliver();
    double calculateFare(int distance);
}

class Car implements Transport {
    public void deliver() {
        System.out.println("\nCar selected: Comfortable ride for 4 passengers.");
        System.out.println("Fare rate: 10 per km");
    }

    public double calculateFare(int distance) {
        return distance * 10.0;
    }
}

class Bike implements Transport {
    public void deliver() {
        System.out.println("\nBike selected: Fast ride for 1 passenger or small package.");
        System.out.println("Fare rate: 5 per km");
    }

    public double calculateFare(int distance) {
        return distance * 5.0;
    }
}

class Auto implements Transport {
    public void deliver() {
        System.out.println("\nAuto selected: Affordable ride for 3 passengers.");
        System.out.println("Fare rate: 7 per km");
    }

    public double calculateFare(int distance) {
        return distance * 7.0;
    }
}

class TransportFactory {
    public static Transport getTransport(String type) {
        switch(type.toUpperCase()) {
            case "CAR": return new Car();
            case "BIKE": return new Bike();
            case "AUTO": return new Auto();
            default: throw new IllegalArgumentException("Invalid transport type: " + type);
        }
    }
}

public class FactoryTransportApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        System.out.println("****Welcome to Smart Transport App****");

        while(!exit) {
            System.out.println("\nAvailable transport types: Car, Bike, Auto");
            System.out.print("Enter your transport choice or type 'exit' to quit: ");
            String choice = sc.nextLine().trim();

            if(choice.equalsIgnoreCase("exit")) {
                exit = true;
                System.out.println("Thank you for using Smart Transport App!");
                break;
            }

            try {
                Transport transport = TransportFactory.getTransport(choice);
                transport.deliver();

                System.out.print("Enter distance to travel in km: ");
                int distance = sc.nextInt();
                sc.nextLine(); // consume newline

                double fare = transport.calculateFare(distance);
                System.out.println("Estimated fare for " + distance + " km: " + fare + " INR");
                System.out.println("----------------------------------------");

            } catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch(Exception e) {
                System.out.println("Invalid input! Please enter a valid number for distance.");
                sc.nextLine();
            }
        }

        sc.close();
    }
}
