/**
 * This program simulates a Vending Machine Factory. A user may 
 * choose their desired item and purchase it by using a vending 
 * machine. This version replicates a regular vending machine and
 * a special vending machine. 
 * @author Rulet S. Fulo and Rachel Lauren C. Manlapig
 * @version 01/08/2023
 */

/**
 * This will be the main entry point of the program
 */
public class VendingMachineApp {

	/**
	 * This method is the entry point of the program which will 
	 * run the GUI of the program.  
	 * @param args array of String that holds all command line parameter arguments
	 */
	public static void main(String[] args) {
		VendingMachineGUI gui = new VendingMachineGUI();
        gui.run();
    }
}