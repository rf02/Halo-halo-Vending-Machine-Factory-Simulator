/**
 * This is a class of products which contains the attributes
 * of a product, such as the items required for creating the
 * product and the amount of calories of the product.
 */
public class Product {
	/**
	 * The name of the product
	 */
	private String productName;
	/**
	 * The collection of items needed to create a product
	 */
	private Items[] items;
	/**
	 * The quantity per item in creating a product
	 */
    private int[] quantityPerItem;

    /**
     * This will create a new object of a product that will
     * be based on other items.
     * @param productName is the name of the product
     * @param items is the items in the product
     */
    public Product(String productName, Items[] items){
    	this.productName = productName;
    	this.items = items;
    	this.quantityPerItem = new int[items.length];
    }

    /**
     * This method will compute for the calories of the product
     * @param quantities per item in the product
     * @return the total amount of calories 
     */
    public int computeCalories(int[] quantities) {
        int totalCalories = 0;
        Items[] items = this.getItems();

        for (int i = 0; i < items.length; i++) {
            totalCalories += items[i].getItemCalories() * quantities[i];
        }

        return totalCalories;
    }
    
    /**
     * This will compute the total price of the product
     * @param quantities per item in the product
     * @return the total price
     */
    public double computePrice(int[] quantities) {
        double totalPrice = 0.0;
        Items[] items = this.getItems();

        for (int i = 0; i < items.length; i++) {
            totalPrice += items[i].getItemPrice() * quantities[i];
        }

        return totalPrice;
    }
    
    /**
     * Setter method to set the quantity of each item for the product.
     * @param quantities of each item in the product
     */
    public void setQuantityPerItem(int[] quantities) {
        this.quantityPerItem = quantities;
    }

    /**
     * Getter method for the items of the product
     * @return the items needed to produce the product
     */
    public Items[] getItems(){
    	return this.items;
    }

    /**
     * Getter method for the number of a specific item required 
     * in creating a product
     * @return the number per item
     */
    public int[] getQuantityPerItem() {
        return this.quantityPerItem;
    }

    /**
     * Getter method for the product's name
     * @return name of the product
     */
    public String getProductName() {
        return this.productName;
    }
}
