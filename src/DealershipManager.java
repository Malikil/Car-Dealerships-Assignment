import java.util.List;
import java.util.Scanner;

public class DealershipManager {
    private final Dealership dealership;
    private final Scanner input;

    public DealershipManager(Dealership dealership, Scanner input) {
        this.dealership = dealership;
        this.input = input;
    }

    public void dealershipMenu() {
        int comm;
        do {
            showMenu();
            comm = Integer.parseInt(input.nextLine());
            switch (comm) {
                case 1 -> viewCars();
                case 2 -> addCar();
                case 3 -> removeCar();
            }
            System.out.println();
        } while (comm != 0);
    }

    private void showMenu() {
        System.out.printf("Inventory Management - %s%n", dealership.getLocation());
        System.out.println(" 1) View existing cars");
        System.out.println(" 2) Add new car");
        System.out.println(" 3) Remove a car");
        System.out.println();
        System.out.println(" 0) Return");
        System.out.print(">");
    }

    private Car getCarFromUser() {
        System.out.print(" Make: ");
        String make = input.nextLine();
        System.out.print("Model: ");
        String model = input.nextLine();
        System.out.print(" Year: ");
        int year = Integer.parseInt(input.nextLine());

        return new Car(make, model, year);
    }

    private void addCar() {
        System.out.println("Adding a new car");
        dealership.addCar(getCarFromUser());
        System.out.println("Car added to inventory");
    }

    private void removeCar() {
        System.out.println("Which car to remove?");
        Car toRemove = getCarFromUser();

        if (dealership.removeCar(toRemove))
            System.out.printf("Removed %s %n", toRemove);
        else
            System.out.println("Car not found, no changes made");
    }

    private void viewCars() {
        List<Car> inventory = dealership.getInventory();
        System.out.printf("Displaying %d cars:%n", inventory.size());
        for (Car c : inventory)
            System.out.println(c);
    }
}
