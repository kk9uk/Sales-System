package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Database {
    private static final String dbAddress = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db1?autoReconnect=true&useSSL=false";
    private static final String dbUsername = "Group1";
    private static final String dbPassword = "CSCI3170";
    private static final String[] tableNames = {"category", "manufacturer", "part", "salesperson", "transaction"};

    private Connection conn = null;
    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.conn = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
    }

    // ====== ADMIN OPERATIONS =======
    public void createAllTables() throws SQLException {
        PreparedStatement[] stmts = {
                conn.prepareStatement("CREATE TABLE category ("
                        + "cID INT(1) NOT NULL,"
                        + "cName VARCHAR(20) NOT NULL,"
                        + "PRIMARY KEY (cID),"
                        + "UNIQUE (cName)"
                        + ");"),
                conn.prepareStatement("CREATE TABLE manufacturer ("
                        + "mID INT(2) NOT NULL,"
                        + "mName VARCHAR(20) NOT NULL,"
                        + "mAddress VARCHAR(50) NOT NULL,"
                        + "mPhoneNumber INT(8) NOT NULL,"
                        + "PRIMARY KEY (mID)"
                        + ");"),
                conn.prepareStatement("CREATE TABLE part ("
                        + "pID INT(3) NOT NULL,"
                        + "pName VARCHAR(20) NOT NULL,"
                        + "pPrice INT(5) NOT NULL,"
                        + "pManufacturerID INT(2) NOT NULL,"
                        + "pCategoryID INT(1) NOT NULL,"
                        + "pWarranty INT(2) NOT NULL,"
                        + "pAvailableQuantity INT(2) NOT NULL,"
                        + "PRIMARY KEY (pID),"
                        + "FOREIGN KEY (pManufacturerID) REFERENCES manufacturer(mID),"
                        + "FOREIGN KEY (pCategoryID) REFERENCES category(cID)"
                        + ");"),
                conn.prepareStatement("CREATE TABLE salesperson ("
                        + "sID INT(2) NOT NULL,"
                        + "sName VARCHAR(20) NOT NULL,"
                        + "sAddress VARCHAR(50) NOT NULL,"
                        + "sPhoneNumber INT(8) NOT NULL,"
                        + "sExperience INT(1) NOT NULL,"
                        + "PRIMARY KEY (sID)"
                        + ");"),
                conn.prepareStatement("CREATE TABLE transaction ("
                        + "tID INT(4) NOT NULL,"
                        + "partID INT(3) NOT NULL,"
                        + "salespersonID INT(2) NOT NULL,"
                        + "tDate DATE NOT NULL,"
                        + "PRIMARY KEY (tID),"
                        + "FOREIGN KEY (partID) REFERENCES part(pID),"
                        + "FOREIGN KEY (salespersonID) REFERENCES salesperson(sID)"
                        + ");")
        };
        for (int i = 0; i < stmts.length; i++) {
            stmts[i].execute();
        }
    }
    public void deleteAllTables() throws SQLException {
        for (int i = 0; i < tableNames.length; i++) {
            PreparedStatement stmt = conn.prepareStatement("DROP TABLE IF EXISTS" + tableNames[i]);
            stmt.execute();
        }
    }


}
