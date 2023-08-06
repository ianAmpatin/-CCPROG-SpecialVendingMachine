import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The GUI class represents the main graphical user interface for the Vending Machine application.
 * It extends the JFrame class and implements the ActionListener and MouseListener interfaces
 * to handle user interactions with the interface elements.
 * The GUI displays the vending machine control panel, information panel, and slot display panel,
 * and allows users to create vending machines, perform maintenance tasks, accept money, and cancel transactions.
 * It also provides access to maintenance functionalities for managing items, machine reserve, and sales reports.
 * 
 * The GUI class is responsible for creating and managing the main JFrame window, various panels,
 * and buttons for user interactions. It interacts with other classes to handle specific tasks,
 * such as creating vending machines, depositing money, performing maintenance, and displaying sales reports.
 * The GUI class serves as the main entry point for users to interact with the Vending Machine application.
 * 
 * The class contains instance variables for different panels, buttons, and ArrayLists to store vending machines.
 * It also includes methods to switch between the slot display and maintenance panels, handle user actions,
 * and open separate windows for performing maintenance tasks.
 * 
 */

public class GUI extends JFrame implements ActionListener {
    // Create Vending Machine Variables
    private JFrame createVendingMachineFrame;
    private JPanel createVendingMachinePanel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel numSlotsLabel;
    private JTextField numSlotsField;
    private JLabel maxLabel;
    private JTextField maxField;

    // Default Variables
    private ImageIcon GUI_ICON;
    private JPanel createPanel;
    private JPanel machinePanel;
    private JPanel controlPanel;
    private JPanel information;
    private JPanel slots;
    private JButton createVendingMachine;
    private JButton maintenanceButton;
    private JButton acceptMoneyButton;
    private JButton cancelTransactionButton;
    private JPanel centralLayer;
    
    // Maintenance
    private JPanel maintenance;

    // ArrayLists
    private ArrayList<SpecialVendingMachine> machineList;
    private SpecialVendingMachine selectedMachine;

    // Other Variables
    private CashHolder client;
    private CashHolder owner;
    
    /**
     * Constructs a new instance of the GUI class.
     * Initializes the main JFrame window, various panels, and buttons for user interactions.
     * Sets up the main layout and configurations for the graphical user interface.
     * The constructor also initializes the ArrayList to store vending machines, the client's cash holder,
     * and sets the initial visibility and appearance of the interface components.
     * 
    */

    public GUI () {
        // Initialize Default Variables
        createPanel = new JPanel();
        machinePanel = new JPanel();
        controlPanel = new JPanel();
        information = new JPanel();
        slots = new JPanel();
        maintenance = new JPanel();
        createVendingMachine = new JButton("Create Machine");
        machineList = new ArrayList<SpecialVendingMachine>();
        maintenanceButton = new JButton("Maintenance");
        acceptMoneyButton = new JButton("Deposit");
        cancelTransactionButton = new JButton("Cancel");
        centralLayer = new JPanel(new CardLayout());

        // Initialize Create Vending Machine Variables
        createVendingMachineFrame = new JFrame("Create Vending Machine");
        createVendingMachineFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createVendingMachinePanel = new JPanel();
        createVendingMachinePanel.setLayout(new GridLayout(4, 2));
        
        nameLabel = new JLabel("Name: ");
        numSlotsLabel = new JLabel("Number of Slots: ");
        maxLabel = new JLabel("Maximum: ");

        nameField = new JTextField();
        numSlotsField = new JTextField();
        maxField = new JTextField();
        
        // Basic Frame Configurations
        GUI_ICON = new ImageIcon(""); // Enter File Path
        this.setTitle("Vending Machine");
        this.setIconImage(GUI_ICON.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1040, 700);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(0x123456));
        this.setLayout(new BorderLayout(0, 0));
        this.setVisible(true);
        
        /* --- Button Section ---  */
        createVendingMachine.setBackground(new Color(0x28A99E));
        createVendingMachine.setFont(new Font("MV Boli", Font.PLAIN, 10));
        createVendingMachine.setPreferredSize(new Dimension(150, 20));
        createVendingMachine.setFocusable(false);
        createVendingMachine.addActionListener(this);
        
        maintenanceButton.setBackground(new Color(0x28A99E));
        maintenanceButton.setFont(new Font("MV Boli", Font.PLAIN, 10));
        maintenanceButton.setPreferredSize(new Dimension(150, 20));
        maintenanceButton.setFocusable(false);
        maintenanceButton.addActionListener(this);

        acceptMoneyButton.setBackground(new Color(0x28A99E));
        acceptMoneyButton.setFont(new Font("MV Boli", Font.PLAIN, 10));
        acceptMoneyButton.setPreferredSize(new Dimension(150, 20));
        acceptMoneyButton.setFocusable(false);
        acceptMoneyButton.addActionListener(this);

        cancelTransactionButton.setBackground(new Color(0x28A99E));
        cancelTransactionButton.setFont(new Font("MV Boli", Font.PLAIN, 10));
        cancelTransactionButton.setPreferredSize(new Dimension(150, 20));
        cancelTransactionButton.setFocusable(false);
        cancelTransactionButton.addActionListener(this);

        /* --- Panel Section ---  */
        slots.setBackground(new Color(0x123456));
        slots.setVisible(true);
        maintenance.setBackground(new Color(0x192841));
        maintenance.setVisible(true);
        
        createPanel.setBackground(Color.BLACK);
        createPanel.setPreferredSize(new Dimension(30, 30));
        createPanel.add(createVendingMachine);
        createPanel.add(maintenanceButton);
        createPanel.add(acceptMoneyButton);
        createPanel.add(cancelTransactionButton);

        machinePanel.setBackground(Color.DARK_GRAY);
        
        controlPanel.setPreferredSize(new Dimension(100, 100));
        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(createPanel, BorderLayout.NORTH);
        controlPanel.add(machinePanel, BorderLayout.CENTER);

        information.setBackground(Color.BLACK);
        information.setPreferredSize(new Dimension(100, 100));


        /* --- Add Component Section --- */
        //maintenance.add(addItemPanel);
        maintenance.add(new MaintenancePanel("Regular Item", "Add Item", this, 1));
        maintenance.add(new MaintenancePanel("Machine Reserve", "Deposit", this, 2));
        maintenance.add(new MaintenancePanel("Insert Item", "Insert", this, 3));
        maintenance.add(new MaintenancePanel("Sales Report", "Sales", this, 6));
        maintenance.add(new MaintenancePanel("Compound Item", "Add Item", this, 4));
        maintenance.add(new MaintenancePanel("Required Item", "Add Item", this, 5));
        maintenance.add(new MaintenancePanel("Machine Reserve", "Collect", this, 7));
        maintenance.add(new MaintenancePanel("Owner Account", "Show Info", this, 8));

        centralLayer.add(slots, "Slots");
        centralLayer.add(maintenance, "Maintenance");
        this.add(centralLayer, BorderLayout.CENTER);
        //this.add(information, BorderLayout.SOUTH);
        this.add(controlPanel, BorderLayout.NORTH);

        /* --- Text Field Section ---  */
        nameField.setFont(new Font("MV Boli", Font.PLAIN, 15));
        numSlotsField.setFont(new Font("MV Boli", Font.PLAIN, 15));
        maxField.setFont(new Font("MV Boli", Font.PLAIN, 15));

        /* --- Label Section --- */
        nameLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        numSlotsLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        maxLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
    }
    /**
    * Constructs a new instance of the GUI class with the specified client and owner CashHolder objects.
    * This constructor is used when initializing the GUI with client and owner information.
    * It calls the default constructor to set up the main JFrame window, panels, and buttons.
    * The constructor also initializes the ArrayList to store vending machines.
    * The client's CashHolder object is set to keep track of the client's cash balance during transactions.
    */
    public GUI (CashHolder client, CashHolder owner) {
        this();
        this.client = client;
        this.owner = owner;

    }

    /**
     * Sets the selected vending machine to the specified SpecialVendingMachine object.
     * This method is used to update the selected vending machine in the GUI.
     * When a user clicks on a vending machine, this method is called to set the selectedMachine variable.
     * .
     * 
     * @param machine The SpecialVendingMachine object representing the selected vending machine.
     */

    public void setSelected(SpecialVendingMachine machine) {
        this.selectedMachine = machine;
    }

    /**
     * Retrieves the currently selected vending machine.
     * This method is used to get the selected vending machine instance in the GUI.
     * The selectedMachine variable holds the reference to the SpecialVendingMachine object representing the selected vending machine.
     * 
     * The method returns the selected vending machine, allowing other parts of the application to access and interact with it.
     * 
     * @return The SpecialVendingMachine object representing the currently selected vending machine.
     */

    public SpecialVendingMachine getSelected() {
        return selectedMachine;
    }

    /**
     * Retrieves the name of the currently visible panel in the specified container.
     * This method is used to determine which panel (Slots or Maintenance) is currently visible in the GUI.
     * It loops through the components in the container and checks if any component is visible.
     * The method is primarily used to toggle the visibility of the central layer panels in the GUI.
     * 
     * @param container The Container whose components are checked for visibility.
     * @return The name of the currently visible panel ('Slots' or 'Maintenance') or null if no panel is visible.
    */

    public String getVisiblePanel(Container container) {
        Component[] components = container.getComponents();
        for (Component component : components)
            if (component.isVisible())
                return container.getComponentZOrder(component) == 0 ? "Slots" : "Maintenance";
        
        return null;
    }

    /**
    * Adds a maintenance frame based on the specified function identifier.
    * This method is used to add a new maintenance frame to the GUI when the user clicks on a maintenance button.
    * The functionIdentifier determines which maintenance frame should be added.
    * @param functionIdentifier An integer representing the maintenance function identifier.
    */
    public void addMaintenanceFrame (int functionIdentifier) {

        switch (functionIdentifier) {
            case 1:
                new AddItem(this);
                break;
            case 2:
                new DepositCurrency(this);
                break;
            case 3: 
                new InsertAt(this);
                break;
            case 4:
                new AddCompound(this);
                break;
            case 5:
                new AddRequiredItems(this);
                break;
            case 6:
                new DisplaySales(this);
                break;
            case 7: 
                new Collect(owner, this);
                break;
            case 8:
                new ShowOwner(owner, this);
                break;
            default:
                break;
        }
    }

    /**
     * Invoked when an action occurs, such as a button click.
     * This method handles various actions triggered by the GUI's components.
     * It checks the source of the action and performs corresponding operations.
     * 
     * The method primarily handles actions related to buttons like 'Create Vending Machine',
     * 'Maintenance', 'Deposit', and 'Cancel Transaction'.
     * 
     * @param e The ActionEvent representing the user's action.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createVendingMachine) {
            new CreateVendingMachine(this, machineList, machinePanel, slots, client);
        }
        else if (e.getSource() == maintenanceButton) {
            
            CardLayout cardLayout = (CardLayout) centralLayer.getLayout();
            cardLayout.next(centralLayer);
            if (this.getVisiblePanel(centralLayer).equalsIgnoreCase("Slots"))
                maintenanceButton.setText("Maintenance");
            else    
                maintenanceButton.setText("Slots");
        }
        else if (e.getSource() == acceptMoneyButton) {
            new AcceptMoney(this);
        }
        else if (e.getSource() == cancelTransactionButton) {
            new CancelTransaction(client, this);
        }
    }
}