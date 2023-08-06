import java.util.ArrayList;

/**
 * The CompoundSlot class represents a slot in a vending machine that can hold a compound item.
 * It extends the Slot class and includes additional functionality to store required items for the compound item.
 * CompoundSlot maintains a list of required items along with the quantity required for each item.
 * It provides methods to add required items to the compound slot and retrieve the list of required items.
 * This class is part of the vending machine system.
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public class CompoundSlot extends Slot {
    //Declaration of Variables
    private ArrayList<RequiredItem> requiredItems;

    /**
     * Constructs a new CompoundSlot object with the specified slot number and item.
     * @param slotNum the slot number of the compound slot
     * @param item the item to be placed in the compound slot
     */
    public CompoundSlot(int slotNum, Item item) {
        super(slotNum, item);
        this.requiredItems = new ArrayList<RequiredItem>();
    }

    /**
     * Constructs a new CompoundSlot object with the specified slot number, item, and maximum quantity.
     * @param slotNum the slot number of the compound slot
     * @param item the item to be placed in the compound slot
     * @param max the maximum quantity that the compound slot can hold
     */

    public CompoundSlot(int slotNum, Item item, int max) {
        super(slotNum, item, max);
        this.requiredItems = new ArrayList<RequiredItem>();
    }

    /**
     * Constructs a new CompoundSlot object with the specified slot number, item, maximum quantity, and initial price.
     * @param slotNum the slot number of the compound slot
     * @param item the item to be placed in the compound slot
     * @param max the maximum quantity that the compound slot can hold
     * @param initialPrice the initial price of the compound slot
     */

    public CompoundSlot(int slotNum, Item item, int max, double initialPrice) {
        this(slotNum, item, max);
        this.getItem().setPrice(initialPrice);
    }

    /**
     * Adds a required item to the compound slot with the specified item and quantity.
     * It updates the calories and price of the compound item based on the added required item.
     * @param item the required item to be added
     * @param quantity the quantity of the required item to be added
     */
    
    public void addRequiredItem (Item item, int quantity) {
        Item reference;
        requiredItems.add(new RequiredItem(item, quantity));
        reference = this.getItem();
        reference.setCalories(reference.getCalories() + (item.getCalories() * quantity));
        reference.setPrice(reference.getPrice() + (item.getPrice() * quantity));
    }

    /**
     * Retrieves the list of required items for the compound slot.
     * @return the list of required items for the compound slot
     */
    public ArrayList<RequiredItem> getRequiredItems() {
        return requiredItems;
    }
    
}