package Domain;

import DataAccess.DataBase;

public class Car {
    private final String carID;
    private final String make;
    private final String model;
    private final int year;
    private boolean isRented;
    private final double pricePerDay;

    public Car(String carID, String make, String model, int year, double pricePerDay, boolean isRented) {
        this.carID = carID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.isRented = false;
        this.pricePerDay = pricePerDay;
    }

    public static String getCarDetails(String s) {
        for (String[] car : DataBase.cars) {
            if (car[0].equals(s)) {
                return car[0] + ", Make: " + car[1] + ", Model " + car[2];
            }
        }
        return null;
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

    public boolean isRented() {
        return isRented;
    }

    public void rentCar() {
        isRented = true;
    }

    public void returnCar() {
        isRented = false;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public double getCarPrice() {
        return pricePerDay;
    }

    @Override
    public String toString() {
        return "ID: " + carID + ", Make: " + make + ", Model " + model + ", Year " + year + ", Price per day: " + pricePerDay;
    }

    public String getId() {
        return this.carID;
    }

    public void setRented(boolean b) {
        this.isRented = b;
    }
}
