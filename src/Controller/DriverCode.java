package Controller;

import DataAccess.DataBase;
import Service.RentalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// Controller -> Service -> DataAccess -> Domain
public class DriverCode {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws ParseException {
        // Setup the database
        DataBase db = new DataBase();

        // Call the menu method
        menu();

        // Save the data to the CSV file
        DataBase.writeData("cars.csv", DataBase.cars);
        DataBase.writeData("customers.csv", DataBase.customers);
        DataBase.writeData("rental_history.csv", DataBase.rentalHistory);

    }

    private static void menu() throws ParseException {
        int ch;
        do {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Rent a car");
            System.out.println("2. Return a car");
            System.out.println("3. Admin login");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            ch = scanner.nextInt();

            switch (ch) {
                case 1:
                    rentCar();
                    break;
                case 2:
                    returnCar();
                    break;
                case 3:
                    adminLogin();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while (ch != 4);
    }

    private static void adminLogin() {
        System.out.println("\n--- Admin Login ---");
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        // Call the service method to authenticate the admin
        RentalService rentalService = new RentalService();
        if (rentalService.authenticateAdmin(username, password)) {
            adminMenu();
        } else {
            System.out.println("Invalid username or password");
        }
    }

    private static void adminMenu() {
        int ch;
        do {
            System.out.println("Admin Menu");
            System.out.println("1. Add car");
            System.out.println("2. Remove car");
            System.out.println("3. Show all cars");
            System.out.println("4. Show all customers");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            ch = scanner.nextInt();

            switch (ch) {
                case 1:
                    addCar();
                    break;
                case 2:
                    deleteCar();
                    break;
                case 3:
                    showAllCars();
                    break;
                case 4:
                    showAllCustomers();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while (ch != 5);
    }

    private static void deleteCar() {
        RentalService rentalService = new RentalService();
        System.out.println("Remove a car");
        rentalService.showAllCars();
        System.out.print("Enter the car ID: ");
        String carId = scanner.next();

        // Call the service method to delete a car
        rentalService.deleteCar(carId);
    }

    private static void showAllCustomers() {
        RentalService rentalService = new RentalService();
        rentalService.showAllCustomers();
    }

    private static void showAllCars() {
        RentalService rentalService = new RentalService();
        rentalService.showAllCars();
    }

    private static void addCar() {
        RentalService rentalService = new RentalService();
        System.out.println("Add a car");
        System.out.print("Enter car ID: ");
        String carId = scanner.next();
        System.out.print("Enter car make: ");
        String carMake = scanner.next();
        System.out.print("Enter car model: ");
        String carModel = scanner.next();
        System.out.print("Enter car year: ");
        int carYear = scanner.nextInt();
        System.out.print("Enter car price: ");
        double carPrice = scanner.nextDouble();

        // Call the service method to add a car
        rentalService.addCar(carId, carMake, carModel, carYear, carPrice);
    }

    private static void rentCar() throws ParseException {
        RentalService rentalService = new RentalService();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("Rent a car");
        System.out.print("Enter your customer ID: ");
        String customerId = scanner.next();

        // Check if the customer ID is registered in the database or not
        rentalService.validateCustomer(customerId);

        // Show the list of available cars
        rentalService.showAvailableCars();

        System.out.print("Enter the car ID: ");
        String carId = scanner.next();

        // Check if the car ID is valid or not
        if (!rentalService.validateCar(carId)) {
            return;
        }
        System.out.print("Enter start date (yyyy-MM-dd): ");
        // TODO: Keep only the date part and ignore the time part
        Date startDate = sdf.parse(scanner.next());
        System.out.print("Enter return date (yyyy-MM-dd): ");
        Date returnDate = sdf.parse(scanner.next());

        // Call the service method to rent a car
        rentalService.rentCar(carId, customerId, startDate, returnDate);
    }

    private static void returnCar() {
        RentalService rentalService = new RentalService();
        System.out.println("Return a car");
        System.out.print("Enter your customer ID to return a car: ");
        String customerId = scanner.next();

        // Check if the customer ID is registered in the database or not
        if (!rentalService.validateCustomer(customerId)) {
            return;
        }

        // Show the list of cars rented by the customer
        if (!rentalService.showRentedCars(customerId)) {
            return;
        }

        System.out.print("Enter the car ID: ");
        String carId = scanner.next();

        // Call the service method to return a car
        rentalService.returnCar(carId, customerId);
    }
}