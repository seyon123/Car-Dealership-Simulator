/**
 * this class is for Car which containes all elements of a Car
 * @author Seyon Rajagopal
 * Student ID: 500885401
 * Class Name: Car.java
 * 
 */

public class Car extends Vehicle implements Comparable<Car> {

	// instance variables
	private int model;
	private int maxRange;
	private double safetyRating;
	private boolean AWD;
	private double price;

	// constant variables for car types
	public static final int SEDAN = 0;
	public static final int SUV = 1;
	public static final int SPORTS = 2;
	public static final int MINIVAN = 3;

	/**
	 * constructor
	 * @param mfr the manufacturer
	 * @param color
	 * @param power
	 * @param numWheels
	 * @param model
	 * @param maxRange
	 * @param safetyRating
	 * @param AWD
	 * @param price
	 */
	public Car(String mfr, String color, int power, int numWheels, int model, int maxRange, double safetyRating, boolean AWD, double price) {
		super(mfr, color, power, numWheels);
		this.model = model;
		this.maxRange = maxRange;
		this.safetyRating = safetyRating;
		this.AWD = AWD;
		this.price = price;
	}


	/**
	 * returns whether or not 2 cars are equal
	 * @param other
	 * @return boolean value if model and AWD are the same and both are equal
	 */
	public boolean equals(Object other) {
		Car othCar = (Car) other;
		return super.equals(othCar) && this.model == othCar.model && this.AWD == othCar.AWD && this.safetyRating == othCar.safetyRating;
	}

	/**
	 * compartor method for comparing price
	 * @param other
	 * @return integer of 0,1,or-1
	 */
	public int compareTo(Car other) {
		if (this.price < other.price){
			return -1;
		}else if(this.price > other.price){
			return 1;
		}
		return 0;
	}

	/**
	 * displays cars in a formatted matter
	 * @return formatted string
	 */
	public String display() {
		return String.format("%s %-9s %-5d %-6.2f %-5s $%-10.2f %-15s", super.display(), modelType(this.model), this.maxRange, this.safetyRating, awdDisplay(this.AWD), this.price, super.powerType());
	}


	/**
	 * helper method to convert model value to string
	 * @param model
	 * @return string of model type
	 */
	public String modelType(int model){
		if(model == 0){
			return "SEDAN";
		}else if(model == 1){
			return "SUV";
		}else if(model == 2){
			return "SPORTS";
		}else if(model == 3){
			return "MINIVAN";
		}
		return "";
	}

	/**
	 * helper method to covert awd value to string
	 * @param awd
	 * @return awd  string value
	 */
	public String awdDisplay(boolean awd){
		if (awd){
			return "AWD";
		}else{
			return "2WD";
		}
	}

	/**
	 * @return the model
	 */
	public int getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(int model) {
		this.model = model;
	}

	/**
	 * @return the maxRange
	 */
	public int getMaxRange() {
		return maxRange;
	}

	/**
	 * @param maxRange the maxRange to set
	 */
	public void setMaxRange(int maxRange) {
		this.maxRange = maxRange;
	}

	/**
	 * @return the safetyRating
	 */
	public double getSafetyRating() {
		return safetyRating;
	}

	/**
	 * @param safetyRating the safetyRating to set
	 */
	public void setSafetyRating(double safetyRating) {
		this.safetyRating = safetyRating;
	}

	/**
	 * @return the aWD
	 */
	public boolean isAWD() {
		return AWD;
	}

	/**
	 * @param aWD the aWD to set
	 */
	public void setAWD(boolean aWD) {
		AWD = aWD;
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
}