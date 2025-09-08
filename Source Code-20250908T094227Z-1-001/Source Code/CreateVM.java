/**
 * This will be the parent class for the two vending machines offered
 * by the program, CreateVM. This will contain the methods and
 * variables necessary for the two VMs to run, such as creating items,
 * restocking items, computing for the total sales, setting the total
 * sales, setting the items, getting the money denomination, getting
 * the total sales, and getting the items.
 */
public abstract class CreateVM {
	/**
	 * This represents the total sales of the vending machine
	 */
	private double totalSales;
	/**
	 * moneyDenomination variable is protected so that money denominations can be
	 * accessed by both vending machines.
	 */
    protected MoneyDenomination moneyDenomination;
    /**
     * This represents the items that will be created to allow the vending machine to operate.
     */
    private Items[] items;
	
    /**
     * This is a constructor for the parent class
     * @param totalSales of the vending machine
     * @param moneyDenomination the denominations available in the machine
     * @param items available in the machine 
     */
    public CreateVM(double totalSales, MoneyDenomination moneyDenomination, Items[] items) {
    	this.setTotalSales(0);
        this.moneyDenomination = new MoneyDenomination();
        this.setItems(new Items[8]);
    }
    
    /**
	 * This will create objects of items available in the vending machine,
	 * that the user can purchase. 
	 */
	public void createItems() {
        items[0] = new Items("Ube", 385.0, 10, 150);
        items[1] = new Items("Leche Flan", 215.0, 10, 200);
        items[2] = new Items("Banana", 125.0, 10, 100);
        items[3] = new Items("Ice", 5.0, 10, 0);
        items[4] = new Items("Condensed Milk", 55.0, 10, 100);
        items[5] = new Items("Sugar", 89.0, 10, 50);
        items[6] = new Items("Macapuno", 135.0, 10, 250);
        items[7] = new Items("Nata de Coco", 58.0, 10, 150);
    }
	
	/**
     * This will allow the user to choose which slot number that needs
     * restocking. Once a slot number was chosen, it will direct the
     * user to the restock() method of the Items class and properly
     * restock the item in the slot.
     * @param slotNumber the chosen slot number of the user
     * @param quantity the number of items in the slot
	 * @return the string for the result of restocking an item 
     */
    public String restockItem(int slotNumber, int quantity) {
    	String result = (String) items[slotNumber - 1].restock(quantity);
    	return result;
    }
    
    /**
     * This method will compute for the total sales of the vending
     * machine, which will reflect on the OverallTransactions class.
     * @param amount of the total sales
     */
    public void totalSales(double amount) {
    	setTotalSales(getTotalSales() + amount);
    }
	
	/**
     * Getter method for the money denomination in the machine.
     * @return available money denomination
     */
    public MoneyDenomination getMoneyDenomination() {
        return moneyDenomination;
    }

    /**
     * Getter method for the total sales of the vending machine.
     * @return the total sales
     */
	public double getTotalSales() {
		return totalSales;
	}

	/**
	 * Setter method for the total sales of the vending machine.
	 * @param totalSales is the total amount of sales, depending
	 * on the user's purchase.
	 */
	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}

	/**
	 * Getter method for the items
	 * @return items
	 */
	public Items[] getItems() {
		return items;
	}

	/**
	 * Setter method to set the information of an item
	 * @param items containing the information of an item
	 */
	public void setItems(Items[] items) {
		this.items = items;
	}	
}
