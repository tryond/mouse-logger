package sequenced_tracer_panel.results_panel;

import sequenced_tracer_panel.tracer_panel.Trace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class TraceResultsPanel extends ResultsPanel implements KeyListener {


    private ArrayList<Trace>[][] traceList;
    private Point[] buttonLocationArr;
    private Dimension[] buttonSizeArr;
    private String sequenceCode;

    private Color[] colorList;


    public TraceResultsPanel()
    {
        setLayout(null);

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


    }

    public void setResultsInformation(ArrayList<Trace>[][] traceList,
                                  Point[] buttonLocationArr,
                                  Dimension[] buttonSizeArr,
                                      String sequenceCode
    )
    {
        // TODO
        addKeyListener(this);

        this.traceList = traceList;
        this.buttonLocationArr = buttonLocationArr;
        this.buttonSizeArr = buttonSizeArr;

        this.sequenceCode = sequenceCode;

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g.create();

        drawButtons(g2d);
        drawTraces(g2d);

        drawCode(g2d);
    }

    private void drawButtons(Graphics2D g2d)
    {

        if (buttonLocationArr == null || buttonSizeArr == null) return;

        for (int i = 0; i < buttonLocationArr.length; ++i) {
            g2d.setColor(Color.gray);

            int posX = buttonLocationArr[i].x;
            int posY = buttonLocationArr[i].y;

            int centerX = posX - (int)(buttonSizeArr[i].getWidth() / 2);
            int centerY = posY - (int)(buttonSizeArr[i].getHeight() / 2);


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

    private void drawTraces(Graphics2D g2d)
    {

        if (traceList == null) return;

        Point a;
        Point b;

        g2d.setColor(Color.black);

        for (int r = 0; r < traceList.length; ++r) {


            g2d.setStroke(new BasicStroke(1));

            // Set trace color

            if (r >= colorList.length) {
                g2d.setColor(randomColor().darker());
            }
            else {
                g2d.setColor(colorList[r]);
            }

            // Draw traces in round

            for (int p = 0; p < traceList[r].length; ++p) {
                for (Trace trace : traceList[r][p]) {
                    for (int i = 0; i < trace.points.size()-1; ++i) {

                        a = trace.points.get(i);
                        b = trace.points.get(i+1);




                        g2d.drawLine(a.x, a.y, b.x, b.y);

                        g2d.fillOval(b.x-1, b.y-1, 2, 2);

                    }



                    Point endPoint = trace.points.get(trace.points.size()-1);
                    g2d.fillOval(endPoint.x-2, endPoint.y-2, 4, 4);

                }
            }
        }

    }

    private void drawCode(Graphics2D g2d)
    {
        FontMetrics fm = g2d.getFontMetrics();

        int x = this.getWidth() - fm.stringWidth(sequenceCode) - 5;
        int y = this.getHeight() - 5;

        g2d.setColor(Color.black);

        g2d.drawString(sequenceCode, x, y);
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

    public void displayResults() {
        setVisible(true);
        repaint();
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        System.out.println("Key Pressed");
        repaint();
    }

    public void keyReleased(KeyEvent e) {

    }
}
