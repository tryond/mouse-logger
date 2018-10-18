package com.plugra.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GetStrokesView {
    private JPanel mainPanel;
    private JTextField usernameText;
    private JTextField directionText;
    private JTextField typeText;
    private JLabel usernameLabel;
    private JLabel directionLabel;
    private JLabel typeLabel;
    private JButton submitButton;

    private ArrayList<GetStrokesViewListener> listeners;


    public void addListener(GetStrokesViewListener listener) {
        listeners.add(listener);
    }


    public JPanel getPanel() {
        return mainPanel;
    }

    public GetStrokesView() {

        listeners = new ArrayList<>();


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // gather text field values
                String username = usernameText.getText();
                String direction = directionText.getText();
                String type = typeText.getText();

                // make query to DB
                for (GetStrokesViewListener listener : listeners) {
                    listener.getStrokes(username, direction, type);
                }
            }
        });
    }
}
