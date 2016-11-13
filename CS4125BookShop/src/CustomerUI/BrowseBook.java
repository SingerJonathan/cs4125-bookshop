package CustomerUI;

import DBInterface.DBHandler;
import Orders.Book;
import UICommon.ThreadedCurrentTime;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import CustomerControls.BuyBookControl;
import DBInterface.DBHandlerFactory;

public class BrowseBook extends JFrame implements ActionListener {

    private DBHandler db = DBHandlerFactory.getDBHandler("Customer");
    private int bookCount = db.countAllBooks();
    private JButton jbtBack;
    private JButton jbtBuyBook;
    private JPanel menu;
    private JPanel buttons;
    private JLabel jlblTime;
    private JLabel jlblDate;
    private JScrollPane listScroller;
    private JTextField HardBook;
    private JTextField Signature;
    private JPanel jpnlTime;
    private JLabel jlblDate2;
    private JLabel jlblH;
    private JLabel jlblS;
    private JPanel TimeDate;
    private String Date;
    private Vector<String> Books = new Vector<>();
    private JList list;
    private String choice;
    private String userName;

    private String pattern = "[0-1]";

    public BrowseBook(String userName) {
        this.userName = userName;
        this.setTitle("Browse Books");
        this.setBounds(100, 100, 500, 300);
        this.setPreferredSize(new Dimension(500, 500));
        this.setLayout(new GridLayout(1, 1));
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
        menu.setLayout(new GridLayout(3, 1));

        buttons = new JPanel();
        buttons.setBounds(new Rectangle(10, 10));
        buttons.setLayout(new GridLayout(3, 2));
        buttons.setBackground(new Color(158, 36, 36));
        buttons.setForeground(Color.WHITE);

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

        jbtBuyBook = new JButton("Buy Book");
        jbtBuyBook.setPreferredSize(new Dimension(100, 100));
        jbtBuyBook.addActionListener(this);
        jbtBuyBook.setBackground(new Color(158, 36, 36));
        jbtBuyBook.setForeground(Color.WHITE);
        jbtBuyBook.setToolTipText("Click to buy book selected from list");
        jbtBuyBook.setEnabled(false);

        for (int i = 1; i <= bookCount; i++) {
            Book book = db.getBook(i);
            if (!book.getName().matches("")) {
                Books.add(book.getName());
            }
        }
        Collections.sort(Books);

        String[] BooksArray = Books.toArray(new String[0]);
        list = new JList(BooksArray);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(Books.size());
        list.setBackground(new Color(158, 36, 36));
        list.setForeground(Color.WHITE);
        listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));

        HardBook = new JTextField(20);
        HardBook.setText("0");
        HardBook.setSize(40, 40);
        HardBook.setBackground(new Color(158, 36, 36));
        HardBook.setForeground(Color.WHITE);
        HardBook.setHorizontalAlignment(SwingConstants.CENTER);

        jlblH = new JLabel("Enter 1/0 for hardbook copy: ", SwingConstants.CENTER);
        jlblH.setSize(40, 40);
        jlblH.setBackground(new Color(158, 36, 36));
        jlblH.setForeground(Color.WHITE);

        Signature = new JTextField(20);
        Signature.setText("0");
        Signature.setSize(40, 40);
        Signature.setBackground(new Color(158, 36, 36));
        Signature.setForeground(Color.WHITE);
        Signature.setHorizontalAlignment(SwingConstants.CENTER);

        jlblS = new JLabel("Enter 1/0 for Signature copy: ", SwingConstants.CENTER);
        jlblS.setSize(40, 40);
        jlblS.setBackground(new Color(158, 36, 36));
        jlblS.setForeground(Color.WHITE);

        TimeDate.add(jlblTime);
        TimeDate.add(jpnlTime);
        TimeDate.add(jlblDate);
        TimeDate.add(jlblDate2);

        buttons.add(jlblH);
        buttons.add(HardBook);
        buttons.add(jlblS);
        buttons.add(Signature);
        buttons.add(jbtBuyBook);
        buttons.add(jbtBack);

        menu.add(TimeDate);
        menu.add(listScroller);
        menu.add(buttons);

        jbtBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtBack.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtBack.setBackground(new Color(158, 36, 36));
            }
        }
        );

        jbtBuyBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtBuyBook.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtBuyBook.setBackground(new Color(158, 36, 36));
            }
        }
        );

        HardBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HardBook.setForeground(Color.BLACK);
                HardBook.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                HardBook.setForeground(Color.WHITE);
                HardBook.setBackground(new Color(158, 36, 36));
            }
        }
        );

        Signature.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Signature.setForeground(Color.BLACK);
                Signature.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                Signature.setForeground(Color.WHITE);
                Signature.setBackground(new Color(158, 36, 36));
            }
        }
        );

        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    if (list.getSelectedIndex() == -1) {
                        //No selection.
                        jbtBuyBook.setEnabled(false);
                    } else {
                        //Selection.
                        jbtBuyBook.setEnabled(true);
                        choice = list.getSelectedValue().toString();
                    }
                }
            }
        });

        HardBook.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { //watch for key strokes
                if (HardBook.getText().length() == 0) {
                    jbtBuyBook.setEnabled(false);
                } else if (!(HardBook.getText().matches(pattern))) {
                    jbtBuyBook.setEnabled(false);
                } else {
                    jbtBuyBook.setEnabled(true);
                }
            }
        });

        Signature.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { //watch for key strokes
                if (Signature.getText().length() == 0) {
                    jbtBuyBook.setEnabled(false);
                } else if (!(HardBook.getText().matches(pattern))) {
                    jbtBuyBook.setEnabled(false);
                } else {
                    jbtBuyBook.setEnabled(true);
                }
            }
        });

        this.add(menu);
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

        if (source.equals(jbtBuyBook)) {
            BuyBookControl buyBookObject = new BuyBookControl();
            buyBookObject.buyBook(choice, userName, Integer.parseInt(HardBook.getText()), Integer.parseInt(Signature.getText()));
            CustomerMainMenu menu = new CustomerMainMenu(userName);
            this.setVisible(false);
        }

    }
}
