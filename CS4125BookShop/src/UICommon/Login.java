package UICommon;

import CustomerUI.CustomerMainMenu;
import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import StaffUI.StoreMainMenu;
import WarehouseUI.WarehouseMainMenu;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {
    private DBHandler db = DBHandlerFactory.getDBHandler("Common");
    private JTextField textFieldName;
    private JLabel labelName;
    private JButton buttonLogin;
    
    public Login() {
        Color windowColor = new Color(158, 36, 36);
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(windowColor);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        labelName = new JLabel("Name:");
        labelName.setBackground(windowColor);
        labelName.setForeground(Color.WHITE);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(6, 6, 3, 3);
        this.add(labelName, c);
        
        textFieldName = new JTextField(15);
        textFieldName.setBackground(windowColor);
        textFieldName.setForeground(Color.WHITE);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 3;
        c.insets = new Insets(6, 3, 3, 6);
        this.add(textFieldName, c);
        
        buttonLogin = new JButton("Login");
        buttonLogin.setBackground(windowColor);
        buttonLogin.setForeground(Color.WHITE);
        buttonLogin.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 3;
        c.insets = new Insets(3, 3, 6, 50);
        this.add(buttonLogin, c);

        textFieldName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                textFieldName.setForeground(Color.BLACK);
                textFieldName.setBackground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                textFieldName.setForeground(Color.WHITE);
                textFieldName.setBackground(windowColor);
            }
        });

        buttonLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonLogin.setBackground(Color.BLACK);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonLogin.setBackground(windowColor);
            }
        });
        
        this.getRootPane().setDefaultButton(buttonLogin);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String userName = textFieldName.getText();
        
        if(source.equals(buttonLogin)) {
            switch (db.checkUser(userName)) {
                case 1:
                    this.setVisible(false);
                    CustomerMainMenu customerMenu = new CustomerMainMenu(userName);
                    break;
                case 2:
                    this.setVisible(false);
                    StoreMainMenu staffMenu = new StoreMainMenu(userName);
                    break;
                case 3:
                    this.setVisible(false);
                    WarehouseMainMenu warehouseMenu = new WarehouseMainMenu(userName);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid Login", "Login Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }
    
    public static void main(String [] args) {
        Login login = new Login();
    }
}
