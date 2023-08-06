/**
 * The AcceptMoney class represents a window for depositing money into a vending machine.
 * Users can enter the denomination value and quantity they want to deposit and click the "Deposit" button.
 * The deposited money will be accepted by the selected vending machine.
 * The AcceptMoney class implements ActionListener and FocusListener interfaces for event handling.
 * It is used to deposit money into the vending machine.
 * This class is part of the vending machine GUI application.
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

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

public class AcceptMoney extends JFrame implements ActionListener, FocusListener {
    // Declaration of variables
    private JPanel contentPanel;
    private JLabel valueLabel;
    private JLabel quantityLabel;
    private JTextField valueField;
    private JTextField quantityField;
    private JButton depositButton;
    private GUI GUI_Frame;
    private Color baseColor;

    /**
     * Constructs a new AcceptMoney object.
     * The constructor initializes the GUI elements and sets up the AcceptMoney window.
     * @param GUI_Frame the main GUI frame to reference the selected vending machine
     */
    public AcceptMoney(GUI GUI_Frame) {
        this.GUI_Frame = GUI_Frame;
        this.baseColor = new Color(0xFFFDD0);
        this.setTitle("Deposit");
        this.setBackground(baseColor);

        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(4, 2, 0, 0));
        contentPanel.setBackground(baseColor);
        contentPanel.setVisible(true);

        valueLabel = new JLabel("Value: ");
        valueLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        valueLabel.setBackground(baseColor);
        valueLabel.setOpaque(true);

        quantityLabel = new JLabel("Quantity: ");
        quantityLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        quantityLabel.setBackground(baseColor);
        quantityLabel.setOpaque(true);

        valueField = new JTextField();
        valueField.setFont(new Font("MV Boli", Font.PLAIN, 15));
        valueField.setBorder(new LineBorder(baseColor, 2));
        valueField.setBackground(baseColor);
        valueField.addFocusListener(this);

        quantityField = new JTextField();
        quantityField.setFont(new Font("MV Boli", Font.PLAIN, 15));
        quantityField.setBorder(new LineBorder(baseColor, 2));
        quantityField.setBackground(baseColor);
        quantityField.addFocusListener(this);

        depositButton = new JButton("Deposit");
        depositButton.setBackground(new Color(0xFFB347));
        depositButton.addActionListener(this);
        depositButton.setFont(new Font("MV Boli", Font.PLAIN, 15));

        contentPanel.add(valueLabel);
        contentPanel.add(valueField);
        contentPanel.add(quantityLabel);
        contentPanel.add(quantityField);
        contentPanel.add(depositButton);

        this.add(contentPanel);
        this.pack();
        this.setVisible(true);
    }

    /**
     * This method is called when a component gains focus.
     * It changes the border color of the focused field to orange.
     * @param e the FocusEvent that occurred
     */
    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == valueField) {
            valueField.setBorder(new LineBorder(Color.ORANGE, 2));
        } else if (e.getSource() == quantityField) {
            quantityField.setBorder(new LineBorder(Color.ORANGE, 2));
        }
    }

    /**
     * This method is called when a component loses focus.
     * It changes the border color of the unfocused field back to the base color.
     * @param e the FocusEvent that occurred
     */
    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == valueField) {
            valueField.setBorder(new LineBorder(baseColor, 2));
        } else if (e.getSource() == quantityField) {
            quantityField.setBorder(new LineBorder(baseColor, 2));
        }
    }

    /**
     * This method is called when an action event occurs, such as clicking a button.
     * It deposits the specified denomination and quantity to the selected vending machine.
     * @param e the ActionEvent that occurred
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == depositButton) {
            double value = Double.parseDouble(valueField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            Denomination denom = new Denomination(value, "Bill");
            SpecialVendingMachine reference = GUI_Frame.getSelected();

            reference.acceptMoney(denom, quantity);

            this.dispose();
            valueField.setText("");
            quantityField.setText("");
            GUI_Frame.validate();
            
        }
    }
}
