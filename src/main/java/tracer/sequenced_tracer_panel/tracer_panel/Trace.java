package sequenced_tracer_panel.tracer_panel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Trace {

    public ArrayList<Point> points;
    public ArrayList<Integer> timesMS;

    public Trace(ArrayList<Point> points, ArrayList<Integer> timesMS) {
        this.points = points;
        this.timesMS = timesMS;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        // Append point values

        sb.append("Points: [");

        for (int i = 0; i < points.size()-1; ++i) {
            sb.append(points.get(i) + ", ");
        }
        sb.append(points.get(points.size()-1) + "]\n");

        // Append time values

        sb.append("Times MS: [");

        for (int i = 0; i < timesMS.size()-1; ++i) {
            sb.append(timesMS.get(i) + ", ");
        }
        sb.append(timesMS.get(timesMS.size()-1) + "]");

        // Return the builder as a string

        return sb.toString();
    }

    // ********************************************************************************************************
    // TODO: TEST

    public static final Double NORMALIZED_TIME = 0.01;       // time in ms between points

    // returns position of cursor along path at every NORMALIZED_TIME ms
    public void normalizeTime() {

        System.out.println("Times: " + timesMS);

        ArrayList<Point> newPoints = new ArrayList<Point>();
        double delta = 0.0;

        ArrayList<Double> timesS = new ArrayList<Double>();
        for (Integer i : timesMS)
        {
            timesS.add((i * 1.0) / 1000.0);
        }

        // difference between pt1 and pt2 along path
        int diffX, diffY;

        // distance to move for each iteration along path p1 to p2
        double distX, distY;

        // points and times to be referenced for interpolation
        if (points.isEmpty()) return;

        // add initial point to the array to be returned
        newPoints.add(points.get(0));

        int taper = 2;

        // begin linear interpolation
        for (int i = taper + 1; i < points.size()-taper; ++i) {

            // make sure delta is positive
            delta += timesS.get(i) >= 0 ? timesS.get(i) : 0;

            // System.out.println("Delta: " + delta);

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
            }

        }

        points = newPoints;
        timesMS = new ArrayList<Integer>(Collections.nCopies(newPoints.size()+1, (int)(NORMALIZED_TIME * 1000)));
    }

    // ********************************************************************************************************
}
