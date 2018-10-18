package com.plugra.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowStrokesView {


    private JPanel mainPanel;
    private JPanel strokePanel;
    private JButton nextButton;


    private ArrayList<ShowStrokesViewListener> listeners;

    public void addListener(ShowStrokesViewListener listener) {
        listeners.add(listener);
    }

    public JPanel getPanel() {
        return mainPanel;
    }


    public ShowStrokesView() {

        listeners = new ArrayList<>();

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (ShowStrokesViewListener listener : listeners) {
                    JPanel panel = listener.getNextStroke();
                }

            }
        });
    }

}
