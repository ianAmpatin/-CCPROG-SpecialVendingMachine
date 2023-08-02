import javax.swing.*;
import java.awt.*;

/**
 * The ProcessingMessage class represents a JFrame that displays a processing message during the purchase of an item or compound slot.
 * It provides a simple graphical user interface to show the item being purchased and relevant information.
 * The class extends JFrame and automatically disposes itself after a specific delay.
 * 
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public class ProcessingMessage extends JFrame{
    // Class variables for the header panel, message panel, closing panel, header label, text area, and scroll panel
     private JPanel headerPanel;
     private JPanel messagePanel;
     private JPanel closingPanel;
     private JLabel headerLabel;
     private JTextArea textArea;
     private JScrollPane scrollPanel;
     private Color baseColor = new Color(0xF6E8B1);

    /**
     * Constructs a new ProcessingMessage frame with default settings.
    */
     public ProcessingMessage () {
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
     * Constructs a new ProcessingMessage frame for displaying the purchase of a single regular item.
     * Shows the processing message with the item name, cost, and a thank you message.
     * 
     * @param item  the item being purchased
     */

     public ProcessingMessage(Item item) {
          this();
          String message = "No Item Selected";
      
          try {
              message = "Purchasing Item...\nSelected item is <" + item.getName() + ">\nCost of Item: Php " + item.getPrice() + "\nThank you! Have A good Day";
          } catch (NullPointerException e) {
              System.out.println(e);
              System.out.println("Pr 1");
          }
      
          textArea.setText(message);
      
          scrollPanel = new JScrollPane(textArea);
      
          headerPanel.add(headerLabel);
          messagePanel.add(scrollPanel, BorderLayout.CENTER);
      
          this.add(headerPanel, BorderLayout.NORTH);
          this.add(messagePanel, BorderLayout.CENTER);
          this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          this.setSize(new Dimension(400, 300));
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

    /**
     * Constructs a new ProcessingMessage frame for displaying the purchase of a compound slot.
     * Shows the processing message with the required items and quantities being added to the compound slot.
     * 
     * @param slot  the compound slot being purchased
     */
    
     public ProcessingMessage(CompoundSlot slot) {
          this();
          String message = "Purchasing item...\n";

          try {
               for (RequiredItem item : slot.getRequiredItems()) {
                    // message += "\nAdding  <" + item.getItem().getName() 
                    // + "\nQuantity: " + item.getRequiredQuantity()
                    // + ">\nCost : Php " + (item.getItem().getPrice() * item.getRequiredQuantity())
                    // + "\nThank you! Have A good Day";
                    message += "Adding (" + item.getRequiredQuantity() + ") of <" + item.getItem().getName() + "> to [" + slot.getItem().getName() + "]\n";
               }
          }
          catch (Exception e) {
               System.out.println(e);
               message = "No Item Selected";
          }

          textArea.setText(message);

          scrollPanel = new JScrollPane(textArea);

          headerPanel.add(headerLabel);
          messagePanel.add(scrollPanel, BorderLayout.CENTER);

          this.add(headerPanel, BorderLayout.NORTH);
          this.add(messagePanel, BorderLayout.CENTER);
          this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          this.setSize(new Dimension(600, 800));
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
