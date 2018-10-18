package com.plugra.model.interfaces;

import com.plugra.model.exceptions.LoginException;
import com.plugra.model.exceptions.RegistrationException;

public interface UserModel {

    public void register(String username, String password, String retype, String firstname, String lastname)  throws RegistrationException;

    public void login(String username, String password) throws LoginException;

    // public void logStroke(String username, String direction, ArrayList<Point> points) throws LogEntryException;

}
