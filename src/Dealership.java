import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private final String location;
    private final ArrayList<Car> inventory;

    public Dealership(String location) {
        this.location = location;
        this.inventory = new ArrayList<>();
    }

    public String getLocation() {
        return location;
    }

    public List<Car> getInventory() {
        ArrayList<Car> temp = new ArrayList<>();
        for (Car c : inventory)
            temp.add(new Car(c.getMake(), c.getModel(), c.getYear()));
        return temp;
    }

    public void addCar(Car car) {
        inventory.add(car);
    }

    public boolean removeCar(Car car) {
        return inventory.remove(car);
    }

    @Override
    public String toString() {
        return String.format("%s - %d cars", location, inventory.size());
    }
}
