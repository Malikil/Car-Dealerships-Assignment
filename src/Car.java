public class Car {
    private final String make;
    private final String model;
    private final int year;

    public Car() {
        make = "";
        model = "";
        year = 0;
    }

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return String.format("Car: %d %s %s", year, make, model);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Car) {
            Car c = (Car) o;
            return c.make.equals(make) &&
                    c.model.equals(model) &&
                    c.year == year;
        }
        else
            return super.equals(o);
    }
}
