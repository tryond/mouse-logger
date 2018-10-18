package results_panel.statistics.graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import sequenced_tracer_panel.tracer_panel.Trace;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class TraceAccelerationPanel extends JPanel {

    private ArrayList<Trace>[] traceArr;

    private Color[] colorList = new Color[]{
            new Color(65, 148, 182),
            new Color(166, 183, 118),
            new Color(230, 201, 94),
            new Color(26, 60, 84),
            new Color(176, 58, 42),
            new Color(225, 115, 55)
    };


    private Color[] colorList2 = new Color[]{
            new Color(134, 71, 237),
            new Color(235, 51, 118),
            new Color(238, 115, 96),
            new Color(235, 50, 93),
            new Color(235, 77, 239),
            new Color(236, 83, 117)
    };

    public TraceAccelerationPanel(ArrayList<Trace>[] traceArr)
    {
        super(new BorderLayout());

        this.traceArr = traceArr;

        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                null ,
                "Position" ,
                "Acceleration: pixels/ms^2" ,
                createDataset() ,
                PlotOrientation.VERTICAL ,
                false , false , false);

        ChartPanel chartPanel = new ChartPanel(xylineChart);

        final XYPlot plot = xylineChart.getXYPlot( );

        plot.setRenderer(createRenderer());

        plot.setBackgroundPaint(Color.white);

        org.jfree.chart.axis.NumberAxis rangeAxis = new org.jfree.chart.axis.NumberAxis("Acceleration");
        rangeAxis.setRange(-0.5, 0.5);
        plot.setRangeAxis(rangeAxis);

        this.add(chartPanel, BorderLayout.CENTER);
    }

    private XYDataset createDataset()
    {
        final XYSeriesCollection dataset = new XYSeriesCollection();

        for (int r = 0; r < traceArr.length; ++r) {

            XYSeries series = new XYSeries("Trace " + r);

            Trace testTrace = traceArr[r].get(0);

            double[] velocityArr = new double[testTrace.points.size()-1];
            int[] timeMSDeltaArr = new int[testTrace.points.size()-1];

            for (int i = 0; i < testTrace.points.size() - 1; ++i) {

                double distance = distanceBetweenPoints(testTrace.points.get(i), testTrace.points.get(i + 1));
                double speed = distance / (double) testTrace.timesMS.get(i+1);

//                series.add(i, speed);
                velocityArr[i] = speed;
            }

            for (int i = 0; i < velocityArr.length-1; ++i) {

                double velocityDelta = velocityArr[i+1] - velocityArr[i];
                double acceleration = velocityDelta / (double) testTrace.timesMS.get(i+1);

                series.add(i, acceleration);
            }


            dataset.addSeries(series);
        }

        return dataset;
    }

    private double distanceBetweenPoints(Point a, Point b)
    {
        return Math.hypot(b.x-a.x, b.y-a.y);
    }

    private XYLineAndShapeRenderer createRenderer()
    {
        XYSplineRenderer renderer = new XYSplineRenderer();

        for (int r = 0; r < traceArr.length; ++r)
        {
            renderer.setSeriesPaint(r, colorList[r]);
            renderer.setSeriesShape(r, new Ellipse2D.Double(-1, -1, 2, 2));
        }

        renderer.setPrecision(10);

        return renderer;
    }








}
