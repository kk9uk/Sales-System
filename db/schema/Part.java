package db.schema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Part {
    private int pID;
    private String pName;
    private int pPrice;
    private int mID;
    private int cID;
    private int pWarrantyPeriod;
    private int pAvailableQuantity;

    public Part(int pID, String pName, int pPrice, int mID, int cID, int pWarrantyPeriod, int pAvailableQuantity) {
        this.pID = pID;
        this.pName = pName;
        this.pPrice = pPrice;
        this.mID = mID;
        this.cID = cID;
        this.pWarrantyPeriod = pWarrantyPeriod;
        this.pAvailableQuantity = pAvailableQuantity;
    }

    public void saveToDatabase(Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO part (pID, pName, pPrice, mID, cID, pWarrantyPeriod, pAvailableQuantity) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, pID);
            statement.setString(2, pName);
            statement.setInt(3, pPrice);
            statement.setInt(4, mID);
            statement.setInt(5, cID);
            statement.setInt(6, pWarrantyPeriod);
            statement.setInt(7, pAvailableQuantity);
            statement.execute();
        }
        catch (SQLException e) {
            System.out.println("[Error]: " + e);
        }
    }
}
