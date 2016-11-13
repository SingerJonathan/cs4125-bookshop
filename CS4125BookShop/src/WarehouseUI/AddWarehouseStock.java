package WarehouseUI;

import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import WarehouseControls.AddWarehouseStockControl;
import Orders.Book;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import UICommon.ThreadedCurrentTime;

public class AddWarehouseStock extends JFrame implements ActionListener {

    private DBHandler db = DBHandlerFactory.getDBHandler("Warehouse");
    private int bookCount = db.countAllBooks();
    private JButton jbtBack;
    private JButton jbtAddStock;
    private JPanel menu;
    private JPanel Top;
    private JPanel buttons;
    private JLabel jlblTime;
    private JLabel jlblDate;
    private JLabel jlblBook;
    private JScrollPane listScroller;
    private JPanel jpnlTime;
    private JLabel jlblDate2;
    private JPanel TimeDate;
    private JTextField M;
    private String Date;
    private Vector<String> Books = new Vector<>();
    private JList list;
    private String choice;
    private String userName;

    private String pattern = "[0-9]+";

    public AddWarehouseStock(String userName) {
        this.userName = userName;
        this.setTitle("Add Stock to Warehouse");
        this.setBounds(100, 100, 500, 300);
        this.setPreferredSize(new Dimension(500, 500));
        this.setLayout(new GridLayout(1, 1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(new Color(35, 132, 30));

        TimeDate = new JPanel();
        TimeDate.setBounds(new Rectangle(500, 500));
        TimeDate.setSize(100, 100);
        TimeDate.setLayout(new GridLayout(1, 4));
        TimeDate.setBackground(new Color(35, 132, 30));
        TimeDate.setForeground(Color.WHITE);

        menu = new JPanel();
        menu.setBounds(new Rectangle(100, 100));
        menu.setLayout(new GridLayout(3, 1));

        Top = new JPanel();
        Top.setBounds(new Rectangle(100, 100));
        Top.setLayout(new GridLayout(2, 1));
        Top.setBackground(new Color(35, 132, 30));
        Top.setForeground(Color.WHITE);

        buttons = new JPanel();
        buttons.setBounds(new Rectangle(10, 10));
        buttons.setLayout(new GridLayout(1, 3));

        jlblTime = new JLabel("Current Time: ", SwingConstants.CENTER);
        jlblTime.setSize(40, 40);
        jlblTime.setBackground(new Color(35, 132, 30));
        jlblTime.setForeground(Color.WHITE);

        jlblDate = new JLabel("Current Date: ", SwingConstants.CENTER);
        jlblDate.setSize(40, 40);
        jlblDate.setBackground(new Color(35, 132, 30));
        jlblDate.setForeground(Color.WHITE);

        jlblBook = new JLabel("Choose Book: ", SwingConstants.CENTER);
        jlblBook.setSize(40, 40);
        jlblBook.setBackground(new Color(35, 132, 30));
        jlblBook.setForeground(Color.WHITE);

        Date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        jlblDate2 = new JLabel(Date, SwingConstants.CENTER);
        jlblDate2.setSize(40, 40);
        jlblDate2.setBackground(new Color(35, 132, 30));
        jlblDate2.setForeground(Color.WHITE);

        int sleepinterval = 1;
        ThreadedCurrentTime tc = new ThreadedCurrentTime(sleepinterval);
        tc.start();
        jpnlTime = tc.getPanelT();
        jpnlTime.setBackground(new Color(35, 132, 30));
        jpnlTime.setForeground(Color.WHITE);

        jbtBack = new JButton("Back");
        jbtBack.setPreferredSize(new Dimension(100, 100));
        jbtBack.addActionListener(this);
        jbtBack.setBackground(new Color(35, 132, 30));
        jbtBack.setForeground(Color.WHITE);
        jbtBack.setToolTipText("Click to go back to main menu");

        jbtAddStock = new JButton("Add Stock");
        jbtAddStock.setPreferredSize(new Dimension(100, 100));
        jbtAddStock.addActionListener(this);
        jbtAddStock.setBackground(new Color(35, 132, 30));
        jbtAddStock.setForeground(Color.WHITE);
        jbtAddStock.setToolTipText("Click to add stock to store");
        jbtAddStock.setEnabled(false);

        for (int i = 1; i <= bookCount; i++) {
            Book book = db.getBook(i);
            String bookName = book.getName();
            if (!bookName.matches("")) {
                Books.add(bookName);
            }
        }
        Collections.sort(Books);

        list = new JList(Books);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(Books.size());
        list.setBackground(new Color(35, 132, 30));
        list.setForeground(Color.WHITE);
        listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));

        M = new JTextField(50);
        M.setSize(40, 40);
        M.setBackground(new Color(35, 132, 30));
        M.setForeground(Color.WHITE);
        M.setHorizontalAlignment(SwingConstants.CENTER);

        TimeDate.add(jlblTime);
        TimeDate.add(jpnlTime);
        TimeDate.add(jlblDate);
        TimeDate.add(jlblDate2);

        Top.add(TimeDate);
        Top.add(jlblBook);

        buttons.add(M);
        buttons.add(jbtAddStock);
        buttons.add(jbtBack);

        menu.add(Top);
        menu.add(listScroller);
        menu.add(buttons);

        jbtBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtBack.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtBack.setBackground(new Color(35, 132, 30));
            }
        }
        );

        jbtAddStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtAddStock.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtAddStock.setBackground(new Color(35, 132, 30));
            }
        }
        );

        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    if (list.getSelectedIndex() == -1/* && choice ==-1*/) {
                        //No selection.
                        jbtAddStock.setEnabled(false);
                    } else {
                        //Selection.
                        jbtAddStock.setEnabled(true);
                        choice = list.getSelectedValue().toString();
                    }
                }
            }
        });

        M.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                M.setForeground(Color.BLACK);
                M.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                M.setForeground(Color.WHITE);
                M.setBackground(new Color(35, 132, 30));
            }
        }
        );

        M.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { //watch for key strokes
                if (M.getText().length() == 0) {
                    jbtAddStock.setEnabled(false);
                } else if (!(M.getText().matches(pattern))) {
                    jbtAddStock.setEnabled(false);
                } else {
                    jbtAddStock.setEnabled(true);
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
            WarehouseMainMenu wmenu = new WarehouseMainMenu(userName);
            this.setVisible(false);
        }

        if (source.equals(jbtAddStock)) {
            AddWarehouseStockControl addWarehouseStockObject = new AddWarehouseStockControl();
            addWarehouseStockObject.addWarehouseStock(choice, Integer.parseInt(M.getText()));
            WarehouseMainMenu wmenu = new WarehouseMainMenu(userName);
            this.setVisible(false);
        }
    }
}
