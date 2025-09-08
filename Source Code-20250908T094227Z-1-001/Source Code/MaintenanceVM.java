import java.util.ArrayList;

/**
 * This is a class for the Maintenance Vending Machine feature which
 * is responsible for ensuring that there are enough stocks of items,
 * handling of payment transactions, and providing a summary of the
 * inventory of the machine.
 */
public class MaintenanceVM {
	/**
	 * The vending machine that was chosen by the user, either regular or special.
	 */
	private CreateVM vendingMachine;

	/**
	 * This constructor will allow the user to create an object of
	 * a class that will help in maintaining the items' stocks and 
	 * money denominations available in the machine.
	 * @param vendingMachine can either be the regular vending machine
	 * or the special vending machine
	 */
    public MaintenanceVM(CreateVM vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    /**
     * This will also be the starting point of the class where
     * the GUI will be called upon.
     */
    public void performMaintenance() {
    	MaintenanceVMGUI maintenanceVMGUI = new MaintenanceVMGUI(this);
        maintenanceVMGUI.initializeGUI();
    }

    /**
     * This method will restock the chosen item in the machine and
     * ask how many items will be restocked.
     * @param slotNumber of the item to restock
     * @param quantity of item
     * @return if restock is successful or not
     */
    public String restock(int slotNumber, int quantity) {
    	String result = null;
    	if(slotNumber >= 1 && slotNumber <= 8) {
	        result = (String)vendingMachine.restockItem(slotNumber, quantity);
        } 
    	return result;
    }

    /**
     * This will replenish the money denominations in the machine.
     */
    public void replenishMoneyDenomination() {
    	vendingMachine.getMoneyDenomination().replenish();
    }
    
    /**
     * This method will print the overall transaction of
     * the vending machine.
     * @return the total sales
     */
    public ArrayList<String> printSummary() {
        ArrayList<String> transaction = new ArrayList<>();
    	
    	Items[] items = vendingMachine.getItems();
        for (Items item : items) {
            transaction.add(item.getItemName() + ": Quantity Sold is " + item.getItemSold());
        }
        transaction.add("Total Sales: " + vendingMachine.getTotalSales());
        return transaction;
    }
}
