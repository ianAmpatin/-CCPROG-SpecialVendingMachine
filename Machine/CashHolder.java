import java.util.ArrayList;
/**
 * The CashHolder class represents a holder of cash in a vending machine or cash register.
 * It manages a collection of denominations and provides methods for adding cash, withdrawing cash,
 * transferring money to another cash holder, and performing various operations on the cash holdings.
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public class CashHolder {
    // Declaration of variables
    ArrayList<Denomination> holderDenom = new ArrayList<Denomination>();
    private String holder;

    /**
     * Constructs a new CashHolder object with the specified holder name.
     * @param holder is the name of the cash holder
     */
    public CashHolder (String holder) {
        this.holder = holder;
    }

    /**
     * This method will retrieve the name of the cash holder.
     * @return is the name of the cash holder
     */
    public String getHolder () {
        return holder;
    }

    /**
     * This method will check if a denomination with the specified value exists in the cash holder.
     * Returns the index of the denomination if found, otherwise returns -1.
     * @param value is the value of the denomination to search for
     * @return the index of the denomination if found, -1 otherwise
     */
    private boolean hasDenomination (double value) {
        for (int i = 0; i < holderDenom.size(); i++) {
            if (holderDenom.get(i).getValue() == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method will add the denomination to the cash holder by increasing the quantity of the specified denomination.
     * If the denomination already exists, the quantity is increased.
     * If the denomination does not exist, a new denomination is added to the cash holder.
     * @param denomination is the denomination that will be added
     * @param quantity is the quantity of the denomination
     */
    public void addCash (Denomination denomination, int quantity) {
        for (int i = 0; i < quantity; i++)
            holderDenom.add(new Denomination(denomination.getValue(), denomination.getDenominationType()));
    }

    /**
     * This method withdraws cash from the cash holder by decreasing the quantity of the specified denomination.
     * If the denomination exists and has sufficient quantity, the specified quantity is withdrawn.
     * @param value is the value of the denomination to withdraw
     * @param quantity is  the quantity that will be withdrawn
     * @return the withdrawn denomination if successful, null otherwise
     */
    public ArrayList<Denomination> withdraw (double value, int quantity) {
        if (this.hasDenomination(value)) {
            ArrayList<Denomination> withdrawal = new ArrayList<Denomination>();

            if (this.countDenomination(value) >= quantity) {
                for (int i = 0; i < quantity; i++) {
                    withdrawal.add(this.removeDenomination(value));
                }
                return withdrawal;
            }
            else {
                System.out.println("ERROR: Insuffience Denominations.");
                return null;
            }
        }
        System.out.println("ERROR: Denomination does not exits.");
        return null;
    }

    /**
     * This method transfers money from the current cash holder to the specified destination cash holder.
     * The transfer is successful if the current cash holder has the specified denomination
     * with sufficient quantity to transfer.
     * @param destination is the destination cash holder
     * @param denomination is the denomination to transfer
     * @param quantity is the quantity of the denomination to transfer
     */
    public void transferMoney (CashHolder destination, Denomination denomination, int quantity) {

        if (this.hasDenomination(denomination.getValue())) {
            if (this.countDenomination(denomination.getValue()) - quantity >= 0) {
                destination.addCash(denomination, quantity);
                this.withdraw(denomination.getValue(), quantity);
            }
            else {
                System.out.println("ERROR: Insufficeint Denominaton.");
            }
        }
    }

    /**
     * This method transfers all the cash from the current cash holder to the specified destination cash holder.
     * Each denomination is transferred one by one until the current cash holder is empty.
     * @param destination is the destination cash holder
     */
    public void transferAll (CashHolder destination) {
        try {
            while (holderDenom.size() >= 0)
                for (Denomination denomination : holderDenom)
                    transferMoney(destination, denomination, this.countDenomination(denomination.getValue()));
                
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method retrieves the denomination with the least value in the cash holder.
     * @return the denomination with the least value, or null if no denominations exist
     */
    public Denomination getLeast () {
        Denomination least;
        try {
            least = this.holderDenom.get(0);
        }
        catch (NullPointerException ne) {
            System.out.println(ne);
            return null;
        }

        for (Denomination denom : this.holderDenom) {
            if (denom.getValue() < least.getValue())
                least = denom;
        }
        return least;
    }

    /**
     * This method retrieves the greatest denomination with a value less than or equal to the specified value.
     * @param value is the value to compare against
     * @return the greatest denomination with a value less than or equal to the specified value,
     * or null if no denominations match the criteria
     */
    public Denomination getGreatestDenomination(double value) {
    Denomination greatest;

    try {
        greatest = this.holderDenom.get(0);
    }
    catch (NullPointerException v) {
        System.out.println(v);
        return null;
    }

    for (int i = 0; i < holderDenom.size(); i++) {

        if (holderDenom.get(i).getValue() <= value)
            if (holderDenom.size() > 0) 
                if (holderDenom.get(i).getValue() >= greatest.getValue()) 
                    greatest = holderDenom.get(i);
    }

    if (greatest.getValue() > value || greatest == null) 
        return null;

    return greatest;
    }

    /**
     * This method calculates and returns the total value of the cash holdings in the cash holder.
     * @return is the total value of the cash holdings
     */
    public double getTotal () {
        double total = 0;

        for (Denomination denom : holderDenom) {
            total += denom.getValue();
        }
        
        return total;
    }

    /**
     * This method will count the number of denominations that have the same value give by the "value" parameter
     * @param value is the value of the denominations that will be counted
     * @return the count of how many denomination that have the same value are there
     */

    public int countDenomination (double value) {
        int count = 0;

        for (Denomination denom : this.holderDenom) 
            if (denom.getValue() == value)
                count++;

        return count;
    }

    /**
     * This method will remove all the denomination that have the same value give by the "value" parameter
     * @param value is the value of the denominations that will be remove
     * @return a reference to the removed denomination
     */
    public Denomination removeDenomination (double value) {
        Denomination reference = null;
        for (int i = 0; i < holderDenom.size(); i++) {
            if (holderDenom.get(i).getValue() == value) {
                reference = holderDenom.get(i);
                holderDenom.remove(i);
            }
        }
        return reference;
    }

    /**
     * This method will retrive all the denomiantions with unique values
     * @return and array list containing denominations that have unique values
     */
    public ArrayList<Denomination> getUniqueDenomination() {
        try {
            ArrayList<Denomination> uniqueValues = new ArrayList<Denomination>();
            
            for (Denomination denomination : holderDenom) {
                boolean alreadyAdded = false;
                for (Denomination unique : uniqueValues) {
                    if (denomination.getValue() == unique.getValue()) {
                        alreadyAdded = true;
                        break;
                    }
                }
                if (!alreadyAdded) {
                    uniqueValues.add(new Denomination(denomination.getValue(), denomination.getDenominationType()));
                }
            }
    
            return uniqueValues;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
}