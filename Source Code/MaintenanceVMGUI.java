import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the GUI for MaintenanceVM.
 */
public class MaintenanceVMGUI implements MachineGUI {
	/**
	 * The maintenance feature of a vending machine
	 */
    private MaintenanceVM maintenanceVM;

    /**
     * This is the constructor for the GUI of MaintenanceVM
     * @param maintenanceVM machine
     */
    public MaintenanceVMGUI(MaintenanceVM maintenanceVM) {
        this.maintenanceVM = maintenanceVM;
    }

    /**
     * This will initialize the GUI of the MaintenanceVM
     */
    public void initializeGUI() {
    	/* Create Frame */
        JFrame frame = new JFrame("Maintenance Vending Machine");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);

        /* Buttons */
        JButton restockButton = new JButton("Restock an Item");
        JButton replenishButton = new JButton("Replenish Money Denomination");
        JButton transactionsButton = new JButton("Print Transaction History");
        JButton backButton = new JButton("Back to Machine");

        restockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restockItem();
            }
        });

        replenishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                replenishMoneyDenomination();
            }
        });
        
        transactionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printTransactionHistory();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goBack(frame);
            }
        });

        /* Panel and Layout */
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.add(restockButton);
        panel.add(replenishButton);
        panel.add(transactionsButton);
        panel.add(backButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * This will prompt the user to input the slot number desired to restock and its quantity.
     * It will also display the result of the user's actions.
     */
    private void restockItem() {
    	String slot = JOptionPane.showInputDialog("Enter the slot number of the item to restock:");
        String quantity = JOptionPane.showInputDialog("Enter the quantity to restock:");
        try {
            int slotNumber = Integer.parseInt(slot);
            int quantityItem = Integer.parseInt(quantity);
            String restockResult = maintenanceVM.restock(slotNumber, quantityItem);
            JOptionPane.showMessageDialog(null, restockResult, "Restocking Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Restocking failed. Please enter a valid slot number.",
                    "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * This will allow the user to replenish the money denominations available in the machine. 
     */
    private void replenishMoneyDenomination() {
        maintenanceVM.replenishMoneyDenomination();
        JOptionPane.showMessageDialog(null, "Money denominations replenished.", "Replenish Successful", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * This will display the transaction history of the machine since it was created.
     */
    public void printTransactionHistory() {
        ArrayList<String> summaryList = new ArrayList<>();
        summaryList = maintenanceVM.printSummary();

        /* Create a JTextArea to display the summary */
        JTextArea textArea = new JTextArea();
        for (String summary : summaryList) {
            textArea.append(summary + "\n");
        }
        textArea.setEditable(false);

        /* Show the summary */
        JFrame frame = new JFrame("Overall Transactions Summary");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(textArea, BorderLayout.CENTER);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * This will return the user to the vending machine.
     * @param frame of the machine
     */
    private void goBack(JFrame frame) {
        frame.dispose();
    }
}
