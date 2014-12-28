package edu.slu.swingdroid;

import edu.slu.swingdroid.db.SubjectsManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jan Kenneth
 */
public class Subjects {
    
    private static List <Subject> subjects;
    private static Subjects instance;
    
    private Subjects() {
        
    }
    
    /**
     * Gets the instance of a subject.
     * If the instance is null, it creates a new instance of subject.
     * @return Returns an instance of subject.
     */
    
    public static Subjects getInstance() {
        if(instance == null){
            instance = new Subjects();
            readDatabase();
            return instance;
        }else{
            readDatabase();
            return instance;
        }
        
    }
    
    /**
     * Reads all the content of the subject table in the database.
     */
    
    public static void readDatabase() {
        
       subjects = SubjectsManager.getAllRows();
        
    }
    
    /**
     * Updates the content of the subject table in the database.
     * @param newList List of the updated subjects.
     * @return True if the update is successful.
     * 
     */
    
    public boolean updateList(List <Subject> newList) {
        List<Subject> dbaseList;
        List<Subject> toBeDeleted;
        List<Subject> toBeAdded; 
        
        
        dbaseList = SubjectsManager.getAllRows();
        
        toBeDeleted = getDiff(dbaseList, newList);
        System.out.println(toBeDeleted);
        toBeAdded = getDiff(newList, dbaseList);
        System.out.println("To be added: " + toBeAdded);
        System.out.println("To be deleted: " + toBeDeleted);
        return SubjectsManager.updateSubjects(toBeAdded, toBeDeleted);
        
        
    }
    
    /**
     * Gets the difference between two lists.
     * @param minuend
     * @param subtrahend
     * @return The difference between the two lists.
     */
    
    private List<Subject> getDiff(List<Subject> minuend, List<Subject> subtrahend) {
        List<Subject> clone = new ArrayList(minuend);
        clone.removeAll(subtrahend);
        System.out.println("returned: " + clone);
        return clone;
    }
    
    /**
     * Gets the list of subjects.
     * @return Returns the list of subjects.
     */
    
    public List<Subject> getSubjects() {
        return subjects;
    }
    
   
    
    
}
