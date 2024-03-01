import Controller.DriverCode;
// Main -> Controller -> Service -> DataAccess -> Domain
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        // TODO: Check if the code is working as expected
        // Only admin panel is working
        System.out.println("Welcome to Car Rental Service!");
        try {
            DriverCode.main(args);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
