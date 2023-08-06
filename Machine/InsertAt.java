import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * The InsertAt class represents a GUI window that allows users to insert an item into a specific slot in the selected vending machine.
 * Users can input the item's name, calories, price, quantity, and the slot number where they wish to insert the item.
 * Upon clicking the "Insert Item" button, the entered item will be added to the specified slot in the vending machine.
 * 
 * The class extends JFrame and implements ActionListener and FocusListener interfaces to handle button clicks and focus
 * events on text fields.
 * 
 * The main functionality of this class is to provide a user-friendly interface for inserting items into the selected vending machine.
 * 
 * Note: The functionality of this class relies on the existence of the Item class and the SpecialVendingMachine class.
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public class InsertAt extends JFrame implements ActionListener, FocusListener{
    private JPanel contentPanel;
    private JLabel nameLabel;
    private JLabel caloriesLabel;
    private JLabel priceLabel;
    private JLabel quantityLabel;
    private JLabel locationLabel;
    private JTextField nameField;
    private JTextField caloriesField;
    private JTextField priceField;
    private JTextField quantityField;
    private JTextField locationField;
    private JButton addButton;
    private GUI GUI_Frame;
    private Color baseColor;

    /**
     * Constructs a new InsertAt instance with the specified parameters.
     * @param GUI_Frame the main GUI frame
     */
    public InsertAt (GUI GUI_Frame) {
        this.setLayout(new BorderLayout());
        this.setTitle("Insert At");
        this.setBackground(new Color(0x123456));
        this.GUI_Frame = GUI_Frame;
        this.baseColor = new Color(0xFFFDD0);

        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(6, 2, 0, 0));
        contentPanel.setBackground(baseColor);
        contentPanel.setVisible(true);

        nameLabel = new JLabel("Item Name: ");
        nameLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        nameLabel.setBackground(baseColor);
        nameLabel.setOpaque(true);
        
        caloriesLabel = new JLabel("Calories: ");
        caloriesLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        caloriesLabel.setBackground(baseColor);
        caloriesLabel.setOpaque(true);

        priceLabel = new JLabel("Price: ");
        priceLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        priceLabel.setBackground(baseColor);
        priceLabel.setOpaque(true);

        quantityLabel = new JLabel("Quantity: ");
        quantityLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        quantityLabel.setBackground(baseColor);
        quantityLabel.setOpaque(true);

        locationLabel = new JLabel("Slot Number: ");
        locationLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        locationLabel.setBackground(baseColor);
        locationLabel.setOpaque(true);

        nameField = new JTextField();
        nameField.setFont(new Font("MV Boli", Font.PLAIN, 15));
        nameField.setBorder(new LineBorder(baseColor, 2));
        nameField.setBackground(baseColor);
        nameField.addFocusListener(this);

        caloriesField = new JTextField();
        caloriesField.setFont(new Font("MV Boli", Font.PLAIN, 15));
        caloriesField.setBorder(new LineBorder(baseColor, 2));
        caloriesField.setBackground(baseColor);
        caloriesField.addFocusListener(this);

        priceField = new JTextField();
        priceField.setFont(new Font("MV Boli", Font.PLAIN, 15));
        priceField.setBorder(new LineBorder(baseColor, 2));
        priceField.setBackground(baseColor);
        priceField.addFocusListener(this);

        quantityField = new JTextField();
        quantityField.setFont(new Font("MV Boli", Font.PLAIN, 15));
        quantityField.setBorder(new LineBorder(baseColor, 2));
        quantityField.setBackground(baseColor);
        quantityField.addFocusListener(this);

        locationField = new JTextField();
        locationField.setFont(new Font("MV Boli", Font.PLAIN, 15));
        locationField.setBorder(new LineBorder(baseColor, 2));
        locationField.setBackground(baseColor);
        locationField.addFocusListener(this);

        addButton = new JButton("Insert Item");
        addButton.setBackground(new Color(0xFFB347));
        addButton.addActionListener(this);
        addButton.setFont(new Font("MV Boli", Font.PLAIN, 15));

        contentPanel.add(nameLabel);
        contentPanel.add(nameField);
        contentPanel.add(caloriesLabel);
        contentPanel.add(caloriesField);
        contentPanel.add(priceLabel);
        contentPanel.add(priceField);
        contentPanel.add(quantityLabel);
        contentPanel.add(quantityField);
        contentPanel.add(locationLabel);
        contentPanel.add(locationField);
        contentPanel.add(addButton);

        this.add(contentPanel);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Handles button clicks and inserts the specified item into the specified slot in the vending machine.
     * 
     * @param e the ActionEvent generated by the button click
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {

            String name = nameField.getText();
            double calories = Double.parseDouble(caloriesField.getText());
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            int location = Integer.parseInt(locationField.getText());
            
            Item item = new Item(name, price, calories);
            SpecialVendingMachine reference = GUI_Frame.getSelected();

            reference.insertAt(item, quantity, location);

            this.dispose();
            nameField.setText("");
            caloriesField.setText("");
            priceField.setText("");
            quantityField.setText("");
            locationField.setText("");
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
        else if (e.getSource() == caloriesField) {
            caloriesField.setBorder(new LineBorder(Color.ORANGE, 2));
        }
        else if (e.getSource() == priceField) {
            priceField.setBorder(new LineBorder(Color.ORANGE, 2));
        }
        else if (e.getSource() == quantityField) {
            quantityField.setBorder(new LineBorder(Color.ORANGE, 2));
        }
        else if (e.getSource() == locationField) {
            quantityField.setBorder(new LineBorder(Color.ORANGE, 2));
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
        else if (e.getSource() == caloriesField) {
            caloriesField.setBorder(new LineBorder(baseColor, 2));
        }
        else if (e.getSource() == priceField) {
            priceField.setBorder(new LineBorder(baseColor, 2));
        }
        else if (e.getSource() == quantityField) {
            quantityField.setBorder(new LineBorder(baseColor, 2));
        }
        else if (e.getSource() == locationField) {
            locationField.setBorder(new LineBorder(baseColor, 2));
        }
    }
}
