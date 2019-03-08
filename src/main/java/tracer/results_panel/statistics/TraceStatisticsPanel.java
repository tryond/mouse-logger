//package results_panel.statistics;
//
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.data.category.DefaultCategoryDataset;
//import sequenced_tracer_panel.tracer_panel.Trace;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.ArrayList;
//
//public class TraceStatisticsPanel extends JPanel {
//
//
//    private ArrayList<Trace>[][] traceArr;
//    private Dimension traceArea;
//    private Point[] buttonPositionArr;
//    private Dimension[] buttonSizeArr;
//
//    public TraceStatisticsPanel(ArrayList<Trace>[][] traceArr,
//                                Dimension traceArea,
//                                Point[] buttonPositionArr,
//                                Dimension[] buttonSizeArr
//    )
//    {
//        super(new BorderLayout());
//
//        this.traceArr = traceArr;
//        this.traceArea = traceArea;
//        this.buttonPositionArr = buttonPositionArr;
//        this.buttonSizeArr = buttonSizeArr;
//
//        // TODO: test
//
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//
//        for (int r = 0; r < traceArr.length; ++r) {
//
//            Trace testTrace = traceArr[r][0].get(0);
//
//            for (int i = 1; i < testTrace.points.size() - 1; ++i) {
//
//                double distance = distanceBetweenPoints(testTrace.points.get(i), testTrace.points.get(i + 1));
//
//                double speed = distance / (double) testTrace.timesMS.get(i+1);
//
//                // TODO: remove
//                System.out.print(speed + " ");
//
//                dataset.addValue(speed,
//                        "trace" + r,
//                        Integer.toString(i));
//
//            }
//            //TODO: remove
//            System.out.println();
//
//        }
//
//        JFreeChart lineChart = ChartFactory.createLineChart("Trace Velocity",
//                "Trace Velocity",
//                "Hello",
//                dataset,
//                PlotOrientation.VERTICAL,
//                true,
//                true,
//                false
//        );
//
//        ChartPanel chartPanel = new ChartPanel(lineChart);
//
//        this.add(chartPanel, BorderLayout.CENTER);
//
//
//    }
//
//    private double distanceBetweenPoints(Point a, Point b)
//    {
//        return Math.hypot(b.x-a.x, b.y-a.y);
//    }
//
//
//}
