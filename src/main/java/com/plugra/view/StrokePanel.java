package com.plugra.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class StrokePanel extends JPanel {

    final static int BORDER = 10;
    final static double MAX_DISTANCE = 40;

    int x_min, x_max;
    int y_min, y_max;

//    private double minDistance;
//    private double maxDistance;

    public ArrayList<Point> points;

    public StrokePanel(ArrayList<Point> points) { // , double minDistance, double maxDistance) {

        // TODO
//        this.minDistance = minDistance;
//        this.maxDistance = maxDistance;

        this.points = points;

        if (points.isEmpty()) return;

        Point p0 = points.get(0);

        x_min = p0.x;
        x_max = p0.x;

        y_min = p0.y;
        y_max = p0.y;

        for (Point point : points) {

            if (point.x < x_min) {
                x_min = point.x;
            }
            else if (point.x > x_max) {
                x_max = point.x;
            }

            if (point.y < y_min) {
                y_min = point.y;
            }
            else if (point.y > y_max) {
                y_max = point.y;
            }

        }
    }

    public void paintComponent(Graphics g) {

        // TODO: setup panel environment
        Graphics2D g2 = (Graphics2D) g;
        // g2.scale(1.0, -1.0);
//            int x = this.getWidth() / 2;
//            int y = this.getHeight() / 2;
//            g2.rotate(Math.toRadians(180.0), x, y);
        super.paintComponent(g2);
        this.setBackground(Color.WHITE);

        // TODO: draw line segments
        Point p1, p2;
        Double distance;

        for (int i = 0; i < points.size()-1; ++i) {

            p1 = points.get(i);
            p2 = points.get(i+1);

            distance = getDistance(p1, p2);

            // TODO
            System.out.println("Distance: " + distance);

            g.setColor(getLineColor(distance));

            g2.drawLine(
                    p1.x,
                    p1.y,
                    p2.x,
                    p2.y
            );
        }

        g.drawOval(points.get(points.size()-1).x, points.get(points.size()-1).y, 3, 3); //FOR CIRCLE
    }

    // TODO
    Color getLineColor(Double distance) {
        int R = (int) ((255 * (MAX_DISTANCE - distance)) / MAX_DISTANCE);
        int G = (int) ((255 * distance) / MAX_DISTANCE);
        int B = 0;

        // TODO
        System.out.println(R + ", " + G + ", " + B);

        return new Color(R,G,B);
    }

    Double getDistance(Point p1, Point p2) {
        return Math.hypot(p1.x-p2.x, p1.y-p2.y);
    }


    public static void main(String[] args) {

    }

}