import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MaintenanceSecurity extends JFrame implements ActionListener{
    private JLabel codeLabel;
    private JTextField codeField;
    private JButton enterButton;
    private JPanel panel;
    private GUI GUI_Frame;

    public MaintenanceSecurity(GUI GUI_Frame) {
        this.GUI_Frame = GUI_Frame;
        this.setBackground(new Color(0x123456));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        codeLabel = new JLabel("Code: ");
        codeField = new JTextField();

        enterButton = new JButton("Enter");
        enterButton.setBackground(new Color(0xFFB346));
        enterButton.addActionListener(this);

        codeLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        codeLabel.setBackground(new Color(0xFFFDD0));
        codeLabel.setOpaque(true);

        codeField.setFont(new Font("MV Boli", Font.PLAIN, 15));
        codeField.setBackground(new Color(0xFFFDD0));

        panel.add(codeLabel);
        panel.add(codeField);
        panel.add(enterButton);

        this.add(panel);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterButton) {
            //String codeText = codeField.getText();

            try {
                //int code = Integer.parseInt(codeText);
                //GUI_Frame.setCode(code);
                this.dispose();
                codeField.setText("");
                GUI_Frame.validate();
                this.validate();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input! Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
