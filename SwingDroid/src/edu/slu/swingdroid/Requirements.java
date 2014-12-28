package edu.slu.swingdroid;

import edu.slu.swingdroid.db.RequirementsManager;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author Chris
 */
public class Requirements {

    private HashMap<Integer, Requirement> requirements = RequirementsManager.getAllRows();
    private static Requirements instance;
    private static boolean debugOn;

    private Requirements() {
    }

    private Requirements(boolean debugOn) {
        this.debugOn = debugOn;
    }

    /**
     * This method will return the singleton instance of this class and will
     * return the instance with debugOn option to true - which will print
     * command line information for methods
     *
     * @return a singleton instance of the class
     */
    public static Requirements getInstance() {
        return (instance == null ? instance = new Requirements(true) : instance);
    }

    /**
     * Returns the value of debugOn
     *
     * @return debugOn
     */
    public static boolean isDebugOn() {
        return debugOn;
    }

    /**
     * Setter for debugOn instance variable
     *
     * @param debugOn
     */
    public static void setDebugOn(boolean debugOn) {
        Requirements.debugOn = debugOn;
    }

    /**
     * This method will insert a row to the Requirement table and - if any - on
     * the Attachment table
     *
     * @param requirement the Requirement instance to be inserted
     */
    public void addRequirement(Requirement requirement) {
        //requirements.put(requirement.getRequirementId(), requirement);
        try {
            RequirementsManager.insertRow(requirement);
        } catch (SQLException e) {
            if (debugOn) {
                System.out.println("Exception caught in Requirements.addRequirement()\n Failed to insert a new Requirement...");
            } else {
                System.err.println("Exception caught in Requirements.addRequirement()\n Failed to insert a new Requirement...");
            }
        }
        sync();
    }

    /**
     *
     * @param newValue new Requirement value for that id
     * @param id requirementId to be updated/edit
     */
    public void editRequirement(Requirement newValue, int id) {
        //requirements.put(id, newValue);
        newValue.setRequirementId(id);
        boolean isSuccessful = RequirementsManager.updateRow(newValue);
        if (debugOn) {
            System.out.println(isSuccessful ? "Requirement # " + id + " was succesfully updated! " : "No requirement was updated");
        }
        sync();
    }

    /**
     * This method allows you to remove a row in the Requirement table using its
     * "requirementId" Note that if you remove the requirement it will also
     * remove all the attachments associated with it
     *
     * @param reqId id of the requirement to be removed
     */
    public void removeRequirement(int reqId) {
        //requirements.remove(reqId);
        Requirement toRemove = new Requirement(null, null, Type.EXAM, null);
        toRemove.setRequirementId(reqId);
        boolean isSuccessful = RequirementsManager.deleteRow(toRemove);// Add on deleteRow()
        if (debugOn) {
            System.out.println(isSuccessful ? "Requirement # " + reqId + " was succesfully deleted! " : "No requirement was updated");
        }
        sync();
    }

    /* Method that will sync the objects' instance of requirements to that of the database */
    private void sync() {
        requirements = RequirementsManager.getAllRows();
        if (debugOn) {
            System.out.println("Syncing Requirements...");
        }
    }

    /**
     * Getter for the requirement instance variable which holds all in memory
     * requirements
     *
     * @return instance of the requirements object
     */
    public HashMap<Integer, Requirement> getRequirements() {
        requirements = RequirementsManager.getAllRows();
        return requirements;
    }

}
