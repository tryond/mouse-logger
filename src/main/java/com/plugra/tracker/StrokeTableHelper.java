package com.plugra.tracker;

import java.awt.*;
import java.util.ArrayList;

public class StrokeTableHelper {

    public static final Double NORMALIZED_TIME = 0.01;       // time in s between points
    public static final Double NORMALIZED_DISTANCE = 5.0;   // distance between points

    private static boolean normalize = true;

    public static String getPoints(com.plugra.tracker.Stroke stroke) {

        ArrayList<Point> points = normalize ? normalizePoints(stroke) : stroke.getPointList();

        if (points.isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < points.size()-1; ++i) {
            sb.append("(" + points.get(i).x + "," + points.get(i).y + "),");
        }

        // append last element
        sb.append("(" + points.get(points.size()-1).x + "," + points.get(points.size()-1).y + ")]");
        return sb.toString();
    }

    public static String getDirection(com.plugra.tracker.Stroke stroke) {
        return stroke.getDirection();
    }

    private static ArrayList<Point> normalizePoints(com.plugra.tracker.Stroke stroke) {

        // TODO: Remove this
        int samePoints = 0;

        ArrayList<Point> newPoints = new ArrayList<>();
        double delta = 0.0;

        // difference between pt1 and pt2 along path
        int diffX, diffY;

        // distance to move for each iteration along path p1 to p2
        double distX, distY;

        // points and times to be referenced for interpolation
        ArrayList<Point> points = stroke.getPointList();
        if (points.isEmpty()) return newPoints;

        ArrayList<Double> times = stroke.getTimeList();

        // add initial point to the array to be returned
        newPoints.add(points.get(0));

        // begin linear interpolation
        for (int i = 1; i < points.size(); ++i) {

            // make sure delta is positive
            delta += times.get(i) >= 0 ? times.get(i) : 0;

            if (delta >= NORMALIZED_TIME) {

                // distance between endpoints
                diffX = points.get(i).x - points.get(i-1).x;
                diffY = points.get(i).y - points.get(i-1).y;

                // distance moved in NORMALIZED_TIME
                distX = (diffX * NORMALIZED_TIME) / delta;
                distY = (diffY * NORMALIZED_TIME) / delta;

                // add points along line
                for (int j = 1; j <= (int)(delta / NORMALIZED_TIME); ++j) {

                    // linearly interpolated point along the path (rounded)
                    int newX = points.get(i-1).x + (int)(distX * j + 0.5);
                    int newY = points.get(i-1).y + (int)(distY * j + 0.5);

                    newPoints.add(new Point(newX, newY));
                }

                // carry only the remaining distance to the next round
                delta -= (int)(delta / NORMALIZED_TIME) * NORMALIZED_TIME;

                // TODO: Remove this
                if (newPoints.get(newPoints.size()-1).equals(newPoints.get(newPoints.size()-2))) ++samePoints;
            }

        }

        // TODO: Remove these
        System.out.println("Percentage Same: " + newPoints.size() / (double) samePoints);
        System.out.println("Same Points: " + samePoints);

        return newPoints;
    }

}
