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

public class BookMenu extends JFrame implements ActionListener {

    private JButton jbtBack;
    private JButton jbtAddBook;
    private JButton jbtRemoveBook;
    private JButton jbtEditBook;
    private JPanel menu;
    private JLabel jlblTime;
    private JLabel jlblDate;
    private JPanel jpnlTime;
    private JLabel jlblDate2;
    private JPanel TimeDate;
    private String Date;
    private String userName;

    public BookMenu(String userName) {
        this.userName = userName;
        this.setTitle("Book Menu");
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

        jbtAddBook = new JButton("Add Book");
        jbtAddBook.setPreferredSize(new Dimension(100, 100));
        jbtAddBook.addActionListener(this);
        jbtAddBook.setBackground(new Color(59, 89, 182));
        jbtAddBook.setForeground(Color.WHITE);
        jbtAddBook.setToolTipText("Click to open add book menu");

        jbtRemoveBook = new JButton("Remove Book");
        jbtRemoveBook.setPreferredSize(new Dimension(100, 100));
        jbtRemoveBook.addActionListener(this);
        jbtRemoveBook.setBackground(new Color(59, 89, 182));
        jbtRemoveBook.setForeground(Color.WHITE);
        jbtRemoveBook.setToolTipText("Click to open remove book menu");

        jbtEditBook = new JButton("Edit Book");
        jbtEditBook.setPreferredSize(new Dimension(100, 100));
        jbtEditBook.addActionListener(this);
        jbtEditBook.setBackground(new Color(59, 89, 182));
        jbtEditBook.setForeground(Color.WHITE);
        jbtEditBook.setToolTipText("Click to open edit book menu");

        TimeDate.add(jlblTime);
        TimeDate.add(jpnlTime);
        TimeDate.add(jlblDate);
        TimeDate.add(jlblDate2);

        menu.add(TimeDate);
        menu.add(jbtAddBook);
        menu.add(jbtRemoveBook);
        menu.add(jbtEditBook);
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

        jbtAddBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtAddBook.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtAddBook.setBackground(new Color(59, 89, 182));
            }
        }
        );

        jbtRemoveBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtRemoveBook.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtRemoveBook.setBackground(new Color(59, 89, 182));
            }
        }
        );

        jbtEditBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtEditBook.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtEditBook.setBackground(new Color(59, 89, 182));
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

        if (source.equals(jbtAddBook)) {
            AddBook abmenu = new AddBook(userName);
            this.setVisible(false);
        }

        if (source.equals(jbtRemoveBook)) {
            DeleteBook dmenu = new DeleteBook(userName);
            this.setVisible(false);
        }

        if (source.equals(jbtEditBook)) {
            EditBook ebmenu = new EditBook(userName);
            this.setVisible(false);
        }
    }
}
