package com.plugra.controller;

import com.plugra.app.Visualizer;
import com.plugra.model.interfaces.StrokesModel;
import com.plugra.model.interfaces.UserModel;
import com.plugra.tracker.Stroke;
import com.plugra.tracker.interfaces.Tracker;
import com.plugra.view.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.System.exit;
import static java.lang.System.in;

public class VisualizerController implements GetStrokesViewListener, ShowStrokesViewListener {

    private final int MIN_NUMBER_OF_POINTS = 15;



    private VisualizerView view;            // controls visual components
    private StrokesModel model;             // stores information
    private ResultSet resultSet;            // stores the results of the table query

    private ArrayList<ArrayList<Point>> strokes;

    private double minSpeed = Double.MAX_VALUE;
    private double maxSpeed = Double.MIN_VALUE;
    private double avgSpeed = 0;
    private double stdDeviation = 0;

    private int currentIndex = 0;

    public VisualizerController(StrokesModel model, VisualizerView view) {

        this.model = model;
        this.view = view;

        view.getStrokesView.addListener(this);
        view.showStrokesView.addListener(this);
    }

    public void go() {
        view.displayGetStrokes();
    }

    private void incrementCurrentIndex() {
        if (currentIndex >= strokes.size()) {
            currentIndex = 0;
        }
        else {
            ++currentIndex;
        }
    }

    private void decrementCurrentIndex() {
        if (currentIndex <= 0) {
            currentIndex = strokes.size()-1;
        }
        else {
            --currentIndex;
        }
    }


    public void getStrokes(String username, String direction, String type) {
        try {
            resultSet = model.getStrokes(username, direction, type);
            buildStrokes();


            // TODO: this needs to actively display strokes and next button

            view.displayPanel(getNextStroke());


            // view.displayShowStrokes();
        }
        catch (SQLException ex) {
            view.showMessage("Result Set doesn't exist");
            ex.printStackTrace();
        }
    }

    private void buildStrokes() throws SQLException {

        int x, y;

        while (resultSet.next()) {

            String pointString = resultSet.getString("points");



            // parse string to get x and y values
            String[] tokens = pointString.split("\\D+");

            // System.out.println(pointString);

            ArrayList<Point> points = new ArrayList<>();

            for (int i = 1; i < tokens.length-1; i += 2) {
                // System.out.println(Integer.parseInt(tokens[i]) + ", " + Integer.parseInt(tokens[i+1]));
                x = Integer.parseInt(tokens[i]);
                y = Integer.parseInt(tokens[i+1]);
                points.add(new Point(x, y));
            }

            if (points.size() >= MIN_NUMBER_OF_POINTS) {

                analyzeStroke(points);

                strokes.add(points);
            }

            // TODO: debug
            System.out.println("min distance: " + minSpeed);
            System.out.println("max distance: " + maxSpeed);



//            StrokePanel panel = new StrokePanel(points);
//            frame.add(panel);
//            frame.setVisible(true);
//
//            // TODO: remove below
//            System.in.read();

        }

    }

    @Override
    public JPanel getNextStroke() {
        int index = currentIndex;
        incrementCurrentIndex();

        return new StrokePanel(strokes.get(index)); // , minSpeed, maxSpeed);
    }

    public void analyzeStroke(ArrayList<Point> points) {

        for (int i = 0; i < points.size()-1; ++i) {

            double distance = getDistanceBetweenPoints(points.get(i), points.get(i+1));

            if (distance > maxSpeed) {
                maxSpeed = distance;
            }

            if (distance < minSpeed) {
                minSpeed = distance;
            }

        }

    }

    private double getDistanceBetweenPoints(Point pointA, Point pointB) {
        return Math.hypot(pointA.x - pointB.x, pointA.y - pointB.y);
    }

}
