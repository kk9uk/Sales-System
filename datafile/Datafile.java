package datafile;

import java.sql.Connection;

public interface Datafile {
    public void parseLine(String line);
    public void saveLineToDatabase(Connection conn);
}
