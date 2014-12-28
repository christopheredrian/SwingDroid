package edu.slu.swingdroid.ui;

import edu.slu.swingdroid.Requirement;
import edu.slu.swingdroid.Requirements;
import edu.slu.swingdroid.Subject;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aspuria, Jeric
 * @author Espiritu, Christopher
 */
public class EditRequirements extends javax.swing.JFrame {

    private Requirements myInstance = Requirements.getInstance();
    private Requirement requirementToEdit;
    private JFrame parent;
    private DefaultTableModel model;

    private boolean editMode;

    /**
     * Creates new form EditRequirements
     *
     * @param requirement
     * @param parent
     */
    public EditRequirements(Requirement requirement, JFrame parent) {
        UIUtilities.setIconImage(this);
        this.requirementToEdit = requirement;
        this.parent = parent;
        editMode = true;
        parent.setVisible(false);
        initComponents();
        dateChooser.getDateEditor().setEnabled(false);
        setTypes();
        initializeValues();
    }

    private void initializeValues() {
        subjectNames.getModel().setSelectedItem(requirementToEdit.getSubject().getSubjectName());
        subjectNames.setEnabled(false);
        descriptionBox.setText(requirementToEdit.getDescription());
        type.getModel().setSelectedItem(requirementToEdit.getType());
        dateChooser.setCalendar(requirementToEdit.getDueDate());
        checkBox.setSelected(requirementToEdit.isActive());
        initializeAttachments(requirementToEdit.getAttachments());

    }

    private void initializeAttachments(List<File> attachments) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        for (File attachment : attachments) {
            tableModel.addRow(new Object[]{new File("files", attachment.getName()).getAbsoluteFile()});
        }
        table.setModel(tableModel);
    }
    /* Helper method for Constructor */

    private void setTypes() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) type.getModel();
        model.addElement(edu.slu.swingdroid.Type.ACTIVITY);
        model.addElement(edu.slu.swingdroid.Type.ASSIGNMENT);
        model.addElement(edu.slu.swingdroid.Type.EXAM);
        model.addElement(edu.slu.swingdroid.Type.MEETING);
        model.addElement(edu.slu.swingdroid.Type.PROJECT);
        model.addElement(edu.slu.swingdroid.Type.QUIZ);
    }
    /* Helper method for determining the type of a given String */

    private static edu.slu.swingdroid.Type determineType(String determine) {
        switch (determine) {
            case "PROJECT":
                return edu.slu.swingdroid.Type.PROJECT;
            case "ACTIVITY":
                return edu.slu.swingdroid.Type.ACTIVITY;
            case "QUIZ":
                return edu.slu.swingdroid.Type.QUIZ;
            case "ASSIGNMENT":
                return edu.slu.swingdroid.Type.ASSIGNMENT;
            case "MEETING":
                return edu.slu.swingdroid.Type.MEETING;
            case "EXAM":
                return edu.slu.swingdroid.Type.EXAM;
            default:
                throw new RuntimeException("Type invalid!");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        wrapper = new javax.swing.JPanel();
        leftPane = new javax.swing.JPanel();
        description = new javax.swing.JLabel();
        type = new javax.swing.JComboBox();
        scroll = new javax.swing.JScrollPane();
        descriptionBox = new javax.swing.JTextArea();
        dueDate = new javax.swing.JLabel();
        subjectNames = new javax.swing.JComboBox();
        dateChooser = new com.toedter.calendar.JDateChooser();
        subject = new javax.swing.JLabel();
        requirementType = new javax.swing.JLabel();
        checkBox = new javax.swing.JCheckBox();
        rightPane = new javax.swing.JPanel();
        scroller = new javax.swing.JScrollPane();
        table = new org.jdesktop.swingx.JXTable();
        addAttachmentBtn = new javax.swing.JButton();
        removeBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        wrapper.setBackground(new java.awt.Color(229, 255, 204));
        wrapper.setBorder(javax.swing.BorderFactory.createTitledBorder("Edit Requirement"));

        leftPane.setBackground(new java.awt.Color(255, 255, 204));
        leftPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Details"));

        description.setText("Description");

        type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeActionPerformed(evt);
            }
        });

        descriptionBox.setBackground(new java.awt.Color(255, 255, 230));
        descriptionBox.setColumns(20);
        descriptionBox.setLineWrap(true);
        descriptionBox.setRows(5);
        descriptionBox.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                descriptionBoxAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        scroll.setViewportView(descriptionBox);

        dueDate.setText("Due Date");

        subjectNames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subjectNamesActionPerformed(evt);
            }
        });

        subject.setText("Subject");

        requirementType.setText("Type");

        checkBox.setBackground(new java.awt.Color(255, 255, 204));
        checkBox.setSelected(true);
        checkBox.setText("Pending");
        checkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout leftPaneLayout = new javax.swing.GroupLayout(leftPane);
        leftPane.setLayout(leftPaneLayout);
        leftPaneLayout.setHorizontalGroup(
            leftPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftPaneLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(leftPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(description)
                            .addComponent(subject))
                        .addGap(18, 18, 18)
                        .addGroup(leftPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(subjectNames, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)))
                    .addGroup(leftPaneLayout.createSequentialGroup()
                        .addGroup(leftPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(requirementType)
                            .addComponent(dueDate))
                        .addGap(33, 33, 33)
                        .addGroup(leftPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkBox))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        leftPaneLayout.setVerticalGroup(
            leftPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(subjectNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subject))
                .addGap(18, 18, 18)
                .addGroup(leftPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(description)
                    .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(leftPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(requirementType))
                .addGap(18, 18, 18)
                .addGroup(leftPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dueDate))
                .addGap(18, 18, 18)
                .addComponent(checkBox)
                .addGap(25, 25, 25))
        );

        rightPane.setBackground(new java.awt.Color(255, 255, 204));
        rightPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Attachments"));
        rightPane.setMinimumSize(new java.awt.Dimension(401, 272));

        table.setBackground(new java.awt.Color(255, 255, 230));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Double click to open file directory"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        scroller.setViewportView(table);

        addAttachmentBtn.setText("Add Attachment");
        addAttachmentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAttachmentBtnActionPerformed(evt);
            }
        });

        removeBtn.setText("Remove");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rightPaneLayout = new javax.swing.GroupLayout(rightPane);
        rightPane.setLayout(rightPaneLayout);
        rightPaneLayout.setHorizontalGroup(
            rightPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPaneLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(addAttachmentBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addGap(40, 40, 40)
                .addComponent(removeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addGap(49, 49, 49))
            .addGroup(rightPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroller)
                .addContainerGap())
        );
        rightPaneLayout.setVerticalGroup(
            rightPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroller, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(rightPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addAttachmentBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(removeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout wrapperLayout = new javax.swing.GroupLayout(wrapper);
        wrapper.setLayout(wrapperLayout);
        wrapperLayout.setHorizontalGroup(
            wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, wrapperLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152)
                .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, wrapperLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(leftPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rightPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        wrapperLayout.setVerticalGroup(
            wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapperLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(leftPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rightPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(wrapper);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeActionPerformed

    private void descriptionBoxAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_descriptionBoxAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_descriptionBoxAncestorAdded

    private void subjectNamesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subjectNamesActionPerformed

    }//GEN-LAST:event_subjectNamesActionPerformed

    private void addAttachmentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAttachmentBtnActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        File selected = fileChooser.getSelectedFile();
        model = (DefaultTableModel) table.getModel();
        for (Object row : model.getDataVector()) {
            Vector current = (Vector) row;
            File file = (File) current.get(0);
            if (file.getAbsolutePath().equalsIgnoreCase(selected.getAbsolutePath())) {
                JOptionPane.showMessageDialog(this, "Duplicate file entry, please"
                        + " rename the file...", "Error", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException("Duplicate file entry...");
            }

        }
        // if(selected.getName())
        //        Files.exists(null, Link);
        File filesDirectory = new File("files");
        if (!filesDirectory.exists()) {
            filesDirectory.mkdir();
        }
        List<File> filesInside = new ArrayList<>(Arrays.asList(filesDirectory.listFiles()));
        if (filesInside.contains(new File(filesDirectory, selected.getName()))) {
            JOptionPane.showMessageDialog(this, "Duplicate file entry, please"
                    + " rename the file...", "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Duplicate file entry...");
        } else {
            model = (DefaultTableModel) table.getModel();
            model.addRow(new Object[]{selected.getAbsoluteFile()});
        }
    }//GEN-LAST:event_addAttachmentBtnActionPerformed

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed
        removeAttachments();
    }//GEN-LAST:event_removeBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        try {
            if (dateChooser.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Please choose a due date", "Error",
                        JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException("Date cannot be null...");
            } else { // We add the requirement
                requirementToEdit.setActive(checkBox.isSelected());
                requirementToEdit.setDescription(descriptionBox.getText());
                requirementToEdit.setDueDate(dateChooser.getCalendar());
                requirementToEdit.setType(determineType(type.getSelectedItem().toString()));
                requirementToEdit.setSubject(new Subject(subjectNames.getSelectedItem().toString()));
                addAttachments(requirementToEdit);
                Requirements.getInstance().editRequirement(requirementToEdit, requirementToEdit.getRequirementId());

                this.dispose();
                HomeUserInterface homeUI = (HomeUserInterface) parent;
                homeUI.syncTable();
                parent.setVisible(true);
            }
        } catch (RuntimeException e) {
            System.err.println(e);
        } catch (Exception e) {
            System.err.println(e);
        }

        //        parent.setVisible(true);
        //        this.dispose();
    }//GEN-LAST:event_saveBtnActionPerformed
    private void removeAttachments() {
        try {
            //editMode = requirement.getAttachments().isEmpty()? true : false;
            model = (DefaultTableModel) table.getModel();
            if (editMode) {
                File filesDirectory = new File("files");
                if (!filesDirectory.exists()) {
                    filesDirectory.mkdir();
                }
                File tableValue = (File) table.getValueAt(table.getSelectedRow(), 0);
                System.out.println(tableValue.getAbsoluteFile());
                File toDelete = new File(filesDirectory, tableValue.getName());
                System.out.println("toDelete: " + toDelete);
                try {
                    System.out.println(requirementToEdit.getAttachments().
                            indexOf(new File(filesDirectory, toDelete.getName())));
                    System.out.println(new File(filesDirectory, toDelete.getName()));
                    requirementToEdit.removeAttachment(requirementToEdit.getAttachments().
                            indexOf(new File(toDelete.getName())));
                    Files.deleteIfExists(toDelete.toPath());
                    System.out.println(table.getSelectedRow());
                    model.removeRow(table.getSelectedRow());
                    System.out.println(requirementToEdit.getAttachments().toString());
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            } else {
                model.removeRow(table.getSelectedRow());
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Please select the row to remove...", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void addAttachments(Requirement requirement) {
        try {
            File filesDirectory = new File("files");
            if (!filesDirectory.exists()) {
                filesDirectory.mkdir();
            }
            if (model == null) {
                return;
            }
            Vector vector = model.getDataVector();
            for (Object object : vector) {
                Vector current = (Vector) object;
                File converted = (File) current.get(0);
                try {
                    File toStore = new File(filesDirectory, "" + converted.getName());
                    Files.copy(converted.toPath(), toStore.toPath());
                    requirement.addAttachment(new File(converted.getName()));
                } catch (FileAlreadyExistsException ex) {
                    JOptionPane.showMessageDialog(this, "Duplicate file entry, please"
                            + " rename the file...", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            // JOptionPane.showMessageDialog(this, "No attachments were added...", "Inform", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        this.dispose();
        parent.setVisible(true);
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        if (evt.getClickCount() == 2) {
            File toOpen = (File) table.getValueAt(table.getSelectedRow(), 0);
            UIUtilities.openFile(toOpen);
        }
    }//GEN-LAST:event_tableMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        parent.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void checkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditRequirements.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditRequirements.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditRequirements.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditRequirements.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EditRequirements(null, null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAttachmentBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JCheckBox checkBox;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel description;
    private javax.swing.JTextArea descriptionBox;
    private javax.swing.JLabel dueDate;
    private javax.swing.JPanel leftPane;
    private javax.swing.JButton removeBtn;
    private javax.swing.JLabel requirementType;
    private javax.swing.JPanel rightPane;
    private javax.swing.JButton saveBtn;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JScrollPane scroller;
    private javax.swing.JLabel subject;
    private javax.swing.JComboBox subjectNames;
    private org.jdesktop.swingx.JXTable table;
    private javax.swing.JComboBox type;
    private javax.swing.JPanel wrapper;
    // End of variables declaration//GEN-END:variables

    private void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}