/**
 * This is a class for the Special Vending Machine. Similar to the
 * Regular Vending Machine, this machine will dispense an item but
 * also a product generated from a number of items available in the
 * machine. It will also allow the user to choose an product or an 
 * item, pay for the product, receive a change if necessary, and 
 * receive the product. This is the child of the CreateVM class.
 */
public class SpecialVM extends CreateVM {
	/**
	 * The products available in the machine
	 */
	private Product[] product;
	
	/**
	 * This will create an object of a special vending machine
	 * @param totalSales of the machine
	 * @param moneyDenomination is the denominations available in the machine
	 * @param items is the items available in the machine 
	 */
	public SpecialVM(double totalSales, MoneyDenomination moneyDenomination, Items[] items) {
		super(totalSales, moneyDenomination, items);
		this.product = new Product[2];
	}
	
	/**
	 * This will create objects of product available in the vending machine,
	 * that the user can purchase, which contains a mix of items.
	 * @return the product
	 */
	public Product[] createProduct() {
		/* Creating Halo-Halo product */
        Items[] haloHaloItems = {getItems()[0], getItems()[2], getItems()[6], getItems()[4], getItems()[1], 
                getItems()[5], getItems()[3], getItems()[7]};
        product[0] = new Product("Halo-Halo", haloHaloItems);

        /* Creating Creamy White Halo-Halo product */
        Items[] creamyWhiteHaloHaloItems = {getItems()[2], getItems()[4], getItems()[1], getItems()[3]};
        product[1] = new Product("Creamy White Halo-Halo", creamyWhiteHaloHaloItems);

        return product;
	}
	
	/**
	 * This will check if the input of the user--the quantities per item--are valid.
	 * @param quantities per item
	 * @return if input is valid or invalid
	 */
	public boolean isValid(int[] quantities) {
        boolean isValidProduct = true;
        for (int quantity : quantities) {
            if (quantity < 1) {
                isValidProduct = false;
            }
        }
        return isValidProduct;
	}
	
	/**
     * This will compute for the change of the user in whole number not in denominations.
     * @param isPaid if the user's payment is valid
     * @param amountPaid by the user
     * @param total price of the product
     * @return the change
     */
    public double change(boolean isPaid, double amountPaid, double total) {
    	double change = 0.0;
    	if(isPaid) {
    		change = amountPaid - total;
		}
    	return change;
    }
	
    /**
     * This will update the machine's total sales and stock quantity.
     * @param chosenProduct of the user
     * @param quantities per item in the product
     */
	public void updateMachine(Product chosenProduct, int[] quantities) {
		/* Update item quantities and total sales */
        for (int i = 0; i < chosenProduct.getItems().length; i++) {
            int quantity = quantities[i];
            chosenProduct.getItems()[i].decreaseStockQuantity(quantity);
        }
        totalSales(chosenProduct.computePrice(quantities));
	}
	
	/**
	 * This will handle all the payment transactions in the vending machine,
	 * and update the machine's total sales and stock quantity by method calling.
	 * @param chosenProduct of the user
	 * @param quantities per item
	 * @param amountPaid by the user
	 */
	public void payment(Product chosenProduct, int[] quantities, double amountPaid) {
        HandlePayment handlePayment = new HandlePayment(moneyDenomination);
		handlePayment.handlePayment(chosenProduct, amountPaid, quantities);
		updateMachine(chosenProduct, quantities);
	}
	
	/**
	 * This will allow the user to purchase an item in Special vending machine.
	 * @param item chosen
	 * @param quantity of item
	 * @param amountPaid by the user
	 * @return if purchase is successful
	 */
	public boolean purchaseItem(Items item, int quantity, double amountPaid) {
        if (item.getItemQuantity() >= quantity) {
            /* Check if payment is sufficient */
            double totalPrice = item.getItemPrice() * quantity;
            if (amountPaid >= totalPrice) {
                /* Handle payment */
                boolean isPaid = new HandlePayment( this.getMoneyDenomination()).handlePayment(item, amountPaid);
                if (isPaid) {
                    /* Update transaction history and stock quantity */
                    item.decreaseStockQuantity(quantity);
                    totalSales(totalPrice);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
	
	/**
	 * Getter method for the product
	 * @return product
	 */
	public Product[] getProduct() {
		return this.product;
	}
}
