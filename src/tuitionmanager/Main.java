/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuitionmanager;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ben
 */
public class Main extends javax.swing.JFrame {
    final int studentTab = 0;
    final int paymentTab = 1;
    
    ConnectionManager theManager = null;
    String termChoiceText;
    String currentUser;
    /**
     * Creates new form Main
     */
    public Main(ConnectionManager theManager, String user) {
        initComponents();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int response = JOptionPane.showConfirmDialog(null,
                        "Are you sure to exit the TuitionManager?", "Confirm close?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    endSession();
                    System.exit(0);
                }
            }});
        if (user.equals("ADMIN")) {
            groupButton.setEnabled(true);
            jButton2.setEnabled(true);
            fileNameL.setEnabled(true);
            jLabel5.setEnabled(true);
            gradeCombo2.setEnabled(true);
        }
        paymentAdd.setVisible(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setResizable(false);
        this.setTitle("Tuition Manager V1.0");
        buttonGroup1.add(individualButton);
        buttonGroup1.add(groupButton);
        buttonGroup2.add(increaseM);
        buttonGroup2.add(decreaseM);
        buttonGroup3.add(fees1);
        buttonGroup3.add(fees2);
        this.theManager = theManager;
        
        
        
        theManager.getBusCosts();
        jFileChooser1.setFileFilter(new FileFilter() {
            
            public String getDescription() {
                return "Excel Documents (*.xls)";
            }
            
            
            
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".xls");
                }
            }
        });
        
        
        studentView.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    please(studentView, studentView.getValueAt(studentView.getSelectedRow(), 0).toString());
                }
            }
        });
        
        paymentsView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = paymentsView.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                handleSelectionEvent(e);
            }
        });
    }
    
    protected void handleSelectionEvent(ListSelectionEvent e) {
        try {
            String studentNumber = paymentsView.getValueAt(paymentsView.getSelectedRow(), 1).toString();
            extraDate.setText(paymentsView.getValueAt(paymentsView.getSelectedRow(), 0).toString());
            extraAmount.setText(paymentsView.getValueAt(paymentsView.getSelectedRow(), 2).toString());
            extraNumber.setText(studentNumber);
            extraAccount.setText(paymentsView.getValueAt(paymentsView.getSelectedRow(), 3).toString());
            getDetails(studentNumber);
        }
        catch (ArrayIndexOutOfBoundsException e1) {
            System.out.println("Error Bro");
        }
        
        
    }
    
    public void endSession() {
        theManager.endSession();
    }
    public void getDetails(String number) {
        theManager.getDetails(number, extraName, extraSurname, extraGrade);
    }
    
    
    public void please(JTable table, String number) {
        String studentNumber = number;
        theManager.fillStudentValues(studentNumber, studentNumberL, nameL, surnameL,gradeL, totalL, paidL, balanceL);
        updateNumbers(studentNumber);
        mainPane.setSelectedIndex(paymentTab);
        paymentPane.setSelectedIndex(1);
    }
    
    public void updateNumbers(String studentNumber) {
        double total = theManager.getTotalDue(studentNumber);
        totalL.setText(total+"");
        theManager.getStudentPayments(studentPaymentTable, studentNumber );
        
        double totalPaid =0 -1 * theManager.getPaid(studentNumber);
        if (totalPaid>0) {
            paidL.setText((-1 * totalPaid)+"");
        }
        paidL.setText(totalPaid+"");
        double balance = theManager.getBalance(studentNumber);
        balanceL.setText(balance+"");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        mainPane = new javax.swing.JTabbedPane();
        studentPane = new javax.swing.JTabbedPane();
        sView = new javax.swing.JPanel();
        studentComboView = new javax.swing.JComboBox();
        teacherLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentView = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        executeButton = new javax.swing.JButton();
        sAdd = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textName = new javax.swing.JTextField();
        textSurname = new javax.swing.JTextField();
        gradeCombo = new javax.swing.JComboBox();
        individualButton = new javax.swing.JRadioButton();
        fees1 = new javax.swing.JRadioButton();
        fees2 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        fileNameL = new javax.swing.JLabel();
        gradeCombo2 = new javax.swing.JComboBox();
        groupButton = new javax.swing.JRadioButton();
        paymentPane = new javax.swing.JTabbedPane();
        paymentView = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        paymentsView = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        extraName = new javax.swing.JTextField();
        extraSurname = new javax.swing.JTextField();
        extraNumber = new javax.swing.JTextField();
        extraGrade = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        extraAmount = new javax.swing.JTextField();
        extraDate = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        extraAccount = new javax.swing.JTextField();
        paymentAdd = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        studentNumberL = new javax.swing.JTextField();
        nameL = new javax.swing.JTextField();
        surnameL = new javax.swing.JTextField();
        gradeL = new javax.swing.JTextField();
        totalL = new javax.swing.JTextField();
        paidL = new javax.swing.JTextField();
        balanceL = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        studentPaymentTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        amountL = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        dateL = new javax.swing.JLabel();
        accountCombo = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        dayCombo = new javax.swing.JComboBox();
        monthCombo = new javax.swing.JComboBox();
        yearCombo = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        amountM = new javax.swing.JTextField();
        desM = new javax.swing.JTextField();
        increaseM = new javax.swing.JRadioButton();
        decreaseM = new javax.swing.JRadioButton();
        buttonM = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        exitMenu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPane.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        studentComboView.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Grade", "All", "Foundation", "Lower-Pre", "Upper-Pre", "Grade 0", "Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5", "Grade 6", "Grade 7" }));
        studentComboView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentComboViewActionPerformed(evt);
            }
        });

        teacherLabel.setFont(teacherLabel.getFont().deriveFont(teacherLabel.getFont().getStyle() | java.awt.Font.BOLD, teacherLabel.getFont().getSize()+3));
        teacherLabel.setText("Teacher");

        studentView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Name", "Surname", "Total due", "Paid", "Balance", "Grade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(studentView);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Class Teacher");

        executeButton.setText("Execute");
        executeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sViewLayout = new javax.swing.GroupLayout(sView);
        sView.setLayout(sViewLayout);
        sViewLayout.setHorizontalGroup(
            sViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sViewLayout.createSequentialGroup()
                .addComponent(studentComboView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(executeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(28, 28, 28)
                .addComponent(teacherLabel)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1355, Short.MAX_VALUE)
        );
        sViewLayout.setVerticalGroup(
            sViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sViewLayout.createSequentialGroup()
                .addGroup(sViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(sViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(studentComboView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(executeButton))
                    .addGroup(sViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(teacherLabel)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE))
        );

        studentPane.addTab("View", sView);

        addButton.setBackground(new java.awt.Color(51, 51, 51));
        addButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Name     :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Surname :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Grade     :");

        textName.setToolTipText("Enter Name");

        textSurname.setToolTipText("Enter Surname");
        textSurname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textSurnameActionPerformed(evt);
            }
        });

        gradeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Grade", "Foundation", "Lower-Pre", "Upper-Pre", "Grade 0", "Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5", "Grade 6", "Grade 7" }));
        gradeCombo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                gradeComboPropertyChange(evt);
            }
        });

        individualButton.setSelected(true);
        individualButton.setText("Individual");

        fees1.setText("Full School Fees");

        fees2.setText("Partial School Fees");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(individualButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gradeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(fees1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fees2)))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(individualButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(gradeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fees1)
                    .addComponent(fees2))
                .addContainerGap())
        );

        jButton2.setText("Choose File");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Grade");
        jLabel5.setEnabled(false);

        fileNameL.setText("Filename");
        fileNameL.setEnabled(false);

        gradeCombo2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Grade", "Foundation", "Lower-Pre", "Upper-Pre", "Grade 0", "Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5", "Grade 6", "Grade 7" }));
        gradeCombo2.setEnabled(false);

        groupButton.setText("Group");
        groupButton.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(groupButton)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fileNameL)
                    .addComponent(gradeCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(fileNameL)))
                    .addComponent(groupButton))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(gradeCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout sAddLayout = new javax.swing.GroupLayout(sAdd);
        sAdd.setLayout(sAddLayout);
        sAddLayout.setHorizontalGroup(
            sAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, sAddLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(294, 294, 294)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(391, Short.MAX_VALUE))
            .addGroup(sAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        sAddLayout.setVerticalGroup(
            sAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sAddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(486, Short.MAX_VALUE))
        );

        studentPane.addTab("Add", sAdd);

        mainPane.addTab("Students", studentPane);

        paymentView.setEnabled(false);
        paymentView.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                paymentViewFocusGained(evt);
            }
        });

        jScrollPane4.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jScrollPane4PropertyChange(evt);
            }
        });

        paymentsView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Student Number", "Amount", "Account"
            }
        ));
        paymentsView.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                paymentsViewPropertyChange(evt);
            }
        });
        jScrollPane4.setViewportView(paymentsView);

        jButton3.setText("Refresh");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("STUDENT INFO");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Name");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Surname");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Student No.");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Grade");

        extraName.setEditable(false);
        extraName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extraNameActionPerformed(evt);
            }
        });

        extraSurname.setEditable(false);

        extraNumber.setEditable(false);
        extraNumber.setFocusable(false);
        extraNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extraNumberActionPerformed(evt);
            }
        });

        extraGrade.setEditable(false);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("Amount");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Date");

        extraAmount.setEditable(false);

        extraDate.setEditable(false);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Account");

        extraAccount.setEditable(false);

        javax.swing.GroupLayout paymentViewLayout = new javax.swing.GroupLayout(paymentView);
        paymentView.setLayout(paymentViewLayout);
        paymentViewLayout.setHorizontalGroup(
            paymentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentViewLayout.createSequentialGroup()
                .addGroup(paymentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 947, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(paymentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paymentViewLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20)
                        .addGap(127, 127, 127))
                    .addGroup(paymentViewLayout.createSequentialGroup()
                        .addGroup(paymentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paymentViewLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(paymentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addGroup(paymentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(paymentViewLayout.createSequentialGroup()
                                            .addComponent(jLabel22)
                                            .addGap(102, 102, 102)
                                            .addComponent(extraSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(paymentViewLayout.createSequentialGroup()
                                            .addGroup(paymentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel25)
                                                .addComponent(jLabel24)
                                                .addComponent(jLabel21))
                                            .addGap(108, 108, 108)
                                            .addGroup(paymentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(extraGrade, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                                .addComponent(extraAmount)
                                                .addComponent(extraDate)
                                                .addComponent(extraName, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(extraNumber, javax.swing.GroupLayout.Alignment.TRAILING))))))
                            .addGroup(paymentViewLayout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(jLabel27))
                            .addGroup(paymentViewLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(extraAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(97, Short.MAX_VALUE))))
        );
        paymentViewLayout.setVerticalGroup(
            paymentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paymentViewLayout.createSequentialGroup()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE))
            .addGroup(paymentViewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addGap(17, 17, 17)
                .addGroup(paymentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(paymentViewLayout.createSequentialGroup()
                        .addGroup(paymentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(extraName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(paymentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(extraSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(paymentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(extraNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(paymentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(extraGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(paymentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(extraAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53))
                    .addGroup(paymentViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(extraDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel26)))
                .addGap(55, 55, 55)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(extraAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        paymentPane.addTab("View", paymentView);

        jLabel1.setText("STUDENT NUMBER");

        jLabel6.setText("NAME");

        jLabel7.setText("SURNAME");

        jLabel8.setText("GRADE");

        jLabel9.setText("TOTAL DUE");

        jLabel10.setText("PAID");

        jLabel11.setText("BALANCE");

        studentNumberL.setEditable(false);

        nameL.setEditable(false);

        surnameL.setEditable(false);

        gradeL.setEditable(false);

        totalL.setEditable(false);

        paidL.setEditable(false);

        balanceL.setEditable(false);
        balanceL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balanceLActionPerformed(evt);
            }
        });

        studentPaymentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Transaction#", "Date", "Amount", "Account"
            }
        ));
        jScrollPane3.setViewportView(studentPaymentTable);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Amount Paid");

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Enter Payment");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Date recevied");

        dateL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        accountCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select account", "Tuition", "Bus" }));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Account");

        dayCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Day", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        monthCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Month", "JAN", "FEB", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" }));

        yearCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Year", "2013", "2014" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel15))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(dayCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(47, 47, 47)
                            .addComponent(monthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(43, 43, 43)
                            .addComponent(yearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(67, 67, 67))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(amountL)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(accountCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dateL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amountL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateL, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(accountCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dayCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("STUDENT INFO");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("CAPTURE PAYMENT");

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 0, 51));
        jButton5.setText("DELETE STUDENT");
        jButton5.setToolTipText("Permanently deletes student from database.");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setText("BLOCK ACCOUNT");
        jButton6.setToolTipText("Block student account if the student did not return after beginning the year.");
        jButton6.setEnabled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("Amount");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("Brief Description");

        increaseM.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        increaseM.setText("Increase Total (+)");
        increaseM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseMActionPerformed(evt);
            }
        });

        decreaseM.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        decreaseM.setText("Decrease Total (-)");

        buttonM.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonM.setText("Update");
        buttonM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(buttonM, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(desM, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(39, 39, 39)
                            .addComponent(jLabel29)
                            .addGap(18, 18, 18)
                            .addComponent(amountM, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(increaseM)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(decreaseM))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel30)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(amountM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(decreaseM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonM)
                .addContainerGap())
        );

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setText("MODIFY ACCOUNT");

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 0, 0));
        jButton4.setText("DELETE PAYMENT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paymentAddLayout = new javax.swing.GroupLayout(paymentAdd);
        paymentAdd.setLayout(paymentAddLayout);
        paymentAddLayout.setHorizontalGroup(
            paymentAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentAddLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel16)
                .addGap(31, 31, 31)
                .addGroup(paymentAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 371, Short.MAX_VALUE)
                .addGroup(paymentAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paymentAddLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paymentAddLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(paymentAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paymentAddLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(78, 78, 78))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jScrollPane3)
            .addGroup(paymentAddLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(paymentAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(paymentAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(paidL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(totalL, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(studentNumberL, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameL, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(surnameL, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gradeL, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(balanceL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        paymentAddLayout.setVerticalGroup(
            paymentAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentAddLayout.createSequentialGroup()
                .addGroup(paymentAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paymentAddLayout.createSequentialGroup()
                        .addGroup(paymentAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paymentAddLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(11, 11, 11)
                                .addGroup(paymentAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(studentNumberL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(paymentAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nameL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(paymentAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(surnameL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(paymentAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(gradeL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(paymentAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(totalL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(paymentAddLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)))
                        .addGap(36, 36, 36)
                        .addGroup(paymentAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(paidL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(paymentAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(balanceL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(paymentAddLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(paymentAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(paymentAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
        );

        paymentPane.addTab("Individual", paymentAdd);

        mainPane.addTab("Payments", paymentPane);

        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Students");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Payments");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        exitMenu.setText("Exit");
        exitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuActionPerformed(evt);
            }
        });
        jMenu1.add(exitMenu);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Initialize New Term");

        jMenuItem2.setText("Term 1");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem4.setText("Term 2");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("Term 3");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuActionPerformed
        // TODO add your handling code here:
        endSession();
        this.dispose();
    }//GEN-LAST:event_exitMenuActionPerformed
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        mainPane.setSelectedIndex(studentTab);
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int value = jFileChooser1.showOpenDialog(this);
        if (value == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser1.getSelectedFile();
            String fileName = file.toString();
            System.out.println(fileName);
            fileNameL.setText(fileName);
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private void gradeComboPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_gradeComboPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_gradeComboPropertyChange
    
    private void textSurnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textSurnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textSurnameActionPerformed
    
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // T add your handling code here:
        
        if (individualButton.isSelected()) {
            if (!textName.equals("") && !textName.equals("") && !gradeCombo.getSelectedItem().toString().equals("Select Grade") && buttonGroup3.getSelection()!=null) {
                
                if (fees1.isSelected()){
                    theManager.addToStudents(textName.getText(),textSurname.getText(), gradeCombo.getSelectedItem().toString(),theManager.getCost(gradeCombo.getSelectedItem().toString()));
                } else {
                    String fees = JOptionPane.showInputDialog("Please enter the tuition amount that the student will have to pay for the remainder of the TERM: ");
                    if (theManager.isNumeric(fees)){
                        double dFees = Double.parseDouble(fees);
                        theManager.addToStudents(textName.getText(),textSurname.getText(), gradeCombo.getSelectedItem().toString(),theManager.getCost(gradeCombo.getSelectedItem().toString()),dFees);
                    }
                    else {
                        JOptionPane.showMessageDialog(this,"Please enter corrent fee amount.");
                    }
                    
                }
                JOptionPane.showMessageDialog(this,"Student Added successfully");
                textName.setText("");
                textSurname.setText("");
                gradeCombo.setSelectedIndex(0);
            }
            else {
                JOptionPane.showMessageDialog(this,"Please enter all information pertaining to the student");
            }
        } else
            if (groupButton.isSelected()) {
                
                if (!fileNameL.getText().equals("Filename") && !gradeCombo2.getSelectedItem().toString().equals("Select Grade") ) {
                    ProgressForm pf = new ProgressForm();
                    pf.theMain();
                    Spreadsheet sheet = new Spreadsheet(fileNameL.getText(),gradeCombo2.getSelectedItem().toString());
                    String grade = gradeCombo2.getSelectedItem().toString();
                    for (int counter=0;counter<sheet.getTeachers().size();counter++) {
                        theManager.addToTeachers(grade, sheet.getTeachers().get(counter));
                    }
                    
                    System.out.println(sheet.length+"!!!!!!!!!!!!!!!!!!!!!");
                    theManager.addToClasses(grade, sheet.tuition, sheet.trip);
                    for (int index=0;index<sheet.length;index++) {
                        theManager.addToStudents(sheet.names.get(index), sheet.surnames.get(index),grade);
                    }
                    pf.close();
                }
                else {
                    JOptionPane.showMessageDialog(this,"Please enter all information pertaining to the List of student");
                }
                
            }
        
    }//GEN-LAST:event_addButtonActionPerformed
    
    private void studentComboViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentComboViewActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_studentComboViewActionPerformed
    
    public String setDate() {
        return yearCombo.getSelectedItem().toString()+"-"+date(monthCombo.getSelectedItem().toString())+"-"+dayCombo.getSelectedItem().toString();
    }
    
    public String date(String date)
    {
        if (date.equals("JAN")) {
            return "01";
        } else
            if (date.equals("FEB")) {
                return "02";
            }  else
                if (date.equals("MAR")) {
                    return "03";
                } else
                    if (date.equals("APR")) {
                        return "04";
                    } else
                        if (date.equals("MAY")) {
                            return "05";
                        } else
                            if (date.equals("JUN")) {
                                return "06";
                            } else
                                if (date.equals("JUL")) {
                                    return "07";
                                } else
                                    if (date.equals("AUG")) {
                                        return "08";
                                    } else
                                        if (date.equals("SEP")) {
                                            return "09";
                                        } else
                                            if (date.equals("OCT")) {
                                                return "10";
                                            }  else
                                                if (date.equals("NOV")) {
                                                    return "11";
                                                } else
                                                    if (date.equals("DEC")) {
                                                        return "12";
                                                    }
        return null;
    }
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (!accountCombo.getSelectedItem().toString().equalsIgnoreCase("Select Account") && theManager.isNumeric(amountL.getText()) && !dayCombo.getSelectedItem().toString().equals("Day") && !monthCombo.getSelectedItem().toString().equals("Month") && !yearCombo.getSelectedItem().toString().equals("Year")) {
            int reply = JOptionPane.showConfirmDialog(null, "Confirm Payment of E" +amountL.getText() +" by "+nameL.getText()+" "+surnameL.getText(), "Confirm",  JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION)
            {
                theManager.addPayment(studentNumberL.getText(),(-1 * Double.parseDouble(amountL.getText())),accountCombo.getSelectedItem().toString(),setDate());
                //dateL.setText(theManager.getCurrentDate().toString());
                ((DefaultTableModel)studentPaymentTable.getModel()).setRowCount(0);
                theManager.getStudentPayments(studentPaymentTable, studentNumberL.getText() );
                updateNumbers(studentNumberL.getText());
                JOptionPane.showMessageDialog(this,"Payment added successfully.","Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,"Please enter correct information for payment");
        }
        amountL.setText("");
        dayCombo.setSelectedIndex(0);
        monthCombo.setSelectedIndex(0);
        yearCombo.setSelectedIndex(0);
        accountCombo.setSelectedIndex(0);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:'
        mainPane.setSelectedIndex(paymentTab);
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed
    
    private void paymentViewFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_paymentViewFocusGained
        // TODO add your handling code here
        
    }//GEN-LAST:event_paymentViewFocusGained
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        theManager.getAllPayments(paymentsView);
        extraDate.setText("");
        extraAmount.setText("");
        extraNumber.setText("");
        extraAccount.setText("");
        extraName.setText("");
        extraSurname.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed
        
    private void executeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeButtonActionPerformed
        // TODO add your handling code here:
        String grade = studentComboView.getSelectedItem().toString();
        
        if(grade.equals("All") && ((DefaultTableModel)studentView.getModel()).getColumnCount()==6) {
            String [] columns = {"StudentID", "Name","Surname", "Total Due", "Paid", "Balance", "Grade"};
            ((DefaultTableModel)studentView.getModel()).setColumnIdentifiers(columns);
        } else
            if (!grade.equals("All") && ((DefaultTableModel)studentView.getModel()).getColumnCount() > 6) {
                ((DefaultTableModel)studentView.getModel()).setColumnCount(6);
            }
        ProgressForm pf = new ProgressForm();
        pf.theMain();
        
        teacherLabel.setText(theManager.getTeacher(grade));
        theManager.getStudents(studentView, grade);
        // ConnectionManager swing1= new ConnectionManager(studentView, grade, "gs");
        //swing1.execute();
        //swing1= new ConnectionManager(studentView, grade, "gc");
        //swing1.execute();
        theManager.getGradeCosts();
        //theManager.fillCosts(studentView, grade);
        ConnectionManager swing1= new ConnectionManager(studentView,pf, grade, "fc", termChoiceText);
        swing1.execute();
    }//GEN-LAST:event_executeButtonActionPerformed
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to permanently delete " +nameL.getText()+" "+surnameL.getText()+"\n This should only be done if the student does not return in the begiining of the year.", "Confirm Delete",  JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION)
        {
            theManager.deleteStudent(studentNumberL.getText());
            mainPane.setSelectedIndex(studentTab);
            executeButton.doClick();
        }
    }//GEN-LAST:event_jButton5ActionPerformed
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to block the account of " +nameL.getText()+" "+surnameL.getText()+"\n Please ensure they have finished paying their remaining school fees first.", "Confirm Account block",  JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION)
        {
        }
    }//GEN-LAST:event_jButton6ActionPerformed
    
    private void extraNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_extraNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_extraNameActionPerformed
    
    private void extraNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_extraNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_extraNumberActionPerformed
    
    private void jScrollPane4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jScrollPane4PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane4PropertyChange
    
    private void paymentsViewPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_paymentsViewPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentsViewPropertyChange
    
    private void buttonMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMActionPerformed
        // TODO add your handling code here:
        if (theManager.isNumeric(amountM.getText()) && !desM.getText().isEmpty()) {
            double amount = Double.parseDouble(amountM.getText());
            if (decreaseM.isSelected()) {
                amount = -amount;}
            int reply = JOptionPane.showConfirmDialog(null, "Confirm modification of " +amount +" on account of " +nameL.getText()+" "+surnameL.getText(), "Confirm",  JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION)
            {
                theManager.addPayment(studentNumberL.getText(), amount, "Admin: " + desM.getText());
                ((DefaultTableModel)studentPaymentTable.getModel()).setRowCount(0);
                theManager.getStudentPayments(studentPaymentTable, studentNumberL.getText() );
                updateNumbers(studentNumberL.getText());
                JOptionPane.showMessageDialog(this,"Account modified successfully.","Success", JOptionPane.INFORMATION_MESSAGE);
                
            }
            amountM.setText("");
            desM.setText("");
            buttonGroup2.clearSelection();
            
            
            
        }
    }//GEN-LAST:event_buttonMActionPerformed
    
    private void increaseMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseMActionPerformed
    
    private void balanceLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balanceLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_balanceLActionPerformed
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String payment = studentPaymentTable.getValueAt(studentPaymentTable.getSelectedRow(), 2).toString();
        
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to permanently delete transaction of "+payment+ " on the account of "+nameL.getText()+" "+surnameL.getText(), "Confirm Payment Delete",  JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION)
        {
            String paymentID = studentPaymentTable.getValueAt(studentPaymentTable.getSelectedRow(), 0).toString();
            theManager.deletePayment(paymentID);
            please(studentView, studentView.getValueAt(studentView.getSelectedRow(), 0).toString());
        }
    }//GEN-LAST:event_jButton4ActionPerformed
    
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        initializeTerm("Term 1");
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        initializeTerm("Term 2");
    }//GEN-LAST:event_jMenuItem4ActionPerformed
    
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        initializeTerm("Term 3");
    }//GEN-LAST:event_jMenuItem5ActionPerformed
    
    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed
    
    
    public void initializeTerm(String term) {
        String grade = studentComboView.getSelectedItem().toString();
        ProgressForm pf = new ProgressForm();
        pf.theMain();
        ConnectionManager swing1= new ConnectionManager(studentView,pf, grade, "it", term);
        swing1.execute();
        
    }
    
    /**
     * @param args the command line arguments
     */
    public void runMain() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Main(theManager).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox accountCombo;
    private javax.swing.JButton addButton;
    private javax.swing.JTextField amountL;
    private javax.swing.JTextField amountM;
    private javax.swing.JTextField balanceL;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton buttonM;
    private javax.swing.JLabel dateL;
    private javax.swing.JComboBox dayCombo;
    private javax.swing.JRadioButton decreaseM;
    private javax.swing.JTextField desM;
    private javax.swing.JButton executeButton;
    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JTextField extraAccount;
    private javax.swing.JTextField extraAmount;
    private javax.swing.JTextField extraDate;
    private javax.swing.JTextField extraGrade;
    private javax.swing.JTextField extraName;
    private javax.swing.JTextField extraNumber;
    private javax.swing.JTextField extraSurname;
    private javax.swing.JRadioButton fees1;
    private javax.swing.JRadioButton fees2;
    private javax.swing.JLabel fileNameL;
    private javax.swing.JComboBox gradeCombo;
    private javax.swing.JComboBox gradeCombo2;
    private javax.swing.JTextField gradeL;
    private javax.swing.JRadioButton groupButton;
    private javax.swing.JRadioButton increaseM;
    private javax.swing.JRadioButton individualButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane mainPane;
    private javax.swing.JComboBox monthCombo;
    private javax.swing.JTextField nameL;
    private javax.swing.JTextField paidL;
    private javax.swing.JPanel paymentAdd;
    private javax.swing.JTabbedPane paymentPane;
    private javax.swing.JPanel paymentView;
    private javax.swing.JTable paymentsView;
    private javax.swing.JPanel sAdd;
    private javax.swing.JPanel sView;
    private javax.swing.JComboBox studentComboView;
    private javax.swing.JTextField studentNumberL;
    private javax.swing.JTabbedPane studentPane;
    private javax.swing.JTable studentPaymentTable;
    private javax.swing.JTable studentView;
    private javax.swing.JTextField surnameL;
    private javax.swing.JLabel teacherLabel;
    private javax.swing.JTextField textName;
    private javax.swing.JTextField textSurname;
    private javax.swing.JTextField totalL;
    private javax.swing.JComboBox yearCombo;
    // End of variables declaration//GEN-END:variables
}
