import javax.swing.*;
import java.awt.*;

/**
 * The cancelTransaction class represent a window that will cancel the current transaction of a client
 * It will show how much denominations in total that the client will receive back for cancelling the transaction
 * 
 * @author Ampatin, Ian Kenneth J
 * @author Paredes, Bill Jethro P
 */

public class CancelTransaction extends JFrame{
     private JPanel headerPanel;
     private JPanel messagePanel;
     private JPanel closingPanel;
     private JLabel headerLabel;
     private JTextArea textArea;
     private JScrollPane scrollPanel;
     private Color baseColor = new Color(0xF6E8B1);

     /**
      * Constructs a new CancelTransaction object.
      * This constructoe will initialize the first half of the GUI component
      */
     public CancelTransaction () {
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
      * Constructs a new CancelTransaction object.
      * This constructor will initailize the second half of the gui components
      * @param client is the client that is making the transaction
      * @param GUI_Frame is the main interface of this object
      */

     public CancelTransaction (CashHolder client, GUI GUI_Frame) {
          this();
          String message = "Cancelling Transaction...";
      
          try {
               double beforeCancel = client.getTotal();

                if (client.getTotal() > 0)
                    GUI_Frame.getSelected().cancelTransaction(client);

               double afterCancel = client.getTotal();

               message = "Cancelling Transaction...\n" 
                         + "Php " + (afterCancel - beforeCancel) + " has been returned.";
          
          } catch (NullPointerException e) {
              System.out.println(e);
              message += "Cancellation Fail";
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
}
