import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The MaintenancePanel class represents a panel that displays a maintenance option in a vending machine GUI.
 * It contains a label and a button, where the label shows the description of the maintenance option and the button triggers the maintenance action.
 * The class implements the ActionListener interface to handle button clicks.
 * This class is part of the vending machine system.
 * 
 * Note: This class assumes the existence of the GUI class.
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public class MaintenancePanel extends JPanel implements ActionListener{
    private JLabel label;
    private JButton button;
    private GUI GUI_Frame;
    private int functionIdentifier;
    
    /**
     * Constructs a new MaintenancePanel with the specified label, button text, GUI frame, and function identifier.
     * 
     * @param labelText the text to be displayed as the label on the panel
     * @param buttonText the text to be displayed on the button
     * @param GUI_Frame the main GUI frame
     * @param functionIdentifier the identifier of the maintenance function to be triggered when the button is clicked
     */

    public MaintenancePanel (String labelText, String buttonText, GUI GUI_Frame, int functionIdentifier) {
        this.GUI_Frame = GUI_Frame;
        this.functionIdentifier = functionIdentifier;
        label = new JLabel(labelText);
        label.setFont(new Font("MV Boli", Font.PLAIN, 15));

        button = new JButton(buttonText);
        button.setFocusable(false);
        button.setFont(new Font("MV Boli", Font.PLAIN, 10));
        button.setBackground(new Color(0xFFB347));
        button.addActionListener(this);

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.setBackground(new Color(0xFFFDD0));
        this.setOpaque(true);
        this.setVisible(true);
        this.add(label);
        this.add(button);
        this.setPreferredSize(new Dimension(150, 100));
    }
    /**
     * Invoked when the button is clicked, triggers the specified maintenance function.
     * @param e the action event generated when button is clicked
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            GUI_Frame.addMaintenanceFrame(functionIdentifier);
        }
    }
}
