package WarehouseUI;

import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import UICommon.ThreadedCurrentTime;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import Orders.Order;
import WarehouseControls.ProcessOrderControl;

public class ProcessOrder extends JFrame implements ActionListener {

    private DBHandler db = DBHandlerFactory.getDBHandler("Warehouse");
    private int orderCount = db.countOrders();
    private JButton jbtBack;
    private JButton jbtBuyBook;
    private JPanel menu;
    private JPanel buttons;
    private JLabel jlblTime;
    private JLabel jlblDate;
    private JScrollPane listScroller;
    private JPanel jpnlTime;
    private JLabel jlblDate2;
    private JPanel TimeDate;
    private String Date;
    private Vector<String> Orders = new Vector<>();
    private JList list;
    private String choice;
    private String userName;

    public ProcessOrder(String userName) {
        this.userName = userName;
        this.setTitle("Process Order");
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

        buttons = new JPanel();
        buttons.setBounds(new Rectangle(10, 10));
        buttons.setLayout(new GridLayout(1, 2));

        jlblTime = new JLabel("Current Time: ", SwingConstants.CENTER);
        jlblTime.setSize(40, 40);
        jlblTime.setBackground(new Color(35, 132, 30));
        jlblTime.setForeground(Color.WHITE);

        jlblDate = new JLabel("Current Date: ", SwingConstants.CENTER);
        jlblDate.setSize(40, 40);
        jlblDate.setBackground(new Color(35, 132, 30));
        jlblDate.setForeground(Color.WHITE);

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

        jbtBuyBook = new JButton("Process Order");
        jbtBuyBook.setPreferredSize(new Dimension(100, 100));
        jbtBuyBook.addActionListener(this);
        jbtBuyBook.setBackground(new Color(35, 132, 30));
        jbtBuyBook.setForeground(Color.WHITE);
        jbtBuyBook.setToolTipText("Click to process order selected from list");
        jbtBuyBook.setEnabled(false);

        for (int i = 1; i <= orderCount; i++) {
            Order order = db.getOrder(i);
            if (order.getProcessed() == 0 && order.getCancelled() == 0) {
                Orders.add("" + order.getID());
            }
        }
        Collections.sort(Orders);

        String[] BooksArray = Orders.toArray(new String[0]);
        list = new JList(BooksArray);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(Orders.size());
        list.setBackground(new Color(35, 132, 30));
        list.setForeground(Color.WHITE);
        listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));

        TimeDate.add(jlblTime);
        TimeDate.add(jpnlTime);
        TimeDate.add(jlblDate);
        TimeDate.add(jlblDate2);

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
                jbtBack.setBackground(new Color(35, 132, 30));
            }
        }
        );

        jbtBuyBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtBuyBook.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtBuyBook.setBackground(new Color(35, 132, 30));
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

        this.add(menu);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        JButton source = (JButton) ae.getSource();
        if (source.equals(jbtBack)) {
            WarehouseMainMenu menu = new WarehouseMainMenu(userName);
            this.setVisible(false);
        }

        if (source.equals(jbtBuyBook)) {
            ProcessOrderControl processOrderObject = new ProcessOrderControl();
            processOrderObject.processOrder(Integer.parseInt(choice));
            WarehouseMainMenu menu = new WarehouseMainMenu(userName);
            this.setVisible(false);
        }
    }
}
