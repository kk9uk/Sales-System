package datafile;

import java.sql.Connection;

import db.schema.Part;

public class PartDatafile implements Datafile {
    private int pID;
    private String pName;
    private int pPrice;
    private int mID;
    private int cID;
    private int pWarrantyPeriod;
    private int pAvailableQuantity;

    public void parseLine(String line) {
        String[] token = line.split("\t");
        this.pID = Integer.parseInt(token[0]);
        this.pName = token[1];
        this.pPrice = Integer.parseInt(token[2]);
        this.mID = Integer.parseInt(token[3]);
        this.cID = Integer.parseInt(token[4]);
        this.pWarrantyPeriod = Integer.parseInt(token[5]);
        this.pAvailableQuantity = Integer.parseInt(token[6]);
    }

    public void saveLineToDatabase(Connection conn) {
        Part part = new Part(this.pID, this.pName, this.pPrice, this.mID, this.cID, this.pWarrantyPeriod, this.pAvailableQuantity);
        part.saveToDatabase(conn);
    }
}
