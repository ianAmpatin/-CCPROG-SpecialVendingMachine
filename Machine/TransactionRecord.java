import java.util.ArrayList;

/**
 * The TransactionRecord class represents a record of transactions made in a vending machine.
 * It stores a list of Transaction objects, each containing the name and price of an item purchased.
 * 
 * The class provides methods to add a new transaction, retrieve the list of transactions,
 * calculate the total amount spent on a specific item, and reset the transaction record.
 * 
 * Note: The class assumes that each transaction corresponds to a single item purchase.
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public class TransactionRecord {
     ArrayList<Transaction> transactionsList;

     /**
     * Constructs a TransactionRecord object with an empty list of transactions.
     */

     public TransactionRecord () {
          transactionsList = new ArrayList<Transaction>();
     }

     /**
     * Adds a new transaction to the record with the given item name and price.
     * 
     * @param itemName  the name of the item purchased in the transaction
     * @param itemPrice the price of the item purchased in the transaction
     */

     public void addTransaction (String itemName, double itemPrice) {
          transactionsList.add(new Transaction(itemName, itemPrice));
     }

     /**
     * Retrieves the list of transactions recorded in the transaction record.
     * 
     * @return an ArrayList containing the Transaction objects representing the transactions
     */

     public ArrayList<Transaction> getTransactions() {
          return transactionsList;
     }

     /**
     * Clears the transaction record, removing all recorded transactions.
     */

     public void resetRecord() {
          this.transactionsList.clear();
     }

     /**
     * Calculates the total amount spent on the item with the given name in all transactions.
     * 
     * @param name the name of the item to calculate the total amount spent
     * @return the total amount spent on the specified item
     */
    
     public double getTotal (String name) {
          double total = 0;
          for (Transaction transaction : transactionsList) {
               if (transaction.getName().equalsIgnoreCase(name))
                    total += transaction.getPrice();
          }
          return total;
     }

}
