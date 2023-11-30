package db.schema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Salesperson {
    private int sID;
    private String sName;
    private String sAddress;
    private int sPhoneNumber;
    private int sExperience;

    public Salesperson(int sID, String sName, String sAddress, int sPhoneNumber, int sExperience) {
        this.sID = sID;
        this.sName = sName;
        this.sAddress = sAddress;
        this.sPhoneNumber = sPhoneNumber;
        this.sExperience = sExperience;
    }

    public void saveToDatabase(Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO salesperson (sID, sName, sAddress, sPhoneNumber, sExperience) VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, sID);
            statement.setString(2, sName);
            statement.setString(3, sAddress);
            statement.setInt(4, sPhoneNumber);
            statement.setInt(5, sExperience);
            statement.execute();
        }
        catch (SQLException e) {
            System.out.println("[Error]: " + e);
        }
    }
}
