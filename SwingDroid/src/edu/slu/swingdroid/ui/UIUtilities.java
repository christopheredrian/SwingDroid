package edu.slu.swingdroid.ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Chris
 */
public class UIUtilities {

    private UIUtilities() {
    }

    /**
     * Used for opening a file using explorer.exe
     *
     * @param file
     */
    public static void openFile(File file) {
        try {
            System.out.println(file.toPath().toAbsolutePath());
            Runtime.getRuntime().exec("explorer /select," + file.toPath().toAbsolutePath());
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Used for setting up the icon image
     *
     * @param frame
     */
    public static void setIconImage(JFrame frame) {
        try {
            URL iconURL = frame.getClass().getResource("/edu/slu/swingdroid/ui/myimageapp/swingdroidicon.png");
            ImageIcon icon = new ImageIcon(iconURL);
            frame.setIconImage(icon.getImage());
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static boolean validateSubjectInput(String input) {
        //"^[\\w]*$"
        if (input != null & input.matches("^[\\w]* *[\\w]*$") & !input.equalsIgnoreCase("")) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(validateSubjectInput("Programming  lab"));
        System.out.println(validateSubjectInput("theo xx"));
    }

}
