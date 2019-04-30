import java.util.*;

/**
 * this class is for the Car dealership which containes all elements of a a car dealership
 * @author Seyon Rajagopal
 * Student ID: 500885401
 * Class Name: CarDealership.java 
 * 
 */
public class CarDealership {
	
	// instance variables
	private ArrayList<Car> cars;
	private SalesTeam sales;
	private AccountingSystem accounting;

	private double minPrice;
	private double maxPrice;
	private boolean electric;
	private boolean awd;
	private boolean price;
	private Car boughtCar = null;

	private int month;
	private int day;

	/**
	 * Constructor
	 */
	public CarDealership(){
		cars = new ArrayList<Car>();
		sales = new SalesTeam();
		accounting = new AccountingSystem();
	}

	/**
	 * add all cars from arraylist to another
	 * @param newCars Arraylist of cars
	 */
	public void addCars(ArrayList<Car> newCars) {
		cars.addAll(newCars);
		if (newCars.size() <= 0){
			throw new IndexOutOfBoundsException("\nSorry, There Are NO Cars In The File!");
		}
	}

	/**
	 * check to see if array of cars is filled
	 * @return true if array of carrs is filled
	 */
	public boolean checkArrayFilled(){
		if (cars.size() <= 0){
			return false;
		}
		return true;
	}

	/**
	 * buys a car using the VIN which also removes it from the arraylist.
	 * The method also generates a random date of year 2019 for the transaction
	 * and adds it to accounting system
	 * @param VIN of car
	 * @return accounting.add(...) String which prints out the transaction
	 */
	public String buyCar(int VIN){
		for (int i = 0; i < cars.size(); i++){
			Car currentCar = cars.get(i);
			if (VIN == currentCar.getVIN()){
				boughtCar = cars.get(i);
				cars.remove(boughtCar);
				String salesperson = sales.getSalesPerson();
				int month = (int)(Math.random()*(12));
				int day = (int)(Math.random()*(31));
				int year = 2019;
				GregorianCalendar calendar;
				calendar = new GregorianCalendar(year,month,day);
				return accounting.add(calendar, boughtCar, salesperson, "BUY", boughtCar.getPrice());
			}
		}
		
		if(cars.size() <= 0){
			throw new IllegalArgumentException("\nThere Are NO Cars To Filter. Please Add Cars Using Command 'ADD' First");
		}
		return null;
	}

	/**
	 * Finds the transaction using transaction id and adds the car back to arraylist
	 * of cars. A return date of < 30 from original transaction is also generated for
	 * the transaction
	 * @param transid the transaction id
	 */
	public void returnCar(int transid) {
		Transaction trans = accounting.getTransaction(transid);

		if (trans != null) {
			Car car = trans.getCar();
			String transtype = trans.getType();

			//check to see if car has been returned by seeing if it has been put back in arraylist of cars
			if (transtype.equals("BUY") && cars.contains(car)){
				throw new IllegalArgumentException("\nSorry, That Item Has Already Been Returned!");
			}

			if (transtype.equals("BUY")) {
				String salesperson = sales.getSalesPerson();

				// Making sure return date is always after buy date
				int month = trans.getDate().get(Calendar.MONTH);
				int day = trans.getDate().get(Calendar.DAY_OF_MONTH);
				int year = trans.getDate().get(Calendar.YEAR);
				int returnDay = (int) (Math.random() * (30 - day)) + day;
				GregorianCalendar calendar;
				calendar = new GregorianCalendar(year, month, returnDay);
				accounting.add(calendar, car, salesperson, "RET", -1 * car.getPrice());
				cars.add(car);
			} else {
				throw new IllegalArgumentException("\nSorry, That Item Can't Be Returned! Cant Return A Return!");
			}
		} else {
			throw new IllegalArgumentException("\nSorry That Transaction Doesnt Exist");
		}
	}


	/**
	 * changes which car is displayed based on which filter is set
	 */
	public void displayInventory(){
		for (int i = 0; i < cars.size(); i++){
			Car currentCar = cars.get(i);
			String displayCar = String.format("%-3d %s",(i+1), currentCar.display());
			// filter check to see if all three filters are being used
			if(electric && price && awd){
				if (currentCar.getPower() == Car.ELECTRIC_MOTOR && (currentCar.getPrice() >= minPrice && currentCar.getPrice() <= maxPrice) && currentCar.isAWD()){
					System.out.println(displayCar);
				}
			// filter check to see if price and awd filters are being used
			}else if(price && awd){
				if((currentCar.getPrice() >= minPrice && currentCar.getPrice() <= maxPrice) && currentCar.isAWD()){
					System.out.println(displayCar);
				}
			// filter check to see if electric and price filters are being used
			}else if(electric && price){
				if (currentCar.getPower() == Car.ELECTRIC_MOTOR && (currentCar.getPrice() >= minPrice && currentCar.getPrice() <= maxPrice)){
					System.out.println(displayCar);
				}
			// filter check to see if awd and electric filters are being used
			}else if(awd && electric){
				if (currentCar.isAWD() && currentCar.getPower() ==  Car.ELECTRIC_MOTOR){
					System.out.println(displayCar);
				}
			// filter check to see if awd filter is being used
			}else if(awd){
				if(currentCar.isAWD()){
					System.out.println(displayCar);
				}
			// filter check to see if eletric filter is being used
			}else if(electric){
				if(currentCar.getPower() == Car.ELECTRIC_MOTOR){
					System.out.println(displayCar);
				}
			// filter check to see if price filter is being used			
			}else if (price){
				if(currentCar.getPrice() >= minPrice && currentCar.getPrice() <= maxPrice){
					System.out.println(displayCar);
				}
			// if no filters are in place just displays the car
			}else{
				System.out.println(displayCar);
			}
		}
	}

	/**
	 * sets boolean for filter by electrc to true
	 */
	public void filterByElectric(){
		electric = true;
	}

	/**
	 * sets boolean for filter by AWD to true
	 */
	public void filterByAWD(){
		awd = true;
	}
 
	/**
	 * sets boolean for filter by price to true
	 * @param minPrice
	 * @param maxPrice
	 */
	public void filterByPrice(double minPrice, double maxPrice){
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		price = true;
	}

	/**
	 * clears booleans for filters by setting all to false (reset)
	 */
	public void FiltersClear(){
		electric = false;
		awd = false;
		price = false;
	}
	
	/**
	 * Sorts all cars in array in order of price
	 */
	public void sortByPrice(){
		Collections.sort(cars);
	}

	/**
	 * comparator class that compares each cars safety rating to help sort
	 */
	class SafetyRatingComparator implements Comparator<Car>{
		public int compare(Car a, Car b){
			if (a.getSafetyRating() < b.getSafetyRating()){
				return 1;
			}
			if (a.getSafetyRating() > b.getSafetyRating()){
				return -1;
			}
			return 0;
		}
	}
 
	/**
	 * Sorts all cars in array in order of safety rating
	 */
	public void sortBySafetyRating(){
		Collections.sort(cars, new SafetyRatingComparator());
	}

	/**
	 * comparator class that compares each cars max range to help sort
	 */
	class MaxRangeComparator implements Comparator<Car>{
		public int compare(Car a, Car b){
			if (a.getMaxRange() < b.getMaxRange()){
				return 1;
			}
			if (a.getMaxRange() > b.getMaxRange()){
				return -1;
			}
			return 0;
		}
	}

	/**
	 * Sorts all cars in array in order of max range
	 */
	public void sortByMaxRange(){
		Collections.sort(cars, new MaxRangeComparator());
	}

	/**
	 * @return the carBought
	 */
	public Car getCarBought() {
		return boughtCar;
	}

	/**
	 * @return the cars
	 */
	public ArrayList<Car> getCars() {
		return cars;
	}

	/**
	 * @param cars the cars to set
	 */
	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}

	/**
	 * @return the sales
	 */
	public SalesTeam getSales() {
		return sales;
	}

	/**
	 * @param sales the sales to set
	 */
	public void setSales(SalesTeam sales) {
		this.sales = sales;
	}

	/**
	 * @return the accounting
	 */
	public AccountingSystem getAccounting() {
		return accounting;
	}

	/**
	 * @param accounting the accounting to set
	 */
	public void setAccounting(AccountingSystem accounting) {
		this.accounting = accounting;
	}

	/**
	 * @return the minPrice
	 */
	public double getMinPrice() {
		return minPrice;
	}

	/**
	 * @param minPrice the minPrice to set
	 */
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	/**
	 * @return the maxPrice
	 */
	public double getMaxPrice() {
		return maxPrice;
	}

	/**
	 * @param maxPrice the maxPrice to set
	 */
	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	/**
	 * @return the electric
	 */
	public boolean isElectric() {
		return electric;
	}

	/**
	 * @param electric the electric to set
	 */
	public void setElectric(boolean electric) {
		this.electric = electric;
	}

	/**
	 * @return the awd
	 */
	public boolean isAwd() {
		return awd;
	}

	/**
	 * @param awd the awd to set
	 */
	public void setAwd(boolean awd) {
		this.awd = awd;
	}

	/**
	 * @return the price
	 */
	public boolean isPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(boolean price) {
		this.price = price;
	}

	/**
	 * @return the boughtCar
	 */
	public Car getBoughtCar() {
		return boughtCar;
	}

	/**
	 * @param boughtCar the boughtCar to set
	 */
	public void setBoughtCar(Car boughtCar) {
		this.boughtCar = boughtCar;
	}

	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}

}