package edu.slu.swingdroid.db.test;

import edu.slu.swingdroid.Subject;
import edu.slu.swingdroid.Subjects;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jan Kenneth
 */
public class SubjectsTest {
    public static void main(String[] args) {
        
        /* returns all the subjects in the database. */
        Subjects subj = Subjects.getInstance();
        
        List<Subject> rows = subj.getSubjects();
        
        for (Subject row : rows) {
            System.out.println(row.getSubjectName()); 
        }
        
        List<Subject> newSubjects = new LinkedList<>();
        newSubjects.add(new Subject("SMA"));
        newSubjects.add(new Subject("PROGAPPS"));
        newSubjects.add(new Subject("THEO3"));
        /* Uncomment these part if you want to add WEBTEK and FLIGHT101 subject. */
//      newSubjects.add(new Subject("WEBTEK")); 
//      newSubjects.add(new Subject("FLIGHT101"));
        
        if (subj.updateList(newSubjects) == false){
            System.out.println("No new subjects.");
        }else{
            System.out.println("Hurray!");
        }
        
    }
}
