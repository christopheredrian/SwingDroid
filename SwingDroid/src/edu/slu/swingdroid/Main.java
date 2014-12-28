package edu.slu.swingdroid;

import edu.slu.swingdroid.Subjects;
import edu.slu.swingdroid.ui.HomeUserInterface;
import edu.slu.swingdroid.ui.UIUtilities;
import edu.slu.swingdroid.ui.WelcomeUI;
import java.awt.Toolkit;

/**
 *
 * @author Chris
 */
public class Main {

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (Subjects.getInstance().getSubjects().isEmpty()) {
                    WelcomeUI dialog = new WelcomeUI(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0); // NOTE close connection here?
                        }
                    });
                    dialog.setVisible(true);
                } else {
                    HomeUserInterface frame = new HomeUserInterface();
                   // UIUtilities.setIconImage(frame); Not working
                    frame.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0); // NOTE close connection here?
                        }
                    });
                    frame.setVisible(true);
                }
            }
        });
    }
}
