import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The DisplaySales class represents a JFrame that displays the sales report of a selected vending machine.
 * It shows the sales and quantities of regular and compound items based on the provided TransactionRecord.
 * The class extends JFrame and provides a simple graphical user interface for the sales report.
 * 
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public class DisplaySales extends JFrame{

     // Class variables for the header panel, message panel, closing panel, header label, text area, and scroll panel
     private JPanel headerPanel;
     private JPanel messagePanel;
     private JPanel closingPanel;
     private JLabel headerLabel;
     private JTextArea textArea;
     private JScrollPane scrollPanel;
     private Color baseColor = new Color(0xF6E8B1);

     /**
     * Constructs a new DisplaySales frame with default settings.
     */
     public DisplaySales () {
          this.setLayout(new BorderLayout());

          headerPanel = new JPanel();
          headerPanel.setLayout(new FlowLayout());
          headerPanel.setBackground(new Color(0x123456));
          headerPanel.setVisible(true);

          closingPanel = new JPanel();
          closingPanel.setLayout(new FlowLayout());
          closingPanel.setBackground(new Color(0x123456));
          closingPanel.setVisible(true);

          messagePanel = new JPanel();
          messagePanel.setLayout(new BorderLayout());
          messagePanel.setBackground(baseColor);
          messagePanel.setVisible(true);

          headerLabel = new JLabel("Processing");
          headerLabel.setFont(new Font("MV Boli", Font.PLAIN, 30));
          headerLabel.setForeground(baseColor);
          headerLabel.setBackground(new Color(0x123456));
          headerLabel.setOpaque(true);

          textArea = new JTextArea();
          textArea.setLineWrap(true);
          textArea.setWrapStyleWord(true);
          textArea.setBackground(baseColor);
          textArea.setFont(new Font("MV Boli", Font.PLAIN, 15));
          textArea.setEditable(false);
     }

     /**
     * Constructs a new DisplaySales frame with the provided GUI frame.
     * Displays the sales report of the selected vending machine based on its TransactionRecord.
     * 
     * @param GUI_Frame  the main GUI frame containing the vending machine panels
     */
     public DisplaySales(GUI GUI_Frame) {
          this();
          TransactionRecord referenceRecord = GUI_Frame.getSelected().getTransactionRecord();
          ArrayList<Slot> referenceSlot = GUI_Frame.getSelected().getSlots();
          ArrayList<CompoundSlot> referenceCompSlot = GUI_Frame.getSelected().getCompoundSlots();
          
          String message;
      
               message = "Sales Report\n\n";

               for (Slot slot : referenceSlot)
                    if (slot.getItem() != null) {
                         double subTotal = referenceRecord.getTotal(slot.getItem().getName());
                         message += "Item: " + slot.getItem().getName() + " | Sold: " + subTotal + " | Quantity: " + (subTotal / slot.getItem().getPrice()) + "\n";
                    }

               for (CompoundSlot slot : referenceCompSlot)
                    if (slot.getItem() != null) {
                         double subTotal = referenceRecord.getTotal(slot.getItem().getName());
                         message += "Item: " + slot.getItem().getName() + " | Sold: " + subTotal + " | Quantity: " + (subTotal / slot.getItem().getPrice()) + "\n";
                    }
      
          textArea.setText(message);
      
          scrollPanel = new JScrollPane(textArea);
      
          headerPanel.add(headerLabel);
          messagePanel.add(scrollPanel, BorderLayout.CENTER);
      
          this.add(headerPanel, BorderLayout.NORTH);
          this.add(messagePanel, BorderLayout.CENTER);
          this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          this.setSize(new Dimension(500, 700));
          this.setVisible(true);
      
          Thread sleepThread = new Thread(() -> {
              try {
                  Thread.sleep(7000);
                  SwingUtilities.invokeLater(() -> {
                      this.dispose();
                  });
              } catch (InterruptedException e) {
                  System.out.println(e);
              }
          });
      
          sleepThread.start();
      }
}
