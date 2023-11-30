package db.schema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Manufacturer {
    private int mID;
    private String mName;
    private String mAddress;
    private int mPhoneNumber;

    public Manufacturer(int mID, String mName, String mAddress, int mPhoneNumber) {
        this.mID = mID;
        this.mName = mName;
        this.mAddress = mAddress;
        this.mPhoneNumber = mPhoneNumber;
    }

    public void saveToDatabase(Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO manufacturer (mID, mName, mAddress, mPhoneNumber) VALUES (?, ?, ?, ?)");
            statement.setInt(1, mID);
            statement.setString(2, mName);
            statement.setString(3, mAddress);
            statement.setInt(4, mPhoneNumber);
            statement.execute();
        }
        catch (SQLException e) {
            System.out.println("[Error]: " + e);
        }
    }
}
