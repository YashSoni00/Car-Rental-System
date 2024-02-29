package Service;

import DataAccess.CarRepository;
import DataAccess.CustomerRepository;
import DataAccess.DataBase;

import java.util.Date;

public class RentalService {
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    // Constructor
    public RentalService() {
        carRepository = new CarRepository();
        customerRepository = new CustomerRepository();
    }

    public void showAllCars() {
        // Call the car repository to get all cars
         carRepository.getAllCars();
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
        if (carRepository.getCarById(carId) != null && customerRepository.getCustomerById(customerId) != null &&
                carRepository.isCarRented(carId)) {
            // Update the car's status to not rented
            carRepository.updateCar(carId, false);

            // Update the customer's rental history if needed
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

    // Additional methods can be added for other rental-related operations
}

