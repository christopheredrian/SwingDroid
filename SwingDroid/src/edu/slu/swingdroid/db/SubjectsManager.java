package edu.slu.swingdroid.db;

import edu.slu.swingdroid.Subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JanKenneth
 */
public class SubjectsManager {

    private static Connection conn = ConnectionManager.getInstance().getConnection();

    private SubjectsManager() {
    }

    /**
     * Gets all the contents of the subjects table in the database.
     *
     * @return Returns a list of the subjects inside the database.
     */
    public static List<Subject> getAllRows() {
        List<Subject> dbResult = new ArrayList<>();

        String sql = "SELECT * FROM subject";
        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);) {

            while (rs.next()) {
                dbResult.add(new Subject(rs.getString("subjectName")));

            }
        } catch (Exception e) {
            System.err.println(e);
        }

        return dbResult;

    }

    /**
     * Updates the subjects table in the database.
     *
     * @param toBeAdded The list of subjects to be added to the database.
     * @param toBeDeleted The list of subjects to be deleted to the database.
     * @return Returns true if the update is success.
     */
    public static boolean updateSubjects(List<Subject> toBeAdded, List<Subject> toBeDeleted) {
        //add some queries...

        int progressChecker = 0;

        List<Subject> currentSubjects = new ArrayList<>();

        String subjectsQuery = "SELECT * FROM subject";

        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(subjectsQuery);) {

            while (rs.next()) {
                currentSubjects.add(new Subject(rs.getString("subjectName")));
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        if (currentSubjects.contains(toBeDeleted)) {
            System.out.println("No subjects deleted");
            progressChecker += 1;
        } else {
            for (Subject current : toBeDeleted) {
                String sql = "DELETE FROM subject WHERE subjectName = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
                    stmt.setString(1, current.getSubjectName());
                    int affected = stmt.executeUpdate();
                    if (affected == 0) {
                        System.out.println("Subject not found!");
                    } else {
                        progressChecker += 1;
                        System.out.println("Delete Success!");
                    }

                } catch (SQLException e) {
                    System.err.println(e);
                }
                // System.out.println(current.getSubjectName()); //subjectName
            }
        }

        for (Subject newSubjs : toBeAdded) {
            String sql = "INSERT INTO subject (subjectName) VALUES(?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
                stmt.setString(1, newSubjs.getSubjectName());
                int affected = stmt.executeUpdate();
                if (affected == 0) {
                    System.out.println("Subject not added!");
                } else {
                    progressChecker += 1;
                    System.out.println("Add Success!");
                }

            } catch (SQLException e) {
                //    System.err.println(e);
            }
        }

        if (progressChecker != 2) {
            return false;
        }

        return true;

    }
}
