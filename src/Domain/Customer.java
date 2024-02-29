package Domain;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String customerId;
    private final String name;
    private final String address;
    private final String phoneNumber;
    private final String email;
    private Car[] rentedCar = new Car[3];
    private List<Rental> rentalHistory;

    public Customer(String customerId, String name, String address, String phoneNumber, String email) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.rentalHistory = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Address: " + address + ", Phone Number: " + phoneNumber + ", Email: " + email;
    }

    public Car[] getRentedCar() {
        return rentedCar;
    }

    public void addRental(Rental rental) {
        rentalHistory.add(rental);
    }

    public List<Rental> getRentalHistory() {
        return rentalHistory;
    }

    public void displayRentals() {
        for (Rental rental : rentalHistory) {
            System.out.println(rental);
        }
    }

    public void setRentedCar(Car[] rentedCar) {
        for (int i = 0; i < rentedCar.length; i++) {
            this.rentedCar[i] = rentedCar[i];
        }
    }

}