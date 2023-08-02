import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The VendingMachineSlot class represents a panel that displays information about a specific vending machine.
 * It provides a "Select" button to view the slots and items inside the vending machine.
 * The class extends JPanel and implements ActionListener and MouseListener to handle user interactions.
 * 
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public class VendingMachineSlot extends JPanel implements ActionListener, MouseListener{
    private JButton selectButton;
    private JLabel machineLabel;
    private JPanel panelContainer;
    private GUI GUI_Frame;
    private SpecialVendingMachine machine;
    private CashHolder client;
    
    /**
    * Constructs a new VendingMachineSlot with default settings.
    */

    public VendingMachineSlot () {
        selectButton = new JButton("Select");
        selectButton.setBackground(new Color(0xFFB347));
        selectButton.setFont(new Font("MV Boli", Font.PLAIN, 10));
        selectButton.setPreferredSize(new Dimension(70, 20));
        selectButton.setFocusable(false);
        selectButton.addActionListener(this);

        this.setBackground(new Color(0x9DC183));
        this.setPreferredSize(new Dimension(100, 120));
        this.setOpaque(true);
        this.add(selectButton);
        this.setVisible(true);
        this.addMouseListener(this);
    }

    /**
     * Constructs a new VendingMachineSlot with the given vending machine, panel location, and GUI frame.
     * 
     * @param machine        the SpecialVendingMachine to be displayed
     * @param panelLocation  the panel where the vending machine information will be displayed
     * @param GUI_Frame      the main GUI frame that contains the vending machine panels
     * @param client is the client of the vending machine
     */
    public VendingMachineSlot (SpecialVendingMachine machine, JPanel panelLocation, GUI GUI_Frame, CashHolder client) {
        this();
        machineLabel = new JLabel(machine.getName());
        machineLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        this.add(machineLabel);
        this.panelContainer = panelLocation;
        this.GUI_Frame = GUI_Frame;
        this.GUI_Frame.addMouseListener(this);
        this.machine = machine;
        this.client = client;
    }

    /**
     * Handles the action when the "Select" button is clicked.
     * Updates the panel container to display the slots and compound slots of the selected vending machine.
     * 
     * @param e  the ActionEvent that triggered the action
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == selectButton) {
            panelContainer.removeAll();
            GUI_Frame.setSelected(machine);
            
            for (Slot slot : machine.getSlots())
                panelContainer.add(new SlotPanel(GUI_Frame, slot, machine, client));
            
            for (CompoundSlot slot : machine.getCompoundSlots()) 
                panelContainer.add(new CompoundSlotPanel(GUI_Frame, slot, machine, client));
            
            GUI_Frame.revalidate();
            GUI_Frame.repaint();
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
     * Handles the mouse enter event when the mouse pointer enters the panel.
     * Changes the background color of the panel to indicate selection.
     * 
     * @param e  the MouseEvent that triggered the event
     */
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == GUI_Frame) {
            if (GUI_Frame.getSelected().getName() == machine.getName()) {
                this.setBackground(new Color(0xDDC6AB));
            }
            else {
                this.setBackground(new Color(0x9DC183));
            }
            GUI_Frame.revalidate();
            GUI_Frame.repaint();

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
