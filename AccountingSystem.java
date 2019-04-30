import java.util.*;

/**
 * This class is for AccountingSystem which containes all elements of an
 * AccountingSystem
 * 
 * @author Seyon Rajagopal 
 * Student ID: 500885401 
 * Class Name: AccountingSystem.java
 * 
 */
public class AccountingSystem {
    // instance variables
    private SalesTeam team;
    private int transid;
    private int lastid;
    private Map<Integer, Transaction> transactions;
    private Set<Integer> keySet;
    private Iterator<Integer>  iterator;
    

    /**
     * Constructor method
     */
    public AccountingSystem(){
        team = new SalesTeam();
        transactions = new TreeMap<Integer, Transaction>();
        keySet = transactions.keySet();
        iterator = keySet.iterator();
    }

    /**
     * 
     * @param date
     * @param car
     * @param salesPerson
     * @param type
     * @param salePrice
     * @return transaction.display() 
     */
    public String add(GregorianCalendar date, Car car, String salesPerson, String type, double salePrice){
        transid = (int)(Math.random()*(500))+100;
        lastid = transid;
        Transaction transaction = new Transaction(transid,date,car,salesPerson,type,salePrice);
        transactions.put(transid,transaction);
        return transaction.display();
    }

    /**
     * Finds transaction by id
     * @param id1
     * @return transaction
     */
    public Transaction getTransaction(int id1){
        iterator = keySet.iterator();
        while(iterator.hasNext()){
            int id = iterator.next();
            if (id1 == id){
                Transaction transaction = transactions.get(id); 
                return transaction;
            }
        }
        return null;
    }

    /**
     * shows all transactions
     * @return reult of type string which has all transactions
     */
    public String displayAllTransactions(){
        String result = "";
        iterator = keySet.iterator();
        if(transactions.size() > 0){
            while(iterator.hasNext()){
                int id = iterator.next();
                Transaction transaction = transactions.get(id);
                result += transaction.display() + "\n";
            }
        }else{
            throw new IllegalArgumentException("There Are No Transactions!");
        }
        return result;
    }

    /**
     * shows all transactions for the month
     * @param month
     * @return result of type sting which  has all transactions for the month
     */
    public String displayMonthTransactions(int month){
        String result = "";
        iterator = keySet.iterator();
        if(transactions.size() > 0){
            while(iterator.hasNext()){
                int id = iterator.next();
                Transaction transaction = transactions.get(id);
                if (month == transaction.getDate().get(Calendar.MONTH)){
                    result += transaction.display() + "\n";
                }
            }
        }else{
            throw new IllegalArgumentException("There Are No Transactions");
        }
        return result;
    }
    /**
     * Calculates the toal of all trnsactions
     * @return total of all trnsactions
     */
    public double getTotalSales(){
        double total = 0.0;
        iterator = keySet.iterator();
        if(transactions.size() > 0){
            while(iterator.hasNext()){
                int id = iterator.next();
                total += transactions.get(id).getPrice();
            }
        }else{
            throw new IllegalArgumentException("There Are No Transactions!");
        }
        return total;
    }

    /**
     * Gets average sales
     * @return total/12 months
     */
    public double getAverageSalesPerMonth(){
        return getTotalSales()/12;
    }

    /**
     * Gets number of car returns
     * @return total car returns
     */
    public int getTotalCarReturns(){
        int total = 0;
        iterator = keySet.iterator();
        if(transactions.size() > 0){
            while(iterator.hasNext()){
                int id = iterator.next();
                if (transactions.get(id).getType().equals("RET")){
                    total += 1;
                }
            }
        }else{
            throw new IllegalArgumentException("There Are No Transactions!");
        }
        return total;
    }

    /**
     * Gets total car buys with account of returns (eq. if car is bought and returned, doesnt count as a sale) 
     * @return total cars sold
     */
    public int getTotalCarsSold(){
        int total = 0;
        iterator = keySet.iterator();
        if(transactions.size() > 0){
            while(iterator.hasNext()){
                int id = iterator.next();
                if (transactions.get(id).getType().equals("BUY")){
                    total += 1;
                }
            }
        }else{
            throw new IllegalArgumentException("There Are No Transactions!");
        }
        return total;
    }

    /**
     * Gets the month with most amount of cars sod
     * @return month which has highest number of cars sold (if tie, returns a month)
     */
    public String getHighestSalesMonth(){
        String text = "";
        
        ArrayList<Integer> monthsales = new ArrayList<Integer>();
        String[] months = {"January", "February", "March","April","May","June","July","August","September","October","November","December"};
        
        if(transactions.size() > 0){
            for(int i = 0; i < 12; i++){
                iterator = keySet.iterator();
                int monthCount = 0;
                while(iterator.hasNext()){
                    int id = iterator.next();
                    if(transactions.get(id).getDate().get(Calendar.MONTH) == i){
                        if (transactions.get(id).getType().equals("BUY")){
                            monthCount += 1;
                        }
                        if (transactions.get(id).getType().equals("RET")){
                            monthCount -= 1;
                        }
                    }
                }
                monthsales.add(monthCount);
            }

            // get max value in arraylist
            int max = 0;
            max = Collections.max(monthsales);
            //get month which has the highest sales, f tie, return the latest month with that tie
            for(int i = 0; i < monthsales.size(); i++){
                if (monthsales.get(i) == max){
                    text += "\n" + months[i] + ": " + max + " car(s)";
                    //return text;
                }
            }

        }else{
            throw new IllegalArgumentException("There Are No Transactions!");
        }
        return text;
    }

    /**
     * Gets number of transactions in month 
     * @param month 
     * @return counter of transactions
     */
    public int getNumTransactionsOfMonth(int month){
        int counter = 0;
        iterator = keySet.iterator();
        while(iterator.hasNext()){
            int id = iterator.next();
            if (month == transactions.get(id).getDate().get(Calendar.MONTH)){
                if (transactions.get(id).getType().equals("BUY")){
                    counter += 1;
                }
                if (transactions.get(id).getType().equals("RET")){
                    counter -= 1;
                }
            }
        }
        return counter;
    }
    
    /**
     * the top sales person(s) there are multiple prints all of them
     * @return text which has name and number of crs sold by sales person
     */
    public String getTopSalesPerson(){
        String text = "";
        ArrayList<Integer> numSales = new ArrayList<Integer>();
        if(transactions.size() > 0){ 
            for(int i = 0; i < team.getSalesPeople().size(); i++){
                int value = 0;
                iterator = keySet.iterator();
                while(iterator.hasNext()){
                    int id = iterator.next();
                    String person = transactions.get(id).getSalesPerson();
                    if(person.equals(team.findSalesPerson(i)) && transactions.get(id).getType().equals("BUY")){
                        value ++;
                    }
                    
                }
                numSales.add(value);
            }
            
            // get max value in arraylist
            int max = 0;
            for(int i = 0; i < numSales.size(); i++){
                if (numSales.get(i) > max){
                    max = numSales.get(i);
                }
            }

            // adds all sales people and the number of cars sold as a string if the number of cars sold is a tie
            for(int i = 0; i < numSales.size(); i++){
                if (numSales.get(i) == max){
                    text += "\nName: " + team.findSalesPerson(i) + "\nNum Cars Sold: " + max;
                }
            }
        }else{
            throw new IllegalArgumentException("There Are No Transactions!");
        }
        return text;
    }


    /**
     * @return the team
     */
    public SalesTeam getTeam() {
        return team;
    }

    /**
     * @param team the team to set
     */
    public void setTeam(SalesTeam team) {
        this.team = team;
    }

    /**
     * @return the id
     */
    public int getId() {
        return transid;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.transid = id;
    }

    /**
     * @param transactions the transactions to set
     */
    public void setTransactions(Map<Integer, Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * @return the keySet
     */
    public Set<Integer> getKeySet() {
        return keySet;
    }

    /**
     * @param keySet the keySet to set
     */
    public void setKeySet(Set<Integer> keySet) {
        this.keySet = keySet;
    }

    /**
     * @return the iterator
     */
    public Iterator<Integer> getIterator() {
        return iterator;
    }

    /**
     * @param iterator the iterator to set
     */
    public void setIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    /**
     * @return the transactions
     */
    public Map<Integer, Transaction> getTransactions() {
        return transactions;
    }

    /**
     * @return the transid
     */
    public int getTransid() {
        return transid;
    }

    /**
     * @param transid the transid to set
     */
    public void setTransid(int transid) {
        this.transid = transid;
    }

    /**
     * @return the lastid
     */
    public int getLastid() {
        return lastid;
    }

    /**
     * @param lastid the lastid to set
     */
    public void setLastid(int lastid) {
        this.lastid = lastid;
    }



    
}