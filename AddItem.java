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
 * The AddItem class represents a GUI window for adding an item to a vending machine.
 * It extends the JFrame class and implements the ActionListener and FocusListener interfaces
 * to handle user interactions and events.
 * 
 * This class provides a user interface with input fields for entering the name, calories,
 * price, and quantity of the item. When the "Add Item" button is clicked, the entered item
 * is added to the selected vending machine. If the item already exists in the machine, the
 * quantity of the existing item is updated. If the item does not exist and there is an empty
 * slot in the machine, the item is inserted into the empty slot.
 * 
 * The GUI elements include a title, labels, text fields for input, and a button for adding the
 * item. The class is associated with the GUI class to interact with the vending machine and
 * update its data accordingly.
 * 
 * The class also implements FocusListener to handle focus events on the input fields. When a
 * field gains focus, its border color changes to orange, and when it loses focus, the border color
 * returns to the base color.
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */
public class AddItem extends JFrame implements ActionListener, FocusListener {
    private JPanel contentPanel;
    private JLabel nameLabel;
    private JLabel caloriesLabel;
    private JLabel priceLabel;
    private JLabel quantityLabel;
    private JTextField nameField;
    private JTextField caloriesField;
    private JTextField priceField;
    private JTextField quantityField;
    private JButton addButton;
    private GUI GUI_Frame;
    private Color baseColor;

    /**
     * Constructs a new AddItem object with the provided GUI frame.
     * The method initializes the GUI elements, sets up the layout, and makes the window visible.
     * 
     * @param GUI_Frame the GUI frame associated with this AddItem window
     */
    public AddItem(GUI GUI_Frame) {
        this.setLayout(new BorderLayout());
        this.setTitle("Add Item");
        this.setBackground(new Color(0x123456));
        this.GUI_Frame = GUI_Frame;
        this.baseColor = new Color(0xFFFDD0);

        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(5, 2, 0, 0));
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
        contentPanel.add(addButton);

        this.add(contentPanel);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Handles the actionPerformed event triggered by the "Add Item" button.
     * When the button is clicked, this method retrieves the entered name, calories, price, and
     * quantity, creates a new Item object, and adds it to the selected vending machine.
     * If the item already exists in the machine, its quantity is updated.
     * If the item does not exist and there is an empty slot in the machine, the item is inserted
     * into the empty slot.
     * The method then closes the window and clears the input fields.
     * 
     * @param e the ActionEvent object representing the button click event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String name = nameField.getText();
            double calories = Double.parseDouble(caloriesField.getText());
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            int confirm = 1;
            
            Item item = new Item(name, price, calories);
            SpecialVendingMachine reference = GUI_Frame.getSelected();

            if (reference.hasItem(item)) {
                reference.addItemQuantity(name, quantity);
            } else {
                for (Slot refItem : reference.getSlots()) {
                    if (refItem.getItem() != null && refItem.getItem().getName().equalsIgnoreCase(name)) {
                        confirm = 0;
                        break;
                    }
                }

                if (confirm == 1) {
                    reference.insertInEmpty(item, quantity);
                }
            }

            try {
                for (Slot slot : reference.getSlots())
                    System.out.println("Item: " + slot.getItem().getName() + "\nSlot Num: " + slot.getSlotNum());
            }
            catch (Exception f) {
                System.out.println(f);
            }


            this.dispose();
            nameField.setText("");
            caloriesField.setText("");
            priceField.setText("");
            quantityField.setText("");
            GUI_Frame.validate();
        }
    }

    /**
     * Handles the focusGained event when the input fields gain focus.
     * When a field gains focus, its border color changes to orange to indicate focus.
     * 
     * @param e the FocusEvent object representing the focus gained event
     */
    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == nameField) {
            nameField.setBorder(new LineBorder(Color.ORANGE, 2));
        } else if (e.getSource() == caloriesField) {
            caloriesField.setBorder(new LineBorder(Color.ORANGE, 2));
        } else if (e.getSource() == priceField) {
            priceField.setBorder(new LineBorder(Color.ORANGE, 2));
        } else if (e.getSource() == quantityField) {
            quantityField.setBorder(new LineBorder(Color.ORANGE, 2));
        }
    }

    /**
     * Handles the focusLost event when the input fields lose focus.
     * When a field loses focus, its border color returns to the base color.
     * 
     * @param e the FocusEvent object representing the focus lost event
     */
    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == nameField) {
            nameField.setBorder(new LineBorder(baseColor, 2));
        } else if (e.getSource() == caloriesField) {
            caloriesField.setBorder(new LineBorder(baseColor, 2));
        } else if (e.getSource() == priceField) {
            priceField.setBorder(new LineBorder(baseColor, 2));
        } else if (e.getSource() == quantityField) {
            quantityField.setBorder(new LineBorder(baseColor, 2));
        }
    }
}

