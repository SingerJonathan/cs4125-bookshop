package StaffUI;

import Customers.Customer;
import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import StaffControls.EditCustomerControl;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import UICommon.ThreadedCurrentTime;

public class EditCustomer extends JFrame implements ActionListener {

    private DBHandler db = DBHandlerFactory.getDBHandler("Staff");
    private int customerCount = db.countAllCustomers();
    private JButton jbtBack;
    private JButton jbtEditCustomer;
    private JPanel menu;
    private JPanel buttons;
    private JPanel Persondetails;
    private JLabel jlblTime;
    private JLabel jlblDate;
    private JPanel jpnlTime;
    private JLabel jlblDate2;
    private JPanel TimeDate;
    private String Date;
    private JLabel NName;
    private JLabel EName;
    private JLabel MName;
    private JTextField N;
    private JTextField E;
    private JTextField M;

    private JScrollPane listScroller;
    private Vector<String> CustomerList = new Vector<>();
    private JList list;
    private String choice;
    private String userName;
    private String pattern = "[0-9]+";

    public EditCustomer(String userName) {
        this.userName = userName;
        this.setTitle("Edit Customer Details");
        this.setBounds(100, 100, 500, 300);
        this.setPreferredSize(new Dimension(600, 600));
        this.setLayout(new GridLayout(2, 1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(new Color(59, 89, 182));

        TimeDate = new JPanel();
        TimeDate.setBounds(new Rectangle(500, 500));
        TimeDate.setSize(100, 100);
        TimeDate.setLayout(new GridLayout(1, 4));
        TimeDate.setBackground(new Color(59, 89, 182));
        TimeDate.setForeground(Color.WHITE);

        menu = new JPanel();
        menu.setBounds(new Rectangle(100, 100));
        menu.setLayout(new GridLayout(3, 1));

        buttons = new JPanel();
        buttons.setBounds(new Rectangle(10, 10));
        buttons.setLayout(new GridLayout(1, 2));

        Persondetails = new JPanel();
        Persondetails.setBounds(new Rectangle(10, 10));
        Persondetails.setLayout(new GridLayout(3, 2));
        Persondetails.setBackground(new Color(59, 89, 182));
        Persondetails.setForeground(Color.WHITE);

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

        jbtEditCustomer = new JButton("Save Changes");
        jbtEditCustomer.setPreferredSize(new Dimension(100, 100));
        jbtEditCustomer.addActionListener(this);
        jbtEditCustomer.setBackground(new Color(59, 89, 182));
        jbtEditCustomer.setForeground(Color.WHITE);
        jbtEditCustomer.setToolTipText("Click to edit customers in the database");
        jbtEditCustomer.setEnabled(false);

        NName = new JLabel("Enter Name: ", SwingConstants.CENTER);
        NName.setSize(40, 40);
        NName.setBackground(new Color(59, 89, 182));
        NName.setForeground(Color.WHITE);

        EName = new JLabel("Enter Email: ", SwingConstants.CENTER);
        EName.setSize(40, 40);
        EName.setBackground(new Color(59, 89, 182));
        EName.setForeground(Color.WHITE);

        MName = new JLabel("Enter Membership: ", SwingConstants.CENTER);
        MName.setSize(40, 40);
        MName.setBackground(new Color(59, 89, 182));
        MName.setForeground(Color.WHITE);

        N = new JTextField(20);
        N.setSize(40, 40);
        N.setBackground(new Color(59, 89, 182));
        N.setForeground(Color.WHITE);
        N.setHorizontalAlignment(SwingConstants.CENTER);

        E = new JTextField(20);
        E.setSize(40, 40);
        E.setBackground(new Color(59, 89, 182));
        E.setForeground(Color.WHITE);
        E.setHorizontalAlignment(SwingConstants.CENTER);

        M = new JTextField(50);
        M.setSize(40, 40);
        M.setBackground(new Color(59, 89, 182));
        M.setForeground(Color.WHITE);
        M.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 1; i <= customerCount; i++) {
            Customer customer = db.getCustomer(i);
            if (!customer.getName().matches("")) {
                CustomerList.add(customer.getName());
            }
        }
        Collections.sort(CustomerList);

        list = new JList(CustomerList);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(CustomerList.size());
        list.setBackground(new Color(59, 89, 182));
        list.setForeground(Color.WHITE);
        listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));

        TimeDate.add(jlblTime);
        TimeDate.add(jpnlTime);
        TimeDate.add(jlblDate);
        TimeDate.add(jlblDate2);

        buttons.add(jbtEditCustomer);
        buttons.add(jbtBack);

        Persondetails.add(NName);
        Persondetails.add(N);
        Persondetails.add(EName);
        Persondetails.add(E);
        Persondetails.add(MName);
        Persondetails.add(M);

        menu.add(TimeDate);
        menu.add(buttons);
        menu.add(listScroller);

        this.add(menu);
        this.add(Persondetails);

        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    if (list.getSelectedIndex() == -1/* && choice ==-1*/) {
                        //No selection.
                        jbtEditCustomer.setEnabled(false);
                    } else {
                        //Selection.
                        jbtEditCustomer.setEnabled(true);
                        choice = list.getSelectedValue().toString();
                        Customer customer = db.getCustomer(choice);
                        int membership = customer.getMemship();
                        String memString = "";
                        switch (membership) {
                            case 1:
                                memString = "bronze";
                                break;
                            case 2:
                                memString = "silver";
                                break;
                            case 3:
                                memString = "gold";
                                break;
                            default:
                                memString = "0";
                                break;
                        }
                        N.setText(customer.getName());
                        E.setText(customer.getEmail());
                        M.setText(memString);
                    }
                }
            }
        });

        N.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                N.setForeground(Color.BLACK);
                N.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                N.setForeground(Color.WHITE);
                N.setBackground(new Color(59, 89, 182));
            }
        }
        );

        M.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                M.setForeground(Color.BLACK);
                M.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                M.setForeground(Color.WHITE);
                M.setBackground(new Color(59, 89, 182));
            }
        }
        );

        E.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                E.setForeground(Color.BLACK);
                E.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                E.setForeground(Color.WHITE);
                E.setBackground(new Color(59, 89, 182));
            }
        }
        );

        jbtBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtBack.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtBack.setBackground(new Color(59, 89, 182));
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

        N.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { //watch for key strokes
                if (N.getText().length() == 0) {
                    jbtEditCustomer.setEnabled(false);
                } else {
                    jbtEditCustomer.setEnabled(true);
                }
            }
        });

        M.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { //watch for key strokes
                if (M.getText().length() == 0) {
                    jbtEditCustomer.setEnabled(false);
                } else {
                    jbtEditCustomer.setEnabled(true);
                }
            }
        });

        E.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { //watch for key strokes
                if (E.getText().length() == 0) {
                    jbtEditCustomer.setEnabled(false);
                } else {
                    jbtEditCustomer.setEnabled(true);
                }
            }
        });

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        JButton source = (JButton) ae.getSource();
        if (source.equals(jbtBack)) {
            ManageCustomerMenu mcmenu = new ManageCustomerMenu(userName);
            this.setVisible(false);
        }

        if (source.equals(jbtEditCustomer)) {
            int membership = 0;
            String name = N.getText();
            String email = E.getText();
            String memString = M.getText();

            switch (memString.toLowerCase()) {
                case "bronze":
                    membership = 1;
                    break;
                case "silver":
                    membership = 2;
                    break;
                case "gold":
                    membership = 3;
                    break;
            }

            EditCustomerControl editCustomerObject = new EditCustomerControl();
            editCustomerObject.editCustomer(db.getCustomer(choice).getID(), name, email, membership);
            ManageCustomerMenu mcmenu = new ManageCustomerMenu(userName);
            this.setVisible(false);
        }
    }
}
