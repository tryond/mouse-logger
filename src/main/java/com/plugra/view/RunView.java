package com.plugra.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RunView {
    private JButton buttonClose;
    private JPanel panelMain;
    private JLabel labelLogging;

    private ArrayList<RunViewListener> listeners;

    public void addListener(RunViewListener listener) {
        listeners.add(listener);
    }

    public JPanel getPanel() {
        return panelMain;
    }

    public RunView() {

        listeners = new ArrayList<>();

        buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (RunViewListener listener : listeners) {
                    listener.close();
                }

            }
        });
    }
}
