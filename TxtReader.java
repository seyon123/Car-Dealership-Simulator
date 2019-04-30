import java.io.*;
import java.util.*;
/**
 * This class is for reading File IO which reads the file and creates car objects
 * Name: Seyon Rajagopal
 * Student ID: 500885401
 * Class Name: TxtReader.java 
 * 
 */

public class TxtReader {

    // instance variables
    private int engineValue,modelValue,price,range,batteryTime;
    private double rating;
    private boolean awdValue;
    private String mfr,color,model,engine,awd,battery;
    private final int NUM_WHEELS = 4;
    private Car car;
    
    // Array list of cars
    public ArrayList<Car> carsList = new ArrayList<Car>();
    
    /**
     * File reader that reads the text file of cars
     * @throws IOException
     */
    public void readFile() throws IOException{
        //create file
        File file = new File("cars.txt");    

        try {
            //Scanner for the file
            Scanner sc = new Scanner(file);

            //iterate through each line in file
            while(sc.hasNextLine()){
                //store each word in each line of file as a variable
                mfr = sc.next();
                color = sc.next();
                model = sc.next();
                engine = sc.next();
                rating = sc.nextDouble();
                range = sc.nextInt();
                awd = sc.next();
                price = sc.nextInt();    

                //call helper methods
                chooseEngine();
                chooseModel();
                chooseAWD();
                
                // Creates car object based on type of car
                if (engine.equals("ELECTRIC_MOTOR")){
                    batteryTime = sc.nextInt();
                    battery = "lithium";
                    car = new ElectricCar(mfr, color, engineValue, NUM_WHEELS, modelValue, range, rating, awdValue, price,batteryTime,battery);
                }else{
                    battery = "";
                    car = new Car(mfr, color, engineValue, NUM_WHEELS, modelValue, range, rating, awdValue, price);
                }
                //add car to arraylist
                carsList.add(car);
            }
            sc.close();
        // Catch exception where file is not found
        } catch (FileNotFoundException e) {
            System.out.println("\nUnable to open file");
            System.exit(0);
        }
        
    }


    /**
     * helper method to change engine type to integer values
     */
    public void chooseEngine(){
        if (engine.equals("ELECTRIC_MOTOR")){
            engineValue = Car.ELECTRIC_MOTOR;
        }else if(engine.equals("GAS_ENGINE")){
            engineValue = Car.GAS_ENGINE;
        }else {
            throw new IllegalArgumentException("Engine Type has incorrect format");
        }
    }

    /**
     * helper methods to change model type to integer values
     */
    public void chooseModel(){
        if(model.equals("SEDAN")){
			modelValue = 0;
		}else if(model.equals("SUV")){
			modelValue = 1;
		}else if(model.equals("SPORTS")){
			modelValue = 2;
		}else if(model.equals("MINIVAN")){
			modelValue = 3;
		}else {
            throw new IllegalArgumentException("Model Type has incorrect format");
        }
    }

    /**
     * helper method to change AWD to boolean values
     */
    public void chooseAWD(){
        if(awd.equals("AWD")){
            awdValue = true;
        }else if(awd.equals("2WD")){
            awdValue = false;
        }else {
            throw new IllegalArgumentException("AWD Type has incorrect format");
        }
    }
}