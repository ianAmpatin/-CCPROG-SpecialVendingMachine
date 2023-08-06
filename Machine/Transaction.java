/**
 * The Transaction class represents a transaction for a specific item, containing its name and price.
 * It is used to store information about an item that was purchased in a vending machine transaction.
 * 
 * The class provides methods to retrieve the name and price of the item in the transaction.
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */
class Transaction {
     private String itemName;
     private double itemPrice;

     /**
     * Constructs a Transaction object with the specified item name and price.
     * 
     * @param itemName  the name of the item in the transaction
     * @param itemPrice the price of the item in the transaction
     */
     public Transaction (String itemName, double itemPrice) {
          this.itemName = itemName;
          this.itemPrice = itemPrice;
     }

     /**
     * This method retrieves the name of the item in the transaction.
     * 
     * @return the name of the item in the transaction
     */
     public String getName () {
          return itemName;
     }

     /**
     * This method retrieves the price of the item in the transaction.
     * 
     * @return the price of the item in the transaction
     */
     public double getPrice () {
          return itemPrice;
     }
}