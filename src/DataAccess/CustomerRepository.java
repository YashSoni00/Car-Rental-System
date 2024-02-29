package DataAccess;

import Domain.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerRepository {

    // Method to add a new customer to the database
    public void addCustomer(Customer customer) {
        DataBase.customers.add(new String[]{customer.getCustomerId(), customer.getName(), customer.getAddress(),
                customer.getPhoneNumber(), customer.getEmail()});
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
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        for (String[] customer : DataBase.customers) {
            customers.add(new Customer(customer[0], customer[1], customer[2], customer[3], customer[4]));
        }
        return customers;
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
}
