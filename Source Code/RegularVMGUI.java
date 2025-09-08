import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This will be the GUI for RegularVM class. This will be the
 * interface for the aforementioned class.
 */
public class RegularVMGUI implements MachineGUI {
	/**
	 * The variable for the regular vending machine
	 */
	private RegularVM vendingMachine;
    /**
     * The amount paid by the user in purchasing an item
     */
	private double amountPaid;

    /**
     * This is a constructor for RegularVMGUI, which will instantiate RegularVM
     * and create the objects of items that will be used in the transactions.
     */
    public RegularVMGUI() {
        vendingMachine = new RegularVM(0.0, new MoneyDenomination(), new Items[8]);
        vendingMachine.createItems(); 
    }

    /**
     * This will initialize the GUI for RegularVM.
     */
    public void initializeGUI() {
    	/* Create the Frame */
        JFrame frame = new JFrame("Regular Vending Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        /* Buttons */
        JButton purchaseButton = new JButton("Purchase an Item");
        JButton maintenanceButton = new JButton("Perform Maintenance Operations");
        JButton historyButton = new JButton("Display Transaction History");
        JButton backButton = new JButton("Back to Main Menu");

        purchaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                purchaseItemGUI();
            }
        });

        maintenanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performMaintenance();
            }
        });

        historyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayTransactionHistory();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goBackToMainMenu(frame);
            }
        });

        /* Panel and Layout */
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.add(purchaseButton);
        panel.add(maintenanceButton);
        panel.add(historyButton);
        panel.add(backButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * This will be the point of transaction for the regular vending machine. This will be
     * responsible for asking the user for input.
     */
	private void purchaseItemGUI() {
		/* Get the items from the vending machine */
		Items[] items = vendingMachine.getItems();

		/* Panel to hold the buttons with grid layout */
		JPanel itemPanel = new JPanel();
		itemPanel.setLayout(new GridLayout(items.length, 1, 10, 10));

		/* Show items to purchase as buttons in the custom dialog */
		for (int i = 0; i < items.length; i++) {
			/* Create the button and add it to the panel */
			JButton itemButton = new JButton("Slot " + (i + 1) + ": " + items[i].getItemName() + " (Available: " +
					items[i].getItemQuantity() + " - Price: P" + items[i].getItemPrice() +
					" - Calories: " + items[i].getItemCalories() + ")");

			int choice = i;
			itemButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					/* Handle the purchase of the item */
					showPurchase(items[choice]);
				}
			});
			itemPanel.add(itemButton);
		}

		/* Dialog to show the items */
		JDialog dialog = new JDialog((JFrame) null, "Purchase Options", true);
		dialog.getContentPane().setLayout(new BorderLayout());
		dialog.getContentPane().add(itemPanel, BorderLayout.CENTER);
		dialog.setSize(500, 300);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	} 
	
	/**
	 * This is the point of transaction after choosing an item in the previous method.
	 * @param items is the item chosen by the user
	 */
	private void showPurchase(Items items) {
		/* Prompt the user to pay */
		String inputAmount = JOptionPane.showInputDialog(null, "Enter the amount paid for " + items.getItemName() + ":");
		try {
			amountPaid = Double.parseDouble(inputAmount);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Invalid amount entered. Transaction cancelled.", "Transaction Failed", JOptionPane.WARNING_MESSAGE);
			return;
		}

		/* Handle the payment and transaction */
		boolean isPaid = new HandlePayment(vendingMachine.getMoneyDenomination()).handlePayment(items, amountPaid);
		if (isPaid) {
			double change = vendingMachine.change(isPaid, amountPaid, items); // change in whole and decimal number
			String denominationsString = vendingMachine.getMoneyDenomination().giveChange(change); // change denominations

			/* Show the denominations string in a JOptionPane */
			String message;
			if (change > 0) {
				message = "Dispensing Item: " + items.getItemName() + "\nChange: P" + String.format("%.2f", change) +
						"\n" + denominationsString;
			} else {
				message = "Dispensing Item: " + items.getItemName() + "\n" + denominationsString;
			}
			JOptionPane.showMessageDialog(null, message, "Transaction Successful", JOptionPane.INFORMATION_MESSAGE);

			/* Update transaction history */
			vendingMachine.updateMachine(items);
		} else {
			JOptionPane.showMessageDialog(null, "Insufficient funds. Transaction cancelled.",
					"Transaction Failed", JOptionPane.WARNING_MESSAGE);
		}
	} 
	
    /**
     * If the user chooses to perform maintenance, then the program will instantiate
     * MaintenanceVM and then call its method.
     */
    private void performMaintenance() {
        MaintenanceVM maintenanceVM = new MaintenanceVM(vendingMachine);
        maintenanceVM.performMaintenance();
    }

    /**
     * If the user chooses to display transaction history, then the program will
     * instantiate MaintenanceVM and MaintenanceVMGUI, the GUI class for MaintenanceVM,
     * to call the method necessary for printing the transactions.
     */
    private void displayTransactionHistory() {
    	MaintenanceVM maintenanceVM = new MaintenanceVM(vendingMachine);
        MaintenanceVMGUI maintenanceGUI = new MaintenanceVMGUI(maintenanceVM);
        maintenanceGUI.printTransactionHistory();
    }

    /**
     * This will return the user to the vending machine.
     * @param frame of the machine
     */
    private void goBackToMainMenu(JFrame frame) {
    	frame.dispose(); // Close the RegularVMGUI window
    	new VendingMachineGUI().run(); // Go to main menu
    }

}