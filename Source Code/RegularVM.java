/**
 * This is a class for the Regular Vending Machine. This machine will
 * consist of items placed in an item slot, which will indicate if it's 
 * available for purchase. It will also allow the user to choose an item,
 * pay for an item, receive a change if necessary, and receive the item.
 * This is the child of the CreateVM class.
 */
public class RegularVM extends CreateVM {
	/**
	 * This will create an object of a regular vending machine
	 * @param totalSales of the machine
	 * @param moneyDenomination is the denominations available in the machine
	 * @param items is the items available in the machine 
	 */
	public RegularVM(double totalSales, MoneyDenomination moneyDenomination, Items[] items) {
		super(totalSales, moneyDenomination, items);
	}
	
	/**
	 * This will check if the choice of the user is within the number of available slots.
	 * @param choice of slot by the user
	 * @return true if the slot is valid, else false.
	 */
    public boolean isValid(int choice) {
        if(choice >= 0 && choice < 8) {
        	return true;
        } else {
            return false;
        }
    }
    
    /**
     * This will compute for the change of the user in whole number not in denominations.
     * @param isPaid if the user's payment is valid
     * @param amountPaid by the user
     * @param item chosen by the user
     * @return the change
     */
    public double change(boolean isPaid, double amountPaid, Items item) {
    	double change = 0.0;
    	
    	if(isPaid) {
    		change = amountPaid - item.getItemPrice();
		}
    	
    	return change;
    }
    
    /**
     * This will update the machine's stock and total sales
     * @param item chosen by the user
     */
    public void updateMachine(Items item) {
    	/* Update item's stock */
    	item.decreaseStockQuantity(); 
    	
    	/* Update total sales of machine transactions */
        totalSales(item.getItemPrice()); 
    }
	
    /**
     * Getter method for the items in the machine.
     * @return items
     */
    public Items[] fetchItems() {
        return getItems();
    }
}
