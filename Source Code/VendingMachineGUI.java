import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
 * This will be the GUI of the VendingMachineApp class, which will be responsible
 * for getting input from the user and process their input.
 */
public class VendingMachineGUI {
    /**
     * Constructor that will initialize the vending machine.
     */
    public VendingMachineGUI() {}

    /**
     * The main entry point of the vending machine.
     */
    public void run() {
    	/* Set up the frame */
    	JFrame frame = new JFrame("Vending Machine");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(600, 400);
    	frame.setLocationRelativeTo(null);

    	/* Welcome message */
    	JLabel welcomeLabel = new JLabel("Welcome to the Vending Machine! What would you like to do?");
    	welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
    	welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 18));

    	/* Buttons */
    	JButton createVMButton = new JButton("Create a Vending Machine");
    	JButton exitButton = new JButton("Exit");

    	createVMButton.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        showVendingMachineOptions();
    	    }
    	});

    	exitButton.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        exitProgram(frame);
    	    }
    	});

    	/* Panel and Layout */
    	JPanel panel = new JPanel();
    	panel.setLayout(new GridLayout(3, 1, 10, 10)); 
    	panel.add(welcomeLabel); 
    	panel.add(createVMButton);
    	panel.add(exitButton);

    	frame.add(panel);
    	frame.setVisible(true);
    }

    /**
     * This will allow the user to choose the vending machine they would like to create. They will
     * also have the option to go back to the main menu.
     */
    private void showVendingMachineOptions() {
        String[] options = {"Regular Vending Machine", "Special Vending Machine", "Back to Main Menu"};
        int choice = JOptionPane.showOptionDialog(null, "Choose the type of Vending Machine:", "Vending Machine Options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
            	RegularVMGUI regularVMGUI = new RegularVMGUI();
                regularVMGUI.initializeGUI();
                break;
            case 1:
            	SpecialVM specialVM = new SpecialVM(0.0, new MoneyDenomination(), new Items[8]);
            	specialVM.createItems();
            	specialVM.createProduct();

            	SpecialVMGUI specialVMGUI = new SpecialVMGUI(specialVM);
            	specialVMGUI.initializeGUI();
                break;
            case 2:
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid choice!");
        }
    }

    /**
     * This will terminate the program.
     * @param frame of the vending machine
     */
    private void exitProgram(JFrame frame) {
        frame.dispose();
        System.exit(0);
    }
}