package edu.slu.swingdroid.db.test;

import edu.slu.swingdroid.db.ConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author chris
 */
public class ConnectionManagerTest {

    public static void main(String[] args) {
        /* Instantiate connection */
        Connection conn = ConnectionManager.getInstance().getConnection();

        /* Checks if conn is successful */
        if (conn != null) {
            System.out.println("Successful connection!");
        } else {
            System.out.println("Connection Unsuccessful");
        }

        /* Query all the subjects in the subject table */
        String sql = "SELECT subjectName FROM subject";
        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);) {
            System.out.println("------------------");
            System.out.println("Subjects Table");
            while (rs.next()) {
                StringBuilder buffer = new StringBuilder();
                buffer.append(rs.getString("subjectName"));
                System.out.println(buffer.toString());
            }
            System.out.println("------------------");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
