package db.schema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Category {
    private int cID;
    private String cName;

    public Category(int cID, String cName) {
        this.cID = cID;
        this.cName = cName;
    }

    public void saveToDatabase(Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO category (cID, cName) VALUES (?, ?)");
            statement.setInt(1, cID);
            statement.setString(2, cName);
            statement.execute();
        }
        catch (SQLException e) {
            System.out.println("[Error]: " + e);
        }
    }
}
