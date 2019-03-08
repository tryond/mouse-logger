//import results_panel.ResultsFrame;
//import sequenced_tracer_menu_bar.SequencedTracerMenuBar;
//import sequenced_tracer_panel.DistractedSequencedTracerPanel;
//import sequenced_tracer_panel.SequencedTracerPanel;
//import sequenced_tracer_panel.SequencedTracerPanelListener;
//import sequenced_tracer_panel.button_panel.SingleButtonPanel;
//import sequenced_tracer_panel.button_panel.TransparentSingleButtonPanel;
//import sequenced_tracer_panel.button_sequence.ButtonSequenceFactory;
//import sequenced_tracer_panel.button_sequence.RandomButtonSequenceFactory;
//import sequenced_tracer_panel.distraction_panel.DistractionPanel;
//import sequenced_tracer_panel.distraction_panel.RandomShapesDepthPanel;
//import sequenced_tracer_panel.results_panel.ResultsPanel;
//import sequenced_tracer_panel.results_panel.TraceResultsPanel;
//import sequenced_tracer_panel.tracer_panel.Trace;
//import sequenced_tracer_panel.tracer_panel.TracerOverlayPanel;
//import sequenced_tracer_panel.tracer_panel.TransparentTracerOverlayPanel;
//import sequenced_tracer_status_panel.SequencedTracerSpeedStatusPanel;
//import sequenced_tracer_status_panel.SequencedTracerStatusPanel;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class Driver {
//
//    final static Dimension PANEL_SIZE = new Dimension(1000, 600);
//
//    final static int NUM_BUTTONS = 5;
//    final static int NUM_ROUNDS = 5;
//
//    final static int NUM_SHAPES = 50;
//    final static int MIN_SHAPE_SIZE = 50;
//    final static int MAX_SHAPE_SIZE = 100;
//
//    public static final int MIN_BUTTON_SIZE = 33;
//    public static final int MAX_BUTTON_SIZE = 60;
//
//    public static final int BOUNDARY = 50;
//    public static final int MIN_BUTTON_DISTANCE = 500;
//    public static final int MAX_BUTTON_DISTANCE = 1000;
//
//    public static final long MIN_BUTTON_DELAY_MS = 250;
//    public static final long MAX_BUTTON_DELAY_MS = 1000;
//
//    public static final int TIMEOUT_MS = 500;
//    public static final int BUFFER_ZONE_PX = 1;
//
//
//
//    public static void main(String[] args) {
//
//        // TODO: should this be final?
//        final JFrame frame = new JFrame("OTL - Windowed Tracer - v0.13");
//
//        ButtonSequenceFactory buttonSequenceFactory = new RandomButtonSequenceFactory(
//                MIN_BUTTON_SIZE,
//                MAX_BUTTON_SIZE,
//                PANEL_SIZE,
//                BOUNDARY,
//                MIN_BUTTON_DISTANCE,
//                MAX_BUTTON_DISTANCE,
//                MIN_BUTTON_DELAY_MS,
//                MAX_BUTTON_DELAY_MS
//        );
//
//        SingleButtonPanel singleButtonPanel = new TransparentSingleButtonPanel();
//
//        TracerOverlayPanel tracerOverlayPanel = new TransparentTracerOverlayPanel(
//                TIMEOUT_MS,
//                BUFFER_ZONE_PX,
//                singleButtonPanel
//        );
//
//        final ResultsPanel resultsPanel = new TraceResultsPanel();
//
//        DistractionPanel distractionPanel = new RandomShapesDepthPanel(
//                NUM_SHAPES,
//                MIN_SHAPE_SIZE,
//                MAX_SHAPE_SIZE
//        );
//
//        final SequencedTracerPanel sequencedTracerPanel = new DistractedSequencedTracerPanel(
//                buttonSequenceFactory,
//                singleButtonPanel,
//                tracerOverlayPanel,
//                resultsPanel,
//                distractionPanel
//        );
//
//        final SequencedTracerMenuBar sequencedTracerMenuBar = new SequencedTracerMenuBar(sequencedTracerPanel);
//
//        // TODO: should this be final?
//        final SequencedTracerStatusPanel sequencedTracerStatusPanel = new SequencedTracerSpeedStatusPanel(sequencedTracerPanel);
//
//        sequencedTracerPanel.setPreferredSize(PANEL_SIZE);
//
//        frame.setJMenuBar(sequencedTracerMenuBar);
//
//        frame.add(sequencedTracerPanel, BorderLayout.CENTER);
//
//        frame.add(sequencedTracerStatusPanel, BorderLayout.PAGE_END);
//
//        frame.pack();
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo(null);
//
//        frame.setResizable(false);
//        frame.setVisible(true);
//
//
//        sequencedTracerPanel.newSequence(
//                NUM_BUTTONS,
//                NUM_ROUNDS
//        );
//
//        sequencedTracerPanel.startSequence();
//
//        SequencedTracerPanelListener sequencedTracerPanelListener = new SequencedTracerPanelListener() {
//            public void newSequence() {
//                System.out.println("New Sequence");
//            }
//
//            public void updateSequence() {
//                System.out.println("Updated Sequence");
//            }
//
//            public void sequenceOver() {
//                System.out.println("Sequence Over");
//
//                ResultsFrame resultsFrame = new ResultsFrame(
//                        sequencedTracerPanel,
//                        sequencedTracerPanel.getTracesByPositionAndRound(),
//                        sequencedTracerPanel.getButtonPositions(),
//                        sequencedTracerPanel.getButtonSizes()
//                );
//
//                resultsFrame.pack();
//                resultsFrame.setLocationRelativeTo(frame);
//                resultsFrame.setVisible(true);
//            }
//
//            public void traceAdded(Trace trace) {
//                System.out.println(trace);
//            }
//        };
//
//        sequencedTracerPanel.addListener(sequencedTracerPanelListener);
//
//    }
//
//}
