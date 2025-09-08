/**
 * This will handle all of the payment given by the user, which will
 * be processed by the program. 
 */
public class HandlePayment {
	/**
	 * This is the money denomination to give change to the user in different denominations.
	 */
	private MoneyDenomination moneyDenomination;
	
	/**
	 * This is a constructor that will handle the payment by accessing
	 * the money denominations available in the machine.
	 * @param moneyDenomination available in the machine
	 */
	public HandlePayment(MoneyDenomination moneyDenomination) {
        this.moneyDenomination = moneyDenomination;
    }
	
	/**
     * A method overload specifically designed for RegularVM class.
     * This will get the payment from the user and process the change to be given.
     * @param item chosen by the user to purchase
	 * @param amountPaid by the user
     * @return true if payment is successful, else false, which will also be displayed.
     */
	public boolean handlePayment(Items item, double amountPaid) {
    	double itemPrice = item.getItemPrice(); // price of items
    	
        /* Check if payment is sufficient */
        if (amountPaid >= itemPrice) {
            double change = amountPaid - itemPrice;
            /* Sufficient payment, thus it will dispense change (if sufficient) */
            if (moneyDenomination.hasDenominations(change)) {
                moneyDenomination.giveChange(change);
                return true;
            }
		}
        return false;
    }

    /**
     * A method overload specifically designed for SpecialVM class.
     * This will get the payment from the user and process the change to be given.
     * @param chosenProduct of the user
     * @param amountPaid by the user
	 * @param quantities per item 
     * @return true if payment is successful, else false, which will also be displayed.
     */
    public boolean handlePayment(Product chosenProduct, double amountPaid, int[] quantities) {
		double totalPrice = chosenProduct.computePrice(quantities);
		boolean isHandled = false;
		
		if (amountPaid >= totalPrice) {
            double change = amountPaid - totalPrice;
            if (moneyDenomination.hasDenominations(change)) {
                moneyDenomination.giveChange(change);
                isHandled = true;
            } else { 
				isHandled = false;
			}				
		} else {
			isHandled = false;
		}
        return isHandled;
    }
}