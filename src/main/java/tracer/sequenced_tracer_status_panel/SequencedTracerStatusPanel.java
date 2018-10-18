package sequenced_tracer_status_panel;

import sequenced_tracer_panel.SequencedTracerPanel;
import sequenced_tracer_panel.SequencedTracerPanelListener;
import sequenced_tracer_panel.tracer_panel.Trace;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class SequencedTracerStatusPanel extends JPanel implements SequencedTracerPanelListener {

    protected final SequencedTracerPanel SEQUENCED_TRACER_PANEL;

    protected JTextArea textArea;

    protected int currentButtonNumber;
    protected int totalButtonNumber;

    protected int currentRoundNumber;
    protected int totalRoundNumber;




    public SequencedTracerStatusPanel(SequencedTracerPanel sequencedTracerPanel)
    {
        super();

        setLayout(new BorderLayout());

        this.SEQUENCED_TRACER_PANEL = sequencedTracerPanel;
        SEQUENCED_TRACER_PANEL.addListener(this);

        setBorder(new EmptyBorder(10, 10, 10, 10));

        textArea = new JTextArea();
        textArea.setEditable(false);

        add(textArea, BorderLayout.WEST);

//        updateSequence();
    }

    protected void updateText() {

        textArea.setText("Position: " + currentButtonNumber + " / " + totalButtonNumber + "\n"
                + "Round: " + currentRoundNumber + " / " + totalRoundNumber
        );

        repaint();
    }

    public void newSequence()
    {

    }

    public void updateSequence()
    {
        currentButtonNumber = SEQUENCED_TRACER_PANEL.getButtonNumber() + 1;
        totalButtonNumber = SEQUENCED_TRACER_PANEL.getTotalButtons();

        currentRoundNumber = SEQUENCED_TRACER_PANEL.getRoundNumber() + 1;
        totalRoundNumber = SEQUENCED_TRACER_PANEL.getTotalRounds();

        updateText();
    }


    public void sequenceOver()
    {

    }

    public void traceAdded(Trace trace)
    {

    }
}
