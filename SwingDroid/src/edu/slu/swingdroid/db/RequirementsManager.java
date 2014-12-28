package edu.slu.swingdroid.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import edu.slu.swingdroid.Requirement;
import edu.slu.swingdroid.Subject;
import edu.slu.swingdroid.Type;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chris
 */
public class RequirementsManager {

    private static Connection connection = ConnectionManager.getInstance().getConnection();
    private static final Logger log = Logger.getLogger(RequirementsManager.class.getName());

    private RequirementsManager() {
    }

    /**
     * This method fetches a row from the Requirement and Attachment tables
     *
     * @param reqId Requirement id of the row
     * @return a Requirement object which represents the row
     * @throws SQLException
     */
    public static Requirement getRow(int reqId) throws SQLException {

        String sql = "SELECT * FROM requirement WHERE requirementId = ?";
        ResultSet rs = null;

        try (
                PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, reqId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                //System.out.println(rs.getString("dueDate"));
                String dateString = rs.getString("dueDate");
                rs.getString("subjectName");
                rs.getString("description");
                // Calendar of the current row
                Calendar cal = GregorianCalendar.getInstance();
                cal.setTimeInMillis(Long.parseLong(dateString));
                Requirement current = new Requirement(rs.getString("description"), cal,
                        determineType(rs.getString("type")), new Subject(rs.getString("subjectName")));
                //System.out.println(current.getSubject().getSubjectName());
                current.setActive(rs.getBoolean("active"));
                current.setRequirementId(rs.getInt("requirementId"));
                /* Insert attachments for current instance - if any */
                current.setAttachments(getAttachments(rs.getInt("requirementId")));
                return current;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, e.toString(), e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    /**
     * A method that returns a HashMap of all the rows in the attachment and
     * requirements table The key is the requirementId of each of the row and
     * the value is a Requirement object
     *
     * @return HashMap representative of all the rows
     */
    public static HashMap<Integer, Requirement> getAllRows() {
        HashMap<Integer, Requirement> toReturn = new HashMap<>();

        String sql = "SELECT requirementId, type, subjectName, dueDate, active, description FROM requirement";
        try (
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);) {
            //System.out.println("Requirements Table");

            while (rs.next()) {
                //System.out.println(rs.getString("dueDate"));
                String dateString = rs.getString("dueDate");
                rs.getString("subjectName");
                rs.getString("description");
                // Calendar of the current row
                Calendar cal = GregorianCalendar.getInstance();
                cal.setTimeInMillis(Long.parseLong(dateString));
                Requirement current = new Requirement(rs.getString("description"), cal,
                        determineType(rs.getString("type")), new Subject(rs.getString("subjectName")));
                //System.out.println(current.getSubject().getSubjectName());
                current.setActive(rs.getBoolean("active"));
                current.setRequirementId(rs.getInt("requirementId"));
                current.setAttachments(getAttachments(rs.getInt("requirementId")));
                toReturn.put(rs.getInt("requirementId"), current);

            }

        } catch (Exception e) {
            log.log(Level.SEVERE, e.toString(), e);
        }
        return toReturn;
    }

    /* A helper method for getting the attachments of a requirement Id from the database 
     used in getRow() and getAllRows methods */
    private static List<File> getAttachments(int id) throws SQLException { // NOTE: Handle Exception inside the method?
        List<File> attachments;
        /* Add attachments to current Requirement object */
        String attachmentQuery = "SELECT * FROM attachment WHERE requirementId = ?";
        ResultSet rs = null;
        attachments = new LinkedList<>();
        PreparedStatement stmt2 = connection.prepareStatement(attachmentQuery);
        stmt2.setInt(1, id);
        rs = stmt2.executeQuery();
        /* While the rs2 Resultset has an attachment append them to the newly created List */
        while (rs.next()) {
            attachments.add(new File(rs.getString("attachmentName")));
        }
        return attachments;
    }

    /* Helper method for getRow() and getAllRows() for converting String to equivalent Enum types */
    private static Type determineType(String determine) {
        switch (determine) {
            case "PROJECT":
                return Type.PROJECT;
            case "ACTIVITY":
                return Type.ACTIVITY;
            case "QUIZ":
                return Type.QUIZ;
            case "ASSIGNMENT":
                return Type.ASSIGNMENT;
            case "MEETING":
                return Type.MEETING;
            case "EXAM":
                return Type.EXAM;
            default:
                throw new RuntimeException("Type invalid!");
        }
    }

    /**
     * This method inserts a requirement object into the database
     *
     * @param bean the requirement to be inserted into the database
     * @return if the insertion is successful
     * @throws java.sql.SQLException
     */
    public static boolean insertRow(Requirement bean) throws SQLException {
        ResultSet keys = null;
        String sql = "INSERT INTO requirement (type, subjectName, dueDate, active, description) VALUES (?, ?, ?, ?, ?)";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, bean.getType().toString());
            stmt.setString(2, bean.getSubject().getSubjectName());
            stmt.setString(3, Long.toString(bean.getDueDate().getTimeInMillis()));
            stmt.setBoolean(4, bean.isActive());
            stmt.setString(5, bean.getDescription());

            int affected = stmt.executeUpdate();
            if (affected == 1) {
                keys = stmt.getGeneratedKeys();
                keys.next();
                int newKey = keys.getInt(1); // The auto-generated key.
                bean.setRequirementId(newKey);
            }
            if (bean.getAttachments().size() > 0) {
                // System.out.println("Adding atttachments");
                addAttachments(bean.getAttachments(), bean.getRequirementId());
            }

        } catch (SQLException e) {
            log.log(Level.WARNING, e.toString(), e);
            return false;
        } catch (Exception e) {
            log.log(Level.SEVERE, e.toString(), e);
        } finally {
            if (keys != null) {
                keys.close();
            }
        }
        return true;
    }

    /* Helper method for insertRow() method */
    private static void addAttachments(List<File> attachment, int reqId) throws SQLException {
        // NOTE: Not sure if return stmt is void/boolean
        for (File current : attachment) {
            String sql = "INSERT INTO attachment (attachmentName, requirementId) VALUES (?,?)";
            ResultSet keys = null;
            // NOTE: attachmentId is auto increment
            try (
                    PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
                // NOTE: This should be relative
                stmt.setString(1,  current.getName());
                stmt.setInt(2, reqId);
                int affected = stmt.executeUpdate();
                if (affected == 1) { // NOTE: Do we need this?
                    keys = stmt.getGeneratedKeys();
                    keys.next();
                    int newKey = keys.getInt(1); // The auto-generated key.
                }
            } catch (Exception e) {
                log.log(Level.SEVERE, e.toString(), e);
            }
        }

    }
    
    /* Helper method used by updateRow() to remove the attachments 
    associated with the given requirementId*/
    private static void removeAttachments(List<File> attachment, int requirementId) {
        String sql = "DELETE FROM attachment WHERE attachmentName = ? AND requirementId = ?";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
           for(File file : attachment){
            stmt.setString(1, file.getName());
            stmt.setInt(2, requirementId);
            int affected = stmt.executeUpdate();
            if (affected == 0) {
                System.out.println("No attachments removed");
            }
           }
        } catch (SQLException e) {
            log.log(Level.WARNING, e.toString(), e);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.toString(), e);
        } finally {
        }

    }

    /**
     * This method updates a row inside the Requirement and Attachment database
     *
     * @param bean the row to be updated
     * @return whether or not the update has been successful
     */
    public static boolean updateRow(Requirement bean) {
        try {
            String sql = "UPDATE requirement SET "
                    + "type = ?,"
                    + "subjectName = ?,"
                    + "dueDate = ?,"
                    + "active = ?,"
                    + "description = ? WHERE requirementId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //conn.setAutoCommit(false);
            preparedStatement.setString(1, bean.getType().toString());
            preparedStatement.setString(2, bean.getSubject().getSubjectName());
            preparedStatement.setString(3, Long.toString(bean.getDueDate().getTimeInMillis()));
            preparedStatement.setBoolean(4, bean.isActive());
            preparedStatement.setString(5, bean.getDescription());
            preparedStatement.setInt(6, bean.getRequirementId());
            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            List<File> currentAttachments = getAttachments(bean.getRequirementId());
            List<File> newAttachments = bean.getAttachments();

            List<File> toRemove = removeFromList(currentAttachments, newAttachments);
            List<File> toAdd = removeFromList(newAttachments, currentAttachments);
            removeAttachments(toRemove, bean.getRequirementId()); // Removing from the attachment database
            addAttachments(toAdd, bean.getRequirementId());    // Adding to the attachment database
            return true;
        } catch (SQLException e) {
            log.log(Level.WARNING, e.toString(), e);
            return false;
        } catch (Exception e) {
            log.log(Level.SEVERE, e.toString(), e);
            return false;
        }
    }

    private static List<File> removeFromList(List<File> file, List<File> toRemove) {
        List<File> temp = new ArrayList<>(file);
        temp.removeAll(toRemove);
        return temp;
    }

    /**
     * This method deletes a row inside the table, it is implemented such that
     * when you delete that row all of its corresponding attachment rows will
     * also be deleted
     *
     * @param bean The row to be deleted
     * @return whether or not the deletion has been successful
     */
    public static boolean deleteRow(Requirement bean) {
        String sql = "DELETE FROM requirement WHERE requirementId = ?";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setInt(1, bean.getRequirementId());
            int affected = stmt.executeUpdate();
            if (affected == 0) {
                throw new RuntimeException("Row #" + bean.getRequirementId() + " not found!");
            }
            return affected == 1;
        } catch (SQLException e) {
            log.log(Level.WARNING, e.toString(), e);
            return false;
        } catch (Exception e) {
            log.log(Level.SEVERE, e.toString(), e);
            return false;
        }
    }

}
