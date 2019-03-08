//package results_panel;
//
//import sequenced_tracer_panel.SequencedTracerPanel;
//import sequenced_tracer_panel.tracer_panel.Trace;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.ArrayList;
//
//public class ResultsFrame extends JFrame {
//
//    private TraceResultsTabbedPanel tabbedPanel;
//    private ResultsPanelMenuBar menuBar;
//
//    public ResultsFrame(SequencedTracerPanel referencePanel,
//                        ArrayList<Trace>[][] traceArr,
//                        Point[] buttonLocationArr,
//                        Dimension[] buttonSizeArr)
//    {
//        super("Results");
//
//        this.tabbedPanel = new TraceResultsTabbedPanel(referencePanel,
//                traceArr,
//                buttonLocationArr,
//                buttonSizeArr
//        );
//
//        this.menuBar = new ResultsPanelMenuBar(tabbedPanel);
//
//        this.add(tabbedPanel, BorderLayout.CENTER);
//
//        this.setJMenuBar(menuBar);
//
//        this.setResizable(false);
//    }
//
//
//}
