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
 * The CompoundSlotPanel class represents a panel that displays information about a compound slot in a vending machine GUI.
 * It provides buttons to select the compound slot and view its data. The panel also changes its appearance based on availability.
 * The class implements ActionListener and MouseListener interfaces to handle button clicks and mouse events.
 * This class is part of the vending machine system.
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public class CompoundSlotPanel extends JPanel implements ActionListener, MouseListener{
    //Declaration of Variables
    private JButton selectButton;
    private GUI GUI_Frame;
    private CompoundSlot itemSlot;
    private JLabel itemLabel;
    private SpecialVendingMachine vendingMachine;
    private CashHolder client;
    
    /**
     * Constructs a new CompoundSlotPanel with the specified compound slot
     * @param slot the compound slot to be displayed
     */
    public CompoundSlotPanel (CompoundSlot slot) {
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
     * Constructs a new CompoundSlotPanel with the specified compound slot and GUI frame.
     * @param GUI_Frame the main GUI frame
     * @param slot the compound slot to be displayed
     */

    public CompoundSlotPanel (GUI GUI_Frame, CompoundSlot itemSlot) {
        this(itemSlot);
        this.GUI_Frame = GUI_Frame;
        this.GUI_Frame.addMouseListener(this);
        this.itemSlot = itemSlot;
    }

    /**
     * Constructs a new CompoundSlotPanel with the specified compound slot, GUI frame, and vending machine.
     * @param GUI_Frame the main GUI frame
     * @param slot the compound slot to be displayed
     * @param machine the vending machine to which the compound slot belongs
     * @param client is the cash holder of the client
     */

    public CompoundSlotPanel (GUI GUI_Frame, CompoundSlot itemSlot, SpecialVendingMachine machine, CashHolder client) {
        this(GUI_Frame, itemSlot);
        vendingMachine = machine;
        this.client = client;
    }

    /**
     * Checks if the specified GUI frame contains a panel of the specified class.
     * @param GUI_Frame the main GUI frame
     * @param panelClass the class of the panel to be checked
     * @return true if the panel is found in the GUI frame, false otherwise
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
     * Handles the action event triggered by clicking the "Select" button.
     * The method retrieves the selected vending machine, updates the GUI frame's selected vending
     * machine, and performs the purchase operation. If a SlotDataPanel is already present in the
     * GUI frame, it removes the panel. The method then revalidates the GUI frame to update the
     * display.
     * 
     * @param e the action event triggered by clicking the "Select" button
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == selectButton) {
            GUI_Frame.setSelected(vendingMachine);
            if (this.hasSpecificPanel(GUI_Frame, SlotDataPanel.class))
                GUI_Frame.getContentPane().remove(GUI_Frame.getContentPane().getComponentCount() - 1);
            
            //vendingMachine.buyRegularItem(itemSlot.getSlotNum(), client_2);
            CompoundSlot slot = vendingMachine.buyCompoundItem(itemSlot.getSlotNum(), client);
            new ProcessingMessage(slot);
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

    /**
     * Handles the mouse entered event for the panel and the "Select" button.
     * When the mouse enters the panel, the method checks the compound slot's quantity. If the
     * quantity is greater than 0, the "Select" button is enabled, and the item label is set to the
     * compound slot's item name. Otherwise, the "Select" button is disabled, and the item label
     * displays "Compound Unavailable". The method then revalidates the GUI frame to update the
     * display.
     * @param e the mouse event for the select button and the paenl
     * */

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == GUI_Frame) {

            if (itemSlot.getQuantity() > 0) {
                selectButton.setEnabled(true);
                itemLabel.setText(itemSlot.getItem().getName());
                itemSlot.setQuantity(GUI_Frame.getSelected().computeCompoundQuantity(itemSlot));
            }
            else {
                selectButton.setEnabled(false);
                itemLabel.setText("Compound Unavailable");
            }
            GUI_Frame.revalidate();
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

    /**
     * Handles the mouse exited event for the "Select" button.
     * When the mouse exits the "Select" button, the button's background color is changed back to
     * its original color. If a SlotDataPanel is already present in the GUI frame, the panel is
     * removed. The method then revalidates the GUI frame to update the display.
     * 
     * @param e the mouse event for the "Select" button
     */
    
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
