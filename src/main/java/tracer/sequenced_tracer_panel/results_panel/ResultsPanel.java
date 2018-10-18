package sequenced_tracer_panel.results_panel;

import sequenced_tracer_panel.tracer_panel.Trace;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class ResultsPanel extends JPanel {

    public abstract void setResultsInformation(ArrayList<Trace>[][] traceList,
                                               Point[] buttonLocationArr,
                                               Dimension[] buttonSizeArr,
                                               String sequenceCode
    );

    public abstract void displayResults();

}
