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
 * The AddRequiredItems class represents a window for adding required items to a compound item in a vending machine.
 * Users can enter the details of the required item, such as name, calories, price, and quantity, and click the "Add Item" button.
 * The added required item will be associated with the specified compound item in the selected vending machine.
 * The AddRequiredItems class implements ActionListener and FocusListener interfaces for event handling.
 * It is used to add required items to compound items in the vending machine.
 * This class is part of the vending machine GUI application.
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public class AddRequiredItems extends JFrame implements ActionListener, FocusListener {
    //Declaration of Variables
    private JPanel contentPanel;
    private JLabel nameLabel;
    private JLabel caloriesLabel;
    private JLabel priceLabel;
    private JLabel quantityLabel;
    private JLabel compoundLabel;
    private JTextField nameField;
    private JTextField caloriesField;
    private JTextField priceField;
    private JTextField quantityField;
    private JTextField compoundField;
    private JButton addButton;
    private GUI GUI_Frame;
    private Color baseColor;

    /**
     * Constructs a new AddRequiredItems object.
     * The constructor initializes the GUI elements and sets up the AddRequiredItems window.
     * @param GUI_Frame the main GUI frame to reference the selected vending machine
     */

    public AddRequiredItems (GUI GUI_Frame) {
        this.setLayout(new BorderLayout());
        this.setTitle("Add Required Item");
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

        compoundLabel = new JLabel("Compound Item: ");
        compoundLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        compoundLabel.setBackground(baseColor);
        compoundLabel.setOpaque(true);

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

        compoundField = new JTextField();
        compoundField.setFont(new Font("MV Boli", Font.PLAIN, 15));
        compoundField.setBorder(new LineBorder(baseColor, 2));
        compoundField.setBackground(baseColor);
        compoundField.addFocusListener(this);

        addButton = new JButton("Add Item");
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
        contentPanel.add(compoundLabel);
        contentPanel.add(compoundField);
        contentPanel.add(addButton);

        this.add(contentPanel);
        this.pack();
        this.setVisible(true);
    }

    /**
     * This method is called when an action event occurs, such as clicking a button.
     * It adds the specified required item to the selected compound item in the vending machine.
     * @param e the ActionEvent that occurred
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {

            String name = nameField.getText();
            double calories = Double.parseDouble(caloriesField.getText());
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            String itemName = compoundField.getText();
            
            SpecialVendingMachine reference = GUI_Frame.getSelected();
            Item item = new Item(name, price, calories);
            RequiredItem reqItem = new RequiredItem(item, quantity);

            CompoundSlot refCompound = reference.getCompoundItem(itemName);

            if (refCompound != null) {
                reference.addRequiredITemToCompound(itemName, reqItem);
            }

            int resutl = reference.computeCompoundQuantity(refCompound);
            refCompound.setQuantity(resutl);

            this.dispose();
            nameField.setText("");
            caloriesField.setText("");
            priceField.setText("");
            quantityField.setText("");
            compoundField.setText("");
            GUI_Frame.validate();
        }
    }

    /**
     * This method is called when a component gains focus.
     * It changes the border color of the focused field to orange.
     * @param e the FocusEvent that occurred
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
        else if (e.getSource() == compoundField) {
            compoundField.setBorder(new LineBorder(Color.ORANGE, 2));
        }
    }
    
    /**
     * This method is called when a component loses focus.
     * It changes the border color of the unfocused field back to the base color.
     * @param e the FocusEvent that occurred
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
        else if (e.getSource() == compoundField) {
            compoundField.setBorder(new LineBorder(baseColor, 2));
        }
    }
    
}
