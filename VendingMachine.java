import java.util.ArrayList;
/**
 * The VendingMachine class represents a vending machine that sells items.
 * It manages the machine's name, number of slots, cash holders, item slots, inventory, and purchase transactions.
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public class VendingMachine implements Maintenance{
    // Declaration of Variables
    private String machineName;
    private final int NUMBER_OF_SLOTS;
    private final int MAXIMUM = 30;
    private CashHolder machineReserve;
    private CashHolder consumer;
    private ArrayList<Slot> itemSlots;
    private TransactionRecord record;
    private double receivedTotal;
    private int itemCount;

    /**
     * Constructs a VendingMachine object with the specified name, number of slots, and item list.
     * @param name is the name of the vending machine
     * @param numOfSlots is the number of slots that the vending machine will have
     * @param itemList is the list of items to be inserted into the machine's inventory
     */
    public VendingMachine (String name, int numOfSlots, ArrayList<Item> itemList, int slotMaximum) {
        this(name, numOfSlots, slotMaximum);

        for( Item item : itemList) {
            insertInEmpty(item, 0);
            this.itemCount++;
        }
    }

    /**
     * Constructs a VendingMachine object with the specified name and number of slots.
     * The item slots and master inventory will be initialized with empty slots.
     * @param name is the name of the vending machine
     * @param numOfSlots is the number of slots in the machine
     */
    public VendingMachine (String name, int numOfSlots, int slotMaximum) {
        machineName = name;
        NUMBER_OF_SLOTS = numOfSlots < 8 ? 8 : (numOfSlots > MAXIMUM) ? MAXIMUM : numOfSlots ;
        machineReserve = new CashHolder (name);
        consumer = new CashHolder("Consumer");
        itemSlots = new ArrayList<Slot>();
        record = new TransactionRecord();
        receivedTotal = 0;

        for (int i = 0; i < NUMBER_OF_SLOTS; i++) {
            itemSlots.add(new Slot(itemSlots.size() + 1, slotMaximum));
        }
    }

    /**
     * This method will retrieve the number of items that the machine currently has
     * 
     * @return the current number of items that the vending machine has
     */

    public int getCurrentItemCount() {
        return itemCount;
    }

    /**
     * This method will retrieve the slot number of a specified item, given the item name
     * @param name is the name of the item that will be searched for
     * @return the slot number that corresponds to the name given. Otherwise, -1 will be returned.
     */
    public int getSlotNumber (String name) {

        for (int i = 0; i < itemSlots.size(); i++)
            if (itemSlots.get(i).getItem().getName().equalsIgnoreCase(name))
                return itemSlots.get(i).getSlotNum();
        return -1;

    }

    /**
     * This method will retrieve the name of the vending machine.
     * @return the machine name
     */
    public String getName () {
        return machineName;
    }

    /**
     * This method will retrueve the number of slots in the vending machine.
     * @return the number of slots
     */
    public int getNumOfSlots () {
        return NUMBER_OF_SLOTS;
    }

    /**
     * This method will display the available items in the vending machine.
     */
    public void displayItemAvail () {
        System.out.println("- Displaying Available Items - ");
        for (int i = 0; i < itemSlots.size(); i++) {

            if(itemSlots.get(i).getItem() != null) {
                if(itemSlots.get(i).getQuantity() > 0) {
                    System.out.println("--------------------------");
                    System.out.println("Slot Number: " + itemSlots.get(i).getSlotNum());
                    System.out.println("Item: " + itemSlots.get(i).getItem().getName());
                    System.out.println("Price: " + itemSlots.get(i).getItem().getPrice());
                    System.out.println("Quantity: " + itemSlots.get(i).getQuantity());
                    System.out.println("Calories: " + itemSlots.get(i).getItem().getCalories());
                    System.out.println("--------------------------\n");
                }
            }
        }
    }

    /**
     * Accepts money from the consumer and adds it to the consumer's cash holder.
     * @param denomination the denomination of the cash
     * @return true if the money was accepted. Otherwise, return false
     */
    public boolean acceptMoney (Denomination denomination, int quantity) {
        receivedTotal += (denomination.getValue() * quantity);
        consumer.addCash(denomination, quantity);
        System.out.println("[" + denomination.getValue() + ", " + quantity + "] " + "has been accepted");
        System.out.println("Received Total: " + receivedTotal);
        return true;
    }

    /**
     * This method will retrieve the balance of the vending machine's reserve.
     * @return the machine reserve balance
     */
    public double getMachineBalance () {
        return machineReserve.getTotal();
    }

    /**
     * Selects an item from the vending machine based on the given slot number.
     * @param slotNumber the number of the slot
     * @return true if the item was successfully selected, false otherwise
     */
    public Item buyRegularItem(int slotNumber, CashHolder destination) {
        System.out.println("Selecting Item...");
    
        for (Slot slot : this.itemSlots) {
            if (slot.getSlotNum() == slotNumber) {
                System.out.println("Slot Exists");
    
                if (slot.getQuantity() > 0) {
                    System.out.println("Item Available");
    
                    if (this.hasChange(slot.getItem().getPrice())) {
                        System.out.println("Machine has Change");
    
                        if (this.hasSufficientBalance(slot)) {
                            System.out.println("Sufficient Balance");
    
                            slot.deductQuantity(1);
                            System.out.println("[ " + slot.getItem().getName() + " ] has been purchased");
                            this.completePurchase();
                            this.giveChange(destination, slot.getItem().getPrice());
                            record.addTransaction(slot.getItem().getName(), slot.getItem().getPrice());
                            System.out.println("Updated Machine: " + machineReserve.getTotal());
                            return slot.getItem();
                        } else {
                            System.out.println("ERROR: Insufficient Balance.");
                            return null;
                        }
                    } else {
                        System.out.println("ERROR: Insufficient Change.");
                        this.cancelTransaction(destination);
                        return null;
                    }
                } else {
                    System.out.println("ERROR: Insufficient Quantity.");
                    return null;
                }
            }
        }
        
        System.out.println("ERROR: Slot is not Available");
        return null;
    }
    

    /**
     * This method will select an item given its item name
     * @param name is the name of the item that shall be selected
     * @return true if an item was successfully selected. Otherwise, return false
     */

    public Item buyRegularItem (String name, CashHolder destination) {
        int slotNumber = this.getSlotNumber(name);
        return this.buyRegularItem(slotNumber, destination);
    }

    /**
     * Checks if the vending machine has enough change to give back to the consumer.
     * @param value the value for which change is required
     * @return true if the machine has enough change, false otherwise
     */
    public boolean hasChange(double value) {
        double change = receivedTotal - value;

        while (change > 0) {
            Denomination denom = machineReserve.getGreatestDenomination(change);
            if (denom == null)
                return false;
            int quantity = (int) (change / denom.getValue());

            if (quantity <= machineReserve.countDenomination(denom.getValue())) {
                change -= denom.getValue() * quantity;
            }
            else {
                return false;
            }
        }
        return true;
    }

    /**
     * Gives the change to the specified cash holder.
     * @param destination is the cash holder that will receive the change
     */
    public void giveChange(CashHolder destination, double cost) {
        System.out.println("\n\nGiving Change...");
        double value = consumer.getTotal() - cost;
            
            if (value > 0.0) {
                while (Math.abs(value) > 0.0) {
                    Denomination denom = machineReserve.getGreatestDenomination(value);
                    int quantity = (int) (value / denom.getValue());
                    System.out.println("Check B");
                    
                    if (quantity <= machineReserve.countDenomination(denom.getValue())) {
                        System.out.println("Value: " + denom.getValue() + "| Quantity: " + quantity);
                        this.machineReserve.transferMoney(destination, denom, quantity);
                        value -= denom.getValue() * quantity;
                    }
                }
            }
    }

    /**
     * This method will check  if the vending machine contains the specified item.
     * @param item is the item to check
     * @return true if the machine contains the item, false otherwise
     */
    public boolean hasItem (Item item) {

        for(Slot slot : itemSlots)
            if (slot.getItem() != null)
                if (slot.getItem().equals(item))
                    return true;
        return false;
    }

    /**
     * This method will display information about the selected item.
     * @param item is the item whose information will be displayed
     */
    public void displayItemInfo (Item item) {
        System.out.println("- " + item.getName() + " Information - ");
        System.out.println("Item Price: " + item.getPrice());
        System.out.println("Item Colories: " + item.getCalories());
    }

    /**
     * This method will insert an item into an empty slot in the vending machine.
     * @param item is the item that will be inserted
     * @param quantity is the quantity of the item
     * @return true if the item was successfully inserted, false otherwise
     */
    public int insertInEmpty (Item item, int quantity) {
        System.out.println("Inserting Item...");

        if (this.hasItem(item)) {
            System.out.println("Machine already contains [" + item.getName() + "]\n");
        }
        else {
            for (int i = 0; i < itemSlots.size(); i++) {
                if (itemSlots.get(i).getItem() == null && itemSlots.get(i) != null) {
                    itemSlots.get(i).setItem(item, quantity);
                    System.out.println("The item [" + item.getName() + "] was inserted in Slot " + itemSlots.get(i).getSlotNum() + "\n");
                    this.itemCount++;
                    return 1;
                }
            }
            System.out.println("Machine is Full\n");
            return -1;
        }
        return 0;
    }

    /**
     * This method will increase the quantity to an existing item in the vending machine by a specified amount.
     * @param name is the name of the item
     * @param quantity is the quantity that will be added
     * @return true if the quantity was added successfully, false otherwise
     */
    public boolean addItemQuantity (String name, int quantity) {
        System.out.println("Adding Quantity...");

        for (int i = 0; i < itemSlots.size(); i++) {
            if (itemSlots.get(i).getItem().getName().equalsIgnoreCase(name)) {
                if (itemSlots.get(i).addQuantity(quantity)) {
                    System.out.println("The quantity of item [" + itemSlots.get(i).getItem().getName() + "] has been increased by <" + quantity + ">\n");
                    record.resetRecord();
                    return true;
                }
                else {
                    System.out.println("ERROR: Adding Failed");
                    return false;
                }
            }
        }
        System.out.println("- Item does not exist -\n");
        return false;
    }

    /**
     * This method will display the slots that require maintenance.
     */
    public void displaySlotsToMaintenance () {
        for (int i = 0; i < itemSlots.size(); i++) {

            if(itemSlots.get(i).getItem() != null) {
                if(itemSlots.get(i).getQuantity() > 0) {
                    System.out.println("--------------------------");
                    System.out.println("Slot Number: " + itemSlots.get(i).getSlotNum());
                    System.out.println("Item: " + itemSlots.get(i).getItem().getName());
                    System.out.println("Price: " + itemSlots.get(i).getItem().getPrice());
                    System.out.println("Quantity: " + itemSlots.get(i).getQuantity());
                    System.out.println("Calories: " + itemSlots.get(i).getItem().getCalories());
                    System.out.println("--------------------------\n");
                }
                else {
                    System.out.println("--------------------------");
                    System.out.println("Slot Number: " + itemSlots.get(i).getSlotNum());
                    System.out.println("This item is not available");
                    System.out.println("--------------------------\n");
                }
            }
            else {
                System.out.println("--------------------------");
                System.out.println("Slot Number: " + itemSlots.get(i).getSlotNum());
                System.out.println("This slot is empty");
                System.out.println("--------------------------\n");
            }
        }
    }

    /**
     * This method will deposit cash into the machine's reserve.
     *
     * @param denom is the denomination of the cash to deposit
     * @param quantity the quantity of cash to deposit
     */
    public void depositMachineReserve(Denomination denom, int quantity) {
        machineReserve.addCash(denom, quantity);
        System.out.println("Total: " + machineReserve.getTotal());
    }

    /**
     * This method will display the sales information of the vending machine.
     */
    public void displaySales () {
        for (Slot slot : this.itemSlots) {
            int count = 0;
            
            for (Transaction transaction : this.record.getTransactions()) {
                if (slot.getItem().getName().equalsIgnoreCase(transaction.getName())) {
                    count++;
                }
            }

            System.out.println("Item: " + slot.getItem().getName() + "| Quantity Sold: " + count);
        }
    }


    /**
     * Removes an empty slot from the vending machine, reducing the slot count and inventory.
     * If an empty slot is found, it is removed, and the item count is decremented.
     * If no empty slot is found, the removal process is unsuccessful.
     * 
     * @return the slot number of the removed slot if successful, or 0 if removal is unsuccessful
    */
    public int removeSlot() {
        System.out.println("Removing Slot...");
        for (int i = itemSlots.size() - 1; i >= 0; i--) {
            if (itemSlots.get(i).getItem() == null) {
                int slotNum = itemSlots.get(i).getSlotNum();
                itemSlots.remove(i);
                this.itemCount--;
                System.out.println("Slot Successfully Removed");
                return slotNum;
            }
        }
        System.out.println("Removal Unsuccessfull");
        return 0;
    }

    /**
     * Retrieves the list of slots in the vending machine.
     * 
     * @return an ArrayList containing the Slot objects representing the slots in the vending machine
     */

    public ArrayList<Slot> getSlots () {
        return itemSlots;
    }

    /**
     * Checks if the vending machine has sufficient balance for a particular slot.
     * 
     * @param slot the slot for which to check the balance
     * @return true if the machine has sufficient balance, false otherwise
     */

    public boolean hasSufficientBalance (Slot slot) {
        return receivedTotal >= slot.getItem().getPrice();
    }

    /**
     * Completes the purchase by transferring money from the consumer to the machine reserve.
     */

    public void completePurchase() {
        System.out.println("Completing Purchase...");
        consumer.transferAll(machineReserve);
        System.out.println("Consumer: " + consumer.getTotal());
        System.out.println("Machine: " + machineReserve.getTotal());
        receivedTotal = 0;
    }

    /**
     * Cancels the transaction by returning the money from the consumer to the specified destination.
     * 
     * @param destination the cash holder to receive the money
     */
    
    public void cancelTransaction (CashHolder destination) {
        System.out.println("Cancelling Transaction...");
        consumer.transferAll(destination);
        System.out.println("Transaction Cancelled.");
        receivedTotal = 0;
    }

    /**
     * Collects all the cash from the machine reserve and transfers it to the specified destination.
     * 
     * @param destination the cash holder to receive the cash
     */

    public void collectAll(CashHolder destination) {
        System.out.println("Collecting...");
        machineReserve.transferAll(destination);
        System.out.println("Collecting Complete");
    }

    /**
     * Checks if the vending machine is full (all slots are occupied).
     * 
     * @return true if the vending machine is full, false otherwise
     */

    public boolean isFull() {
        return itemCount == NUMBER_OF_SLOTS;
    }

    /**
     * Increases the item count of the vending machine.
     * Used when an item is inserted into an empty slot.
     */
    public void increaseItemCount() {
        itemCount++;
    }

    /**
     * Retrieves the transaction record of the vending machine.
     * 
     * @return the TransactionRecord object representing the transaction record
     */

    public TransactionRecord getTransactionRecord () {
        return record;
    }
}