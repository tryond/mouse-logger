package results_panel;

import javax.swing.*;
import sequenced_tracer_panel.tracer_panel.Trace;
import java.awt.*;
import java.util.ArrayList;

public abstract class StarPanel extends JPanel {

    protected ArrayList<Trace>[][] traceArr;
    protected Point panelCenterPoint;

    final protected int ALPHA = 245;

    protected final Color[] COLORS = new Color[]{
            new Color(65, 148, 182),
            new Color(166, 183, 118),
            new Color(230, 201, 94),
            new Color(26, 60, 84),
            new Color(176, 58, 42),
            new Color(225, 115, 55)
    };

    final protected Color BACKGROUND_COLOR = Color.white;

    final protected Color AXIS_COLOR = new Color(225, 225, 225, 100);


    public StarPanel(ArrayList<Trace>[][] traceArr) {
        this.traceArr = traceArr;
    }

    public void paint(Graphics g) {
        super.paint(g);

        // determine the center of the new panel
        panelCenterPoint = getCenterOfFrame();

        Graphics2D g2d = (Graphics2D) g.create();

        drawAxis(g2d);
        drawStrokes(g2d);
    }



    private Point getCenterOfFrame()
    {
//        return new Point(getPreferredSize().width / 2, getPreferredSize().height / 2);
        return new Point(this.getWidth() / 2, this.getHeight() / 2);
    }


    private void drawAxis(Graphics2D g2) {

        g2.setColor(AXIS_COLOR);

        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2.setStroke(dashed);

        g2.drawLine(0, panelCenterPoint.y, getWidth(), panelCenterPoint.y);
        g2.drawLine(panelCenterPoint.x, 0, panelCenterPoint.x, getHeight());
    }

    protected abstract void drawStrokes(Graphics2D g2d);
}
