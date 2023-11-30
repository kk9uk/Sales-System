package datafile;

import java.sql.Connection;

import db.schema.Manufacturer;

public class ManufacturerDatafile implements Datafile {
    private int mID;
    private String mName;
    private String mAddress;
    private int mPhoneNumber;

    public void parseLine(String line) {
        String[] token = line.split("\t");
        this.mID = Integer.parseInt(token[0]);
        this.mName = token[1];
        this.mAddress = token[2];
        this.mPhoneNumber = Integer.parseInt(token[3]);
    }

    public void saveLineToDatabase(Connection conn) {
        Manufacturer manufacturer = new Manufacturer(this.mID, this.mName, this.mAddress, this.mPhoneNumber);
        manufacturer.saveToDatabase(conn);
    }
}
