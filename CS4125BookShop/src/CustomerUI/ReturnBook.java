package CustomerUI;

import CustomerControls.ReturnBookControl;
import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import Orders.Order;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import UICommon.ThreadedCurrentTime;

public class ReturnBook extends JFrame implements ActionListener {

    private DBHandler db = DBHandlerFactory.getDBHandler("Customer");
    private JButton jbtBack;
    private JButton jbtReturnBook;
    private JPanel menu;
    private JPanel buttons;
    private JPanel bookdetails;
    private JLabel jlblTime;
    private JLabel jlblDate;
    private JPanel jpnlTime;
    private JLabel jlblDate2;
    private JPanel TimeDate;
    private String Date;
    private JLabel Bname;
    private JLabel Rreason;
    private JLabel Name;
    private JTextField B;
    private JTextField R;
    private JTextField N;
    private String userName;

    public ReturnBook(String userName) {
        this.userName = userName;
        this.setTitle("Return Book");
        this.setBounds(100, 100, 500, 300);
        this.setPreferredSize(new Dimension(500, 500));
        this.setLayout(new GridLayout(2, 1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(new Color(158, 36, 36));

        TimeDate = new JPanel();
        TimeDate.setBounds(new Rectangle(500, 500));
        TimeDate.setSize(100, 100);
        TimeDate.setLayout(new GridLayout(1, 4));
        TimeDate.setBackground(new Color(158, 36, 36));
        TimeDate.setForeground(Color.WHITE);

        menu = new JPanel();
        menu.setBounds(new Rectangle(100, 100));
        menu.setLayout(new GridLayout(2, 1));

        buttons = new JPanel();
        buttons.setBounds(new Rectangle(10, 10));
        buttons.setLayout(new GridLayout(1, 2));

        bookdetails = new JPanel();
        bookdetails.setBounds(new Rectangle(10, 10));
        bookdetails.setLayout(new GridLayout(3, 3));
        bookdetails.setBackground(new Color(158, 36, 36));
        bookdetails.setForeground(Color.WHITE);

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

        jbtBack = new JButton("Back");
        jbtBack.setPreferredSize(new Dimension(100, 100));
        jbtBack.addActionListener(this);
        jbtBack.setBackground(new Color(158, 36, 36));
        jbtBack.setForeground(Color.WHITE);
        jbtBack.setToolTipText("Click to go back to main menu");

        jbtReturnBook = new JButton("Return Book");
        jbtReturnBook.setPreferredSize(new Dimension(100, 100));
        jbtReturnBook.addActionListener(this);
        jbtReturnBook.setBackground(new Color(158, 36, 36));
        jbtReturnBook.setForeground(Color.WHITE);
        jbtReturnBook.setToolTipText("Click to create a new account");
        jbtReturnBook.setEnabled(false);

        Bname = new JLabel("Enter Name of Book: ", SwingConstants.CENTER);
        Bname.setSize(40, 40);
        Bname.setBackground(new Color(158, 36, 36));
        Bname.setForeground(Color.WHITE);

        Rreason = new JLabel("Enter Reason for Return: ", SwingConstants.CENTER);
        Rreason.setSize(40, 40);
        Rreason.setBackground(new Color(158, 36, 36));
        Rreason.setForeground(Color.WHITE);

        Name = new JLabel("Enter Your Name: ", SwingConstants.CENTER);
        Name.setSize(40, 40);
        Name.setBackground(new Color(158, 36, 36));
        Name.setForeground(Color.WHITE);

        B = new JTextField(20);
        B.setSize(40, 40);
        B.setBackground(new Color(158, 36, 36));
        B.setForeground(Color.WHITE);
        B.setHorizontalAlignment(SwingConstants.CENTER);

        R = new JTextField(20);
        R.setSize(40, 40);
        R.setBackground(new Color(158, 36, 36));
        R.setForeground(Color.WHITE);
        R.setHorizontalAlignment(SwingConstants.CENTER);

        N = new JTextField(50);
        N.setSize(40, 40);
        N.setBackground(new Color(158, 36, 36));
        N.setForeground(Color.WHITE);
        N.setHorizontalAlignment(SwingConstants.CENTER);

        TimeDate.add(jlblTime);
        TimeDate.add(jpnlTime);
        TimeDate.add(jlblDate);
        TimeDate.add(jlblDate2);

        buttons.add(jbtReturnBook);
        buttons.add(jbtBack);

        bookdetails.add(Bname);
        bookdetails.add(B);
        bookdetails.add(Rreason);
        bookdetails.add(R);
        bookdetails.add(Name);
        bookdetails.add(N);

        menu.add(TimeDate);
        menu.add(buttons);

        this.add(menu);
        this.add(bookdetails);

        B.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                B.setForeground(Color.BLACK);
                B.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                B.setForeground(Color.WHITE);
                B.setBackground(new Color(158, 36, 36));
            }
        }
        );

        R.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                R.setForeground(Color.BLACK);
                R.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                R.setForeground(Color.WHITE);
                R.setBackground(new Color(158, 36, 36));
            }
        }
        );

        N.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                N.setForeground(Color.BLACK);
                N.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                N.setForeground(Color.WHITE);
                N.setBackground(new Color(158, 36, 36));
            }
        }
        );

        jbtBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtBack.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtBack.setBackground(new Color(158, 36, 36));
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

        B.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { //watch for key strokes
                if (B.getText().length() == 0) {
                    jbtReturnBook.setEnabled(false);
                } else {
                    jbtReturnBook.setEnabled(true);
                }
            }
        });

        R.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { //watch for key strokes
                if (R.getText().length() == 0) {
                    jbtReturnBook.setEnabled(false);
                } else {
                    jbtReturnBook.setEnabled(true);
                }
            }
        });

        N.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { //watch for key strokes
                if (N.getText().length() == 0) {
                    jbtReturnBook.setEnabled(false);
                } else {
                    jbtReturnBook.setEnabled(true);
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
            CustomerMainMenu menu = new CustomerMainMenu(userName);
            this.setVisible(false);
        }

        if (source.equals(jbtReturnBook)) {
            if (!N.getText().matches(userName)) {
                JOptionPane.showMessageDialog(null, "Your name must match your login.", "Invalid Name", JOptionPane.ERROR_MESSAGE);
            } else {
                Order order = db.getOrder(B.getText(), N.getText(), 1, 0, 0);
                if (order.getID() == 0) {
                    JOptionPane.showMessageDialog(null, "The order either doesn't exist, hasn't been processed yet, or has been cancelled or returned.",
                            "Order Not Found", JOptionPane.ERROR_MESSAGE);
                } else {
                    ReturnBookControl returnBookObject = new ReturnBookControl();
                    returnBookObject.returnBook(B.getText(), N.getText(), R.getText());
                    CustomerMainMenu menu = new CustomerMainMenu(userName);
                    this.setVisible(false);
                }
            }
        }
    }
}
