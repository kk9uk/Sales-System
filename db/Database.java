package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import datafile.*;

public class Database {
    private static final String dbAddress = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db1?autoReconnect=true&useSSL=false";
    private static final String dbUsername = "Group1";
    private static final String dbPassword = "CSCI3170";
    private static final String[] tableNames = {"transaction", "salesperson", "part", "manufacturer", "category"};

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
                        + "mID INT(2) NOT NULL,"
                        + "cID INT(1) NOT NULL,"
                        + "pWarrantyPeriod INT(2) NOT NULL,"
                        + "pAvailableQuantity INT(2) NOT NULL,"
                        + "PRIMARY KEY (pID),"
                        + "FOREIGN KEY (mID) REFERENCES manufacturer(mID),"
                        + "FOREIGN KEY (cID) REFERENCES category(cID)"
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
                        + "pID INT(3) NOT NULL,"
                        + "sID INT(2) NOT NULL,"
                        + "tDate DATE NOT NULL,"
                        + "PRIMARY KEY (tID),"
                        + "FOREIGN KEY (pID) REFERENCES part(pID),"
                        + "FOREIGN KEY (sID) REFERENCES salesperson(sID)"
                        + ");")
        };
        for (int i = 0; i < stmts.length; i++) {
            stmts[i].execute();
        }
    }
    public void deleteAllTables() throws SQLException {
        for (int i = 0; i < tableNames.length; i++) {
            PreparedStatement stmt = conn.prepareStatement("DROP TABLE IF EXISTS " + tableNames[i]);
            stmt.execute();
        }
    }

    public void loadDataFromFolder(String path) throws Exception {
        CategoryDatafile datafile1 = new CategoryDatafile();
        BufferedReader reader1 = new BufferedReader(new FileReader(path + "/category.txt"));
        String line1 = reader1.readLine();
        while (line1 != null) {
            datafile1.parseLine(line1);
            datafile1.saveLineToDatabase(conn);
            line1 = reader1.readLine();
        }

        ManufacturerDatafile datafile2 = new ManufacturerDatafile();
        BufferedReader reader2 = new BufferedReader(new FileReader(path + "/manufacturer.txt"));
        String line2 = reader2.readLine();
        while (line2 != null) {
            datafile2.parseLine(line2);
            datafile2.saveLineToDatabase(conn);
            line2 = reader2.readLine();
        }

        PartDatafile datafile3 = new PartDatafile();
        BufferedReader reader3 = new BufferedReader(new FileReader(path + "/part.txt"));
        String line3 = reader3.readLine();
        while (line3 != null) {
            datafile3.parseLine(line3);
            datafile3.saveLineToDatabase(conn);
            line3 = reader3.readLine();
        }

        SalespersonDatafile datafile4 = new SalespersonDatafile();
        BufferedReader reader4 = new BufferedReader(new FileReader(path + "/salesperson.txt"));
        String line4 = reader4.readLine();
        while (line4 != null) {
            datafile4.parseLine(line4);
            datafile4.saveLineToDatabase(conn);
            line4 = reader4.readLine();
        }

        TransactionDatafile datafile5 = new TransactionDatafile();
        BufferedReader reader5 = new BufferedReader(new FileReader(path + "/transaction.txt"));
        String line5 = reader5.readLine();
        while (line5 != null) {
            datafile5.parseLine(line5);
            datafile5.saveLineToDatabase(conn);
            line5 = reader5.readLine();
        }
    }

    public void ShowTableContent(String Table_Name) {
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
            ResultSet rs = stmt.executeQuery();
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
            ResultSet rs = stmt.executeQuery();
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
            ResultSet rs = stmt.executeQuery();
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
            ResultSet rs = stmt.executeQuery();
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
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int t_id = rs.getInt(1);
                int p_id = rs.getInt(2);
                int s_id = rs.getInt(3);
                Date t_date = rs.getDate(4);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String t_dateStr = dateFormat.format(t_date);
                System.out.println("| " 
                                + t_id 
                                + " | " 
                                + p_id
                                + " | "
                                + s_id
                                + " | "
                                + t_dateStr
                                + " |");
            }
        } catch (SQLException e) {
            System.out.println("[Error]: " + e);
        }
    }


    // ====== SALESPERSON OPERATIONS =======

    public void Search_for_Parts(int search_crit, String search_word, int search_order) {
        try {
            // Initialize variable
            String criterion = "";
            if (search_crit == 1) {
                criterion = "P.pName";
            } else {
                criterion = "M.mName";
            }

            String order = "";
            if (search_order == 1) {
                order = "ASC";
            } else {
                order = "DESC";
            }
            
            PreparedStatement stmt = conn.prepareStatement("SELECT P.*, M.mName, C.cName FROM part P "
                                                            + "join manufacturer M on M.mID = P.mID "
                                                            + "join category C on C.cID = P.cID "
                                                            + "where " +  criterion + " LIKE '%" + search_word + "%' "
                                                            + "order by P.pPrice " + order);
            ResultSet rs = stmt.executeQuery();
            System.out.println("| ID | Name | Manufacturer | Category | Quantity | Warranty | Price |");
            while (rs.next()) {
                int p_id = rs.getInt(1);
                String p_name = rs.getString(2);
                int p_price = rs.getInt(3);
                int p_warranty = rs.getInt(6);
                int p_quantity = rs.getInt(7);
                String m_name = rs.getString(8);
                String c_name = rs.getString(9);
                System.out.println("| " 
                                + p_id 
                                + " | " 
                                + p_name
                                + " | "
                                + m_name
                                + " | "
                                + c_name
                                + " | "
                                + p_quantity
                                + " | "
                                + p_warranty
                                + " | "
                                + p_price
                                + " |");
            }
            Systen.out.println("End of Query");
        } catch (SQLException e) {
            System.out.println("[Error]: " + e);
        }
    }

    public void Sell_a_part(int part_id, int salesperson_id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT pAvailableQuantity, pName FROM part where pID = ?");
            stmt.setInt(1, part_id);

            // Check if exist this product
            if (!stmt.executeQuery().next()) {
                System.out.println("[Error]: No such product.");
                return;
            }

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int quantity = rs.getInt(1);
                String part_Name = rs.getString(2);
            }

            // Check if it is out of stock
            if (quantity == 0) {
                System.out.println("[Error]: This part cannot be sold. (Quantity = 0)");
                return;
            }
            
            System.out.println("Product: " + part_Name + "(id: " + part_id + ") Remaining Quantity: " + (quantity - 1));

            // Update Quantity
            stmt = conn.prepareStatement("UPDATE part SET pAvailableQuantity = pAvailableQuantity - 1 WHERE pID = " + part_id);
            stmt.executeUpdate();

            // Update transaction record
            stmt = conn.prepareStatement("SELECT COUNT(*) FROM transaction");
            rs = stmt.executeQuery();
            int transaction_counter = 0;
            if (rs.next()) {
                transaction_counter = rs.getInt(1);
            }

            stmt = conn.prepareStatement("INSERT INTO transaction (tID, pID, sID, tDate) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, transaction_counter + 1);
            stmt.setInt(2, part_id);
            stmt.setInt(3, salesperson_id);
            stmt.setDate(4, new Date(System.currentTimeMillis()));
            stmt.execute();

        } catch (SQLException e) {
            System.out.println("[Error]: " + e);
        }
    }
}
