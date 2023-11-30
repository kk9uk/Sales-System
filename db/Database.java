package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String dbAddress = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db1?autoReconnect=true&useSSL=false";
    private static final String dbUsername = "Group1";
    private static final String dbPassword = "CSCI3170";

    private Connection conn = null;
    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.conn = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
    }
}
