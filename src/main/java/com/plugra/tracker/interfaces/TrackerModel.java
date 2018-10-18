package com.plugra.tracker.interfaces;

import com.plugra.tracker.exceptions.LogEntryException;

public interface TrackerModel {

    public void logEntry(String table, String[] values) throws LogEntryException;

}
