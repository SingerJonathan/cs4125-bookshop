package WarehouseUI;

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

public class WarehouseMainMenu extends JFrame implements ActionListener {

    private JButton jbtExit;
    private JButton jbtProcessOrder;
    private JButton jbtAddStockStore;
    private JButton jbtAddStockWarehouse;
    private JPanel menu;
    private JLabel jlblTime;
    private JLabel jlblDate;
    private JPanel jpnlTime;
    private JLabel jlblDate2;
    private JPanel TimeDate;
    private String Date;
    private String userName;

    public WarehouseMainMenu(String userName) {
        this.userName = userName;
        this.setTitle("Warehouse Menu");
        this.setBounds(100, 100, 500, 300);
        this.setPreferredSize(new Dimension(500, 500));
        this.setLayout(new GridLayout(1, 1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TimeDate = new JPanel();
        TimeDate.setBounds(new Rectangle(50, 50));
        TimeDate.setSize(40, 40);
        TimeDate.setLayout(new GridLayout(1, 4));
        TimeDate.setBackground(new Color(35, 132, 30));
        TimeDate.setForeground(Color.WHITE);

        menu = new JPanel();
        menu.setBounds(new Rectangle(100, 100));
        menu.setLayout(new GridLayout(5, 1));

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

        jbtExit = new JButton("Exit");
        jbtExit.setPreferredSize(new Dimension(100, 100));
        jbtExit.addActionListener(this);
        jbtExit.setBackground(new Color(35, 132, 30));
        jbtExit.setForeground(Color.WHITE);
        jbtExit.setToolTipText("Click to exit system");

        jbtProcessOrder = new JButton("Process Order");
        jbtProcessOrder.setPreferredSize(new Dimension(100, 100));
        jbtProcessOrder.addActionListener(this);
        jbtProcessOrder.setBackground(new Color(35, 132, 30));
        jbtProcessOrder.setForeground(Color.WHITE);
        jbtProcessOrder.setToolTipText("Click to open process order");

        jbtAddStockStore = new JButton("Add Stock to Store");
        jbtAddStockStore.setPreferredSize(new Dimension(100, 100));
        jbtAddStockStore.addActionListener(this);
        jbtAddStockStore.setBackground(new Color(35, 132, 30));
        jbtAddStockStore.setForeground(Color.WHITE);
        jbtAddStockStore.setToolTipText("Click to open add stock to store");

        jbtAddStockWarehouse = new JButton("Add Stock to Warehouse");
        jbtAddStockWarehouse.setPreferredSize(new Dimension(100, 100));
        jbtAddStockWarehouse.addActionListener(this);
        jbtAddStockWarehouse.setBackground(new Color(35, 132, 30));
        jbtAddStockWarehouse.setForeground(Color.WHITE);
        jbtAddStockWarehouse.setToolTipText("Click to open add stock to warehouse");

        TimeDate.add(jlblTime);
        TimeDate.add(jpnlTime);
        TimeDate.add(jlblDate);
        TimeDate.add(jlblDate2);

        menu.add(TimeDate);
        menu.add(jbtProcessOrder);
        menu.add(jbtAddStockStore);
        menu.add(jbtAddStockWarehouse);
        menu.add(jbtExit);

        jbtExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtExit.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtExit.setBackground(new Color(35, 132, 30));
            }
        }
        );

        jbtProcessOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtProcessOrder.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtProcessOrder.setBackground(new Color(35, 132, 30));
            }
        }
        );

        jbtAddStockStore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtAddStockStore.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtAddStockStore.setBackground(new Color(35, 132, 30));
            }
        }
        );

        jbtAddStockWarehouse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtAddStockWarehouse.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtAddStockWarehouse.setBackground(new Color(35, 132, 30));
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
        if (source.equals(jbtExit)) {
            System.exit(0);
        }

        if (source.equals(jbtProcessOrder)) {
            ProcessOrder omenu = new ProcessOrder(userName);
            this.setVisible(false);
        }

        if (source.equals(jbtAddStockStore)) {
            AddStoreStock ssmenu = new AddStoreStock(userName);
            this.setVisible(false);
        }

        if (source.equals(jbtAddStockWarehouse)) {
            AddWarehouseStock wsmenu = new AddWarehouseStock(userName);
            this.setVisible(false);
        }
    }
}
