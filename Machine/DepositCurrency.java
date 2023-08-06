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
 * The DepositCurrency class represents a GUI window that allows users to deposit currency denominations into the selected
 * vending machine. Users can input the denomination value, type, and quantity they wish to deposit. Upon clicking the "Deposit"
 * button, the entered denomination will be added to the machine's reserve based on the specified quantity.
 * 
 * The class extends JFrame and implements ActionListener and FocusListener interfaces to handle button clicks and focus
 * events on text fields.
 * 
 * The main functionality of this class is to provide a user-friendly interface for depositing currency denominations into the
 * selected vending machine's reserve.
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public class DepositCurrency extends JFrame implements ActionListener, FocusListener {
    private JPanel contentPanel;
    private JLabel valueLabel;
    private JLabel typeLabel;
    private JLabel quantityLabel;
    private JTextField valueField;
    private JTextField typeField;
    private JTextField quantityField;
    private JButton depositButton;
    private GUI GUI_Frame;
    private Color baseColor;

    /**
     * Constructs a new DepositCurrency instance with the specified parameters.
     * 
     * @param GUI_Frame the main GUI frame
     */

    public DepositCurrency(GUI GUI_Frame) {
        this.GUI_Frame = GUI_Frame;
        this.baseColor = new Color(0xFFFDD0);
        this.setTitle("Deposity Currency");
        this.setBackground(baseColor);

        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(4, 2, 0, 0));
        contentPanel.setBackground(baseColor);
        contentPanel.setVisible(true);

        valueLabel = new JLabel("Value: ");
        valueLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        valueLabel.setBackground(baseColor);
        valueLabel.setOpaque(true);

        typeLabel = new JLabel("Type: ");
        typeLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        typeLabel.setBackground(baseColor);
        typeLabel.setOpaque(true);

        quantityLabel = new JLabel("Quantity: ");
        quantityLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        quantityLabel.setBackground(baseColor);
        quantityLabel.setOpaque(true);

        valueField = new JTextField();
        valueField.setFont(new Font("MV Boli", Font.PLAIN, 15));
        valueField.setBorder(new LineBorder(baseColor, 2));
        valueField.setBackground(baseColor);
        valueField.addFocusListener(this);

        typeField = new JTextField();
        typeField.setFont(new Font("MV Boli", Font.PLAIN, 15));
        typeField.setBorder(new LineBorder(baseColor, 2));
        typeField.setBackground(baseColor);
        typeField.addFocusListener(this);

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
        contentPanel.add(typeLabel);
        contentPanel.add(typeField);
        contentPanel.add(quantityLabel);
        contentPanel.add(quantityField);
        contentPanel.add(depositButton);

        this.add(contentPanel);
        this.pack();
        this.setVisible(true);
    }


    /**
     * Handles focus gained events for the text fields.
     * 
     * @param e the FocusEvent generated when a text field gains focus
     */

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == typeField) {
            typeField.setBorder(new LineBorder(Color.ORANGE, 2));
        }
        else if (e.getSource() == valueField) {
            valueField.setBorder(new LineBorder(Color.ORANGE, 2));
        }
        else if (e.getSource() == quantityField) {
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
        if (e.getSource() == typeField) {
            typeField.setBorder(new LineBorder(baseColor, 2));
        }
        else if (e.getSource() == valueField) {
            valueField.setBorder(new LineBorder(baseColor, 2));
        }
        else if (e.getSource() == quantityField) {
            quantityField.setBorder(new LineBorder(baseColor, 2));
        }
    }

    /**
    * Handles button clicks and deposits the specified denomination into the machine's reserve.
    * 
    * @param e the ActionEvent generated by the button click
    */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == depositButton) {
            double value = Double.parseDouble(valueField.getText());
            String type = typeField.getText();
            int quantity = Integer.parseInt(quantityField.getText());

            Denomination denom = new Denomination(value, type);
            SpecialVendingMachine reference = GUI_Frame.getSelected();

            reference.depositMachineReserve(denom, quantity);

            this.dispose();
            valueField.setText("");
            typeField.setText("");
            quantityField.setText("");
            GUI_Frame.validate();
        }
    }
    
}
