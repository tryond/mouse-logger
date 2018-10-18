package com.plugra.model.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface StrokesModel {

    public ResultSet getStrokes(String username, String direction, String type) throws SQLException;

}
