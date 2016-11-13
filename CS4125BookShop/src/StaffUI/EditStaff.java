package StaffUI;

import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import Staff.Staff;
import StaffControls.EditStaffControl;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import UICommon.ThreadedCurrentTime;

public class EditStaff extends JFrame implements ActionListener {

    private DBHandler db = DBHandlerFactory.getDBHandler("Staff");
    private int staffCount = db.countAllStaff();
    private JButton jbtBack;
    private JButton jbtEditStaff;
    private JPanel menu;
    private JPanel buttons;
    private JPanel Persondetails;
    private JLabel jlblTime;
    private JLabel jlblDate;
    private JPanel jpnlTime;
    private JLabel jlblDate2;
    private JPanel TimeDate;
    private String Date;
    private JLabel Fname;
    private JLabel Sname;
    private JLabel Aname;
    private JLabel Ename;
    private JLabel Phone;
    private JLabel Warehouse;
    private JTextField F;
    private JTextField S;
    private JTextField A;
    private JTextField E;
    private JTextField P;
    private JTextField W;
    private JScrollPane listScroller;
    private Vector<String> StaffList = new Vector<>();
    private JList list;
    private String choice;
    private String pattern = "[0-9]+";
    private String warehousePattern = "[0-1]";
    private String userName;

    public EditStaff(String userName) {
        this.userName = userName;
        this.setTitle("Edit Staff");
        this.setBounds(100, 100, 500, 300);
        this.setPreferredSize(new Dimension(600, 600));
        this.setLayout(new GridLayout(2, 1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(new Color(59, 89, 182));

        TimeDate = new JPanel();
        TimeDate.setBounds(new Rectangle(500, 500));
        TimeDate.setSize(100, 100);
        TimeDate.setLayout(new GridLayout(1, 4));
        TimeDate.setBackground(new Color(59, 89, 182));
        TimeDate.setForeground(Color.WHITE);

        menu = new JPanel();
        menu.setBounds(new Rectangle(100, 100));
        menu.setLayout(new GridLayout(3, 1));

        buttons = new JPanel();
        buttons.setBounds(new Rectangle(10, 10));
        buttons.setLayout(new GridLayout(1, 2));

        Persondetails = new JPanel();
        Persondetails.setBounds(new Rectangle(10, 10));
        Persondetails.setLayout(new GridLayout(6, 2));
        Persondetails.setBackground(new Color(59, 89, 182));
        Persondetails.setForeground(Color.WHITE);

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

        jbtEditStaff = new JButton("Save Changes");
        jbtEditStaff.setPreferredSize(new Dimension(100, 100));
        jbtEditStaff.addActionListener(this);
        jbtEditStaff.setBackground(new Color(59, 89, 182));
        jbtEditStaff.setForeground(Color.WHITE);
        jbtEditStaff.setToolTipText("Click to edit staff in the database");
        jbtEditStaff.setEnabled(false);

        Fname = new JLabel("Enter First Name: ", SwingConstants.CENTER);
        Fname.setSize(40, 40);
        Fname.setBackground(new Color(59, 89, 182));
        Fname.setForeground(Color.WHITE);

        Sname = new JLabel("Enter Surname: ", SwingConstants.CENTER);
        Sname.setSize(40, 40);
        Sname.setBackground(new Color(59, 89, 182));
        Sname.setForeground(Color.WHITE);

        Aname = new JLabel("Enter Address: ", SwingConstants.CENTER);
        Aname.setSize(40, 40);
        Aname.setBackground(new Color(59, 89, 182));
        Aname.setForeground(Color.WHITE);

        Ename = new JLabel("Enter Email: ", SwingConstants.CENTER);
        Ename.setSize(40, 40);
        Ename.setBackground(new Color(59, 89, 182));
        Ename.setForeground(Color.WHITE);

        Phone = new JLabel("Enter Phone Number: ", SwingConstants.CENTER);
        Phone.setSize(40, 40);
        Phone.setBackground(new Color(59, 89, 182));
        Phone.setForeground(Color.WHITE);

        Warehouse = new JLabel("Enter 0/1 for Warehouse Staff: ", SwingConstants.CENTER);
        Warehouse.setSize(40, 40);
        Warehouse.setBackground(new Color(59, 89, 182));
        Warehouse.setForeground(Color.WHITE);

        F = new JTextField(20);
        F.setSize(40, 40);
        F.setBackground(new Color(59, 89, 182));
        F.setForeground(Color.WHITE);
        F.setHorizontalAlignment(SwingConstants.CENTER);

        S = new JTextField(20);
        S.setSize(40, 40);
        S.setBackground(new Color(59, 89, 182));
        S.setForeground(Color.WHITE);
        S.setHorizontalAlignment(SwingConstants.CENTER);

        A = new JTextField(50);
        A.setSize(40, 40);
        A.setBackground(new Color(59, 89, 182));
        A.setForeground(Color.WHITE);
        A.setHorizontalAlignment(SwingConstants.CENTER);

        E = new JTextField(50);
        E.setSize(40, 40);
        E.setBackground(new Color(59, 89, 182));
        E.setForeground(Color.WHITE);
        E.setHorizontalAlignment(SwingConstants.CENTER);

        P = new JTextField(50);
        P.setSize(40, 40);
        P.setBackground(new Color(59, 89, 182));
        P.setForeground(Color.WHITE);
        P.setHorizontalAlignment(SwingConstants.CENTER);

        W = new JTextField(50);
        W.setSize(40, 40);
        W.setBackground(new Color(59, 89, 182));
        W.setForeground(Color.WHITE);
        W.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 1; i <= staffCount; i++) {
            Staff staff = db.getStaff(i);
            if (!staff.getName().matches("")) {
                StaffList.add(staff.getName());
            }
        }
        Collections.sort(StaffList);

        list = new JList(StaffList);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(StaffList.size());
        list.setBackground(new Color(59, 89, 182));
        list.setForeground(Color.WHITE);
        listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));

        TimeDate.add(jlblTime);
        TimeDate.add(jpnlTime);
        TimeDate.add(jlblDate);
        TimeDate.add(jlblDate2);

        buttons.add(jbtEditStaff);
        buttons.add(jbtBack);

        Persondetails.add(Fname);
        Persondetails.add(F);
        Persondetails.add(Sname);
        Persondetails.add(S);
        Persondetails.add(Aname);
        Persondetails.add(A);
        Persondetails.add(Ename);
        Persondetails.add(E);
        Persondetails.add(Phone);
        Persondetails.add(P);
        Persondetails.add(Warehouse);
        Persondetails.add(W);

        menu.add(TimeDate);
        menu.add(buttons);
        menu.add(listScroller);

        this.add(menu);
        this.add(Persondetails);

        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    if (list.getSelectedIndex() == -1/* && choice ==-1*/) {
                        //No selection.
                        jbtEditStaff.setEnabled(false);
                    } else {
                        //Selection.
                        jbtEditStaff.setEnabled(true);
                        choice = list.getSelectedValue().toString();
                        Staff staff = db.getStaff(choice);
                        String name[] = staff.getName().split(" ");
                        F.setText(name[0]);
                        S.setText(name[1]);
                        A.setText(staff.getAddress());
                        E.setText(staff.getEmail());
                        P.setText("" + staff.getPhoneNumber());
                        W.setText("" + staff.getWarehouse());
                    }
                }
            }
        });

        F.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                F.setForeground(Color.BLACK);
                F.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                F.setForeground(Color.WHITE);
                F.setBackground(new Color(59, 89, 182));
            }
        }
        );

        A.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                A.setForeground(Color.BLACK);
                A.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                A.setForeground(Color.WHITE);
                A.setBackground(new Color(59, 89, 182));
            }
        }
        );

        P.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                P.setForeground(Color.BLACK);
                P.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                P.setForeground(Color.WHITE);
                P.setBackground(new Color(59, 89, 182));
            }
        }
        );

        S.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                S.setForeground(Color.BLACK);
                S.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                S.setForeground(Color.WHITE);
                S.setBackground(new Color(59, 89, 182));
            }
        }
        );

        E.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                E.setForeground(Color.BLACK);
                E.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                E.setForeground(Color.WHITE);
                E.setBackground(new Color(59, 89, 182));
            }
        }
        );

        W.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                W.setForeground(Color.BLACK);
                W.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                W.setForeground(Color.WHITE);
                W.setBackground(new Color(59, 89, 182));
            }
        }
        );

        jbtBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtBack.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtBack.setBackground(new Color(59, 89, 182));
            }
        }
        );

        jbtEditStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtEditStaff.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtEditStaff.setBackground(new Color(59, 89, 182));
            }
        }
        );

        F.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { //watch for key strokes
                if (F.getText().length() == 0) {
                    jbtEditStaff.setEnabled(false);
                } else {
                    jbtEditStaff.setEnabled(true);
                }
            }
        });

        A.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { //watch for key strokes
                if (A.getText().length() == 0) {
                    jbtEditStaff.setEnabled(false);
                } else {
                    jbtEditStaff.setEnabled(true);
                }
            }
        });

        P.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { //watch for key strokes
                if (!(P.getText().matches(pattern)) || P.getText().length() == 0) {
                    jbtEditStaff.setEnabled(false);
                } else {
                    jbtEditStaff.setEnabled(true);
                }
            }
        });

        W.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { //watch for key strokes
                if (!(W.getText().matches(warehousePattern)) || W.getText().length() == 0) {
                    jbtEditStaff.setEnabled(false);
                } else {
                    jbtEditStaff.setEnabled(true);
                }
            }
        });

        S.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { //watch for key strokes
                if (S.getText().length() == 0) {
                    jbtEditStaff.setEnabled(false);
                } else {
                    jbtEditStaff.setEnabled(true);
                }
            }
        });

        E.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { //watch for key strokes
                if (E.getText().length() == 0) {
                    jbtEditStaff.setEnabled(false);
                } else {
                    jbtEditStaff.setEnabled(true);
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
            StaffMenu smenu = new StaffMenu(userName);
            this.setVisible(false);
        }

        if (source.equals(jbtEditStaff)) {
            EditStaffControl editStaffObject = new EditStaffControl();
            editStaffObject.editStaff(db.getStaffID(choice), F.getText() + " " + S.getText(), A.getText(), E.getText(), Integer.parseInt(P.getText()), Integer.parseInt(W.getText()));
            StaffMenu smenu = new StaffMenu(userName);
            this.setVisible(false);
        }
    }
}
