import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The SlotPanel class represents a panel that displays information about a slot in the vending machine.
 * It contains a "Select" button that allows users to select the slot and purchase the item in the slot.
 * The class also provides mouse interactions to enable or disable the "Select" button based on the slot's availability,
 * display item information in a SlotDataPanel when the "Select" button is hovered over, and handle user actions when the "Select" button is clicked.
 * This class is part of the vending machine system GUI.
 * 
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public class SlotPanel extends JPanel implements ActionListener, MouseListener{
    private JButton selectButton;
    private GUI GUI_Frame;
    private Slot itemSlot;
    private JLabel itemLabel;
    private SpecialVendingMachine vendingMachine;
    private CashHolder client;
    private double change;

    /**
     * Constructs a new SlotPanel to display slot information without a GUI frame or vending machine.
     * This constructor is used when the panel is not associated with any vending machine.
     * 
     * @param slot  the Slot object containing the slot information
     */

    public SlotPanel (Slot slot) {
        this.itemSlot = slot;
        selectButton = new JButton("Select");
        selectButton.setFocusable(false);
        selectButton.setFont(new Font("MV Boli", Font.PLAIN, 10));
        selectButton.setBackground(new Color(0xFFB347));
        selectButton.addActionListener(this);
        selectButton.addMouseListener(this);
        itemLabel = new JLabel("Unavailable");
        itemLabel.setFont(new Font("MV Boli", Font.PLAIN, 10));


        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.setBackground(new Color(0xFFFDD0));
        this.setOpaque(true);
        this.setVisible(true);
        this.add(itemLabel);
        this.add(selectButton);
        this.setPreferredSize(new Dimension(100, 100));
    }

    /**
     * Constructs a new SlotPanel with the specified GUI frame and slot information.
     * This constructor is used when the panel is associated with a GUI frame but not a vending machine.
     * 
     * @param GUI_Frame  the GUI frame that contains this panel
     * @param itemSlot   the Slot object containing the slot information
     */

    public SlotPanel (GUI GUI_Frame, Slot itemSlot) {
        this(itemSlot);
        this.GUI_Frame = GUI_Frame;
        this.GUI_Frame.addMouseListener(this);
    }

    /**
     * Constructs a new SlotPanel with the specified GUI frame, slot information, and vending machine.
     * 
     * @param GUI_Frame     the GUI frame that contains this panel
     * @param itemSlot      the Slot object containing the slot information
     * @param vendingMachine  the SpecialVendingMachine object associated with this panel
     * @param client is the client of the vending machine
     */

    public SlotPanel (GUI GUI_Frame, Slot itemSlot, SpecialVendingMachine machine, CashHolder client) {
        this(GUI_Frame, itemSlot);
        vendingMachine = machine;
        this.client = client;
    }
    

    /**
     * Checks if the GUI frame contains a specific panel class.
     * 
     * @param GUI_Frame    the GUI frame to check for the panel
     * @param panelClass   the specific panel class to look for
     * @return true if the GUI frame contains the specific panel class, false otherwise
     */
    
    public boolean hasSpecificPanel(GUI GUI_Frame, Class<? extends JPanel> panelClass) {
        Container contentPane = GUI_Frame.getContentPane();
        for (Component component : contentPane.getComponents()) {
            if (panelClass.isInstance(component)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method will set the value of the change variable
     * @param change is the change that the client received
     */

     public void setChange(double change) {
        this.change = change;
     }

     /**
      * This method will get the change
      * @return the change of the client
      */
     public double getChange () {
        return change;
     }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == selectButton) {
            GUI_Frame.setSelected(vendingMachine);
            
            if (this.hasSpecificPanel(GUI_Frame, SlotDataPanel.class))
                GUI_Frame.getContentPane().remove(GUI_Frame.getContentPane().getComponentCount() - 1);
            
            Item purchasedItem = vendingMachine.buyRegularItem(itemSlot.getSlotNum(), client, this);
            new ProcessingMessage(purchasedItem, this.getChange());
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
        if(e.getSource() == GUI_Frame) {
            if (itemSlot.getQuantity() > 0) {
                selectButton.setEnabled(true);
                itemLabel.setText(itemSlot.getItem().getName());
            }
            else {
                selectButton.setEnabled(false);
                itemLabel.setText("Unavailable");
            }
            GUI_Frame.revalidate();
            GUI_Frame.repaint();
        }
        else if (e.getSource() == selectButton) {
            if (itemSlot.getQuantity() > 0) {
                new SlotDataPanel(GUI_Frame, itemSlot);
                selectButton.setBackground(Color.GREEN);
            }
            else {
                selectButton.setBackground(Color.RED);
            }
            
            GUI_Frame.revalidate();
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == selectButton) {
            selectButton.setBackground(new Color(0xFFB347));
            if (this.hasSpecificPanel(GUI_Frame, SlotDataPanel.class)) {
                GUI_Frame.getContentPane().remove(GUI_Frame.getContentPane().getComponentCount() - 1);
            }

            GUI_Frame.revalidate();
        }
    }
}
