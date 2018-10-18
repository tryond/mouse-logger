package results_panel.exit_pattern;

import results_panel.StarPanel;
import sequenced_tracer_panel.tracer_panel.Trace;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ExitPatternPanel extends StarPanel {

    public ExitPatternPanel(ArrayList<Trace>[][] traceArr)
    {
        super(traceArr);

        setBackground(BACKGROUND_COLOR);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g.create();

        String s = "Exit Pattern";

        FontMetrics fm = g2d.getFontMetrics();

        int x = this.getWidth() - fm.stringWidth(s) - 5;
        int y = this.getHeight() - 5;

        g2d.drawString(s, x, y);
    }

    protected void drawStrokes(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(1));

        Point a;
        Point b = new Point();

        Point startPoint;

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

                    startPoint = trace.points.get(0);

                    for (int i = 0; i < trace.points.size() - 1; ++i) {

                        a = new Point(trace.points.get(i));
                        b = new Point(trace.points.get(i + 1));

                        a.x = a.x - startPoint.x + panelCenterPoint.x;
                        a.y = a.y - startPoint.y + panelCenterPoint.y;

                        b.x = b.x - startPoint.x + panelCenterPoint.x;
                        b.y = b.y - startPoint.y + panelCenterPoint.y;

                        g2d.drawLine(a.x, a.y, b.x, b.y);
                    }

                    // place endpoint on trace

                    g2d.fillOval(b.x-2, b.y-2, 4, 4);

                }
            }
        }
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
