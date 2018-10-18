package com.plugra.app;

import com.plugra.controller.LoginController;
import com.plugra.controller.interfaces.LoginListener;
import com.plugra.model.DBUserModel;
import com.plugra.model.Database;
import com.plugra.model.ElephantDatabase;
import com.plugra.tracker.MouseTracker;
import com.plugra.tracker.MouseTrackerModel;
import com.plugra.view.View;

import javax.swing.*;

public class MouseLogger implements LoginListener {

    // Global Attributes
    // Frame
    // Database

    public static void main(String[] args) {


        /*
        1. Create frame
        2. Connect to database
        3. Login
        4. Start each tracker
        5. Close when done
         */

        JFrame frame = new MKTrackerFrame();

        Database database = new ElephantDatabase();

        // Create user model which references database
        DBUserModel userModel = new DBUserModel(database);

        // Create mouse tracker model which references database
        MouseTrackerModel mouseTrackerModel = new MouseTrackerModel(database);

        // TODO: testing new View(JFrame frame)
        // Create view master
        View view = new View(frame);

        // Create loginController which references database model and view master
        LoginController loginController = new LoginController(userModel, view);

        // Create mouse tracker which references mouse tracker model for data input and output
        MouseTracker mouseTracker = new MouseTracker(mouseTrackerModel);

        // Allow mouse tracker to be notified of when to start tracking
        loginController.addTracker(mouseTracker);

        // Start loginController
        loginController.go();
    }

    // TODO
    public void userLoggedIn(String username) {

        System.out.println(username + " logged in");

    }
}
