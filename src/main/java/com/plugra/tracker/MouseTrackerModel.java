package com.plugra.tracker;

import com.plugra.model.Database;
import com.plugra.tracker.exceptions.LogEntryException;
import com.plugra.tracker.interfaces.TrackerModel;

import java.sql.SQLException;

public class MouseTrackerModel implements TrackerModel {

    private Database database = null;

    public MouseTrackerModel(Database db) {
        database = db;
    }

    public void logEntry(String table, String[] values) throws LogEntryException {
        try {
            database.insertIntoTable(table, values);
        }
        catch (SQLException ex) {
            LogEntryException.throwLogFailedException();
        }
    }
}
