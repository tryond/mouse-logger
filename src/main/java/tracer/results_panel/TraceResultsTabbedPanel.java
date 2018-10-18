package results_panel;

import results_panel.entry_pattern.EntryPatternPanel;
import results_panel.exit_pattern.ExitPatternPanel;
import sequenced_tracer_panel.SequencedTracerPanel;
import sequenced_tracer_panel.results_panel.TraceResultsPanel;
import sequenced_tracer_panel.tracer_panel.Trace;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TraceResultsTabbedPanel extends JTabbedPane {

    // TODO: remove these
    private Dimension traceArea;
    private ArrayList<Trace>[][] traceArr;
    private Point[] buttonLocationArr;
    private Dimension[] buttonSizeArr;
    // TODO: end remove

    private SequencedTracerPanel referencePanel;

    private TraceResultsPanel traceResultsPanel;
    private EntryPatternPanel entryPatternPanel;
    private ExitPatternPanel exitPatternPanel;

    public TraceResultsTabbedPanel(SequencedTracerPanel referencePanel,
                                   ArrayList<Trace>[][] traceArr,
                                   Point[] buttonLocationArr,
                                   Dimension[] buttonSizeArr)
    {
        super();

        // TODO: remove
        this.traceArea = referencePanel.getPreferredSize();
        this.traceArr = traceArr;
        this.buttonLocationArr = buttonLocationArr;
        this.buttonSizeArr = buttonSizeArr;
        // TODO: end remove


        this.referencePanel = referencePanel;

        this.traceResultsPanel = new TraceResultsPanel();
        traceResultsPanel.setResultsInformation(traceArr,
                buttonLocationArr,
                buttonSizeArr,
                referencePanel.getSequenceCode()
        );

        this.entryPatternPanel = new EntryPatternPanel(traceArr);

        this.exitPatternPanel = new ExitPatternPanel(traceArr);

        this.add("Trace Results", this.traceResultsPanel);
        this.add("Entry Pattern", this.entryPatternPanel);
        this.add("Exit Pattern", this.exitPatternPanel);

        this.traceResultsPanel.setPreferredSize(referencePanel.getPreferredSize());
        this.entryPatternPanel.setPreferredSize(referencePanel.getPreferredSize());
        this.exitPatternPanel.setPreferredSize(referencePanel.getPreferredSize());

    }

    // TODO: REMOVE
    public ArrayList<Trace>[][] getTraceArr()
    {
        return traceArr;
    }
    public Dimension getTraceArea()
    {
        return traceArea;
    }
    public Point[] getButtonLocationArr()
    {
        return buttonLocationArr;
    }
    public Dimension[] getButtonSizeArr()
    {
        return buttonSizeArr;
    }
    // TODO: end REMOVE

    public JPanel getTraceResultsPanel()
    {
        return traceResultsPanel;
    }

    public JPanel getEntryPatternPanel()
    {
        return entryPatternPanel;
    }

    public JPanel getExitPatternPanel()
    {
        return exitPatternPanel;
    }


}
