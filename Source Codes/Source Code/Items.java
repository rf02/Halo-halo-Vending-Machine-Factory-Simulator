/**
 * This is a class of Items which contains the attributes
 * of an item, such as its name, price, calories, and
 * also include the number of stocks a certain item has.
 * It will also count the item's quantity in the machine,
 * count the number of items sold, and allow the user to
 * restock their chosen item.
 */
public class Items {
	/**
	 * This is the item's name
	 */
	private String itemName;
    /**
     * This is the item's price
     */
	private double itemPrice;
	/**
	 * This is the quantity per item in the machine
	 */
    private int itemQuantity;
    /**
     * The amount of calories of the item
     */
    private int itemCalories;
    /**
     * For the quantity of an item in the machine
     */
    private int itemSold;
	
    /**
     * This is a constructor for the attributes of an item
     * @param itemName the name of the item
     * @param itemPrice the price of the item
     * @param itemQuantity the number of stocks of the item
     * @param itemCalories the amount of calories of the item
     */
    public Items(String itemName, double itemPrice, int itemQuantity, int itemCalories) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.itemCalories = itemCalories;
        this.setItemSold(0);
    }
	
	/**
	 * Getter method for the name of the item
	 * @return a string of item's name
	 */
	public String getItemName() {
		return this.itemName;
	}
	
	/**
	 * Setter method for setting the item name
	 * @param itemName is the item's name
	 */
	public void setItemName(String itemName) {
        this.itemName = itemName;
    }
	
	/**
	 * Getter method for the price of the item
	 * @return a float number of the item's price
	 */
	public double getItemPrice() {
		return this.itemPrice;
	}
	
	/**
	 * Getter method for the number of stocks of the item
	 * @return a number of the item's stocks
	 */
	public int getItemQuantity() {
		return this.itemQuantity;
	}
	
	/**
	 * Getter method for the amount of calories of the item
	 * @return a number of the item's calories
	 */
	public int getItemCalories() {
		return this.itemCalories;
	}
	
	/**
	 * Setter method for setting the amount of calories of the item
	 * @param itemCalories the amount of calories of the item 
	 */
	public void setItemCalories(int itemCalories) {
		this.itemCalories = itemCalories;
	}
	
	/**
	 * The method is specifically designed for RegularVM.
	 * This is a counter for the quantity of an item in the vending
	 * machine. It will also increment the amount of items sold in 
	 * the vending machine.
	 */
	public void decreaseStockQuantity() {
        if (itemQuantity > 0) {
        	itemQuantity--;
        	setItemSold(getItemSold() + 1);
        } else {
        	itemQuantity = 0;
        }
    }
	
	/**
	 * A method overloading, specifically for SpecialVM.
	 * This is a counter for the quantity of an item in the vending
	 * machine. It will also increment the amount of items sold in 
	 * the vending machine.
	 * @param quantity of item
	 */
	public void decreaseStockQuantity(int quantity) {
        if (itemQuantity >= quantity) {
        	itemQuantity -= quantity;
        	setItemSold(getItemSold() + quantity);
        } 
    }
	
	/**
	 * This method will restock an item in the slot when the
	 * quantity is less than or equal to 10. This will allow 
	 * for a better and smoother use of the vending machine.
	 * @param quantity of the item in the slot
	 * @return string of result of restocking
	 */
	public String restock(int quantity) {
		StringBuilder result = new StringBuilder();
		
        if (this.itemQuantity + quantity <= 10) {
        	this.itemQuantity += quantity;
        	result.append("Restocked ").append(quantity).append(" ").append(this.itemName).append(".");
        } else {
            result.append("Exceeded the number of items required to replenish items. Restocking cancelled.");
        }
        return result.toString();
    }

	/**
	 * Getter method for the number of items sold in the machine
	 * @return quantity of items sold
	 */
	public int getItemSold() {
		return itemSold;
	}

	/**
	 * Setter method for the amount of items sold
	 * @param itemSold the amount of items sold
	 */
	public void setItemSold(int itemSold) {
		this.itemSold = itemSold;
	}
}
