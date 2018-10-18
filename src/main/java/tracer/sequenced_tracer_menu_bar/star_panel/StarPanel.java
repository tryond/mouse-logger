package sequenced_tracer_menu_bar.star_panel;

import sequenced_tracer_panel.tracer_panel.Trace;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class StarPanel extends JPanel {

    private ArrayList<Trace>[][] traceArr;

    private Point panelCenterPoint;

    public StarPanel(ArrayList<Trace>[][] traceArr) {
        this.traceArr = traceArr;
    }

    public void paint(Graphics g) {
        super.paint(g);

        setBackground(Color.gray);

        // determine the center of the new panel
        panelCenterPoint = getCenterOfFrame();

        Graphics2D g2d = (Graphics2D) g.create();

        drawAxis(g2d);
        drawStrokes(g2d);

        System.out.println("HELLO?!");
    }



    private Point getCenterOfFrame() {
        return new Point(getPreferredSize().width / 2, getPreferredSize().height / 2);
    }


    private void drawAxis(Graphics2D g2) {

        g2.setColor(new Color(0, 0, 0, 127));

        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2.setStroke(dashed);

        g2.drawLine(0, panelCenterPoint.y, getWidth(), panelCenterPoint.y);
        g2.drawLine(panelCenterPoint.x, 0, panelCenterPoint.x, getHeight());
    }

    private void drawStrokes(Graphics2D g2d) {

        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.BLACK);

        Point a;
        Point b;

        Point endpoint;

        for (int r = 0; r < traceArr.length; ++r) {
            for (int p = 0; p < traceArr[r].length; ++p) {
                for (Trace trace : traceArr[r][p]) {

                    endpoint = trace.points.get(trace.points.size() - 1);

                    for (int i = 0; i < trace.points.size() - 1; ++i) {

                        a = trace.points.get(i);
                        b = trace.points.get(i + 1);

//                        a.x = a.x - endpoint.x + panelCenterPoint.x;
//                        a.y = a.y - endpoint.y + panelCenterPoint.y;
//
//                        b.x = b.x - endpoint.x + panelCenterPoint.x;
//                        b.y = b.y - endpoint.y + panelCenterPoint.y;

                        g2d.drawLine(a.x, a.y, b.x, b.y);
                    }

                }
            }
        }
    }





}


