/**
 * The RequiredItem class represents an item that is required in a certain quantity.
 * It contains information about the required item and the quantity needed.
 * This class is part of the vending machine system.
 * 
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public class RequiredItem {
    private final int REQUIRED_QUANTITY;
    private Item item;


    /**
     * Constructs a new RequiredItem with the specified item and required quantity.
     * The required quantity must be non-negative; otherwise, it will be set to 0.
     * 
     * @param item the item that is required
     * @param quantity the quantity of the item required
     */

    public RequiredItem (Item item, int quantity) {
        REQUIRED_QUANTITY = (quantity > 0) ? quantity : 0;
        this.item = item;
    }
    
    /**
     * Gets the required quantity of the item.
     * 
     * @return the required quantity
     */

    public int getRequiredQuantity() {
        return REQUIRED_QUANTITY;
    }

    /**
     * Gets the item that is required.
     * 
     * @return the required item
     */

    public Item getItem() {
        return item;
    }

    /**
     * Checks if this RequiredItem is equal to another item.
     * Two RequiredItem objects are considered equal if their corresponding items are equal.
     * 
     * @param item the item to compare with this RequiredItem's item
     * @return true if the items are equal, false otherwise
     */

    public boolean equals (Item item) {
        if (item == null) 
            return false;
        
        return this.item.equals(item);
    }
}
