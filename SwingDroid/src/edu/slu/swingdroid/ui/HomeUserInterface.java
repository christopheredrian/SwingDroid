package edu.slu.swingdroid.ui;

import edu.slu.swingdroid.Requirement;
import edu.slu.swingdroid.Requirements;
import edu.slu.swingdroid.db.ConnectionManager;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXMonthView;
import org.jdesktop.swingx.sort.RowFilters;

/**
 *
 * @author Faelnar
 * @author Pambid
 * @author Espiritu
 * @author Garcia
 */
public class HomeUserInterface extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    private Connection conn;
    private Requirements requirements = Requirements.getInstance();

    /**
     * Creates new form HomeUserInterface
     */
    public HomeUserInterface() {
        UIUtilities.setIconImage(this);
        initComponents();
        initializeTopPane();
        syncTable(); // Get values from database
        allRadio.setSelected(true);
        initializeSearch();
    }
    /* Syncing values from database to table*/

    public void syncTable() {
        tableModel = (DefaultTableModel) table.getModel();
        clearRows();
        HashMap<Integer, Requirement> requirementsMap = requirements.getRequirements();
        for (Map.Entry<Integer, Requirement> entrySet : requirementsMap.entrySet()) {
            Integer key = entrySet.getKey();
            Requirement value = entrySet.getValue();
            tableModel.addRow(new Object[]{new SimpleDateFormat("yyyy-MM-dd").
                format(value.getDueDate().getTime()), value, value.getSubject()
                .getSubjectName(), value.getType(), (value.isActive()
                ? "Pending" : "Done"), value.getAttachments().size()});
            calendar.addFlaggedDates(value.getDueDate().getTime());
        }
    }

    private void clearRows() {
        tableModel.setRowCount(0);
        calendar.setFlaggedDates();
    }

    private void initializeSearch() {
        table.setRowFilter(RowFilters.regexFilter(Pattern.compile(searchField.getText())));
    }

    private void initializeTopPane() {
        Calendar now = Calendar.getInstance();
        timeField.setText(new SimpleDateFormat("hh:mm:ss aa").format(now.getTime()));
        dateField.setText(new SimpleDateFormat("EEEEEE, MMMMM, yyyy").format(now.getTime()));
        dayField.setText("" + now.get(Calendar.DAY_OF_MONTH));
        Timer t1;
        t1 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //timeField.repaint();
                Calendar now = Calendar.getInstance();
                timeField.setText(new SimpleDateFormat("hh:mm:ss aa").format(now.getTime()));
            }
        });
        t1.start();

    }

    private void filter() {
        List<RowFilter<Object, Object>> rfs = new ArrayList<>();
        /* Fiter for Active/Inactive */
        rfs.add(RowFilters.regexFilter(activeFilter(), 4));
        /* Filter for searchField */
        rfs.add(RowFilters.regexFilter(Pattern.compile(searchField.getText(),
                Pattern.CASE_INSENSITIVE)));
        String toCompile = parseSelectedRadioBtn(determineSelectedButton());
        rfs.add(RowFilters.regexFilter(Pattern.compile(toCompile, Pattern.CASE_INSENSITIVE), 0));
        RowFilter<Object, Object> af = RowFilter.andFilter(rfs);
        table.setRowFilter(af);
    }

    private String activeFilter() {
        return showDoneCheck.isSelected() ? "" : "Pending";
    }

    private String parseSelectedRadioBtn(String selected) {
        Calendar now = Calendar.getInstance();
        String year = Integer.toString(now.get(Calendar.YEAR));
        String month = Integer.toString(now.get(Calendar.MONTH) + 1);
        String day = Integer.toString(now.get(Calendar.DAY_OF_MONTH));
        String days7 = Integer.toString(now.get(Calendar.DAY_OF_MONTH) + 7);
        System.out.println("Month" + month);
        System.out.println(month + " " + day + " " + days7);
        switch (selected) {
            case "MONTH":
                return String.format("%s-%s", year, month);
            case "WEEK":
                return String.format("%s-%s-[%s...%s]", year, month, day, days7);     // not working        
            case "DAY":
                return String.format("%s-%s-%s", year, month, day);
            case "ALL":
                return "";
        }
        return "";
    }

    /* Helper method for method above */
    private String determineSelectedButton() {
        if (monthRadio.isSelected()) {
            return "MONTH";
        }
        if (weekRadio.isSelected()) {
            return "WEEK";
        }
        if (dayRadio.isSelected()) {
            return "DAY";
        }
        if (allRadio.isSelected()) {
            return "ALL";
        }
        return "INVALID";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        wrapper = new javax.swing.JPanel();
        scroller = new javax.swing.JScrollPane();
        table = new org.jdesktop.swingx.JXTable();
        calendar = new org.jdesktop.swingx.JXMonthView();
        filterPane = new javax.swing.JPanel();
        monthRadio = new javax.swing.JRadioButton();
        weekRadio = new javax.swing.JRadioButton();
        dayRadio = new javax.swing.JRadioButton();
        allRadio = new javax.swing.JRadioButton();
        showDoneCheck = new javax.swing.JCheckBox();
        optionsPane = new javax.swing.JPanel();
        addBtn = new org.jdesktop.swingx.JXButton();
        editBtn = new org.jdesktop.swingx.JXButton();
        deleteBtn = new org.jdesktop.swingx.JXButton();
        editSubjectsBtn = new org.jdesktop.swingx.JXButton();
        topPane = new javax.swing.JPanel();
        requirementsField = new javax.swing.JTextField();
        dateField = new javax.swing.JTextField();
        dayField = new javax.swing.JTextField();
        timeField = new javax.swing.JTextField();
        searchField = new org.jdesktop.swingx.JXSearchField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        wrapper.setBackground(new java.awt.Color(255, 255, 204));

        table.setBackground(new java.awt.Color(255, 255, 230));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Due Date", "Description", "Subject", "Type", "Status", "Attachments"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setToolTipText("Double click to edit, CTRL-F to find");
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setShowGrid(true);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        scroller.setViewportView(table);

        calendar.setBackground(new java.awt.Color(229, 255, 204));
        calendar.setFirstDisplayedDay(new java.util.Date(1416844800000L));
        calendar.setFocusCycleRoot(true);
        calendar.setInheritsPopupMenu(true);
        calendar.setRequestFocusEnabled(false);
        calendar.setTraversable(true);
        calendar.setVerifyInputWhenFocusTarget(false);
        calendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calendarActionPerformed(evt);
            }
        });

        filterPane.setBackground(new java.awt.Color(255, 255, 204));
        filterPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Filter by"));
        filterPane.setLayout(new java.awt.GridBagLayout());

        monthRadio.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(monthRadio);
        monthRadio.setText("Month");
        monthRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthRadioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(23, 12, 0, 0);
        filterPane.add(monthRadio, gridBagConstraints);

        weekRadio.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(weekRadio);
        weekRadio.setText("7 Days");
        weekRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weekRadioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 14, 0, 0);
        filterPane.add(weekRadio, gridBagConstraints);

        dayRadio.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(dayRadio);
        dayRadio.setText("Day");
        dayRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayRadioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(23, 0, 0, 0);
        filterPane.add(dayRadio, gridBagConstraints);

        allRadio.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(allRadio);
        allRadio.setText("All");
        allRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allRadioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        filterPane.add(allRadio, gridBagConstraints);

        showDoneCheck.setBackground(new java.awt.Color(255, 255, 204));
        showDoneCheck.setSelected(true);
        showDoneCheck.setText("Show Done");
        showDoneCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDoneCheckActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 14, 14, 6);
        filterPane.add(showDoneCheck, gridBagConstraints);

        optionsPane.setBackground(new java.awt.Color(255, 255, 204));
        optionsPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15), "Options"));
        optionsPane.setLayout(new java.awt.GridLayout(2, 1, 10, 20));

        addBtn.setBackground(new java.awt.Color(150, 229, 255));
        addBtn.setBorder(null);
        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        optionsPane.add(addBtn);

        editBtn.setBackground(new java.awt.Color(150, 229, 255));
        editBtn.setBorder(null);
        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        optionsPane.add(editBtn);

        deleteBtn.setBackground(new java.awt.Color(150, 229, 255));
        deleteBtn.setBorder(null);
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        optionsPane.add(deleteBtn);

        editSubjectsBtn.setBackground(new java.awt.Color(150, 229, 255));
        editSubjectsBtn.setBorder(null);
        editSubjectsBtn.setText("Subjects");
        editSubjectsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSubjectsBtnActionPerformed(evt);
            }
        });
        optionsPane.add(editSubjectsBtn);

        topPane.setBackground(new java.awt.Color(255, 255, 204));

        requirementsField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        requirementsField.setText("REQUIREMENTS");
        requirementsField.setBorder(null);
        requirementsField.setFocusable(false);
        requirementsField.setOpaque(false);
        requirementsField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requirementsFieldActionPerformed(evt);
            }
        });

        dateField.setBackground(new java.awt.Color(255, 193, 130));
        dateField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        dateField.setText("Thursday, October 2014");
        dateField.setBorder(null);
        dateField.setFocusable(false);
        dateField.setOpaque(false);
        dateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateFieldActionPerformed(evt);
            }
        });

        dayField.setBackground(new java.awt.Color(255, 255, 204));
        dayField.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        dayField.setText("25");
        dayField.setBorder(null);
        dayField.setFocusable(false);
        dayField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayFieldActionPerformed(evt);
            }
        });

        timeField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        timeField.setText("7:08 AM");
        timeField.setBorder(null);
        timeField.setFocusable(false);
        timeField.setOpaque(false);
        timeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeFieldActionPerformed(evt);
            }
        });

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout topPaneLayout = new javax.swing.GroupLayout(topPane);
        topPane.setLayout(topPaneLayout);
        topPaneLayout.setHorizontalGroup(
            topPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPaneLayout.createSequentialGroup()
                .addGroup(topPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topPaneLayout.createSequentialGroup()
                        .addComponent(dayField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dateField, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                        .addGap(32, 32, 32)
                        .addComponent(timeField, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(topPaneLayout.createSequentialGroup()
                        .addComponent(requirementsField, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(searchField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        topPaneLayout.setVerticalGroup(
            topPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dayField, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(timeField, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(topPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(requirementsField, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout wrapperLayout = new javax.swing.GroupLayout(wrapper);
        wrapper.setLayout(wrapperLayout);
        wrapperLayout.setHorizontalGroup(
            wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapperLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(wrapperLayout.createSequentialGroup()
                        .addComponent(filterPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(optionsPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, wrapperLayout.createSequentialGroup()
                        .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(topPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(scroller))
                        .addGap(1, 1, 1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        wrapperLayout.setVerticalGroup(
            wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapperLayout.createSequentialGroup()
                .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(wrapperLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(calendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(wrapperLayout.createSequentialGroup()
                        .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(wrapperLayout.createSequentialGroup()
                                .addGap(175, 175, 175)
                                .addComponent(scroller, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(wrapperLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(topPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 299, Short.MAX_VALUE)))
                        .addGap(10, 10, 10)
                        .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(filterPane, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(optionsPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(11, 11, 11))
        );

        getContentPane().add(wrapper);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void requirementsFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requirementsFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_requirementsFieldActionPerformed

    private void dateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateFieldActionPerformed

    private void monthRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthRadioActionPerformed
        filter();
    }//GEN-LAST:event_monthRadioActionPerformed

    private void allRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allRadioActionPerformed
        filter();
    }//GEN-LAST:event_allRadioActionPerformed

    private void timeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeFieldActionPerformed

    private void dayFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dayFieldActionPerformed

    private void dayRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayRadioActionPerformed
        filter();

    }//GEN-LAST:event_dayRadioActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        filter();
    }//GEN-LAST:event_searchFieldActionPerformed
//    );    }
    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        editRequirement();
    }//GEN-LAST:event_editBtnActionPerformed

    private void editRequirement() {
        try {
            tableModel = (DefaultTableModel) table.getModel();
            //System.out.println("Row count: " + table.getRowCount() + "Row selected: " + table.getSelectedRow());
            Requirement toEdit = (Requirement) table.getValueAt(table.getSelectedRow(), 1); // NOTE
            new EditRequirements(toEdit, this).setVisible(true);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Please select a row to edit...", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Please select a row to edit...", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed

        if (table.getSelectedRow() <= -1) {
            JOptionPane.showMessageDialog(this, "Please select the row to remove...",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to"
                    + "remove the requirement?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                Requirement toDelete = (Requirement) tableModel.getValueAt(table.getSelectedRow(), 1);
                File filesDirectory = new File("files");
                if (!filesDirectory.exists()) {
                    filesDirectory.mkdir();
                }

                if (!toDelete.getAttachments().isEmpty() || toDelete.getAttachments() != null) {
                    for (File attachment : toDelete.getAttachments()) {
                        File current = new File(filesDirectory, attachment.getName());
                        try {
                            Files.deleteIfExists(current.toPath());
                        } catch (IOException ex) {
                            System.err.println(ex);
                        }
                    }
                }
                requirements.removeRequirement(toDelete.getRequirementId());
            }
            syncTable();
        }

    }//GEN-LAST:event_deleteBtnActionPerformed

    private void weekRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weekRadioActionPerformed
        filter();
    }//GEN-LAST:event_weekRadioActionPerformed

    private void editSubjectsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSubjectsBtnActionPerformed
        new SubjectUI(this).setVisible(true);
    }//GEN-LAST:event_editSubjectsBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        new AddRequirementUI(this).setVisible(true);
    }//GEN-LAST:event_addBtnActionPerformed

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
        // TODO add your handling code here:
        // displayQuery(searchField.getText());
    }//GEN-LAST:event_searchFieldKeyReleased

    private void showDoneCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDoneCheckActionPerformed
        filter();
    }//GEN-LAST:event_showDoneCheckActionPerformed

    private void calendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calendarActionPerformed
        JTextArea textArea = new JTextArea();
        Calendar selectedCalendar = Calendar.getInstance();
        selectedCalendar.setTime(((JXMonthView) evt.getSource()).getSelectionDate());
        HashMap<Integer, Requirement> temp = requirements.getRequirements();
        List<Requirement> toDisplay = new LinkedList<>();
        for (Map.Entry<Integer, Requirement> entrySet : temp.entrySet()) {
            Integer key = entrySet.getKey();
            Requirement value = entrySet.getValue();
            //  System.out.println(selectedCalendar.get(Calendar.MONTH));
            if (value.getDueDate().get(Calendar.DAY_OF_YEAR)
                    == selectedCalendar.get(Calendar.DAY_OF_YEAR)) {
                toDisplay.add(value);
            }
        }
        
        for (Requirement row : toDisplay) {
            textArea.append("\nSubject: " + row.getSubject() + "\n"
                    + "Description: " + row.getDescription() + "\n"
                    + "Type: " + row.getType() + "\n"
                    + "\n-------------------------------------------------------------------------");
        }
        
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textArea.setLineWrap(true);
        scrollPane.requestFocus();
        textArea.requestFocusInWindow();
        scrollPane.setPreferredSize(new Dimension(600, 300));
        //[153,204,255]

        UIManager UI = new UIManager();
        UI.put("OptionPane.background", new ColorUIResource(153, 204, 255));
        UI.put("Panel.background", new ColorUIResource(153, 204, 255));
        JOptionPane.showMessageDialog(
                this, scrollPane,
                "Day requirements", JOptionPane.PLAIN_MESSAGE);
        String info = textArea.getText();
    }//GEN-LAST:event_calendarActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        if (evt.getClickCount() == 2) {
            editRequirement();
        }
    }//GEN-LAST:event_tableMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeUserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeUserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeUserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeUserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomeUserInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXButton addBtn;
    private javax.swing.JRadioButton allRadio;
    private javax.swing.ButtonGroup buttonGroup1;
    private org.jdesktop.swingx.JXMonthView calendar;
    private javax.swing.JTextField dateField;
    private javax.swing.JTextField dayField;
    private javax.swing.JRadioButton dayRadio;
    private org.jdesktop.swingx.JXButton deleteBtn;
    private org.jdesktop.swingx.JXButton editBtn;
    private org.jdesktop.swingx.JXButton editSubjectsBtn;
    private javax.swing.JPanel filterPane;
    private javax.swing.JRadioButton monthRadio;
    private javax.swing.JPanel optionsPane;
    private javax.swing.JTextField requirementsField;
    private javax.swing.JScrollPane scroller;
    private org.jdesktop.swingx.JXSearchField searchField;
    private javax.swing.JCheckBox showDoneCheck;
    private org.jdesktop.swingx.JXTable table;
    private javax.swing.JTextField timeField;
    private javax.swing.JPanel topPane;
    private javax.swing.JRadioButton weekRadio;
    private javax.swing.JPanel wrapper;
    // End of variables declaration//GEN-END:variables
}
