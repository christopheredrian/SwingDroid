package edu.slu.swingdroid.test;

import edu.slu.swingdroid.Subject;
import edu.slu.swingdroid.Subjects;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Chris
 */
public class SubjectsTest {
    
    private static Subjects subjects = Subjects.getInstance();
    
    public static void main(String[] args) {
        /* Prints all subjects */
        printSubjects(); // Working properly
        /* Updating the list of subjects */
        updatesubjects();
        printSubjects();
        
    }
    
    
    private static void printSubjects(){
        List<Subject> rows = subjects.getSubjects();
        for (Subject record : rows) {
            System.out.println(record.getSubjectName());
        }
    }
    private static void updatesubjects(){
        List<Subject> newSubjects = new LinkedList<>();
        newSubjects.add(new Subject("SMA"));
        newSubjects.add(new Subject("THEO3"));
        newSubjects.add(new Subject("PROGAPPS"));
        subjects.updateList(newSubjects);
    }
}
