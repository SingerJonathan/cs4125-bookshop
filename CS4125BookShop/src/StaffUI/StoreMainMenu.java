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

public class StoreMainMenu extends JFrame implements ActionListener {

    private JButton jbtExit;
    private JButton jbtManageBooks;
    private JButton jbtManageStock;
    private JButton jbtManageStaff;
    private JButton jbtManageCustomer;
    private JLabel jlblTime;
    private JLabel jlblDate;
    private JPanel jpnlTime;
    private JLabel jlblDate2;
    private JPanel menu;
    private JPanel TimeDate;
    private String Date;
    private String userName;

    public StoreMainMenu(String userName) {
        this.userName = userName;
        this.setTitle("Store Menu");
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
        menu.setLayout(new GridLayout(6, 1));

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

        jbtExit = new JButton("Exit");
        jbtExit.setPreferredSize(new Dimension(100, 100));
        jbtExit.addActionListener(this);
        jbtExit.setBackground(new Color(59, 89, 182));
        jbtExit.setForeground(Color.WHITE);
        jbtExit.setToolTipText("Click to Exit");

        jbtManageBooks = new JButton("Manage Books");
        jbtManageBooks.setPreferredSize(new Dimension(100, 100));
        jbtManageBooks.addActionListener(this);
        jbtManageBooks.setBackground(new Color(59, 89, 182));
        jbtManageBooks.setForeground(Color.WHITE);
        jbtManageBooks.setToolTipText("Click to open manage book menu");

        jbtManageStock = new JButton("Manage Stock");
        jbtManageStock.setPreferredSize(new Dimension(100, 100));
        jbtManageStock.addActionListener(this);
        jbtManageStock.setBackground(new Color(59, 89, 182));
        jbtManageStock.setForeground(Color.WHITE);
        jbtManageStock.setToolTipText("Click to open manage stock menu");

        jbtManageStaff = new JButton("Manage Staff");
        jbtManageStaff.setPreferredSize(new Dimension(100, 100));
        jbtManageStaff.addActionListener(this);
        jbtManageStaff.setBackground(new Color(59, 89, 182));
        jbtManageStaff.setForeground(Color.WHITE);
        jbtManageStaff.setToolTipText("Click to open manage staff menu");

        jbtManageCustomer = new JButton("Manage Customers");
        jbtManageCustomer.setPreferredSize(new Dimension(100, 100));
        jbtManageCustomer.addActionListener(this);
        jbtManageCustomer.setBackground(new Color(59, 89, 182));
        jbtManageCustomer.setForeground(Color.WHITE);
        jbtManageCustomer.setToolTipText("Click to open manage customer menu");

        TimeDate.add(jlblTime);
        TimeDate.add(jpnlTime);
        TimeDate.add(jlblDate);
        TimeDate.add(jlblDate2);

        menu.add(TimeDate);
        menu.add(jbtManageBooks);
        menu.add(jbtManageStock);
        menu.add(jbtManageStaff);
        menu.add(jbtManageCustomer);
        menu.add(jbtExit);

        jbtExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtExit.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtExit.setBackground(new Color(59, 89, 182));
            }
        }
        );

        jbtManageBooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtManageBooks.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtManageBooks.setBackground(new Color(59, 89, 182));
            }
        }
        );

        jbtManageStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtManageStock.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtManageStock.setBackground(new Color(59, 89, 182));
            }
        }
        );

        jbtManageStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtManageStaff.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtManageStaff.setBackground(new Color(59, 89, 182));
            }
        }
        );

        jbtManageCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtManageCustomer.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtManageCustomer.setBackground(new Color(59, 89, 182));
            }
        }
        );

        this.setBackground(new Color(59, 89, 182));
        this.setForeground(Color.WHITE);
        this.add(menu);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        JButton source = (JButton) ae.getSource();
        if (source.equals(jbtExit)) {
            System.exit(0);
        }

        if (source.equals(jbtManageBooks)) {
            BookMenu bmenu = new BookMenu(userName);
            this.setVisible(false);
        }

        if (source.equals(jbtManageStock)) {
            StockMenu smenu = new StockMenu(userName);
            this.setVisible(false);
        }

        if (source.equals(jbtManageStaff)) {
            StaffMenu pmenu = new StaffMenu(userName);
            this.setVisible(false);
        }
        if (source.equals(jbtManageCustomer)) {
            ManageCustomerMenu mcmenu = new ManageCustomerMenu(userName);
            this.setVisible(false);
        }
    }
}
