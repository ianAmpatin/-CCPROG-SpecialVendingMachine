/**
 * The Item class represents an item available in a vending machine. 
 * Each item has a name, price, and calorie content.
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public class Item {
    // Declartion of variables
    private String itemName;
    private double itemPrice;
    private double calories;

    /**
     * Constructs a new Item object with the provided name, price, and calorie content.
     * @param name is the name of the item
     * @param price is the price of the item
     * @param calories is the calorie content of the item
     */
    public Item (String name, double price, double calories) {
        itemName = name;
        itemPrice = price;
        this.calories = calories;
    }

    /**
     * This method will set the name of the item.
     * @param name is the name of the item
     */
    public void setName (String name) {
        itemName = name;
    }

    /**
     * This method sets the price of the item.
     * @param price is the price of the item
     */
    public void setPrice (double price) {
        itemPrice = price;
    }

    /**
     * This method sets the calorie content of the item.
     * @param calories is the calorie content of the item
     */
    public void setCalories (double calories) {
        this.calories = calories;
    }

    /**
     * This method retrieves the name of the item.
     * @return is the name of the item
     */
    public String getName() {
        return itemName;
    }

    /**
     * This method retrieves the price of the item.
     * @return is the price of the item
     */
    public double getPrice () {
        return itemPrice;
    }

    /**
     * This method retrieves the calorie content of the item.
     * @return is the calorie content of the item
     */   
    public double getCalories () {
        return calories;
    }

    /**
     * This method compares this Item object to the specified object for equality.
     * Two items are considered equal if they have the same name, price, and calorie content.
     * @param compItem the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    public boolean equals (Object compItem) {
        return itemName.equalsIgnoreCase(((Item)compItem).getName()) && itemPrice == (((Item)compItem).getPrice()) && calories == (((Item)compItem).getCalories());
    }
}