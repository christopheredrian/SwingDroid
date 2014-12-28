
package edu.slu.swingdroid.db.test;

import edu.slu.swingdroid.Requirement;
import edu.slu.swingdroid.Subject;
import edu.slu.swingdroid.Type;
import edu.slu.swingdroid.db.RequirementsManager;
import java.io.File;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Chris
 */
public class RequirementsManagerTest {
        public static void main(String[] args) {

        try {
            //Runtime.getRuntime().exec("explorer.exe /select," + "C:"); // To open a file path using explorer
            testGetAllRow();
//            // testRemoveRow();
            //testInsertRow();
            testUpdateRow();
            testGetAllRow();

            //  System.out.println(getRow(19).toString());
            //  System.out.println(Calendar.getInstance().getTimeInMillis());

            //HashMap<Integer, Requirement> req = RequirementsManager.getAllRows();
            //System.out.println(req.toString());

        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    private static void testRemoveRow() {
        Requirement newReq = new Requirement("updated desc again", Calendar.getInstance(), Type.ACTIVITY, new Subject("SMA"));
        newReq.setRequirementId(22);
        RequirementsManager.deleteRow(newReq);
    }

    private static void testUpdateRow() {
        Requirement newReq = new Requirement("Exam for something", Calendar.getInstance(), Type.EXAM, new Subject("THEO3"));
        newReq.setActive(true);
        newReq.addAttachment(new File("Exam11454.pdf"));
     //  newReq.addAttachment(new File("SacramentsProg.pdf"));
        //newReq.addAttachment(new File("Deliverables.pdf"));
        //new File("test.txt");
        newReq.setRequirementId(40);
        RequirementsManager.updateRow(newReq);
        //updateRowBETA(newReq)

    }

    private static void testInsertRow() {
        try {
            Calendar toSet = Calendar.getInstance();
            toSet.set(2015, 0, 23);
            Requirement toInsert = new Requirement("Assignment", toSet, Type.EXAM, new Subject("THEO3"));
            toInsert.addAttachment(new File("hello.txt"));
            toInsert.addAttachment(new File("god.txt"));
            toInsert.addAttachment(new File("Sacraments.pdf"));
            toInsert.addAttachment(new File("Sacraments2.pdf"));
            RequirementsManager.insertRow(toInsert);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    private static void testGetAllRow() {
        HashMap<Integer, Requirement> req = RequirementsManager.getAllRows();
        for (Map.Entry<Integer, Requirement> entrySet : req.entrySet()) {
            Integer key = entrySet.getKey();
            Requirement value = entrySet.getValue();
            System.out.println(value);
        }
    }
}
