package StaffUI;

import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import Orders.Book;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import UICommon.ThreadedCurrentTime;

public class CheckStock extends JFrame implements ActionListener {

    private DBHandler db = DBHandlerFactory.getDBHandler("Staff");
    private int bookCount = db.countAllBooks();
    private JButton jbtBack;
    private JPanel menu;
    private JPanel Top;
    private JLabel jlblTime;
    private JLabel jlblDate;
    private JLabel jlblBook;
    private JScrollPane listScroller;
    private JPanel jpnlTime;
    private JLabel jlblDate2;
    private JPanel TimeDate;
    private String Date;
    private Vector<String> books = new Vector<>();
    private Vector<Integer> amount = new Vector<>();
    private Vector<String> stock = new Vector<>();
    private JList list;
    private int choice = -1;
    private String userName;

    private String pattern = "[0-9]+";

    public CheckStock(String userName) {
        this.userName = userName;
        this.setTitle("Current Stock");
        this.setBounds(100, 100, 500, 300);
        this.setPreferredSize(new Dimension(500, 500));
        this.setLayout(new GridLayout(1, 1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(new Color(59, 89, 182));

        TimeDate = new JPanel();
        TimeDate.setBounds(new Rectangle(500, 500));
        TimeDate.setSize(100, 100);
        TimeDate.setLayout(new GridLayout(1, 4));
        TimeDate.setBackground(new Color(59, 89, 182));
        TimeDate.setForeground(Color.WHITE);

        Top = new JPanel();
        Top.setBounds(new Rectangle(100, 100));
        Top.setLayout(new GridLayout(2, 1));
        Top.setBackground(new Color(59, 89, 182));
        Top.setForeground(Color.WHITE);

        menu = new JPanel();
        menu.setBounds(new Rectangle(100, 100));
        menu.setLayout(new GridLayout(2, 1));
        menu.setBackground(new Color(59, 89, 182));
        menu.setForeground(Color.WHITE);

        jlblTime = new JLabel("Current Time: ", SwingConstants.CENTER);
        jlblTime.setSize(40, 40);
        jlblTime.setBackground(new Color(59, 89, 182));
        jlblTime.setForeground(Color.WHITE);

        jlblDate = new JLabel("Current Date: ", SwingConstants.CENTER);
        jlblDate.setSize(40, 40);
        jlblDate.setBackground(new Color(59, 89, 182));
        jlblDate.setForeground(Color.WHITE);

        jlblBook = new JLabel("Choose Book: ", SwingConstants.CENTER);
        jlblBook.setSize(40, 40);
        jlblBook.setBackground(new Color(59, 89, 182));
        jlblBook.setForeground(Color.WHITE);

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

        for (int i = 1; i <= bookCount; i++) {
            Book book = db.getBook(i);
            String bookName = book.getName();
            if (!bookName.matches("")) {
                books.add(bookName);
                amount.add(db.getStoreStockAmount(db.getBookID(bookName)));
                stock.add(books.get(i - 1) + "  " + " Stock: " + amount.get(i - 1));
            }
        }
        Collections.sort(stock);

        list = new JList(stock);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(stock.size());
        list.setBackground(new Color(59, 89, 182));
        list.setForeground(Color.WHITE);
        listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));

        TimeDate.add(jlblTime);
        TimeDate.add(jpnlTime);
        TimeDate.add(jlblDate);
        TimeDate.add(jlblDate2);

        Top.add(TimeDate);
        Top.add(jbtBack);

        menu.add(Top);
        menu.add(listScroller);

        jbtBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtBack.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtBack.setBackground(new Color(59, 89, 182));
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
            StockMenu smenu = new StockMenu(userName);
            this.setVisible(false);
        }
    }
}
