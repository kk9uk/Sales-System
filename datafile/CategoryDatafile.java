package datafile;

import java.sql.Connection;

import db.schema.Category;

public class CategoryDatafile implements Datafile {
    private int cID;
    private String cName;

    public CategoryDatafile() {

    }

    public void parseLine(String line) {
        String[] token = line.split("\t");
        this.cID = Integer.parseInt(token[0]);
        this.cName = token[1];
    }

    public void saveLineToDatabase(Connection conn) {
        Category category = new Category(cID, cName);
        category.saveToDatabase(conn);
    }
}
