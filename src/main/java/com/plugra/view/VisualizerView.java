package com.plugra.view;

import javax.swing.*;

public class VisualizerView {

    private JFrame frame;

    public GetStrokesView getStrokesView;
    public ShowStrokesView showStrokesView;

    public VisualizerView() {

        frame = new JFrame("Stroke Visualizer 1.0");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        getStrokesView = new GetStrokesView();
        showStrokesView = new ShowStrokesView();

    }

    public void displayPanel(JPanel panel) {
        System.out.println("Display Panel");

        frame.setContentPane(panel);
        refresh();
    }

    public void displayGetStrokes() {
        System.out.println("Display Get Strokes");

        frame.setContentPane(getStrokesView.getPanel());
        refresh();
    }

    public void displayShowStrokes() {
        System.out.println("Display View Strokes");

        frame.setContentPane(showStrokesView.getPanel());
        refresh();
    }

    private void refresh() {
        frame.revalidate();
        frame.pack();
        frame.setVisible(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
