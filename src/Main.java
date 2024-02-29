import Controller.DriverCode;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        System.out.println("Welcome to Car Rental Service!");
        try {
            DriverCode.main(args);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
