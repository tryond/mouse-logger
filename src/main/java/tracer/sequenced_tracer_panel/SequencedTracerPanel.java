package sequenced_tracer_panel;

import sequenced_tracer_panel.button_panel.SingleButtonListener;
import sequenced_tracer_panel.button_panel.SingleButtonPanel;
import sequenced_tracer_panel.button_sequence.ButtonSequence;
import sequenced_tracer_panel.button_sequence.ButtonSequenceFactory;
import sequenced_tracer_panel.results_panel.ResultsPanel;
import sequenced_tracer_panel.tracer_panel.Trace;
import sequenced_tracer_panel.tracer_panel.TracerOverlayListener;
import sequenced_tracer_panel.tracer_panel.TracerOverlayPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class SequencedTracerPanel extends JLayeredPane implements SingleButtonListener, TracerOverlayListener {

    private final ButtonSequenceFactory buttonSequenceFactory;
    private final SingleButtonPanel singleButtonPanel;
    private final TracerOverlayPanel tracerOverlayPanel;

    // TODO
    private final ResultsPanel resultsPanel;

    private ButtonSequence buttonSequence;

    private ArrayList<SequencedTracerPanelListener> listeners;

    private int buttonIndex = -1;
    private int roundIndex = 0;

    private boolean sequenceStarted = false;

    private ArrayList<Trace>[][] traceList;

    public SequencedTracerPanel(
            ButtonSequenceFactory buttonSequenceFactory,
            SingleButtonPanel singleButtonPanel,
            TracerOverlayPanel tracerOverlayPanel,
            ResultsPanel resultsPanel)
    {
        super();

        // Set instance variables

        this.buttonSequenceFactory = buttonSequenceFactory;
        this.singleButtonPanel = singleButtonPanel;
        this.tracerOverlayPanel = tracerOverlayPanel;

        // TODO
        this.resultsPanel = resultsPanel;

        listeners = new ArrayList<SequencedTracerPanelListener>();

        // Setup layout manager to cartesian

        setLayout(null);

        // Add listeners

        singleButtonPanel.addListener(this);
        tracerOverlayPanel.addListener(this);

        // Set panel bounds

        singleButtonPanel.setBounds(0, 0, getWidth(), getHeight());
        tracerOverlayPanel.setBounds(0, 0, getWidth(), getHeight());

        // TODO
        resultsPanel.setBounds(0, 0, getWidth(), getHeight());

        // Add panels to layered panel

        add(singleButtonPanel, Integer.valueOf(1));
        add(tracerOverlayPanel, Integer.valueOf(2));

        // TODO
        add(resultsPanel, Integer.valueOf(3));

        // Set blank button sequence

        buttonSequence = buttonSequenceFactory.createRandomButtonSequence(0, 0);
    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);

        singleButtonPanel.setPreferredSize(preferredSize);
        tracerOverlayPanel.setPreferredSize(preferredSize);

        singleButtonPanel.setBounds(0, 0, preferredSize.width, preferredSize.height);
        tracerOverlayPanel.setBounds(0, 0, preferredSize.width, preferredSize.height);
    }

    public void newSequence(int buttons, int rounds) {
        buttonSequence = buttonSequenceFactory.createRandomButtonSequence(buttons, rounds);
        resetSequence();
    }

    public void resetSequence() {

        // TODO
        resultsPanel.setVisible(false);

        // Reset sequence started

        sequenceStarted = false;

        // Reset indices

        buttonIndex = -1;
        roundIndex = 0;

        // Reset panels

        singleButtonPanel.setButtonVisible(false);
        tracerOverlayPanel.setTracing(false);

        // Reset traceResultsPanel list

        traceList = new ArrayList[buttonSequence.rounds][buttonSequence.positions];

        // Notify listeners

        notifyNewSequence();

        // Begin the sequence

        startSequence();
    }

    public String getSequenceCode() {
        return buttonSequence.code;
    }

    public void loadSequenceWithCode(String code) {
        buttonSequence = buttonSequenceFactory.createButtonSequenceFromCode(code);
        resetSequence();
    }

    public int getButtonNumber() {
        return buttonIndex;
    }

    public int getTotalButtons()
    {
        return buttonSequence.positions;
    }

    public Point[] getButtonPositions()
    {
        return buttonSequence.positionArr;
    }

    public int getRoundNumber()
    {
        return roundIndex;
    }

    public int getTotalRounds()
    {
        return buttonSequence.rounds;
    }

    public Dimension[] getButtonSizes()
    {
        return buttonSequence.sizeArr;
    }

    public ArrayList<Trace>[][] getTracesByPositionAndRound()
    {
        return traceList;
    }

    // TODO: is this logical given how tracerOverlay's value is set initially?
    public Dimension getTraceArea()
    {
        return tracerOverlayPanel.getSize();
    }

    public void startSequence()
    {
        // Set button position and size

        // Generate the start button
        if (buttonIndex == -1)
        {
            singleButtonPanel.setButtonSize(buttonSequence.sizeArr[buttonSequence.positions-1]);
            singleButtonPanel.setButtonCenterLocation(buttonSequence.positionArr[buttonSequence.positions-1]);
            singleButtonPanel.setButtonVisible(true);
        }
        // Generate the button at button index
        else {
            singleButtonPanel.setButtonSize(buttonSequence.sizeArr[buttonIndex]);
            singleButtonPanel.setButtonCenterLocation(buttonSequence.positionArr[buttonIndex]);
            singleButtonPanel.setButtonVisible(true);
        }
    }

    public void stopSequence()
    {
        singleButtonPanel.setButtonVisible(false);
        tracerOverlayPanel.setTracing(false);
    }

    protected void sequenceOver()
    {
        // Deactivate button

        singleButtonPanel.setButtonVisible(false);

        // Deactivate tracer

        tracerOverlayPanel.setTracing(false);

        // Notify listeners

        notifySequenceOver();

        // TODO
        resultsPanel.setResultsInformation(traceList,
                buttonSequence.positionArr,
                buttonSequence.sizeArr,
                buttonSequence.code
        );
        resultsPanel.displayResults();
        resultsPanel.setBounds(0, 0, getWidth(), getHeight());
    }

    public void addListener(SequencedTracerPanelListener listener)
    {
        listeners.add(listener);
    }

    public void removeListener(SequencedTracerPanelListener listener)
    {
        listeners.remove(listener);
    }

    public void buttonPressed(AbstractButton button)
    {
        if (!sequenceStarted)
        {
            sequenceStarted = true;
            tracerOverlayPanel.setTracing(true);
        }

        nextInSequence();
    }

    private void nextInSequence()
    {
        buttonIndex++;

        if (buttonIndex >= buttonSequence.positions)
        {
            buttonIndex = 0;
            roundIndex++;
        }

        if (roundIndex >= buttonSequence.rounds) {
            roundIndex = 0;
            sequenceOver();
        }
        else {

            traceList[roundIndex][buttonIndex] = new ArrayList<Trace>();

            singleButtonPanel.setButtonSize(buttonSequence.sizeArr[buttonIndex]);
            singleButtonPanel.setButtonCenterLocation(buttonSequence.positionArr[buttonIndex]);
            singleButtonPanel.setButtonVisible(true);

            notifySequenceChanged();
        }

    }

    public void traceCreated(Trace trace)
    {
        traceList[roundIndex][buttonIndex].add(trace);

        for (SequencedTracerPanelListener listener : listeners)
        {
            listener.traceAdded(trace);
        }
    }

    private void notifySequenceChanged()
    {
        for (SequencedTracerPanelListener listener : listeners)
        {
            listener.updateSequence();
        }
    }

    private void notifyNewSequence()
    {
        for (SequencedTracerPanelListener listener : listeners)
        {
            listener.newSequence();
        }
    }

    private void notifySequenceOver()
    {
        for (SequencedTracerPanelListener listener : listeners)
        {
            listener.sequenceOver();
        }
    }
}
