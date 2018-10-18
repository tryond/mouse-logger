package com.plugra.view;

import javax.swing.*;
import java.awt.*;

public class View {

    private JFrame frame;

    public LoginView loginView;
    public RegisterView registerView;
    public DisclaimerView disclaimerView;
    public RunView runView;
    public MouseTypeView mouseTypeView;

    public View(JFrame frame) {

        // TODO: this is a test
        this.frame = frame;
//        frame = new JFrame("Mouse Logger 1.0");
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo(null);

        loginView = new LoginView();
        registerView = new RegisterView();
        disclaimerView = new DisclaimerView();
        runView = new RunView();
        mouseTypeView = new MouseTypeView();
    }

    public void displayLogin() {
        System.out.println("Display Login");

        frame.setContentPane(loginView.getPanel());
        refresh();
    }

    public void displayRegister() {
        System.out.println("Display Register");

        frame.setContentPane(registerView.getPanel());
        refresh();
    }

    public void displayDisclaimer() {
        System.out.println("Display Disclaimer");

        frame.setContentPane(disclaimerView.getPanel());
        refresh();
    }

    public void displayRun() {
        System.out.println("Display Run");

        frame.setContentPane(runView.getPanel());
        refresh();
    }

    public void displayMouseType() {
        System.out.println("Display Mouse Type");

        frame.setContentPane(mouseTypeView.getPanel());
        refresh();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void minimizeFrame() {
        frame.setState(Frame.ICONIFIED);
    }

    private void refresh() {
        frame.revalidate();
        frame.pack();
        frame.setVisible(true);
    }
}
