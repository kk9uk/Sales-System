package datafile;

import java.sql.Connection;
import java.util.Calendar;

import db.schema.Transaction;

public class TransactionDatafile implements Datafile {
    private int tID;
    private int pID;
    private int sID;
    private Calendar tDate;

    public TransactionDatafile() {

    }

    public void parseLine(String line) {
        String[] token = line.split("\t");
        this.tID = Integer.parseInt(token[0]);
        this.pID = Integer.parseInt(token[1]);
        this.sID = Integer.parseInt(token[2]);
        this.tDate = strToCalendar(token[3]);
    }

    public void saveLineToDatabase(Connection conn) {
        Transaction transaction = new Transaction(tID, pID, sID, tDate);
        transaction.saveToDatabase(conn);
    }

    private static Calendar strToCalendar(String str) {
        Calendar calendar = Calendar.getInstance();
        String[] token = str.split("/");
        int day = Integer.parseInt(token[0]);
        int month = Integer.parseInt(token[1]) - 1;
        int year = Integer.parseInt(token[2]);
        calendar.set(year, month, day);
        return calendar;
    }
}
