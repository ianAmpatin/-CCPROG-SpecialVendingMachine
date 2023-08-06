import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The SlotDataPanel class represents a panel that displays information about a slot in the vending machine.
 * It displays the name, type, quantity, calories, and price of the item in the slot.
 * The class also allows for mouse interactions to change the font size and background color of certain labels.
 * This class is part of the vending machine system GUI.
 * 
 * Note: This class assumes the existence of the GUI class, Slot class, and CompoundSlot class.
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public class SlotDataPanel extends JPanel implements ActionListener, MouseListener{
    private JLabel nameLabel;
    private JLabel typeLabel;
    private JLabel quantityLabel;
    private JLabel caloriesLabel;
    private JLabel priceLabel;
    private JButton close;
    private GUI frame;
    
    /**
     * Constructs a new SlotDataPanel with the specified information and dimensions.
     * 
     * @param frame     the GUI frame that contains this panel
     * @param name      the name of the item in the slot
     * @param type      the type of the item in the slot
     * @param quantity  the quantity of the item in the slot
     * @param calories  the calories of the item in the slot
     * @param price     the price of the item in the slot
     * @param width     the width of the panel
     * @param height    the height of the panel
     */
    
    public SlotDataPanel (GUI frame, String name, String type, int quantity, double calories, double price, int width, int height) {
        nameLabel = new JLabel();
        nameLabel.setText(name);
        nameLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        
        typeLabel = new JLabel();
        typeLabel.setText(type);
        typeLabel.setFont(new Font("MV Boli", Font.PLAIN, 10));
        typeLabel.setHorizontalAlignment(JLabel.CENTER);
        typeLabel.addMouseListener(this);
        
        quantityLabel = new JLabel();
        quantityLabel.setText("Quantity: " + Integer.toString(quantity));
        quantityLabel.setFont(new Font("MV Boli", Font.PLAIN, 10));
        quantityLabel.addMouseListener(this);
        
        caloriesLabel = new JLabel();
        caloriesLabel.setText("Calories: " + Double.toString(calories) + " cal");
        caloriesLabel.setFont(new Font("MV Boli", Font.PLAIN, 10));
        caloriesLabel.addMouseListener(this);
        
        priceLabel = new JLabel();
        priceLabel.setText("Price: Php" + Double.toString(price));
        priceLabel.setFont(new Font("MV Boli", Font.PLAIN, 10));
        priceLabel.addMouseListener(this);
        
        this.add(nameLabel);
        this.add(typeLabel);
        this.add(caloriesLabel);
        this.add(priceLabel);
        this.add(quantityLabel);
        this.setLayout(new GridLayout(1, 5, 0, 0));
        this.setBackground(new Color(0xF6E8B1));
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(width, height));
        this.setVisible(true);
        this.frame = frame;
        frame.add(this, BorderLayout.SOUTH);
        this.validate();
    }

    /**
     * Constructs a new SlotDataPanel with the information from a Slot object.
     * 
     * @param frame  the GUI frame that contains this panel
     * @param slot   the Slot object containing the item information
     */

    public SlotDataPanel (GUI frame, Slot slot) {
        this(frame, slot.getItem().getName(), slot.getItemType(), slot.getQuantity(), slot.getItem().getCalories(), slot.getItem().getPrice(), 200, 100);
    }

    /**
     * Constructs a new SlotDataPanel with the information from a CompoundSlot object.
     * 
     * @param frame  the GUI frame that contains this panel
     * @param slot   the CompoundSlot object containing the item information
     */

    public SlotDataPanel (GUI frame, CompoundSlot slot) {
        this (frame, slot.getItem().getName(), slot.getItemType(), slot.getQuantity(), slot.getItem().getCalories(), slot.getItem().getPrice(), 200, 100);
    }

    /**
     * Constructs a new empty SlotDataPanel.
     * This constructor is used when no slot information is available.
     * 
     * @param frame  the GUI frame that contains this panel
     */
    
    public SlotDataPanel(GUI frame) {
        this.setPreferredSize(new Dimension(100, 100));
        this.setBackground(Color.DARK_GRAY);
        this.setOpaque(true);
        this.setVisible(true);
        this.frame = frame;
        frame.add(this, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == close) {
            frame.remove(this);
            frame.revalidate();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == close) {
            close.setBackground(new Color(0xFF1818));
        }
        else if (e.getSource() == typeLabel) {
            typeLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        }
        else if (e.getSource() == caloriesLabel) {
            caloriesLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        }
        else if (e.getSource() == priceLabel) {
            priceLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        }
        else if (e.getSource() == quantityLabel) {
            quantityLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == close) {
            close.setBackground(new Color(0xFFB347));
        }
        else if (e.getSource() == typeLabel) {
            typeLabel.setFont(new Font("MV Boli", Font.PLAIN, 10));
        }
        else if (e.getSource() == caloriesLabel) {
            caloriesLabel.setFont(new Font("MV Boli", Font.PLAIN, 10));
        }
        else if (e.getSource() == priceLabel) {
            priceLabel.setFont(new Font("MV Boli", Font.PLAIN, 10));
        }
        else if (e.getSource() == quantityLabel) {
            quantityLabel.setFont(new Font("MV Boli", Font.PLAIN, 10));
        }
    }
}
