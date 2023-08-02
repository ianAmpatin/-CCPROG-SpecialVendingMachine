import java.util.ArrayList;

/**
 * The Slot class represents a slot that can hold a certain quantity of items.
 * 
 *  It tracks the slot number, the item stored in the slot, the quantity of the item,
 *  and the type of the item.
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public class Slot {
    // Declartion of variables
    private final int UPPER_LIMIT = 50;
    private final int MAXIMUM;
    private final int SLOT_NUM;
    private ArrayList<Item> slotItems;
    private Item item;
    private String itemType;


    /**
     * Constructs a Slot object with the specified slot number, item, quantity, and item type.
     * @param slotNum is the number that will be assigned to the slot
     * @param slotItem is be the item that the slot will hold
     * @param quantity is the number of items that the slot holds
     * @param itemType is the type of the item
     */
    public Slot (int slotNum, ArrayList<Item> slotItem, String itemType, int max) {
        this(slotNum, slotItem, max);
        this.itemType = itemType;
    }

    /**
     * Constructs a Slot object with the specified slot number, item, and quantity.
     * The item type is set to "single" by default.
     * 
     * @param slotNum is the number that will be assigned to the slot
     * @param slotItem is the item that the slot will hold
     * @param quantity is the number of items that the slot holds
     */
    public Slot (int slotNum, ArrayList<Item> slotItem, int max) {
        this(slotNum, max);
        //this.slotItems = slotItem;

        if(this.validateQuantity(slotItem.size())) {
            this.slotItems = slotItem;
        }
        else {
            for (int i = 0; i < slotItem.size() && i < MAXIMUM; i++) {
                this.slotItems.add(new Item(slotItem.get(i).getName(), slotItem.get(i).getPrice(), slotItem.get(i).getCalories()));
                slotItem.remove(slotItem.size() - 1);
            }
        }
    }

    /**
     * Constructs a Slot object with the specified slot number, item, quantity, and item type.
     * 
     * @param slotNum   the number that will be assigned to the slot
     * @param slotItem  the item that the slot will hold
     * @param itemType  the type of the item
     * @param max       the maximum quantity of items the slot can hold
     */

    public Slot (int slotNum, Item slotItem, int max) {
        this(slotNum, max);
        this.item = slotItem;
    }

    /**
     * Constructs a Slot object with the specified slot number and maximum quantity.
     * The item and quantity are set to null and 0 respectively.
     * 
     * @param slotNum  the number that will be assigned to the slot
     * @param max      the maximum quantity of items the slot can hold
     */

    public Slot (int slotNum, Item item) {
        this(slotNum, 50);
        this.item  = item;
    }
    
    /**
     * Constructs a Slot object with the specified slot number.
     * The item and quantity are set to null and 0 respectively.
     * @param slotNum is the number that will be assigned to the slot
     */
    public Slot (int slotNum, int max) {
        SLOT_NUM = slotNum;
        MAXIMUM = (max < 10) ? 10 : (max > UPPER_LIMIT) ? UPPER_LIMIT : max;
        slotItems = new ArrayList<Item>();
    }

    /**
     * This methods validates the given quantity.
     * @param num is the quantity to validate
     * @return true if the quantity is within the valid range, false otherwise
     */
    private boolean validateQuantity (int num) {
        if (num >= 0 && num <= MAXIMUM)
            return true;
        return false;
    }

    /**
     * This method will retrieve the number of items that the slot currently holds
     * @return is the quantity of the item
     */
    public int getQuantity () {
        if (slotItems == null || slotItems.isEmpty()) return 0;
        return slotItems.size();
    }

    /**
     * This method will retrive the item stored in the slot.
     * @return is the item stored in the slot
     */
    public Item getItem () {
        return item;
    }

    /**
     * This method will retrieve the number assigned to the slot.
     * @return is the slot number assigned to the slot
     */
    public int getSlotNum () {
        return SLOT_NUM;
    }
    
    /**
     * This method will retrieve the type of the item.
     * @return is the type of the item
     */
    public String getItemType () {
        return itemType;
    }

    /**
     * This method will check if the slot is full (quantity equals maximum).
     * @return true if the slot is full, false otherwise
     */
    public boolean isFull () {
        return slotItems.size() == MAXIMUM;
    }

    /**
     * This method adds the specified quantity to the current quantity of the item in the slot,
     * as long as it does not exceed the maximum quantity.
     * @param quantity is the quantity that will be added to the slot
     * @return true if the addition was successful, false otherwise
     */
    public boolean addQuantity (int quantity) {
        if (quantity > 0 && this.getQuantity() + quantity <= MAXIMUM) {
            for(int i = 0; i < quantity; i++)
                slotItems.add(new Item(item.getName(), item.getPrice(), item.getCalories()));
            return true;
        }
        return false;
    }

    /**
     * This method deducts the specified quantity from the current quantity of the item in the slot,
     * as long as the resulting quantity is greater than 0.
     * @param quantity is the quantity that will be deducted
     * @return true if the deduction was successful, false otherwise
     */
    public boolean deductQuantity (int quantity) {
        if(quantity >= 0 && this.getQuantity() - quantity >= 0) {
            for(int i = 0; i < quantity; i++)
                slotItems.remove(this.getQuantity() - 1);
            return true;
        }
        return false;
    }

    /**
     * This method will set the item and quantity in the slot to a new item and quantity.
     * @param itemSub is the item to set
     * @param quantity is the quantity to set
     */
    public void setItem (Item itemSub, int quantity) {
        this.item = itemSub;
        this.removeAllItems();
        this.addQuantity(quantity);
    }

    /**
     * This method sets the quantity of the slot to the specified value.
     * If the new quantity is greater than the current quantity, additional items are added.
     * If the new quantity is less than the current quantity, excess items are removed.
     * 
     * @param quantity  the new quantity to set
     */

    public void setQuantity (int quantity) {
        if (slotItems == null || slotItems == null) return;

        if(slotItems.size() > quantity) {
            while (slotItems.size() != quantity)
                this.deductQuantity(1);
        }
        else if (slotItems.size() < quantity) {
            while (slotItems.size() != quantity)
                this.addQuantity(1);
        }
    }

    /**
     * This method removes all items from the slot, setting the quantity to 0.
     */

    public void removeAllItems() {
        if (this.getQuantity() == 0) return;

        while(slotItems.size() != 0) 
            this.deductQuantity(1);
    }

    /**
     * This method retrieves the upper limit of the quantity of items the slot can hold.
     * 
     * @return the upper limit of the quantity of items the slot can hold
     */
    
    public int getUpperLimit() {
        return UPPER_LIMIT;
    }
}
