/**
 * The Maintenance interface provides a contract for classes that represent maintenance operations in a vending machine system.
 * It defines methods for displaying item information, inserting items in an empty slot, displaying slots for maintenance,
 * and removing slots from the vending machine.
 * 
 * Classes that implement this interface are responsible for handling maintenance-related tasks in the vending machine system.
 * 
 * Note: This interface assumes the existence of the Item class.
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public interface Maintenance {
    /**
     * Displays information about the specified item.
     * 
     * @param item the item whose information is to be displayed
     */

    public void displayItemInfo(Item item);

    /**
     * Inserts the specified item in an empty slot and returns the quantity inserted.
     * 
     * @param item     the item to be inserted
     * @param quantity the quantity of the item to be inserted
     * @return the actual quantity of the item inserted in the empty slot
     */
    public int insertInEmpty (Item item, int quantity);

    /**
     * Displays the slots that require maintenance.
     */
    public void displaySlotsToMaintenance();

    /**
     * Removes a slot from the vending machine and returns the slot number that was removed.
     * 
     * @return the slot number of the removed slot
     */
    
    public int removeSlot();
}