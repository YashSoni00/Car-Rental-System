package Service;

import DataAccess.CarRepository;
import DataAccess.CustomerRepository;
import DataAccess.DataBase;

import java.util.Date;
import java.util.Scanner;

public class RentalService {
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    Scanner scanner = new Scanner(System.in);

    // Constructor
    public RentalService() {
        carRepository = new CarRepository();
        customerRepository = new CustomerRepository();
    }

    // Call the car repository to get all cars existing in the database
    public void showAllCars() {
         carRepository.getAllCars();
    }

    // Method to show a list of all cars available for rent
    public void showAvailableCars() {
        // Call the car repository to get all available cars
        carRepository.getAllAvailableCars();
    }

    // Method to rent a car
    public void rentCar(String carId, String customerId, Date startDate, Date returnDate) {
    int numberOfDays = (int) ((returnDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
        if (carRepository.getCarById(carId) != null && customerRepository.getCustomerById(customerId) != null &&
                !carRepository.isCarRented(carId)) {
            // Business logic to calculate the rental cost
            double rentalCost = numberOfDays * carRepository.getCarById(carId).getPricePerDay();

            // Update the car's status to rented
            carRepository.updateCar(carId, true);

            // Add the rental to the customer's history
            customerRepository.addRentalToCustomer(customerId, carId, startDate, returnDate);

            System.out.println("Car rented successfully. Total cost: " + rentalCost);
        } else {
            System.out.println("Car is not available for rent or customer does not exist.");
        }
    }

    // Method to return a car
public void returnCar(String carId, String customerId) {
        if (carRepository.getCarById(carId) != null && !carRepository.isCarRented(carId) &&
                customerRepository.getCustomerById(customerId) != null) {
            // Update the car's status to available
            carRepository.updateCar(carId, false);

            // Remove the rental from the customer's history
            customerRepository.removeRentalFromCustomer(customerId, carId);

            System.out.println("Car returned successfully.");
        } else {
            System.out.println("Car is not rented or customer does not exist.");
        }
    }

    public boolean authenticateAdmin(String username, String password) {
        // Code to authenticate the admin
        if (username.equals(DataBase.ADMIN_USERNAME) && password.equals(DataBase.ADMIN_PASSWORD)) {
            return true;
        }
        return false;
    }

    public void addCar(String carId, String carName, String carType, int carYear, double carPrice) {
        // Call the car repository to add a car
        carRepository.addCar(carId, carName, carType, carYear, carPrice);
    }

    public void showAllCustomers() {
        // Call the customer repository to get all customers
        customerRepository.getAllCustomers();
    }

    public void deleteCar(String carId) {
        // Call the car repository to delete a car
        carRepository.deleteCar(carId);
    }

    public void addCustomer(String customerId, String customerName, String customerAddress, String customerPhone, String customerEmail) {
        // Call the customer repository to add a customer
        customerRepository.addCustomer(customerId, customerName, customerAddress, customerPhone, customerEmail);
    }

    public boolean validateCustomer(String customerId) {
        if (customerRepository.getCustomerById(customerId) == null) {
            System.out.print("Register as a new customer (y/n): ");

            char choice = scanner.next().charAt(0);
            if (choice == 'y') {
                System.out.print("Enter your name: ");
                String name = scanner.next();
                System.out.print("Enter your address: ");
                String address = scanner.next();
                System.out.print("Enter your phone number: ");
                String phone = scanner.next();
                System.out.print("Enter your email: ");
                String email = scanner.next();

                addCustomer(customerId, name, address, phone, email);
            } else {
                System.out.println("Cannot rent or return a car without being a registered customer.");
                return false;
            }
        }
        return true;
    }

    public boolean showRentedCars(String customerId) {
        // Code to show the cars rented by a customer
        if (customerRepository.getCustomerById(customerId) != null) {
            if (customerRepository.displayRentals(customerId)) {
                return true;
            } else {
                System.out.println("No rentals found for customer -" + customerRepository.getCustomerById(customerId).getName());
                return false;
            }
        } else {
            System.out.println("Customer does not exist.");
        }
        return true;
    }

    public boolean validateCar(String carId) {
        // Code to validate the car ID
        if (carRepository.getCarById(carId) == null) {
            System.out.println("Car not found");
            return false;
        }
        return true;
    }
    // Additional methods can be added for other rental-related operations
}

