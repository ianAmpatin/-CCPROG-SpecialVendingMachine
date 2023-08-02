import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * The CreateVendingMachine class represents a GUI window that allows users to create a new SpecialVendingMachine instance.
 * Users can input the name of the vending machine, the number of slots it will have, and the maximum quantity of items
 * each slot can hold. Upon clicking the "Create Machine" button, a new SpecialVendingMachine instance will be created with
 * the entered details, and it will be added to the list of vending machines. A VendingMachineSlot panel will also be added
 * to the main machine panel to represent the newly created vending machine visually in the GUI.
 * 
 * The class extends JFrame and implements ActionListener and FocusListener interfaces to handle button clicks and focus
 * events on text fields.
 * 
 * The main functionality of this class is to provide a user-friendly interface for creating and adding vending machines to
 * the GUI.
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public class CreateVendingMachine extends JFrame implements ActionListener, FocusListener{
    // Default Variables
    private JPanel createVendingMachinePanel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel numSlotsLabel;
    private JTextField numSlotsField;
    private JLabel maxLabel;
    private JTextField maxField;
    private JButton createButton;
    private ArrayList<SpecialVendingMachine> machineList;
    private JPanel machinePanel;
    private JPanel slots;
    private GUI GUI_Frame;
    private Color baseColor;
    private CashHolder client;
    
    /**
     * Constructs a new CreateVendingMachine instance with the specified parameters.
     * 
     * @param GUI_Frame the main GUI frame
     * @param machineList the list of vending machines
     * @param machinePanel the panel containing all vending machine slots
     * @param slots the panel containing all the slots
     * @param client is the client of the vending machine
     */

    public CreateVendingMachine(GUI GUI_Frame, ArrayList<SpecialVendingMachine> machineList, JPanel machinePanel, JPanel slots, CashHolder client) {
        // Initialize Create Vending Machine Variables
        this.client = client;
        this.machineList = machineList;
        this.machinePanel = machinePanel;
        this.slots = slots;
        this.GUI_Frame = GUI_Frame;
        this.setTitle("Create Vending Machine");
        this.setBackground(new Color(0x123456));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.baseColor = new Color(0xFFFDD0);
        createVendingMachinePanel = new JPanel();
        createVendingMachinePanel.setLayout(new GridLayout(4, 2, 0, 0));
        createVendingMachinePanel.setBackground(baseColor);
        nameLabel = new JLabel("Name: ");
        numSlotsLabel = new JLabel("Number of Slots: ");
        maxLabel = new JLabel("Maximum: ");
        nameField = new JTextField();
        numSlotsField = new JTextField();
        maxField = new JTextField();
        createButton = new JButton("Create Machine");
        createButton.setBackground(new Color(0xFFB347));
        createButton.addActionListener(this);
        createButton.setFont(new Font("MV Boli", Font.PLAIN, 15));

        /* --- Text Field Section ---  */
        nameField.setFont(new Font("MV Boli", Font.PLAIN, 15));
        nameField.setBorder(new LineBorder(baseColor, 2));
        nameField.setBackground(baseColor);
        nameField.addFocusListener(this);
        numSlotsField.setFont(new Font("MV Boli", Font.PLAIN, 15));
        numSlotsField.setBorder(new LineBorder(baseColor, 2));
        numSlotsField.setBackground(baseColor);
        numSlotsField.addFocusListener(this);
        maxField.setFont(new Font("MV Boli", Font.PLAIN, 15));
        maxField.setBorder(new LineBorder(baseColor, 2));
        maxField.setBackground(baseColor);
        maxField.addFocusListener(this);

        /* --- Label Section --- */
        nameLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        nameLabel.setBackground(new Color(0xFFFDD0));
        nameLabel.setOpaque(true);
        numSlotsLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        numSlotsLabel.setBackground(new Color(0xFFFDD0));
        numSlotsLabel.setOpaque(true);
        maxLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        maxLabel.setBackground(new Color(0xFFFDD0));
        maxLabel.setOpaque(true);

        createVendingMachinePanel.add(nameLabel);
        createVendingMachinePanel.add(nameField);
        createVendingMachinePanel.add(numSlotsLabel);
        createVendingMachinePanel.add(numSlotsField);
        createVendingMachinePanel.add(maxLabel);
        createVendingMachinePanel.add(maxField);
        createVendingMachinePanel.add(createButton);
        
        this.add(createVendingMachinePanel);
        this.pack();
        this.setVisible(true);
    }
    
    /**
     * Handles button clicks and creates a new SpecialVendingMachine instance with the entered details.
     * 
     * @param e the ActionEvent generated by the button click
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == createButton) {
            String name = nameField.getText();
            int numOfSlots = Integer.parseInt(numSlotsField.getText());
            int maximum = Integer.parseInt(maxField.getText());
            int confirm = 1;

            for(SpecialVendingMachine VM : machineList) {
                if (VM.getName().equalsIgnoreCase(name)) {
                    confirm = 0;
                    break;
                }
            }

            if (confirm == 1) {
                machineList.add(new SpecialVendingMachine(name, numOfSlots, maximum));
                machinePanel.add(new VendingMachineSlot(machineList.get(machineList.size() - 1), slots, GUI_Frame, client));
                GUI_Frame.setSelected(machineList.get(machineList.size() - 1));
            }

            this.dispose();
            nameField.setText("");
            numSlotsField.setText("");
            maxField.setText("");
            GUI_Frame.validate();
        }
    }

    /**
     * Handles focus gained events for the text fields.
     * 
     * @param e the FocusEvent generated when a text field gains focus
     */

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == nameField) {
            nameField.setBorder(new LineBorder(Color.ORANGE, 2));
        }
        else if (e.getSource() == numSlotsField) {
            numSlotsField.setBorder(new LineBorder(Color.ORANGE, 2));
        }
        else if (e.getSource() == maxField) {
            maxField.setBorder(new LineBorder(Color.ORANGE, 2));
        }
    }

    /**
     * Handles focus lost events for the text fields.
     * 
     * @param e the FocusEvent generated when a text field loses focus
     */
    
    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == nameField) {
            nameField.setBorder(new LineBorder(baseColor, 2));
        }
        else if (e.getSource() == numSlotsField) {
            numSlotsField.setBorder(new LineBorder(baseColor, 2));
        }
        else if (e.getSource() == maxField) {
            maxField.setBorder(new LineBorder(baseColor, 2));
        }
    }
}
