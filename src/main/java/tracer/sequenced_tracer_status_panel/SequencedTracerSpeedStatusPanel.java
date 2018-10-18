package sequenced_tracer_status_panel;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import sequenced_tracer_panel.SequencedTracerPanel;
import sequenced_tracer_panel.tracer_panel.Trace;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class SequencedTracerSpeedStatusPanel extends SequencedTracerStatusPanel {

    private DescriptiveStatistics stats;

    private double averageVelocity = 0.000;
    private double standardDeviation = 0.000;

    // TODO: TEST
    protected String currentCode;
    protected JTextArea codeTextArea;

    public SequencedTracerSpeedStatusPanel(SequencedTracerPanel sequencedTracerPanel)
    {
        super(sequencedTracerPanel);

        setBackground(Color.darkGray);

        // TODO: TEST
        codeTextArea = new JTextArea();
        codeTextArea.setEditable(false);
        add(codeTextArea, BorderLayout.EAST);

        updateSequence();
    }

    protected void updateText() {

        String velocityStr = String.format("%.3f px/ms", averageVelocity);
        String sdStr = String.format("%.3f px/ms", standardDeviation);

        textArea.setText("Position: " + currentButtonNumber + " / " + totalButtonNumber + "\n"
                + "Round: " + currentRoundNumber + " / " + totalRoundNumber + "\n"
                + "Average Speed: " + velocityStr + "\n"
                + "Standard Deviation: " + sdStr
        );

        textArea.setForeground(Color.white);

        textArea.setBackground(Color.darkGray);

        // TODO: TEST
        codeTextArea.setText("Code: " + currentCode);
        codeTextArea.setForeground(Color.white);
        codeTextArea.setBackground(Color.darkGray);

        repaint();
    }

    private double getPixelsPerMS(Point a, Point b, int timeMS) {
        return distanceBetweenPoints(a, b) / (timeMS * 1.0);
    }

    private double distanceBetweenPoints(Point a, Point b) {
        return Math.hypot(b.x - a.x, b.y - a.y);
    }

    public void updateSequence()
    {

        currentCode = SEQUENCED_TRACER_PANEL.getSequenceCode();

        super.updateSequence();
    }

    public void newSequence()
    {
        stats = new DescriptiveStatistics();

        updateSequence();
    }

    public void traceAdded(Trace trace)
    {
        for (int i = 1; i < trace.points.size(); ++i) {
            stats.addValue(getPixelsPerMS(trace.points.get(i-1), trace.points.get(i), trace.timesMS.get(i)));
        }

        averageVelocity = stats.getMean();
        standardDeviation = stats.getStandardDeviation();

        updateText();
    }
}
