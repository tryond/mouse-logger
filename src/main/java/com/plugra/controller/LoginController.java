package com.plugra.controller;

import com.plugra.model.exceptions.LoginException;
import com.plugra.model.exceptions.RegistrationException;
import com.plugra.model.interfaces.UserModel;
import com.plugra.tracker.interfaces.Tracker;
import com.plugra.view.*;

import java.util.ArrayList;

import static java.lang.System.exit;

public class LoginController implements LoginViewListener, RegisterViewListener, DisclaimerViewListener, RunViewListener, MouseTypeViewListener {

    private String username;                // which user to track
    private String mouseType;               // mouse or trackpad
    private Float pollRate;                 // seconds between polls

    private View view;                      // controls visual components
    private UserModel model;                // stores information

    private ArrayList<Tracker> trackers;    // Track and Log User Behavior

    public LoginController(UserModel model, View view) {

        trackers = new ArrayList<>();

        this.model = model;
        this.view = view;

        view.loginView.addListener(this);
        view.registerView.addListener(this);
        view.disclaimerView.addListener(this);
        view.runView.addListener(this);
        view.mouseTypeView.addListener(this);
    }

    // Adds a behavioral tracker
    public void addTracker(Tracker tracker) {
        trackers.add(tracker);
    }

    // Begin GUI
    public void go() {
        view.displayLogin();
    }

    // Attempt to Login through model
    public void attemptLogin(String username, String password) {
        try {
            model.login(username, password);

            // Login Success
            view.showMessage("Login Success");

            // Set username for the remainder of the program
            this.username = username;

            // TODO
            // Begin Tracking
//            goToRun();

            goToMouseType();

        }
        catch (LoginException ex) {

            // DEBUG
            System.out.println(ex.toString());

            view.showMessage(ex.toString());
            ex.printStackTrace();
        }
    }

    // Attempt to Register through model
    public void attemptRegister(String username, String password, String retype, String firstname, String lastname) {
        try {
            model.register(username, password, retype, firstname, lastname);

            // Registration Success
            view.showMessage("Registration Success");

            // Set username for the remainder of the program
            this.username = username;

            // Disclaimer
            goToDisclaimer();

        }
        catch (RegistrationException ex) {

            // DEBUG
            System.out.println(ex.getMessage());

            view.showMessage(ex.toString());
            ex.printStackTrace();
        }
    }

    // Navigate to Login Screen
    public void goToLogin() {
        view.displayLogin();
    }

    // Navigate to Register Screen
    public void goToRegister() {
        view.displayRegister();
    }

    // Navigate to Disclaimer Screen
    public void goToDisclaimer() {
        view.displayDisclaimer();
    }

    public void goToMouseType() {
        view.displayMouseType();
    }

    public void termsAccepted() {
        view.displayMouseType();
    }

    // Navigate to Run Screen
    public void goToRun() {

        // Notify Trackers to Start Tracking
        for (Tracker tracker : trackers) {
            tracker.startTracking(username, mouseType);
        }

        view.displayRun();

        view.minimizeFrame();
    }

    public void mouseTypeSelected(String mouseType) {

        this.mouseType = mouseType;

        // TODO: REMOVE
        System.out.println(mouseType);

        goToRun();
    }

    // Close the Application
    public void close() {
        exit(0);
    }
}
