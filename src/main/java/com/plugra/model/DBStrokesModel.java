package com.plugra.model;

import com.plugra.model.interfaces.StrokesModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBStrokesModel implements StrokesModel {

    private String strokesTable = "strokes";
    private String[] strokesTableFields = {"username", "direction", "points", "id", "cursor_type", "poll_rate"};

    private Database database = null;

    public DBStrokesModel(Database db) {
        database = db;
    }

    public ResultSet getStrokes(String username, String direction, String type) throws SQLException {

        String[] colNames = {"username", "direction", "cursor_type"};
        String[] colValues = {username, direction, type};

        return database.getFromTable("strokes", colNames, colValues);


    }
}
