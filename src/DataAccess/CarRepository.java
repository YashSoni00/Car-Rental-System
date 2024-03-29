package DataAccess;

import Domain.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarRepository {
//    public CarRepository() {
//        DataBase carsData = new DataBase();
//    }

    // Method to add a new car to the database
    public void addCar(String carId, String make, String model, int year, double pricePerDay) {
        // Code to add a new car to the cars table in the database class
        Car car = new Car(carId, make, model, year, pricePerDay, false);
        DataBase.addCar(car);
    }

    // Method to retrieve a car by its ID
    public Car getCarById(String id) {
        return DataBase.getCarById(id);
    }

    // Method to update a car's information
    public void updateCar(String carId, boolean b) {
        // Code to update a car's information in the cars table in the database class
        Car car = getCarById(carId);
        if (car != null) {
            car.setRented(b);
            DataBase.updateCarData(car, b);
        }
    }

    // Method to delete a car from the database
    public void deleteCar(String id) {
        // Code to delete a car from the cars table in the database class
        DataBase.removeCar(id);
    }

    // Method to check if a car is rented
    public boolean isCarRented(String carId) {
        Car car = getCarById(carId);
        if (car != null) {
            return car.isRented();
        }
        return false;
    }

    // Method to list all cars in the database
    public void getAllCars() {
        // Print the list of cars
        for (String[] car : DataBase.cars) System.out.println(Arrays.toString(car));
    }

    public static void main(String[] args) {
        CarRepository carRepository = new CarRepository();
        carRepository.getAllCars();
    }

    public void getAllAvailableCars() {
        List<Car> availableCars = new ArrayList<>();
        for (String[] car : DataBase.cars) {
            if (!Boolean.parseBoolean(car[5])) {
                availableCars.add(new Car(car[0], car[1], car[2], Integer.parseInt(car[3]),
                        Double.parseDouble(car[4]), Boolean.parseBoolean(car[5])));
            }
        }
        for (Car car : availableCars) {
            System.out.println(car);
        }
    }
}