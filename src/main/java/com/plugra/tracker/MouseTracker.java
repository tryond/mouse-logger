package com.plugra.tracker;

import com.plugra.controller.interfaces.ControllerListener;
import com.plugra.tracker.exceptions.LogEntryException;
import com.plugra.tracker.interfaces.StrokeBuilderListener;
import com.plugra.tracker.interfaces.Tracker;
import com.plugra.tracker.interfaces.TrackerModel;

import static java.lang.System.exit;

public class MouseTracker implements StrokeBuilderListener, Tracker {

    private CursorTracker cursorTracker;
    private StrokeBuilder strokeBuilder;

    private TrackerModel model;         // model which stores tracked information
    private String username;            // name of user to track
    private String type;                // type of tracker
    private Double pollRate;            // seconds between each poll

    public void startTracking(String username, String type) {

        this.username = username;
        this.type = type;
        this.pollRate = StrokeTableHelper.NORMALIZED_TIME;

        System.out.println("Start MouseTracker: " + username);

        // Start listening to the cursor tracker
        cursorTracker.addListener(strokeBuilder);
    }

    public MouseTracker(TrackerModel model) {

        this.model = model;

        cursorTracker = new CursorTracker();
        strokeBuilder = new StrokeBuilder();

        strokeBuilder.addListener(this);
    }

    public void strokeBuilt(Stroke stroke) {

        System.out.println(type + " stroke built");

        // TODO: Normalize and derive direction
        try {
            model.logEntry("strokes", new String[] {
                    username,
                    null,
                    null,
                    StrokeTableHelper.getPoints(stroke),
                    null,
                    null,
                    null,
                    null,
                    type,
                    pollRate.toString()
            });
        }
        catch (LogEntryException ex) {
            System.err.println(ex.toString());
            exit(1);
        }
    }

//        public void logStroke(String username, String direction, ArrayList<Point> points) throws LogEntryException {
//
//        try {
//            database.insertIntoTable(strokesTable, new String[] {username, direction, pointsToString(points)});
//        }
//        catch (SQLException ex) {
//            ex.printStackTrace();
//
//            // Log Stoke Failed
//            LogEntryException.throwLogStrokeFailedException();
//        }
//
//    }
//
//    public static String pointsToString(ArrayList<Point> points) {
//
//        if (points.isEmpty()) return "[]";
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("[");
//
//        for (int i = 0; i < points.size()-1; ++i) {
//            sb.append("(" + points.get(i).x + "," + points.get(i).y + "),");
//        }
//
//        // append last element
//        sb.append("(" + points.get(points.size()-1).x + "," + points.get(points.size()-1).y + ")]");
//        return sb.toString();
//    }

}
