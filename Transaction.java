import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * This class is for Transaction which containes all elements of a Tansaction
 * @author Seyon Rajagopal
 * Student ID: 500885401
 * Class Name: Transaction.java
 * 
 */
public class Transaction {
    //instance variables
    private int transactionid;
    private GregorianCalendar date;
    private String salesPerson;
    private Car car;
    private String type;
    private double price;

    /**
     * Constructor method
     * @param transactionid
     * @param date
     * @param car
     * @param salesPerson
     * @param type
     * @param price
     */
    public Transaction(int transactionid, GregorianCalendar date, Car car, String salesPerson, String type, double price){
        this.transactionid = transactionid;
        this.date = date;
        this.car = car;
        this.salesPerson = salesPerson;
        this.type = type;
        this.price = price;        
    }

    /**
     * A string of formatted text for the transaction to  be displayed
     * @return formatted string of transaction
     */
    public String display() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");
		return "Date: "+ sdf.format(date.getTime()) + "     Sales Person: " + salesPerson + "\nTransaction ID: " + transactionid + "          Sale Type: " + type + "\nItem:\n------------------------------------------------\nCar VIN: " +car.getVIN() + "                          $" + String.format("%.2f",price) + "\n------------------------------------------------"; 
    }
    
    /**
     * @return the transactionid
     */
    public int getTransactionid() {
        return transactionid;
    }

    /**
     * @param transactionid the transactionid to set
     */
    public void setTransactionid(int transactionid) {
        this.transactionid = transactionid;
    }

    /**
     * @return the date
     */
    public GregorianCalendar getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    /**
     * @return the salesPerson
     */
    public String getSalesPerson() {
        return salesPerson;
    }

    /**
     * @param salesPerson the salesPerson to set
     */
    public void setSalesPerson(String salesPerson) {
        this.salesPerson = salesPerson;
    }

    /**
     * @param car the car to set
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the car
     */
    public Car getCar() {
        return car;
    }
}