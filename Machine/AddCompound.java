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
 * The AddCompound class represents a GUI window for adding a compound item to a vending machine.
 * It extends the JFrame class and implements the ActionListener and FocusListener interfaces
 * to handle user interactions and events.
 * 
 * This class provides a user interface with input fields for entering the name and initial price
 * of the compound item. When the "Add Compound" button is clicked, the entered item is added to
 * the selected vending machine if it does not already exist as a compound item in the machine.
 * 
 * The GUI elements include a title, labels, text fields for input, and a button for adding the
 * compound item.
 * 
 * The class is associated with the GUI class to interact with the vending machine and update its
 * data accordingly.
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */
public class AddCompound extends JFrame implements ActionListener, FocusListener {
    private JPanel contentPanel;
    private JLabel nameLabel;
    private JLabel initialPriceLabel;
    private JTextField nameField;
    private JTextField initialPriceField;
    private JButton addButton;
    private GUI GUI_Frame;
    private Color baseColor;

    /**
     * Constructs a new AddCompound object with the provided GUI frame.
     * The method initializes the GUI elements, sets up the layout, and makes the window visible.
     * 
     * @param GUI_Frame the GUI frame associated with this AddCompound window
     */
    public AddCompound(GUI GUI_Frame) {
        this.setLayout(new BorderLayout());
        this.setTitle("Add Compound");
        this.setBackground(new Color(0x123456));
        this.GUI_Frame = GUI_Frame;
        this.baseColor = new Color(0xFFFDD0);

        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(3, 2, 0, 0));
        contentPanel.setBackground(baseColor);
        contentPanel.setVisible(true);

        nameLabel = new JLabel("Item Name: ");
        nameLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        nameLabel.setBackground(baseColor);
        nameLabel.setOpaque(true);

        initialPriceLabel = new JLabel("Price: ");
        initialPriceLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        initialPriceLabel.setBackground(baseColor);
        initialPriceLabel.setOpaque(true);

        nameField = new JTextField();
        nameField.setFont(new Font("MV Boli", Font.PLAIN, 15));
        nameField.setBorder(new LineBorder(baseColor, 2));
        nameField.setBackground(baseColor);
        nameField.addFocusListener(this);

        initialPriceField = new JTextField();
        initialPriceField.setFont(new Font("MV Boli", Font.PLAIN, 15));
        initialPriceField.setBorder(new LineBorder(baseColor, 2));
        initialPriceField.setBackground(baseColor);
        initialPriceField.addFocusListener(this);

        addButton = new JButton("Add Compound");
        addButton.setBackground(new Color(0xFFB347));
        addButton.addActionListener(this);
        addButton.setFont(new Font("MV Boli", Font.PLAIN, 15));

        contentPanel.add(nameLabel);
        contentPanel.add(nameField);
        contentPanel.add(initialPriceLabel);
        contentPanel.add(initialPriceField);
        contentPanel.add(addButton);

        this.add(contentPanel);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Handles the actionPerformed event triggered by the "Add Compound" button.
     * When the button is clicked, this method retrieves the entered name and initial price,
     * creates a new Item object, and adds it as a compound item to the selected vending machine.
     * The method then closes the window and clears the input fields.
     * 
     * @param e the ActionEvent object representing the button click event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String name = nameField.getText();
            double initialPrice = Double.parseDouble(initialPriceField.getText());

            Item item = new Item(name, initialPrice, 0);
            SpecialVendingMachine reference = GUI_Frame.getSelected();

            if (!reference.hasCompound(item)) {
                reference.addCompoundItem(item);
            }

            this.dispose();
            nameField.setText("");
            initialPriceField.setText("");
            GUI_Frame.validate();
        }
    }

    /**
     * Handles the focusGained event when the input fields gain focus.
     * When a field gains focus, this method changes its border color to orange to indicate focus.
     * 
     * @param e the FocusEvent object representing the focus gained event
     */
    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == nameField) {
            nameField.setBorder(new LineBorder(Color.ORANGE, 2));
        } else if (e.getSource() == initialPriceField) {
            initialPriceField.setBorder(new LineBorder(Color.ORANGE, 2));
        }
    }

    /**
     * Handles the focusLost event when the input fields lose focus.
     * When a field loses focus, this method changes its border color back to the base color.
     * 
     * @param e the FocusEvent object representing the focus lost event
     */
    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == nameField) {
            nameField.setBorder(new LineBorder(baseColor, 2));
        } else if (e.getSource() == initialPriceField) {
            initialPriceField.setBorder(new LineBorder(baseColor, 2));
        }
    }
}
