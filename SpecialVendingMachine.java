import java.util.ArrayList;

/**
 * The SpecialVendingMachine class represents a specialized vending machine that can handle compound items.
 * It extends the VendingMachine class and adds additional functionality to handle compound items, which are items composed of multiple required items.
 * The class provides methods to add and manage compound items, compute the compound item quantity based on the availability of required items, and purchase compound items.
 * This class is part of the vending machine system and extends the VendingMachine class.
 * 
 * Note: This class assumes the existence of the VendingMachine, CompoundSlot, RequiredItem, Item, and CashHolder classes.
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public class SpecialVendingMachine extends VendingMachine{
    private ArrayList<CompoundSlot> compoundItems;

    /**
     * Constructs a new SpecialVendingMachine with the given name, number of slots, and slot maximum.
     * 
     * @param name          the name of the vending machine
     * @param numOfSlots    the number of slots in the vending machine
     * @param slotMaximum   the maximum capacity of each slot in the vending machine
     */

    public SpecialVendingMachine(String name, int numOfSlots, int slotMaximum) {
        super(name, numOfSlots, slotMaximum);
        compoundItems = new ArrayList<CompoundSlot>();
    }

    /**
     * Constructs a new SpecialVendingMachine with the given name, number of slots, item list, and slot maximum.
     * 
     * @param name          the name of the vending machine
     * @param numOfSlots    the number of slots in the vending machine
     * @param itemList      the list of items in the vending machine
     * @param slotMaximum   the maximum capacity of each slot in the vending machine
     */

    public SpecialVendingMachine(String name, int numOfSlots, ArrayList<Item> itemList, int slotMaximum) {
        super(name, numOfSlots, itemList, slotMaximum);
    }

    /**
     * Adds a new compound item to the vending machine with the given item and required items.
     * 
     * @param item       the main item to be added as a compound item
     * @param reqItems   the list of required items and their required quantities for the compound item
     */

    public void addCompoundItem (Item item, ArrayList<RequiredItem> reqItems) {
        addCompoundItem(item);

        for (RequiredItem itemReq : reqItems) {
            compoundItems.get(compoundItems.size() - 1).addRequiredItem(itemReq.getItem(), itemReq.getRequiredQuantity());
        }
    }

    /**
     * Adds a new compound item to the vending machine with the given item.
     * 
     * @param item   the main item to be added as a compound item
     */

    public void addCompoundItem(Item item) {
        System.out.println("Adding Compound Item...");
        int slotNum = removeSlot();
        this.increaseItemCount();
        if(slotNum != 0) {
            compoundItems.add(new CompoundSlot(slotNum, item));
            System.out.println("Compound Item Successfully Added");
            return;
        }
        System.out.println("Compound Item not Added");
    }

    /**
     * Computes the maximum quantity of a compound item that can be produced based on the availability of required items in the vending machine.
     * 
     * @param slot   the CompoundSlot containing the compound item information
     * @return the maximum quantity of the compound item that can be produced
     */

    public int computeCompoundQuantity(CompoundSlot slot) {
        ArrayList<Slot> slotReference;
        ArrayList<RequiredItem> requiredReference;
        try {
            slotReference = this.getSlots();
            requiredReference = slot.getRequiredItems();
        }
        catch (NullPointerException e) {
            System.out.println(e);
            return 0;
        }
    
        if (requiredReference == null || slotReference == null) {
            System.out.println("ERROR: Required items or slots are not available.");
            return 0;
        }
    
        int quantity = Integer.MAX_VALUE;
    
        for (RequiredItem requiredItem : requiredReference) {
            if (!this.hasItem(requiredItem.getItem())) {
                System.out.println("ERROR: <" + requiredItem.getItem().getName() + "> is not available.");
                return 0;
            }
    
            int itemQuantity = Integer.MAX_VALUE;
            for (Slot referenceSlot : slotReference) {
                if (referenceSlot.getItem() != null && referenceSlot.getItem().equals(requiredItem.getItem())) {
                    int requiredQuantity = requiredItem.getRequiredQuantity();
                    if (requiredQuantity == 0) {
                        System.out.println("ERROR: Required quantity for <" + requiredItem.getItem().getName() + "> is zero.");
                        return 0;
                    }
    
                    int availableQuantity = referenceSlot.getQuantity();
                    itemQuantity = Math.min(itemQuantity, availableQuantity / requiredQuantity);
                }
            }
    
            if (itemQuantity == Integer.MAX_VALUE) {
                System.out.println("Insufficient Required Item Quantity: " + requiredItem.getItem().getName());
                return 0;
            }
    
            quantity = Math.min(quantity, itemQuantity);
        }
    
        return quantity;
    }

    /**
     * Purchases a compound item from the vending machine at the specified slot number and gives the change to the destination CashHolder.
     * 
     * @param slotNumber   the slot number of the compound item in the vending machine
     * @param destination  the CashHolder to receive the change after the purchase
     * @return the Item that was purchased, or null if the purchase was not successful
     */

    public CompoundSlot buyCompoundItem (int slotNumber, CashHolder destination) {
        System.out.println("Purchasing Compound Item...");

        for (CompoundSlot slot : compoundItems) {

            if (slot.getSlotNum() == slotNumber) {

                if (this.computeCompoundQuantity(slot) > 0) {

                    if (this.hasChange(slot.getItem().getPrice())) {

                        if (this.hasSufficientBalance(slot)) {

                            ArrayList<RequiredItem> reference = slot.getRequiredItems();
                            ArrayList<Slot> referenceSlots = this.getSlots();

                            for (RequiredItem reqItem : reference) {
                                for (Slot refSlot : referenceSlots) {

                                    if (refSlot.getItem() != null) {

                                        if (refSlot.getItem().equals(reqItem.getItem())) {
                                            refSlot.deductQuantity(reqItem.getRequiredQuantity());
                                        }
                                    }
                                }
                            }

                            System.out.println("[" + slot.getItem().getName() + "] has been purchased");
                            this.giveChange(destination, slot.getItem().getPrice());
                            this.getTransactionRecord().addTransaction(slot.getItem().getName(), slot.getItem().getPrice());
                            this.completePurchase();
                            return slot;


                        }
                        else {
                            System.out.println("ERROR: Insufficient Balance");
                            return null;
                        }

                    }
                    else {
                        System.out.println("ERROR: Insufficeint Change");
                        return null;
                    }
                    
                }
                else {
                    System.out.println("ERROR: Insufficeint Quantity.");
                    return null;
                }

            }
            else {
                System.out.println("ERROR: Slot does not exist.");
                return null;
            }
        }
        return null;
    }

    /**
     * Returns the list of compound slots in the vending machine.
     * 
     * @return the list of compound slots
     */

    public ArrayList<CompoundSlot> getCompoundSlots () {
        return compoundItems;
    }

    /**
     * Adds a required item to the compound item with the given name.
     * 
     * @param name     the name of the compound item to which the required item will be added
     * @param reqItem  the RequiredItem containing the required item and its required quantity
     */

    public void addRequiredITemToCompound (String name, RequiredItem reqItem) {
        
        for (CompoundSlot slot : compoundItems) {
            if (slot.getItem().getName().equalsIgnoreCase(name)) {
                slot.addRequiredItem(reqItem.getItem(), reqItem.getRequiredQuantity());
            }
        }
    }
    
    /**
     * Checks if a slot number is available in the vending machine (not occupied by a regular item or compound item).
     * 
     * @param slotNum  the slot number to check for availability
     * @return true if the slot number is available, false otherwise
     */

    public boolean checkSlotAvail (int slotNum) {
        for (Slot slot : this.getSlots()) 
            if (slot.getSlotNum() == slotNum)
                return false;

        for (CompoundSlot slot : this.getCompoundSlots()) 
            if (slot.getSlotNum() == slotNum)
                return false;

        return true;
    }

    /**
     * Inserts an item with the given quantity at the specified position in the vending machine.
     * 
     * @param item      the item to be inserted
     * @param quantity  the quantity of the item to be inserted
     * @param position  the position (slot number) at which to insert the item
     * @return 1 if the insertion is successful, 0 if the machine already contains the item, -1 if the machine is full, or 0 if the slot is occupied
     */
    
    public int insertAt(Item item, int quantity, int position) {
        System.out.println("Inserting item at <" + position + ">...");

        if (this.hasItem(item)) {
            System.out.println("Machine already contains [" + item.getName() + "]\n");
            return 0;
        }
        else if (this.checkSlotAvail(position)) {
            System.out.println("ERROR: Slot is occupied.");
            return 0;
        }
        else {
            for (int i = 0; i < this.getSlots().size(); i++) {
                if (this.getSlots().get(i).getItem() == null && this.getSlots().get(i).getSlotNum() == position) {

                    this.getSlots().get(i).setItem(item, quantity);
                    System.out.println("The item [" + item.getName() + "] was inserted in Slot " + this.getSlots().get(i).getSlotNum() + "\n");
                    this.increaseItemCount();

                    return 1;
                }
            }
            System.out.println("Machine is Full\n");
            return -1;
        }
    }

    /**
     * Checks if the vending machine contains a compound item with the given main item.
     * 
     * @param item  the main item to check for in the compound items
     * @return true if the vending machine contains the compound item, false otherwise
     */

    public boolean hasCompound(Item item) {

        for (CompoundSlot compoundItem : this.getCompoundSlots())
            if(compoundItem.getItem().equals(item))
                return true;

        return false;
    }

    /**
     * Gets the number of compound items in the vending machine.
     * 
     * @return the number of compound items
     */

    public int getCompoundQuantity () {

        if (compoundItems == null || compoundItems.get(0) == null) {
            return 0;
        }
        return compoundItems.size();
    }

    /**
     * Checks if the vending machine contains a required item within a compound item.
     * 
     * @param item         the main item of the compound item to check
     * @param itemRequired the required item to check for within the compound item
     * @return true if the vending machine contains the required item in the compound item, false otherwise
     */

    public boolean containsRequiredItem (Item item, RequiredItem itemRequired) {

        for (CompoundSlot slot : this.compoundItems) {
            if (slot.getItem().equals(item)) {

                for (RequiredItem reqItem : slot.getRequiredItems()) {
                    if (reqItem.getItem().equals(itemRequired.getItem()))
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets the compound item with the given name from the vending machine.
     * 
     * @param name  the name of the compound item to retrieve
     * @return the CompoundSlot object representing the compound item, or null if not found
     */
    
    public CompoundSlot getCompoundItem (String name) {

        for (CompoundSlot slot : this.compoundItems) 
            if (slot.getItem().getName().equalsIgnoreCase(name)) 
                return slot;

        return null;
    }


}
