package datafile;

import java.sql.Connection;

import db.schema.Salesperson;

public class SalespersonDatafile implements Datafile {
    private int sID;
    private String sName;
    private String sAddress;
    private int sPhoneNumber;
    private int sExperience;

    public SalespersonDatafile() {

    }

    public void parseLine(String line) {
        String[] token = line.split("\t");
        this.sID = Integer.parseInt(token[0]);
        this.sName = token[1];
        this.sAddress = token[2];
        this.sPhoneNumber = Integer.parseInt(token[3]);
        this.sExperience = Integer.parseInt(token[4]);
    }

    public void saveLineToDatabase(Connection conn) {
        Salesperson salesperson = new Salesperson(sID, sName, sAddress, sPhoneNumber, sExperience);
        salesperson.saveToDatabase(conn);
    }
}
