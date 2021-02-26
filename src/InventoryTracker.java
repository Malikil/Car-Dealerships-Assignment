import java.util.ArrayList;
import java.util.Scanner;

public class InventoryTracker {
    private final static ArrayList<Dealership> dealerships = new ArrayList<>();
    private final static FileManager file = new FileManager("inventory.txt");
    private final static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        if (file.fileExists())
            dealerships.addAll(file.readFile());

        int comm;
        do {
            showMenu();
            comm = Integer.parseInt(input.nextLine());
            switch (comm) {
                case 1 -> addDealership();
                case 2 -> removeDealership();
                case 3 -> selectDealership();
            }
            System.out.println();
        } while (comm != 0);

        input.close();
        file.writeFile(dealerships);
    }

    private static void showMenu() {
        System.out.println("Dealership Management");
        System.out.println(" 1) Add Dealership");
        System.out.println(" 2) Remove Dealership");
        System.out.println(" 3) Select Dealership");
        System.out.println();
        System.out.println(" 0) Exit");
        System.out.print(">");
    }

    private static void addDealership() {
        System.out.print("Dealership Location: ");
        String loc = input.nextLine();
        dealerships.add(new Dealership(loc));
        System.out.println("New dealership added");
    }

    private static void removeDealership() {
        System.out.println("Dealership Location: ");
        String loc = input.nextLine();
        int index = -1;
        for (int i = 0; i < dealerships.size() && index < 0; i++)
            if (dealerships.get(i).getLocation().equals(loc))
                index = i;

        if (index > -1) {
            dealerships.remove(index);
            System.out.printf("Removed dealership at %s%n", loc);
        }
        else
            System.out.println("Dealership not found, no changes made");
    }

    private static void selectDealership() {
        if (dealerships.size() < 1) {
            System.out.println("No dealerships to display");
            return;
        }

        for (int i = 0; i < dealerships.size(); i++)
            System.out.printf("%3d) %s%n", i + 1, dealerships.get(i));

        System.out.print("Enter dealership number or 0 to cancel: ");
        int index = Integer.parseInt(input.nextLine());
        if (index > 0 && index <= dealerships.size()) {
            Dealership dealership = dealerships.get(index - 1);

            DealershipManager manager = new DealershipManager(dealership, input);
            manager.dealershipMenu();
        }
    }
}
