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

public class StockMenu extends JFrame implements ActionListener {

    private JButton jbtBack;
    private JButton jbtAddStock;
    private JButton jbtRemoveStock;
    private JButton jbtCheckStock;
    private JPanel menu;
    private JLabel jlblTime;
    private JLabel jlblDate;
    private JPanel jpnlTime;
    private JLabel jlblDate2;
    private JPanel TimeDate;
    private String Date;
    private String userName;

    public StockMenu(String userName) {
        this.userName = userName;
        this.setTitle("Stock Menu");
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

        jbtAddStock = new JButton("Add Stock");
        jbtAddStock.setPreferredSize(new Dimension(100, 100));
        jbtAddStock.addActionListener(this);
        jbtAddStock.setBackground(new Color(59, 89, 182));
        jbtAddStock.setForeground(Color.WHITE);
        jbtAddStock.setToolTipText("Click to open add stock menu");

        jbtRemoveStock = new JButton("Remove Stock");
        jbtRemoveStock.setPreferredSize(new Dimension(100, 100));
        jbtRemoveStock.addActionListener(this);
        jbtRemoveStock.setBackground(new Color(59, 89, 182));
        jbtRemoveStock.setForeground(Color.WHITE);
        jbtRemoveStock.setToolTipText("Click to open remove stock menu");

        jbtCheckStock = new JButton("Check Store Stock");
        jbtCheckStock.setPreferredSize(new Dimension(100, 100));
        jbtCheckStock.addActionListener(this);
        jbtCheckStock.setBackground(new Color(59, 89, 182));
        jbtCheckStock.setForeground(Color.WHITE);
        jbtCheckStock.setToolTipText("Click to display current stock");

        TimeDate.add(jlblTime);
        TimeDate.add(jpnlTime);
        TimeDate.add(jlblDate);
        TimeDate.add(jlblDate2);

        menu.add(TimeDate);
        menu.add(jbtAddStock);
        menu.add(jbtRemoveStock);
        menu.add(jbtCheckStock);
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

        jbtAddStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtAddStock.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtAddStock.setBackground(new Color(59, 89, 182));
            }
        }
        );

        jbtRemoveStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtRemoveStock.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtRemoveStock.setBackground(new Color(59, 89, 182));
            }
        }
        );

        jbtCheckStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtCheckStock.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtCheckStock.setBackground(new Color(59, 89, 182));
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

        if (source.equals(jbtAddStock)) {
            AddStock as = new AddStock(userName);
            this.setVisible(false);
        }

        if (source.equals(jbtRemoveStock)) {
            DeleteStock ds = new DeleteStock(userName);
            this.setVisible(false);
        }

        if (source.equals(jbtCheckStock)) {
            CheckStock cs = new CheckStock(userName);
            this.setVisible(false);
        }
    }
}
