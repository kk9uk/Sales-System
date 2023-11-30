package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    public void ShowTableContent(String Table_Name) throws SQLException {
        System.out.println("Content of table" + Table_Name + " : ");
        switch (Table_Name) {
            case "category":
                ShowCategoryTable(Table_Name);
                break;
            case "manufacturer":
                ShowManufacturerTable(Table_Name);
                break;
            case "part":
                ShowPartTable(Table_Name);
                break;
            case "salesperson":
                ShowSalespersonTable(Table_Name);
                break;
            case "transaction":
                ShowTransactionTable(Table_Name);
                break;
            default: System.out.println("[Error]: Invalid input table name.\n"); break;
        }
    }

    private void ShowCategoryTable(String tName) {
        System.out.println("| c_id | c_Name |");
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT cID, cName FROM " + tName);
            Result rs = stmt.executeQuery();
            while (rs.next()) {
                int c_id = rs.getInt(1);
                String c_name = rs.getString(2);
                System.out.println("| " + c_id + " | " + c_name + " |");
            }
        } catch (SQLException e) {
            System.out.println("[Error]: " + e);
        }
    }

    private void ShowManufacturerTable(String tName) {
        System.out.println("| m_id | m_Name | m_Address | m_PhoneNumber |");
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT mID, mName, mAddress, mPhoneNumber FROM " + tName);
            Result rs = stmt.executeQuery();
            while (rs.next()) {
                int m_id = rs.getInt(1);
                String m_Name = rs.getString(2);
                String m_Address = rs.getString(3);
                int m_PhoneNumber = rs.getInt(4);
                System.out.println("| " 
                                + m_id 
                                + " | " 
                                + m_Name 
                                + " | "
                                + m_Address
                                + " | "
                                + m_PhoneNumber
                                + " |");
            }
        } catch (SQLException e) {
            System.out.println("[Error]: " + e);
        }
    }

    private void ShowPartTable(String tName) {
        System.out.println("| p_id | p_Name | p_Price | m_id | c_id | p_WarrantyPeriod | p_AvailableQuantity |");
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT pID, pName, pPrice, mID, cID, pWarrantyPeriod, pAvailableQuantity FROM " + tName);
            Result rs = stmt.executeQuery();
            while (rs.next()) {
                int p_id = rs.getInt(1);
                String p_Name = rs.getString(2);
                int p_Price = rs.getInt(3);
                int m_id = rs.getInt(4);
                int c_id = rs.getInt(5);
                int p_WarrantyPeriod = rs.getInt(6);
                int p_AvailableQuantity = rs.getInt(7);
                System.out.println("| " 
                                + p_id 
                                + " | " 
                                + p_Name 
                                + " | "
                                + p_Price
                                + " | "
                                + m_id
                                + " | "
                                + c_id
                                + " | "
                                + p_WarrantyPeriod
                                + " | "
                                + p_AvailableQuantity
                                + " |");
            }
        } catch (SQLException e) {
            System.out.println("[Error]: " + e);
        }
    }

    private void ShowSalespersonTable(String tName) {
        System.out.println("| s_id | s_Name | s_Address | s_PhoneNumber | s_Experience |");
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT sID, sName, sAddress, sPhoneNumber, sExperience FROM " + tName);
            Result rs = stmt.executeQuery();
            while (rs.next()) {
                int s_id = rs.getInt(1);
                String s_Name = rs.getString(2);
                String s_Address = rs.getString(3);
                int s_PhoneNumber = rs.getInt(4);
                int s_Experience = rs.getInt(5);
                System.out.println("| " 
                                + s_id 
                                + " | " 
                                + s_Name 
                                + " | "
                                + s_Address
                                + " | "
                                + s_PhoneNumber
                                + " | "
                                + s_Experience
                                + " |");
            }
        } catch (SQLException e) {
            System.out.println("[Error]: " + e);
        }
    }

    private void ShowTransactionTable(String tName) {
        System.out.println("| t_id | p_id | s_id | t_Date |");
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT tID, pID, sID, tDate FROM " + tName);
            Result rs = stmt.executeQuery();
            while (rs.next()) {
                int t_id = rs.getInt(1);
                int p_id = rs.getInt(2);
                int s_id = rs.getInt(3);
                
                System.out.println("| " 
                                + t_id 
                                + " | " 
                                + p_id
                                + " | "
                                + s_id
                                + " | "

                                + " |");
            }
        } catch (SQLException e) {
            System.out.println("[Error]: " + e);
        }
    }
}
