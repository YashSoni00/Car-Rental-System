package DataAccess;

import Domain.Car;
import Domain.Customer;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// This class will be used to replicate the database connection with the help of CSV files
public class DataBase {
    public static final String ADMIN_USERNAME = "admin";
    public static final String ADMIN_PASSWORD = "password";
    // List to store the cars and customers
    public static List<String[]> cars = new ArrayList<>();
    public static List<String[]> customers = new ArrayList<>();
    public static List<String[]> rentalHistory = new ArrayList<>();

    public DataBase() {
        DefaultCarsData();
        DefaultCustomerData();
        DefaultRentalHistory();
    }

    public static void addCar(Car car) {
        cars.add(new String[]{car.getId(), car.getMake(), car.getModel(), String.valueOf(car.getYear()),
                String.valueOf(car.getPricePerDay()), String.valueOf(car.isRented())});
        writeData("cars.csv", cars);
    }

    // Method to create the CSV file for the cars
    public void DefaultCarsData() {
        // Code to create the CSV file for the cars
//        cars.add(new String[]{"id", "make", "model", "year", "price_per_day", "is_rented"});
        cars.addAll(readData("cars.csv")); // Reading data from the CSV file
//        cars.add(new String[]{"1", "Toyota", "Corolla", "2019", "50", "false"});
//        cars.add(new String[]{"2", "Honda", "Civic", "2018", "60", "false"});
//        cars.add(new String[]{"3", "Ford", "Fusion", "2017", "70", "false"});
//        cars.add(new String[]{"4", "Chevrolet", "Malibu", "2016", "80", "false"});
//        cars.add(new String[]{"5", "Nissan", "Altima", "2015", "90", "false"});
    }


    // Method to create the CSV file for the customers
    public void DefaultCustomerData() {
        // Code to create the CSV file for the customers
//        customers.add(new String[]{"customer_id", "name", "address", "phone_number", "email"});
        customers.addAll(readData("customers.csv")); // Reading data from the CSV file
//        customers.add(new String[]{"1", "John Doe", "123 Controller.Main St", "123-456-7890", "johndoe@gmail.com"});
//        customers.add(new String[]{"2", "Jane Smith", "456 Elm St", "456-789-0123", "janesmith@gmail.com"});
//        customers.add(new String[]{"3", "Tom Brown", "789 Oak St", "789-012-3456", "tombrown@gmail.com"});
//        customers.add(new String[]{"4", "Sue White", "101 Pine St", "101-112-1314", "suewhite@gmail.com"});
//        customers.add(new String[]{"5", "Mike Black", "202 Cedar St", "202-303-4041", "mikeblack@gmail.com"});
    }

    public void DefaultRentalHistory() {
        // Code to create the CSV file for the rental history
//         rentalHistory.add(new String[]{"customer_id", "car_id", "rental_date", "return_date"});
        rentalHistory.addAll(readData("rental_history.csv")); // Reading data from the CSV file
//         rentalHistory.add(new String[]{"1", "1", "2021-01-01", "2021-01-05"});
//         rentalHistory.add(new String[]{"2", "2", "2021-01-02", "2021-01-06"});
//         rentalHistory.add(new String[]{"3", "3", "2021-01-03", "2021-01-07"});
//         rentalHistory.add(new String[]{"4", "4", "2021-01-04", "2021-01-08"});
//         rentalHistory.add(new String[]{"5", "5", "2021-01-05", "2021-01-09"});
    }

    public static Customer getCustomerById(String customerId) {
        for (String[] customer : customers) {
            if (customer[0].equals(customerId)) {
                return new Customer(customer[0], customer[1], customer[2], customer[3], customer[4]);
            }
        }
        return null;
    }

    public static void updateCustomerData(Customer customer) {
        Scanner sc = new Scanner(System.in);
        for (String[] c : customers) {
            if (c[0].equals(customer.getCustomerId())) {
                System.out.println("Enter the updated customer name: ");
                c[1] = sc.nextLine();
                System.out.println("Enter the updated customer address: ");
                c[2] = sc.nextLine();
                System.out.println("Enter the updated customer phone number: ");
                c[3] = sc.nextLine();
                System.out.println("Enter the updated customer email: ");
                c[4] = sc.nextLine();
                break;
            }
        }
    }

    public static void removeCar(String id) {
        for (String[] car : DataBase.cars) {
            if (car[0].equals(id)) {
                DataBase.cars.remove(car);
                break;
            }
        }
        writeData("cars.csv", cars);
    }

    public static void removeCustomer(String customerId) {
        for (String[] customer : DataBase.customers) {
            if (customer[0].equals(customerId)) {
                DataBase.customers.remove(customer);
                break;
            }
        }
    }


    // Code to write the cars list to a CSV file
    public static void writeData(String CSV_FILE_NAME, List<String[]> data) {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File(CSV_FILE_NAME);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file, false);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeAll(data); // Writing data to a csv file
            writer.close(); // closing writer connection
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static List<String[]> readData(String CSV_FILE_NAME) {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File(CSV_FILE_NAME);
        try {
            // create FileReader object with file as parameter
            FileReader inputfile = new FileReader(file);

            // create CSVReader object filewriter object as parameter
            CSVReader reader = new CSVReader(inputfile);
            List<String[]> cars = reader.readAll(); // Writing data to a csv file
            reader.close(); // closing writer connection
            return cars;
        } catch (CsvException | IOException e) {
            throw new RuntimeException(e);
        }
    }

        public static Car getCarById(String id) {
        for (String[] car : cars) {
            if (car[0].equals(id)) {
                return new Car(car[0], car[1], car[2], Integer.parseInt(car[3]), Double.parseDouble(car[4]), Boolean.parseBoolean(car[5]));
            }
        }
        return null;
    }

    public static void updateCarData(Car car, boolean b) {
        for (String[] c : cars) {
            if (c[0].equals(car.getId())) {
                c[5] = String.valueOf(b);
                break;
            }
        }
        writeData("cars.csv", cars);
    }

    // Main method to test the code
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();

        // Write the data to the CSV file
//        writeData("cars.csv", cars);
//        writeData("customers.csv", customers);
//        writeData("rental_history.csv", rentalHistory);

//         Read the data from the CSV file
//        List<String[]> cars = readData("cars.csv");
//        for (String[] car : cars) {
//            System.out.println(Arrays.toString(car));
//        }
//        List<String[]> customers = readData("customers.csv");
//        for (String[] customer : customers) {
//            System.out.println(Arrays.toString(customer));
//        }
//        List<String[]> rentalHistory = readData("rental_history.csv");
//        for (String[] rental : rentalHistory) {
//            System.out.println(Arrays.toString(rental));
//        }
    }
}
