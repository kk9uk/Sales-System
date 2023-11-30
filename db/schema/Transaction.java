package db.schema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.sql.Date;

public class Transaction {
    private int tID;
    private int pID;
    private int sID;
    private Calendar tDate;

    public Transaction(int tID, int pID, int sID, Calendar tDate) {
        this.tID = tID;
        this.pID = pID;
        this.sID = sID;
        this.tDate = tDate;
    }

    public void saveToDatabase(Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO transaction (tID, pID, sID, tDate) VALUES (?, ?, ?, ?)");
            statement.setInt(1, tID);
            statement.setInt(2, pID);
            statement.setInt(3, sID);
            statement.setDate(4, new Date(tDate.getTimeInMillis()));
            statement.execute();
        }
        catch (SQLException e) {
            System.out.println("[Error]: " + e);
        }
    }
}
