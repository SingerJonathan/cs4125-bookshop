package StaffUI;

import java.awt.Color;
import java.awt.Rectangle;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import UICommon.ThreadedCurrentTime;

public class ManageCustomerMenu extends JFrame implements ActionListener {

    private JButton jbtBack;
    private JButton jbtRemoveCustomer;
    private JButton jbtEditCustomer;
    private JButton jbtCreateAccount;
    private JPanel menu;
    private JLabel jlblTime;
    private JLabel jlblDate;
    private JPanel jpnlTime;
    private JLabel jlblDate2;
    private JPanel TimeDate;
    private String Date;
    private String userName;

    public ManageCustomerMenu(String userName) {
        this.userName = userName;
        this.setTitle("Manage Customer");
        this.setBounds(100, 100, 500, 300);
        this.setPreferredSize(new Dimension(500, 500));
        this.setLayout(new GridLayout(1, 1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TimeDate = new JPanel();
        TimeDate.setBounds(new Rectangle(50, 50));
        TimeDate.setSize(40, 40);
        TimeDate.setLayout(new GridLayout(1, 4));
        TimeDate.setBackground(new Color(59, 89, 182));
        TimeDate.setForeground(Color.WHITE);

        menu = new JPanel();
        menu.setBounds(new Rectangle(100, 100));
        menu.setLayout(new GridLayout(5, 1));

        jlblTime = new JLabel("Current Time: ", SwingConstants.CENTER);
        jlblTime.setSize(40, 40);
        jlblTime.setBackground(new Color(59, 89, 182));
        jlblTime.setForeground(Color.WHITE);

        jlblDate = new JLabel("Current Date: ", SwingConstants.CENTER);
        jlblDate.setSize(40, 40);
        jlblDate.setBackground(new Color(59, 89, 182));
        jlblDate.setForeground(Color.WHITE);

        Date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        jlblDate2 = new JLabel(Date, SwingConstants.CENTER);
        jlblDate2.setSize(40, 40);
        jlblDate2.setBackground(new Color(59, 89, 182));
        jlblDate2.setForeground(Color.WHITE);

        int sleepinterval = 1;
        ThreadedCurrentTime tc = new ThreadedCurrentTime(sleepinterval);
        tc.start();
        jpnlTime = tc.getPanelT();
        jpnlTime.setBackground(new Color(59, 89, 182));
        jpnlTime.setForeground(Color.WHITE);

        jbtBack = new JButton("Back");
        jbtBack.setPreferredSize(new Dimension(100, 100));
        jbtBack.addActionListener(this);
        jbtBack.setBackground(new Color(59, 89, 182));
        jbtBack.setForeground(Color.WHITE);
        jbtBack.setToolTipText("Click to go back to main menu");

        jbtRemoveCustomer = new JButton("Remove Customer");
        jbtRemoveCustomer.setPreferredSize(new Dimension(100, 100));
        jbtRemoveCustomer.addActionListener(this);
        jbtRemoveCustomer.setBackground(new Color(59, 89, 182));
        jbtRemoveCustomer.setForeground(Color.WHITE);
        jbtRemoveCustomer.setToolTipText("Click to open remove customer menu");

        jbtEditCustomer = new JButton("Edit Customer");
        jbtEditCustomer.setPreferredSize(new Dimension(100, 100));
        jbtEditCustomer.addActionListener(this);
        jbtEditCustomer.setBackground(new Color(59, 89, 182));
        jbtEditCustomer.setForeground(Color.WHITE);
        jbtEditCustomer.setToolTipText("Click to open edit customer menu");

        jbtCreateAccount = new JButton("Create Customer Account");
        jbtCreateAccount.setPreferredSize(new Dimension(100, 100));
        jbtCreateAccount.addActionListener(this);
        jbtCreateAccount.setBackground(new Color(59, 89, 182));
        jbtCreateAccount.setForeground(Color.WHITE);
        jbtCreateAccount.setToolTipText("Click to open create account menu");

        TimeDate.add(jlblTime);
        TimeDate.add(jpnlTime);
        TimeDate.add(jlblDate);
        TimeDate.add(jlblDate2);

        menu.add(TimeDate);
        menu.add(jbtRemoveCustomer);
        menu.add(jbtEditCustomer);
        menu.add(jbtCreateAccount);
        menu.add(jbtBack);

        jbtBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtBack.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtBack.setBackground(new Color(59, 89, 182));
            }
        }
        );

        jbtCreateAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtCreateAccount.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtCreateAccount.setBackground(new Color(59, 89, 182));
            }
        }
        );

        jbtRemoveCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtRemoveCustomer.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtRemoveCustomer.setBackground(new Color(59, 89, 182));
            }
        }
        );

        jbtEditCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtEditCustomer.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtEditCustomer.setBackground(new Color(59, 89, 182));
            }
        }
        );

        this.add(menu);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        JButton source = (JButton) ae.getSource();
        if (source.equals(jbtBack)) {
            StoreMainMenu menu = new StoreMainMenu(userName);
            this.setVisible(false);
        }

        if (source.equals(jbtRemoveCustomer)) {
            DeleteCustomer dscenu = new DeleteCustomer(userName);
            this.setVisible(false);
        }

        if (source.equals(jbtCreateAccount)) {
            CreateAccount cmenu = new CreateAccount(userName);
            this.setVisible(false);
        }

        if (source.equals(jbtEditCustomer)) {
            EditCustomer ecmenu = new EditCustomer(userName);
            this.setVisible(false);
        }
    }
}
