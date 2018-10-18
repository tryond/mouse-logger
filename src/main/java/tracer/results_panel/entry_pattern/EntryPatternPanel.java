package results_panel.entry_pattern;

import results_panel.StarPanel;
import sequenced_tracer_panel.tracer_panel.Trace;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EntryPatternPanel extends StarPanel {

    public EntryPatternPanel(ArrayList<Trace>[][] traceArr)
    {
        super(traceArr);

        setBackground(BACKGROUND_COLOR);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g.create();

        String s = "Entry Pattern";

        FontMetrics fm = g2d.getFontMetrics();

        int x = this.getWidth() - fm.stringWidth(s) - 5;
        int y = this.getHeight() - 5;

        g2d.drawString(s, x, y);
    }

    protected void drawStrokes(Graphics2D g2d) {

        g2d.setStroke(new BasicStroke(1));

        Point a, b;

        Point endpoint;

        for (int r = 0; r < traceArr.length; ++r) {

            // Set trace color for the round

            if (r >= COLORS.length) {
                g2d.setColor(randomColor().darker());
            }
            else {
                g2d.setColor(COLORS[r]);
            }

            // TODO: test
            g2d.setColor(setAlpha(g2d.getColor(), ALPHA));

            for (int p = 0; p < traceArr[r].length; ++p) {
                for (Trace trace : traceArr[r][p]) {

                    endpoint = trace.points.get(trace.points.size() - 1);

                    for (int i = 0; i < trace.points.size() - 1; ++i) {

                        a = new Point(trace.points.get(i));
                        b = new Point(trace.points.get(i + 1));

                        a.x = a.x - endpoint.x + panelCenterPoint.x;
                        a.y = a.y - endpoint.y + panelCenterPoint.y;

                        b.x = b.x - endpoint.x + panelCenterPoint.x;
                        b.y = b.y - endpoint.y + panelCenterPoint.y;

                        g2d.drawLine(a.x, a.y, b.x, b.y);
                    }
                }
            }
        }

        // Place endpoint on panel center

        g2d.setColor(Color.black);
        g2d.fillOval(panelCenterPoint.x-2, panelCenterPoint.y-2, 4, 4);

    }

    private Color randomColor()
    {
        Random rng = new Random();

        return new Color(
                rng.nextInt(256),
                rng.nextInt(256),
                rng.nextInt(256)
        );
    }

    private Color setAlpha(Color refColor, int alpha) {

        return new Color(refColor.getRed(),
                refColor.getGreen(),
                refColor.getBlue(),
                alpha);

    }


}
