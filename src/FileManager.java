import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    private final String filename;

    public FileManager(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public boolean fileExists() {
        File file = new File(filename);
        return file.exists();
    }

    private String carToString(Car c) {
        return String.format("%s|%s|%d%n",
                c.getMake(),
                c.getModel(),
                c.getYear()
        );
    }

    public void writeFile(List<Dealership> dealerships) {
        try (FileWriter outfile = new FileWriter(filename)) {
            for (Dealership d : dealerships)
                outfile.write(String.format("%s%n", dealershipToString(d)));
        }
        catch (IOException e) {
            System.out.println("Error writing file");
        }
    }

    private String dealershipToString(Dealership d) {
        StringBuilder str = new StringBuilder();
        str.append(d.getLocation()).append(String.format("%n"));
        for (Car c : d.getInventory())
            str.append(carToString(c));

        return str.toString();
    }

    private Car parseCar(String strCar) {
        String[] args = strCar.split("\\|");
        return new Car(args[0], args[1], Integer.parseInt(args[2]));
    }

    private Dealership readDealership(Scanner infile) {
        Dealership d = new Dealership(infile.nextLine());
        for (String carline = infile.nextLine();
                !carline.equals("");
                carline = infile.nextLine())
            d.addCar(parseCar(carline));

        return d;
    }

    public ArrayList<Dealership> readFile() {
        ArrayList<Dealership> list = new ArrayList<>();

        try (FileReader fr = new FileReader(filename)) {
            Scanner infile = new Scanner(fr);
            while (infile.hasNextLine())
                list.add(readDealership(infile));
            infile.close();
        }
        catch (IOException e) {
            System.out.println("Error reading file");
        }

        return list;
    }
}
