import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShowOwner extends JFrame{
     private JPanel headerPanel;
     private JPanel messagePanel;
     private JPanel closingPanel;
     private JLabel headerLabel;
     private JTextArea textArea;
     private JScrollPane scrollPanel;
     private Color baseColor = new Color(0xF6E8B1);

     public ShowOwner () {
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

     public ShowOwner (CashHolder owner, GUI GUI_Frame) {
          this();
          String message = "Showing owner denomination summary...";
      
          try {
               ArrayList<Denomination> uniqueReference = owner.getUniqueDenomination();

               for (Denomination unique : uniqueReference) {
                    message += "\nValue: Php" + unique.getValue() + " | Quantity: " + owner.countDenomination(unique.getValue());
               }

               
          } catch (NullPointerException e) {
              System.out.println(e);
              System.out.println("Action Failed");
              message = "No Denominations";
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
