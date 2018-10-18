package sequenced_tracer_panel.tracer_panel;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class TracerOverlayPanel extends JPanel implements MouseInputListener {

    private final int TIMEOUT_MS;
    private final int BUFFER_ZONE_PX;
    private final JPanel PANEL_BEING_TRACED;

    private boolean tracing;

    private long timeOfLastUpdateNS;
    private boolean nowFormingTrace;

    private ArrayList<Point> currentCursorPoints;
    private ArrayList<Integer> currentTimeBetweenPointsMS;
    private Point lastPointTraced;

    private ArrayList<TracerOverlayListener> listeners;

    public TracerOverlayPanel(int timeoutMS, int bufferZonePx, JPanel tracedPanel) {

        super();

        // Set instance variables

        this.TIMEOUT_MS = timeoutMS;
        this.BUFFER_ZONE_PX = bufferZonePx;
        this.PANEL_BEING_TRACED = tracedPanel;

        tracing = false;

        timeOfLastUpdateNS = System.nanoTime();
        nowFormingTrace = false;

        listeners = new ArrayList<TracerOverlayListener>();
    }

    @Override
    public Dimension getPreferredSize() {
        return PANEL_BEING_TRACED.getPreferredSize();
    }

    public boolean isTracing()
    {
        return tracing;
    }

    public void setTracing(boolean tracing)
    {
        // If the current setting must change
        if (this.tracing != tracing)
        {
            // If parameter says to start
            if (tracing) {
                startTracing();
            }
            // If parameter says to stop
            else {
                stopTracing();
            }
        }

        this.tracing = tracing;
    }

    private void startTracing()
    {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    private void stopTracing()
    {
        removeMouseListener(this);
        removeMouseMotionListener(this);
    }

    //region Helper methods


    private boolean cursorIsIdle() {
        return getTimeSinceLastUpdateMS() > TIMEOUT_MS;
    }

    private int getTimeSinceLastUpdateMS() {
        return (int) ((System.nanoTime() - timeOfLastUpdateNS) / 1e6);
    }

    private void startNewTrace(int x, int y) {
        currentCursorPoints = new ArrayList<Point>();
        currentTimeBetweenPointsMS = new ArrayList<Integer>();
        lastPointTraced = null;

        nowFormingTrace = true;
        addPoint(x, y);
    }

    private void addPoint(int x, int y) {
        currentCursorPoints.add(new Point(x, y));
        currentTimeBetweenPointsMS.add(getTimeSinceLastUpdateMS());
        timeOfLastUpdateNS = System.nanoTime();
        lastPointTraced = new Point(x, y);
    }

    private void finishCurrentTrace() {
        nowFormingTrace = false;
        notifyListeners(new Trace(currentCursorPoints, currentTimeBetweenPointsMS));
    }

    private void notifyListeners(Trace trace)
    {

        // TODO: test
        trace.normalizeTime();


        for (TracerOverlayListener listener : listeners) {
            listener.traceCreated(trace);
        }
    }

    private boolean pointOutOfBufferZone(Point pointInQuestion) {

        if (lastPointTraced == null) return true;

        // check x coordinate
        if (Math.abs(pointInQuestion.x - lastPointTraced.x) > BUFFER_ZONE_PX) {
            return true;
        }

        // check y coordinate
        if (Math.abs(pointInQuestion.y - lastPointTraced.y) > BUFFER_ZONE_PX) {
            return true;
        }

        // point still within range of buffer zone
        return false;
    }



    //endregion

    //region MouseInputListener methods
    public void mouseClicked(MouseEvent e) {
        dispatchEvent(e);
    }

    public void mousePressed(MouseEvent e) {
        evaluateMousePress(e);
        dispatchEvent(e);
    }

    public void mouseReleased(MouseEvent e) {
        dispatchEvent(e);
    }

    public void mouseEntered(MouseEvent e) {
        dispatchEvent(e);
    }

    public void mouseExited(MouseEvent e) {
        dispatchEvent(e);
    }

    public void mouseDragged(MouseEvent e) {
        dispatchEvent(e);
    }

    public void mouseMoved(MouseEvent e) {
        evaluateMouseMove(e);
        dispatchEvent(e);
    }
    //endregion

    private void evaluateMousePress(MouseEvent e) {
        if (nowFormingTrace) {
            finishCurrentTrace();
        }
    }

    private void evaluateMouseMove(MouseEvent e) {
        // if mouse hasn't really moved locations - prevents multiple calls
        if (!pointOutOfBufferZone(e.getPoint())) return;

        // get new position of mouse
        int x = e.getX();
        int y = e.getY();

        // start new trace if cursor is idle or if not currently building
        if (cursorIsIdle() || !nowFormingTrace) {
            startNewTrace(x, y);
        }
        else {
            addPoint(x, y);
        }
    }


    private void dispatchEvent(MouseEvent e) {


        Point glassPanePoint = e.getPoint();
        Point containerPoint = SwingUtilities.convertPoint(this,
                glassPanePoint, PANEL_BEING_TRACED);

        if (containerPoint.y < 0) { // we're not in the content pane
            // Could have special code to handle mouse events over
            // the menu bar or non-system window decorations, such as
            // the ones provided by the Java look and feel.
        }
        else {
            // The mouse event is probably over the content pane.
            // Find out exactly which component it's over.
            Component component = SwingUtilities.getDeepestComponentAt(
                    PANEL_BEING_TRACED, containerPoint.x, containerPoint.y);

            if (component != null) {
                // Forward events to component below
                Point componentPoint = SwingUtilities.convertPoint(
                        this, glassPanePoint, component);
                component.dispatchEvent(new MouseEvent(component, e
                        .getID(), e.getWhen(), e.getModifiers(),
                        componentPoint.x, componentPoint.y, e
                        .getClickCount(), e.isPopupTrigger()));

            }
        }


        requestFocus();

    }

    public void addListener(TracerOverlayListener listener)
    {
        listeners.add(listener);
    }

    public void removeListener(TracerOverlayListener listener)
    {
        listeners.remove(listener);
    }

}
