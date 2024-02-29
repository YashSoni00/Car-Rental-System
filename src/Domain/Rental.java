package Domain;

import java.util.Calendar;
import java.util.Date;

public class Rental {
    private final String customerId;
    private final String carId;
    private final Date startDate;
    private final Date endDate;
    private final double cost;
    private boolean isReturned;

    Date getStartDate() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        date.setDate(Calendar.DATE);
        return date;
    }

    public Rental(String carId, String customerId, Date endDate, double cost) {
        this.carId = carId;
        this.customerId = customerId;
        this.startDate = getStartDate();
        this.endDate = endDate;
        this.cost = cost * (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
    }


    public String getCarId() {
        return carId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getCost() {
        return cost;
    }

    public boolean getIsReturned() {
        return isReturned;
    }

    public void returnCar() {
        isReturned = true;
    }

}
