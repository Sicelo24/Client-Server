/**
 * Event.java
 * This class creates and stores the attendee details in a Derby database
 * @author Sicelo Zitha (216140943)
 * Date: 28 September 2020
 */

package eventregistration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Event {
    static final String DATABASE_URL = "jdbc:derby://localhost:1527/Sunshine";
    private final String username = "Administrator";
    private final String password = "Password";
    
    private String studentNumber;
    private String firstName;
    private String lastName;
    private String programme;
    private String hasResources;
    
    // Constructors
    public Event(String studentNumber) {
        this.studentNumber = studentNumber;
    }
    
    public Event(String studentNumber, String firstName, String lastName, String programme, String hasResources) {
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.programme = programme;
        this.hasResources = hasResources;
    }

    // Getters
    public String getStudentNumber() {
        return studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProgramme() {
        return programme;
    }

    public String getHasResources() {
        return hasResources;
    }
 
    public boolean isUniqueStudentNumber() {
        Connection connection = null;   
        PreparedStatement statement = null;
        String sql = "SELECT StudentNumber FROM Event WHERE StudentNumber = ?";
        ResultSet resultSet = null;     
        boolean unique = true;
        
        try {
            connection = DriverManager.getConnection(DATABASE_URL, "Administrator", "Password");
            statement = connection.prepareStatement(sql);
            statement.setString(1, studentNumber);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                unique = false;     // duplicate student number found
            }    
        }
        catch(SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error: " + sqlException);
        }
        finally {
            // Method 1
            try {
                if (resultSet != null)
                    resultSet.close();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
            }
            try {
                if (statement != null)
                    statement.close();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
            }
            try {
                if (connection != null)
                    connection.close();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
            }
        }
            
        return unique;
    }
    
    public void save() {
        Connection connection = null;   // manage connections
        Statement statement = null;     // query statement
        int ok; 

        try {
            connection = DriverManager.getConnection(DATABASE_URL, "Administrator", "Password");
            statement = connection.createStatement();    
            ok = statement.executeUpdate("INSERT INTO Event VALUES('" + studentNumber + "', '"
                                                + firstName + "', '" 
                                                + lastName + "', '" 
                                                + programme + "', '" 
                                                + hasResources + "')");
            if (ok > 0) {
                JOptionPane.showMessageDialog(null, "Success! The information has been updated.");
            }
            else {
                JOptionPane.showMessageDialog(null, "Error: Could not update information.");
            }
        }
        catch(SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error: Could not update. " + sqlException);
        }
        finally {
            // Method 1
            try {
                if (statement != null)
                    statement.close();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
            }
            try {
                if (connection != null)
                    connection.close();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
            }
        }
    }  
}
			