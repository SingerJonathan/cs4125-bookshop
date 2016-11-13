package CustomerUI;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import UICommon.ThreadedCurrentTime;

public class CustomerMainMenu extends JFrame implements ActionListener {

    private JButton jbtExit;
    private JButton jbtBrowseBooks;
    private JButton jbtReturnBook;
    private JButton jbtCancelOrder;
    private JLabel jlblTime;
    private JLabel jlblDate;
    private JPanel jpnlTime;
    private JLabel jlblDate2;
    private JPanel menu;
    private JPanel TimeDate;
    private String Date;
    private String userName;

    public CustomerMainMenu(String userName) {
        this.userName = userName;
        this.setTitle("Customer Menu");
        this.setBounds(100, 100, 500, 300);
        this.setPreferredSize(new Dimension(500, 500));
        this.setLayout(new GridLayout(1, 1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TimeDate = new JPanel();
        TimeDate.setBounds(new Rectangle(50, 50));
        TimeDate.setSize(40, 40);
        TimeDate.setLayout(new GridLayout(1, 4));
        TimeDate.setBackground(new Color(158, 36, 36));
        TimeDate.setForeground(Color.WHITE);

        menu = new JPanel();
        menu.setBounds(new Rectangle(100, 100));
        menu.setLayout(new GridLayout(5, 1));

        jlblTime = new JLabel("Current Time: ", SwingConstants.CENTER);
        jlblTime.setSize(40, 40);
        jlblTime.setBackground(new Color(158, 36, 36));
        jlblTime.setForeground(Color.WHITE);

        jlblDate = new JLabel("Current Date: ", SwingConstants.CENTER);
        jlblDate.setSize(40, 40);
        jlblDate.setBackground(new Color(158, 36, 36));
        jlblDate.setForeground(Color.WHITE);

        Date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        jlblDate2 = new JLabel(Date, SwingConstants.CENTER);
        jlblDate2.setSize(40, 40);
        jlblDate2.setBackground(new Color(158, 36, 36));
        jlblDate2.setForeground(Color.WHITE);

        int sleepinterval = 1;
        ThreadedCurrentTime tc = new ThreadedCurrentTime(sleepinterval);
        tc.start();
        jpnlTime = tc.getPanelT();
        jpnlTime.setBackground(new Color(158, 36, 36));
        jpnlTime.setForeground(Color.WHITE);

        jbtExit = new JButton("Exit");
        jbtExit.setPreferredSize(new Dimension(100, 100));
        jbtExit.addActionListener(this);
        jbtExit.setBackground(new Color(158, 36, 36));
        jbtExit.setForeground(Color.WHITE);
        jbtExit.setToolTipText("Click to Exit");

        jbtBrowseBooks = new JButton("Browse Books");
        jbtBrowseBooks.setPreferredSize(new Dimension(100, 100));
        jbtBrowseBooks.addActionListener(this);
        jbtBrowseBooks.setBackground(new Color(158, 36, 36));
        jbtBrowseBooks.setForeground(Color.WHITE);
        jbtBrowseBooks.setToolTipText("Click to open Browse book menu");

        jbtReturnBook = new JButton("Return Book");
        jbtReturnBook.setPreferredSize(new Dimension(100, 100));
        jbtReturnBook.addActionListener(this);
        jbtReturnBook.setBackground(new Color(158, 36, 36));
        jbtReturnBook.setForeground(Color.WHITE);
        jbtReturnBook.setToolTipText("Click to open return book menu");

        jbtCancelOrder = new JButton("Cancel Order");
        jbtCancelOrder.setPreferredSize(new Dimension(100, 100));
        jbtCancelOrder.addActionListener(this);
        jbtCancelOrder.setBackground(new Color(158, 36, 36));
        jbtCancelOrder.setForeground(Color.WHITE);
        jbtCancelOrder.setToolTipText("Click to open cancel order menu");

        TimeDate.add(jlblTime);
        TimeDate.add(jpnlTime);
        TimeDate.add(jlblDate);
        TimeDate.add(jlblDate2);

        menu.add(TimeDate);
        menu.add(jbtBrowseBooks);
        menu.add(jbtReturnBook);
        menu.add(jbtCancelOrder);
        menu.add(jbtExit);

        jbtExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtExit.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtExit.setBackground(new Color(158, 36, 36));
            }
        }
        );

        jbtBrowseBooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtBrowseBooks.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtBrowseBooks.setBackground(new Color(158, 36, 36));
            }
        }
        );

        jbtReturnBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtReturnBook.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtReturnBook.setBackground(new Color(158, 36, 36));
            }
        }
        );

        jbtCancelOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtCancelOrder.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtCancelOrder.setBackground(new Color(158, 36, 36));
            }
        }
        );

        this.setBackground(new Color(158, 36, 36));
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

        if (source.equals(jbtBrowseBooks)) {
            BrowseBook bbmenu = new BrowseBook(userName);
            this.setVisible(false);
        }

        if (source.equals(jbtReturnBook)) {
            ReturnBook rmenu = new ReturnBook(userName);
            this.setVisible(false);
        }

        if (source.equals(jbtCancelOrder)) {
            CancelOrder crmenu = new CancelOrder(userName);
            this.setVisible(false);
        }
    }
}
