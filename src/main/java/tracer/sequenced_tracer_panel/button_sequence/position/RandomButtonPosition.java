package sequenced_tracer_panel.button_sequence.position;

import java.awt.*;
import java.util.Random;

public class RandomButtonPosition implements ButtonPositionStrategy {

    private final Dimension PANEL_SIZE;
    private final int MIN_DISTANCE_BETWEEN_BUTTONS;
    private final int MAX_DISTANCE_BETWEEN_BUTTONS;
    private final int BOUNDARY;

    private final Random RNG;

    public RandomButtonPosition(Dimension panelSize,
                                int minDistanceBetweenButtons,
                                int maxDistanceBetweenButtons,
                                int boundary,
                                Random rng
    ) {
        PANEL_SIZE = panelSize;
        BOUNDARY = boundary;
        RNG = rng;

        int minPanelDimension = (PANEL_SIZE.width < PANEL_SIZE.height ? PANEL_SIZE.width : PANEL_SIZE.height) - (2 * BOUNDARY);

        // min distance should be no less than 0 and no greater than 1/4 panel size
        if (minDistanceBetweenButtons < 0) {
            MIN_DISTANCE_BETWEEN_BUTTONS = 0;
        }
        else if (minDistanceBetweenButtons > (minPanelDimension / 4)) {
            MIN_DISTANCE_BETWEEN_BUTTONS = minPanelDimension / 4;
        }
        else {
            MIN_DISTANCE_BETWEEN_BUTTONS = minDistanceBetweenButtons;
        }

        // max distance should be no less than 1/2 panel size
        if (maxDistanceBetweenButtons < (minPanelDimension / 2)) {
            MAX_DISTANCE_BETWEEN_BUTTONS = minPanelDimension / 2;
        }
        else {
            MAX_DISTANCE_BETWEEN_BUTTONS = maxDistanceBetweenButtons;
        }

    }

    public Point[] generateButtonPositions(int numPositions) {

        if (numPositions <= 0) return null;

        Point[] buttonPositionArr = new Point[numPositions];
        buttonPositionArr[0] = getNextPositionInBounds();

        for (int i = 1; i < numPositions; ++i) {

            do {
                buttonPositionArr[i] = getNextPositionInBounds();
            }
            // continue until random position in range is found
            while (!pointInAcceptableRange(buttonPositionArr[i-1], buttonPositionArr[i]));
        }

        return buttonPositionArr;
    }

    private Point getNextPositionInBounds() {
        // get x coordinate in panel and inside of boundaries
        int x = RNG.nextInt(PANEL_SIZE.width - (2 * BOUNDARY)) + BOUNDARY;

        // get y coordinate in panel and inside of boundaries
        int y = RNG.nextInt(PANEL_SIZE.height - (2 * BOUNDARY)) + BOUNDARY;

        return new Point(x, y);
    }

    private boolean pointInAcceptableRange(Point a, Point b) {

        double distanceBetweenPoints = getDistanceBetweenPoints(a, b);

        if (distanceBetweenPoints > MAX_DISTANCE_BETWEEN_BUTTONS) {
            return false;
        }

        if (distanceBetweenPoints < MIN_DISTANCE_BETWEEN_BUTTONS) {
            return false;
        }

        return true;
    }

    private double getDistanceBetweenPoints(Point a, Point b) {
        return Math.hypot(b.x-a.x, b.y-a.y);
    }
}
