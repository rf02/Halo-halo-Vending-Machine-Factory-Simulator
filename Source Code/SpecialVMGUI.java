import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This will be the GUI for SpecialVM class. This will be the
 * interface for the aforementioned class.
 */
public class SpecialVMGUI implements MachineGUI {
	/**
	 * The special vending machine
	 */
    private SpecialVM specialVM;
    /**
     * Amount paid by the user to purchase an item or product
     */
    private double amountPaid;
    /**
     * Frame that will be used in the machine
     */
    private JFrame frame;

    /**
     * This is a constructor for the GUI of SpecialVM
     * @param specialVM machine
     */
    public SpecialVMGUI(SpecialVM specialVM) {
        this.specialVM = specialVM;
    }

    /**
     * This will initialize the GUI for SpecialVM.
     */
    public void initializeGUI() {
    	/* Create Frame */
        frame = new JFrame("Special Vending Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        /* Buttons */
        JButton purchaseButton = new JButton("Purchase an Item");
        JButton purchaseProductButton = new JButton("Purchase a Product");
        JButton maintenanceButton = new JButton("Perform Maintenance Operations");
        JButton historyButton = new JButton("Display Transaction History");
        JButton backButton = new JButton("Back to Main Menu");

        purchaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	showPurchaseItemGUI();
            }
        });

        purchaseProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	showPurchaseOptions();
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
                goBackToMainMenu();
            }
        });

        /* Panel and Layout */
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.add(purchaseButton);
        panel.add(purchaseProductButton);
        panel.add(maintenanceButton);
        panel.add(historyButton);
        panel.add(backButton);

        frame.add(panel);
        frame.setVisible(true);
    }
    
    /**
     * This method will display the GUI for purchasing an individual item.
     */
    private void showPurchaseItemGUI() {
        /* Prompt the user to choose an item to purchase */
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new GridLayout(specialVM.getItems().length, 1, 10, 10));
        
        for (int i = 0; i < specialVM.getItems().length; i++) {
            JButton itemButton = new JButton("Slot " + (i + 1) + ": " + specialVM.getItems()[i].getItemName() + 
											 "(Available: " + specialVM.getItems()[i].getItemQuantity() +
                                             " - Price: " + specialVM.getItems()[i].getItemPrice() + 
                                             " - Calories: " + specialVM.getItems()[i].getItemCalories() + ")");
            int itemIndex = i;
            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    /* Handle the purchase of the item */
                    purchaseItem(specialVM.getItems()[itemIndex]);
                }
            });
            itemPanel.add(itemButton);
        }

        /* Frame for item selection */
        JFrame itemFrame = new JFrame("Select Item to Purchase");
        itemFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        itemFrame.setSize(400, 300);
        itemFrame.setLocationRelativeTo(null);
        itemFrame.add(itemPanel);
        itemFrame.setVisible(true);
    }
    
    /**
     * This will allow the user to purchase an item.
     * @param item chosen by the user
     */
    private void purchaseItem(Items item) {
        try {
            /* Prompt user to pay */
            String inputAmount = JOptionPane.showInputDialog(null, "Enter the amount paid for " + item.getItemName() + ":");
            double amountPaid = Double.parseDouble(inputAmount);

            /* Handle the payment and transaction */
            boolean isPaid = specialVM.purchaseItem(item, 1, amountPaid);
            if (isPaid) {
            	
            	double change = specialVM.change(isPaid, amountPaid, item.getItemPrice()); // change in whole and decimal number
                String denominationsString = specialVM.getMoneyDenomination().giveChange(change); // change denominations
            	
                /* Show the denominations string in a JOptionPane */
                String message;
                if (change > 0) {
                    message = "Dispensing Item: " + item.getItemName() + "\nChange: P" + String.format("%.2f", change) +
                            "\n" + denominationsString;
                } else {
                    message = "Dispensing Item: " + item.getItemName() + "\n" + denominationsString;
                }
                JOptionPane.showMessageDialog(null, message, "Transaction Successful", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Transaction failed. Please try again.",
                        "Transaction Failed", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid quantity and amount.",
                    "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * This will prompt the user to choose a product they would like to customize.
     */
    private void showPurchaseOptions() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, specialVM.getProduct().length, 10, 10));
        
        for (int i = 0; i < specialVM.getProduct().length; i++) {
            JButton productButton = new JButton("Product " + (i + 1) + ": " + specialVM.getProduct()[i].getProductName());
            int productIndex = i;
            productButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showProductCustomization(productIndex);
                }
            });
            panel.add(productButton);
        }

        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * This will be the point of transaction for the special vending machine. This will be
     * responsible for asking the user for input, pay for product, and process transactions.
     * @param productIndex is the "slot number" of the chosen product of the user
     */
    private void showProductCustomization(int productIndex) {
        Product selectedProduct = specialVM.getProduct()[productIndex];
        int[] quantities = new int[selectedProduct.getItems().length];

        /* Panel showing the items of the selected product */
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(selectedProduct.getItems().length, 2, 10, 10));

        /* Prompt user input for quantity per item */
        for (int i = 0; i < selectedProduct.getItems().length; i++) {
            JLabel itemLabel = new JLabel(selectedProduct.getItems()[i].getItemName() + ":");
            JTextField quantityField = new JTextField();
            panel.add(itemLabel);
            panel.add(quantityField);
        }

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < selectedProduct.getItems().length; i++) {
                    try {
                        quantities[i] = Integer.parseInt(((JTextField) panel.getComponent(i * 2 + 1)).getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid quantity.",
                                "Invalid Input", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                /* Create the product and process the payment if quantities per item is greater than 0 */
                if (specialVM.isValid(quantities)) {
                    /* Display the preparation messages for preparing the product */
                    StringBuilder preparationMessage = new StringBuilder();
                    preparationMessage.append("Preparing product: ").append(selectedProduct.getProductName()).append("\n");
                    for (int i = 0; i < selectedProduct.getItems().length; i++) {
                        int quantity = quantities[i];
                        String itemName = selectedProduct.getItems()[i].getItemName();
                        for (int j = 0; j < quantity; j++) {
                            preparationMessage.append("Adding ").append(itemName).append("\n");
                        }
                    }
                    JOptionPane.showMessageDialog(null, preparationMessage.toString(), "Preparing Product", JOptionPane.INFORMATION_MESSAGE);

                    /* Compute the total price and total calories of the product */
                    double totalPrice = selectedProduct.computePrice(quantities);
                    int totalCalories = selectedProduct.computeCalories(quantities);

                    /* Display the total price and total calories of the product */
                    String priceMessage = "Price of " + selectedProduct.getProductName() + ": P" + String.format("%.2f", totalPrice);
                    String caloriesMessage = "Total calories of " + selectedProduct.getProductName() + ": " + totalCalories;
                    JOptionPane.showMessageDialog(null, priceMessage + "\n" + caloriesMessage, "Product Price and Calories", JOptionPane.INFORMATION_MESSAGE);

                    /* Prompt the user to pay */
                    String inputAmount = JOptionPane.showInputDialog(null, "Enter the amount paid: ");
                    try {
                        amountPaid = Double.parseDouble(inputAmount);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid amount entered. Transaction cancelled.", "Transaction Failed", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    /* Handle payment and transaction */
                    boolean isPaid = new HandlePayment(specialVM.getMoneyDenomination()).handlePayment(selectedProduct, amountPaid, quantities);
                    if (isPaid) {
                        double change = specialVM.change(isPaid, amountPaid, totalPrice); // change in whole and decimal number
                        String denominationsString = specialVM.getMoneyDenomination().giveChange(change); // change denominations

                        /* Show the transaction result */
                        String message;
                        if (change > 0) {
                            message = "Total calories of " + selectedProduct.getProductName() + ": " + totalCalories + "\n" +
                                    "Dispensing Product: " + selectedProduct.getProductName() + "\n" +
                                    "Change: P" + String.format("%.2f", change) + "\n" + denominationsString;
                        } else {
                            message = "Dispensing Product: " + selectedProduct.getProductName() + "\n" + denominationsString;
                        }
                        JOptionPane.showMessageDialog(null, message, "Transaction Successful", JOptionPane.INFORMATION_MESSAGE);

                        /* Update transaction history */
                        specialVM.updateMachine(selectedProduct, quantities);
                        
                        frame.getContentPane().removeAll();
                        initializeGUI();
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient funds. Transaction cancelled.",
                                "Transaction Failed", JOptionPane.WARNING_MESSAGE);
						initializeGUI();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient items to create " + selectedProduct.getProductName() + ". Transaction cancelled.",
                            "Transaction Failed", JOptionPane.WARNING_MESSAGE);
					initializeGUI();
                }
            }
        });

        /* Frame for this method */
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(confirmButton, BorderLayout.SOUTH);
		frame.revalidate();
        frame.repaint();
    }

    /**
     * If the user chooses to perform maintenance, then the program will instantiate
     * MaintenanceVM and then call its method.
     */
    private void performMaintenance() {
        MaintenanceVM maintenanceVM = new MaintenanceVM(specialVM);
        maintenanceVM.performMaintenance();
    }

    /**
     * If the user chooses to display transaction history, then the program will
     * instantiate MaintenanceVM and MaintenanceVMGUI, the GUI class for MaintenanceVM,
     * to call the method necessary for printing the transactions.
     */
    private void displayTransactionHistory() {
        MaintenanceVM maintenanceVM = new MaintenanceVM(specialVM);
        MaintenanceVMGUI maintenanceGUI = new MaintenanceVMGUI(maintenanceVM);
        maintenanceGUI.printTransactionHistory();
    }

    /**
     * This will return the user to the vending machine.
     */
    private void goBackToMainMenu() {
        frame.dispose();
        new VendingMachineGUI().run();
    }
}