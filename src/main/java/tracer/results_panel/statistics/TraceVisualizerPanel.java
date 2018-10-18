package results_panel.statistics;

import sequenced_tracer_panel.tracer_panel.Trace;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class TraceVisualizerPanel extends JPanel {

    private final int BORDER = 30;

    private ArrayList<Trace>[] positionTraces;
    private Point startButtonLocation;
    private Dimension startButtonSize;
    private Point endButtonLocation;
    private Dimension endButtonSize;

    private int xMin, xMax, yMin, yMax;

    private Color[] colorList;


    public TraceVisualizerPanel(ArrayList<Trace>[] positionTraces,
                                Point startButtonLocation,
                                Dimension startButtonSize,
                                Point endButtonLocation,
                                Dimension endButtonSize)
    {
        super();
        setLayout(null);

        this.positionTraces = positionTraces;
        this.startButtonSize = startButtonSize;
        this.startButtonLocation = startButtonLocation;
        this.endButtonSize = endButtonSize;
        this.endButtonLocation = endButtonLocation;

        setupBounds();

        // TODO: test
        setBackground(Color.white);

        colorList = new Color[]{
                new Color(65, 148, 182),
                new Color(166, 183, 118),
                new Color(230, 201, 94),
                new Color(26, 60, 84),
                new Color(176, 58, 42),
                new Color(225, 115, 55)
        };

        this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g.create();

        drawButtons(g2d);
        drawTraces(g2d);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(
                xMax - xMin + (2 * BORDER),
                yMax - yMin + (2 *BORDER)
                );
    }

    public void setupBounds()
    {

        if (positionTraces == null) return;

        Point firstPoint = positionTraces[0].get(0).points.get(0);

        xMin = firstPoint.x;
        xMax = firstPoint.x;

        yMin = firstPoint.y;
        yMax = firstPoint.y;


        // TODO: REMOVE
        System.out.println("xMin: " + xMin);
        System.out.println("xMax: " + xMax);

        // TODO: REMOVE
        System.out.println("yMin: " + yMin);
        System.out.println("yMax: " + yMax);


        // Check points in traces to see if they contain point mins and maxes

        for (ArrayList<Trace> traceList : positionTraces) {
            for (Trace trace : traceList) {
                for (Point point : trace.points)
                {

                    // Check x min and max

                    if (point.x < xMin) {
                        xMin = point.x;
                    }
                    else if (point.x > xMax) {
                        xMax = point.x;
                    }

                    // Check y min and max

                    if (point.y < yMin) {
                        yMin = point.y;
                    }
                    else if (point.y > yMax) {
                        yMax = point.y;
                    }

                }
            }
        }

        // Check startButton corners

        Point lowerButtonCorner;
        Point upperButtonCorner;

        lowerButtonCorner = new Point(startButtonLocation.x - (startButtonSize.width / 2),
                startButtonLocation.y - (startButtonSize.height / 2)
        );

        upperButtonCorner = new Point(startButtonLocation.x + (startButtonSize.width / 2),
                startButtonLocation.y + (startButtonSize.height / 2)
        );

        if (lowerButtonCorner.x < xMin) {
            xMin = lowerButtonCorner.x;
        }
        else if (upperButtonCorner.x > xMax) {
            xMax = upperButtonCorner.x;
        }

        if (lowerButtonCorner.y < yMin) {
            yMin = lowerButtonCorner.y;
        }
        else if (upperButtonCorner.y > yMax) {
            yMax = upperButtonCorner.y;
        }

        // Check endButton corners

        lowerButtonCorner = new Point(endButtonLocation.x - (endButtonSize.width / 2),
                endButtonLocation.y - (endButtonSize.height / 2)
        );

        upperButtonCorner = new Point(endButtonLocation.x + (endButtonSize.width / 2),
                endButtonLocation.y + (endButtonSize.height / 2)
        );

        if (lowerButtonCorner.x < xMin) {
            xMin = lowerButtonCorner.x;
        }
        else if (upperButtonCorner.x > xMax) {
            xMax = upperButtonCorner.x;
        }

        if (lowerButtonCorner.y < yMin) {
            yMin = lowerButtonCorner.y;
        }
        else if (upperButtonCorner.y > yMax) {
            yMax = upperButtonCorner.y;
        }

    }

    private void drawTraces(Graphics2D g2d)
    {

        if (positionTraces == null) return;

        Point a;
        Point b = new Point();

        g2d.setColor(Color.black);

        for (int r = 0; r < positionTraces.length; ++r) {

            g2d.setStroke(new BasicStroke(1));

            // Set trace color

            if (r >= colorList.length) {
                g2d.setColor(Color.black);
            } else {
                g2d.setColor(colorList[r]);
            }

            // Draw traces in round

            for (Trace trace : positionTraces[r]) {
                for (int i = 0; i < trace.points.size() - 1; ++i) {

                    a = trace.points.get(i);
                    b = trace.points.get(i + 1);


                    a = new Point(trace.points.get(i));
                    b = new Point(trace.points.get(i + 1));

                    a.x -= xMin - BORDER;
                    a.y -= yMin - BORDER;

                    b.x -= xMin - BORDER;
                    b.y -= yMin - BORDER;


                    g2d.drawLine(a.x, a.y, b.x, b.y);

                    g2d.fillOval(b.x - 1, b.y - 1, 2, 2);

                }

                // place endpoint on trace

                g2d.fillOval(b.x-2, b.y-2, 4, 4);

            }
        }

    }

    private void drawButtons(Graphics2D g2d)
    {
        Point[] buttonLocationArr = new Point[]{startButtonLocation, endButtonLocation};
        Dimension[] buttonSizeArr = new Dimension[]{startButtonSize, endButtonSize};

        for (int i = 0; i < buttonLocationArr.length; ++i) {
            g2d.setColor(Color.gray);

            int posX = buttonLocationArr[i].x;
            int posY = buttonLocationArr[i].y;

            int centerX = posX - (int)(buttonSizeArr[i].getWidth() / 2);
            int centerY = posY - (int)(buttonSizeArr[i].getHeight() / 2);

            centerX -= xMin - BORDER;
            centerY -= yMin - BORDER;

            int width = buttonSizeArr[i].width;
            int height = buttonSizeArr[i].height;

            final float dash1[] = {10.0f};
            final BasicStroke dashed =
                    new BasicStroke(1.0f,
                            BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER,
                            10.0f, dash1, 0.0f);
            g2d.setStroke(dashed);

            g2d.draw(
                    new RoundRectangle2D.Double(
                            centerX,
                            centerY,
                            width,
                            height,
                            10,
                            10
                    )
            );
        }
    }

}
