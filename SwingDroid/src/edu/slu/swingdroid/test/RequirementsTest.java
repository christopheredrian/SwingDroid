
package edu.slu.swingdroid.test;

import edu.slu.swingdroid.Requirement;
import edu.slu.swingdroid.Requirements;
import edu.slu.swingdroid.Subject;
import edu.slu.swingdroid.Type;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Chris
 */
public class RequirementsTest {
    public static void main(String[] args) {
        Requirements myInstance = Requirements.getInstance();
        /* You can now use the myInstance variable to insert,update and delete rows inside the Requirements database */
        
        /* Row insertion */
        
        Calendar dueDate = Calendar.getInstance();
        dueDate.set(2014, Calendar.DECEMBER, 06); // year, month, year - this is December 06, 2014
        Requirement req = new Requirement("Take QUIZ  on programmig applications", dueDate , Type.QUIZ, new Subject("PROGAPPS")); // Required parameters 
        //req.setAttachments();    - This method to setAttachment - just pass a List of attachments names to it
        //req.addAttachment(null); - Add attachment to the req instance
        req.setActive(true); // This is to set the requirement to be active
        /* NOTE THAT REQUIREMENT ID IS AUTO-GENERATED, can be overriden using updateRow */
        myInstance.addRequirement(req); // This call will add a new row to the Requirement and Attachment table
        
        
        
        /* Getting all rows */
        HashMap<Integer, Requirement> allRows = myInstance.getRequirements();
        
        /* Displaying those rows */
        for (Map.Entry<Integer, Requirement> entrySet : allRows.entrySet()) {
            Integer key = entrySet.getKey();
            Requirement value = entrySet.getValue();
            //System.out.println(value.toString()); // This is not working properly YET for there is no toString() override for the Requirement class
            /* An alternative would be doing it manually */
            System.out.println(value.getRequirementId() +","+ value.getDescription() +","+  value.getDueDate().getTime() +","+ value.getSubject()+","+ value.getType());
        }
      
        /* Row deletion */
        try {
         myInstance.removeRequirement(28);
        } catch (RuntimeException e) {
            System.err.println("Row # not found...");
       }
        
        
        /***************************************************************************************************************************************/
        
        /* Getting all rows again to see the deleted requirement */
        allRows = myInstance.getRequirements();
        
        /* Displaying those rows */
        for (Map.Entry<Integer, Requirement> entrySet : allRows.entrySet()) {
            Integer key = entrySet.getKey();
            Requirement value = entrySet.getValue();
            //System.out.println(value.toString()); // This is not working properly YET for there is no toString() override for the Requirement class
            /* An alternative would be doing it manually */
            System.out.println(value.getRequirementId() +","+ value.getDescription() +","+  value.getDueDate().getTime() +","+ value.getSubject()+","+ value.getType());
        }
        
    }
}
