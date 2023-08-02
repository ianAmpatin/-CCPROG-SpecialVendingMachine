/**
 * The Denomination class represents a denomination used in a vending machine for payment.
 * Each denomination has a value, denomination type (bill or coin), and quantity.
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */
public class Denomination {
    // Declaration of variables
    private final double VALUE;
    private final String DENOMINATION_TYPE;


    /**
     * Constructs a new Denomination object with the provided value, denomination type, and quantity.
     * The value must be greater than 0, and the denomination type must be either "Bill" or "Coin".
     * @param value is the value of the denomination
     * @param denominationType is the denomination type (bill or coin)
     * @param quantity is the quantity of the denomination
     */
    public Denomination (double value, String denominationType) {
        VALUE = value > 0 ? value : 0;
        DENOMINATION_TYPE = (denominationType.contentEquals("Bill") || denominationType.contentEquals("Coin")) ? denominationType : "Bill";
    }

    /**
     * This method will retrieve the value of the denomination.
     * @return is the value of the denomination
     */
    public double getValue () {
        return VALUE;
    }

    /**
     * This method will retrieve the denomination type.
     * @return the denomination type (bill or coin)
     */
    public String getDenominationType () {
        return DENOMINATION_TYPE;
    }
}