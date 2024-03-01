package DataAccess;

import Domain.Customer;
import Domain.Car;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Date;

public class CustomerRepository {
//    public CustomerRepository() {
//        DataBase db = new DataBase();
//    }

    // Method to add a new customer to the database
    public void addCustomer(String customerId, String name, String address, String phoneNumber, String email) {
        // TODO: Code to add a new customer to the customers table in the database class
        DataBase.customers.add(new String[]{customerId, name, address, phoneNumber, email});
    }

    // Method to retrieve a customer by their ID
    public Customer getCustomerById(String customerId) {
        return DataBase.getCustomerById(customerId);
    }

    // Method to update a customer's information
    public void updateCustomer(Customer customer) {
        DataBase.updateCustomerData(customer);
    }

    // Method to delete a customer from the database
    public void deleteCustomer(String customerId) {
        DataBase.removeCustomer(customerId);
    }

    // Method to list all customers in the database
    public void getAllCustomers() {
        // Print the list of customers
        for (String[] customer : DataBase.customers) {
            System.out.println(Arrays.toString(customer));
        }
    }

    public void addRentalToCustomer(String customerId, String carId, Date startDate, Date returnDate) {
        // Code to add a rental to the customer's rental history in the database class
        DataBase.rentalHistory.add(new String[]{customerId, carId, startDate.toString(), returnDate.toString()});
    }

    public void removeRentalFromCustomer(String customerId, String carId) {
        // Code to remove a rental from the customer's rental history in the database class
        for (String[] rental : DataBase.rentalHistory) {
            if (rental[0].equals(customerId) && rental[1].equals(carId)) {
                DataBase.rentalHistory.remove(rental);
                return;
            }
        }
        throw new IllegalArgumentException("Rental not found for customer " + customerId + " and car " + carId);
    }

    public boolean displayRentals(String customerId) {
        boolean found = false;
        for (String[] rental : DataBase.rentalHistory) {
            if (String.valueOf(rental[0]).equals(customerId)) {
                System.out.println("Car ID: " + Car.getCarDetails(rental[1]) +
                        " rented from " + rental[2] + " to " + rental[3]);
                found = true;
            }
        }
        return found;
    }
}
