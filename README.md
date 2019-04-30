# Car-Dealership-Simulator
This is a Command Line based program that simulates a Car Dealership based on user input and a txt file of cars which can be [generated](https://squishy123.github.io/carsgen/). It works in a way that cars can be bought, sorted, filtered and returned. It is very user friendly, so any incorrect command will be caught and user will be told to correct the error.

**Note: RET only works when there is a transaction id of type buy is 
        inputted after it or just RET in which it returns the last buy 
        transaction. (both work) If there is an error due to car 
        already being returned when it hasn't, it is most likely due 
        to a duplicate VIN. Also, in sales stats, total cars sold is 
        the total sold, total returned is the toal cars returned and 
        highests sales month is total cars bought subtracted total cars
        returned. For top sales person the number of cars sold is only 
        cars sold.


## Commands:

- ADD                     - Adds all cars to Car Dealership.<\n>
- L                       - Lists all cars in Car Dealership.
- BUY [INDEX]             - Buys car from the Dealership.
- RET                     - Returns the last car back to dealership
- RET [ID]                - Returns the car with transaction id back to the Dealership.
- SPR                     - Sorts all cars by price.
- SSR                     - Sorts by safety rating.
- SMR                     - Sort by max range.
- FPR [MIN] [MAX]         - Filters to cars by price with 2 integer 
                        values of minimum price and maximum price.
- FEL                     - Filters to only cars that are elecrtric.
- FAW                     - Filters to only cars that have AWD.
- FCL                     - Clears all set filters.
- SALES                   - Shows a list of all transactions.
- SALES STATS             - shows the sales statistics for the year which includes total sales,
                        average sales, total cars sold and returned, and highest sales months.
- SALES TOPSP             - shows the top sales person and amount of cars sold. If it were a tie, 
                        shows all the sales people who are in the tie.
- SALES [month]           - shows all transactions for the given month.
- SALES  TEAM             - shows all members of the car dealership's sales team.
- Q                       - Quit and exit the program.
