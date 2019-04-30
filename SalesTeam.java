import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;


/**
 * This class is for SalesTeam which containes all elements of a SalesTeam
 * @author Seyon Rajagopal
 * Student ID: 500885401
 * Class Name: SalesTeam.java
 * 
 */
public class SalesTeam {
    
    //instance variables
    private LinkedList<String> salesPeople;
    private ListIterator<String> iterator;
    private ArrayList<Integer> numSales = new ArrayList<Integer>();
    
    /**
     * Constructor method
     */
    public SalesTeam(){
        salesPeople = new LinkedList<String>();
        salesPeople.addLast("Joe");
        salesPeople.addLast("Mary");
        salesPeople.addLast("James");
        salesPeople.addLast("Tim");
        salesPeople.addLast("Ben");
        salesPeople.addLast("Alice");
    }

    /**
     * gets a random sales person from the list of sales people
     * @return person 
     */
    public String getSalesPerson(){
        int ranPerson = (int)(Math.random()*(salesPeople.size()));
        iterator = salesPeople.listIterator(ranPerson);
        String person =  iterator.next();
        return person;
    }

    /**
     * gets all sales people as a string
     * @return salesPeopleString
     */
    public String getAllSalesPeople(){
        iterator = salesPeople.listIterator();
        String salesPeopleString = "";
        for (int i = 0; i < salesPeople.size(); i++){
            salesPeopleString += "\n" + (i+1) + ". " + iterator.next();
        }
        return salesPeopleString;
    }

    /**
     * finds sales person based on index
     * @param index
     * @return person
     */
    public String findSalesPerson(int index){
        iterator = salesPeople.listIterator(index);
        String person = iterator.next();
        return person;
    }

    /**
     * @return the salesPeople
     */
    public LinkedList<String> getSalesPeople() {
        return salesPeople;
    }

    /**
     * @param salesPeople the salesPeople to set
     */
    public void setSalesPeople(LinkedList<String> salesPeople) {
        this.salesPeople = salesPeople;
    }

    /**
     * @return the iterator
     */
    public ListIterator<String> getIterator() {
        return iterator;
    }

    /**
     * @param iterator the iterator to set
     */
    public void setIterator(ListIterator<String> iterator) {
        this.iterator = iterator;
    }

    /**
     * @return the numSales
     */
    public ArrayList<Integer> getNumSales() {
        return numSales;
    }

    /**
     * @param numSales the numSales to set
     */
    public void setNumSales(ArrayList<Integer> numSales) {
        this.numSales = numSales;
    }
}