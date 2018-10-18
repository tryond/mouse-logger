package results_panel;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.filter.DecodeOptions;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImage;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLine;
import results_panel.statistics.TraceStatisticsPanel;
import results_panel.statistics.TraceVisualizerPanel;
import results_panel.statistics.graph.TraceAccelerationPanel;
import results_panel.statistics.graph.TraceVelocityPanel;
import results_panel.statistics.graph.TraceXPanel;
import results_panel.statistics.graph.TraceYPanel;
import sequenced_tracer_panel.tracer_panel.Trace;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class ResultsPanelMenuBar extends JMenuBar {

    private TraceResultsTabbedPanel traceResultsTabbedPanel;

    private JMenu file;

    private JMenu export;

    private JMenuItem exportTraceResults;
    private JMenuItem exportEntryPattern;
    private JMenuItem exportExitPattern;

    private JMenuItem exportReport;

    public ResultsPanelMenuBar(TraceResultsTabbedPanel traceResultsTabbedPanel)
    {
        super();

        this.traceResultsTabbedPanel = traceResultsTabbedPanel;

        file = new JMenu("File");

        export = new JMenu("Export");

        exportTraceResults = new JMenuItem("Trace Results");
        exportEntryPattern = new JMenuItem("Entry Pattern");
        exportExitPattern = new JMenuItem("Exit Pattern");

        exportReport = new JMenuItem("Report");

        exportTraceResults.addActionListener(new HandleExportTraceResults());
        exportEntryPattern.addActionListener(new HandleExportEntryPattern());
        exportExitPattern.addActionListener(new HandleExportExitPattern());

        exportReport.addActionListener(new HandleExportReport());

        export.add(exportTraceResults);
        export.add(exportEntryPattern);
        export.add(exportExitPattern);

        export.add(exportReport);

        file.add(export);

        this.add(file);
    }


    private class HandleExportTraceResults implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JPanel panel = traceResultsTabbedPanel.getTraceResultsPanel();
            PanelPrinter.print(panel);
        }
    }

    private class HandleExportEntryPattern implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JPanel panel = traceResultsTabbedPanel.getEntryPatternPanel();
            PanelPrinter.print(panel);
        }
    }

    private class HandleExportExitPattern implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JPanel panel = traceResultsTabbedPanel.getExitPatternPanel();
            PanelPrinter.print(panel);
        }
    }

    private class HandleExportReport implements ActionListener {
        public void actionPerformed(ActionEvent e) {


            // TODO: TEST
            PDDocument document = new PDDocument();

            JPanel[] graphPanels = generateGraphPanels();

            try {

                addPanelToPDF(document, traceResultsTabbedPanel.getTraceResultsPanel());
                addPanelToPDF(document, traceResultsTabbedPanel.getEntryPatternPanel());
                addPanelToPDF(document, traceResultsTabbedPanel.getExitPatternPanel());


                // TODO: TEST
                // Add graphs to PDF

                for (int i = 0; i < graphPanels.length; ++i) {
                    if (graphPanels[i] != null) {
                        addPanelToPDF(document, graphPanels[i]);
                    }
                }


                // Get file to save to

                File file;

                JFileChooser c = new JFileChooser();
                c.showSaveDialog(null);
                file = c.getSelectedFile();
                if (file == null) {
                    return;
                }

                if (!FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("pdf")) {
                    file = new File(file.toString() + ".pdf");  // append .xml if "foo.jpg.xml" is OK
                    file = new File(file.getParentFile(), FilenameUtils.getBaseName(file.getName()) + ".pdf"); // ALTERNATIVELY: remove the extension (if any) and replace it with ".xml"
                }

                // Save the document

                document.save(file);
                document.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }


//            for (int i = 1; i < traceResultsTabbedPanel.getTraceArr()[0].length; ++i) {
//
//                // TODO: test
//                JFrame frame = new JFrame("Test Statistics");
//
//                frame.getContentPane().setBackground(Color.darkGray);
//
//                // TODO: does this work?
////                frame.setLayout(new GridBagLayout());
//
//                ArrayList<Trace>[] traceList = new ArrayList[traceResultsTabbedPanel.getTraceArr().length];
//
//                int position = i;
//
//                for (int r = 0; r < traceResultsTabbedPanel.getTraceArr().length; ++r) {
//                    traceList[r] = traceResultsTabbedPanel.getTraceArr()[r][position];
//                }
//
//                Point[] buttonLocationArr = traceResultsTabbedPanel.getButtonLocationArr();
//                Dimension[] buttonSizeArr = traceResultsTabbedPanel.getButtonSizeArr();
//
//                TraceVisualizerPanel statsPanel = new TraceVisualizerPanel(traceList,
//                        buttonLocationArr[position - 1],
//                        buttonSizeArr[position - 1],
//                        buttonLocationArr[position],
//                        buttonSizeArr[position]
//                );
//
//                statsPanel.setSize(statsPanel.getPreferredSize());
//
//                // TODO: TEST
//
//                JPanel panel = statsPanel;
//
//                int SCALE = 10;
//
//                BufferedImage image;
//
//                JPanel imagePanel = new JPanel();
//
//                try
//                {
//                    image = new BufferedImage((int)(panel.getWidth() * SCALE),
//                            (int)(panel.getHeight() * SCALE),
//                            BufferedImage.TYPE_INT_RGB);
//
//                    Graphics2D graphics2D = image.createGraphics();
//                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                    graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//                    graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
//
//                    // TODO
//                    graphics2D.scale(SCALE, SCALE);
//
//
//                    panel.paint(graphics2D);
//
//                    // TODO: TEST 2
//                    imagePanel = new ImagePanel(image);
//
//                }
//                catch(Exception exception)
//                {
//                    //code
//                }
//
//                // TODO: END TEST
//
//
//                imagePanel.setBorder(BorderFactory.createBevelBorder(3));
//
//
//                JPanel graphContainer = new JPanel();
//
//                if (statsPanel.getPreferredSize().height > statsPanel.getPreferredSize().width) {
//                    frame.setLayout(new GridLayout(1, 2));
//                    graphContainer.setLayout(new GridLayout(2, 1));
//
//
//
//
//                }
//                else {
//                    frame.setLayout(new GridLayout(2, 1));
//                    graphContainer.setLayout(new GridLayout(1, 1));
//
//                }
//
//                JPanel traceVelocityPanel = new TraceVelocityPanel(traceList);
//                traceVelocityPanel.setPreferredSize(new Dimension(400, 400));
//
//                JPanel traceAccelerationPanel = new TraceAccelerationPanel(traceList);
//                traceAccelerationPanel.setPreferredSize(new Dimension(400, 400));
//
//                graphContainer.add(traceVelocityPanel);
//                graphContainer.add(traceAccelerationPanel);
//
//                graphContainer.setBorder(BorderFactory.createTitledBorder("Trace Statistics"));
//
//                frame.getContentPane().add(imagePanel, BorderLayout.CENTER);
//                frame.getContentPane().add(graphContainer);
//
//
//                frame.pack();
//                frame.setVisible(true);
//
//
//
//
//
//
//            }
//        }
        }


        public JPanel[] generateGraphPanels()
        {

            // Setup array to return

            ArrayList<Trace>[][] tracesAtRoundAndPosition = traceResultsTabbedPanel.getTraceArr();
            int numRounds = tracesAtRoundAndPosition.length;
            int numPositions = tracesAtRoundAndPosition[0].length;

            Point[] buttonLocationArr = traceResultsTabbedPanel.getButtonLocationArr();
            Dimension[] buttonSizeArr = traceResultsTabbedPanel.getButtonSizeArr();

            JPanel[] graphPanelArr = new JPanel[numPositions];

            // Setup variables

            Point startLocation, endLocation;
            Dimension startSize, endSize;

            // For each position gather traces from all rounds

            for (int pos = 0; pos < numPositions; ++pos) {

                graphPanelArr[pos] = new JPanel();

                // Fill array with first trace from each round

                ArrayList<Trace>[] positionTraceArr = new ArrayList[numRounds];

                for (int round = 0; round < numRounds; ++round)
                {
                    positionTraceArr[round] = tracesAtRoundAndPosition[round][pos];
                }



                if (pos == 0) {
                    startLocation = buttonLocationArr[buttonLocationArr.length-1];
                    startSize = buttonSizeArr[buttonSizeArr.length-1];
                }
                else {
                    startLocation = buttonLocationArr[pos-1];
                    startSize = buttonSizeArr[pos-1];
                }
                endLocation = buttonLocationArr[pos];
                endSize = buttonSizeArr[pos];


                TraceVisualizerPanel statsPanel = new TraceVisualizerPanel(
                        positionTraceArr,
                        startLocation,
                        startSize,
                        endLocation,
                        endSize
                );

                statsPanel.setSize(statsPanel.getPreferredSize());

                System.out.println(statsPanel.getSize());


                BufferedImage bimage = PanelPrinter.getImage(statsPanel);

                JPanel imagePanel = new ImagePanel(bimage);

                JPanel graphContainer = new JPanel();

                if (statsPanel.getPreferredSize().height > statsPanel.getPreferredSize().width) {
                    graphPanelArr[pos].setLayout(new GridLayout(1, 2));
                    graphContainer.setLayout(new GridLayout(2, 1));
                } else {
                    graphPanelArr[pos].setLayout(new GridLayout(2, 1));
                    graphContainer.setLayout(new GridLayout(1, 1));

                }

//                JPanel traceVelocityPanel = new TraceVelocityPanel(positionTraceArr);
                JPanel traceVelocityPanel = new TraceXPanel(positionTraceArr);

//                traceVelocityPanel.setPreferredSize(new Dimension(400, 400));

//                JPanel traceAccelerationPanel = new TraceAccelerationPanel(positionTraceArr);
                JPanel traceAccelerationPanel = new TraceYPanel(positionTraceArr);

//                traceAccelerationPanel.setPreferredSize(new Dimension(400, 400));

                graphContainer.add(traceVelocityPanel);
                graphContainer.add(traceAccelerationPanel);

                graphContainer.setBorder(BorderFactory.createTitledBorder("Trace Statistics"));

                // Add image panel and graphs panel to container panel

                imagePanel.setBackground(Color.white);
                graphContainer.setBackground(Color.white);

                graphPanelArr[pos].add(imagePanel);
                graphPanelArr[pos].add(graphContainer);

//                graphPanelArr[pos].setSize(traceResultsTabbedPanel.getTraceArea());

                graphPanelArr[pos].setBackground(Color.white);

                graphPanelArr[pos].setPreferredSize(traceResultsTabbedPanel.getTraceArea());


                // TODO: comment out
                JFrame frame = new JFrame();
                frame.getContentPane().add(graphPanelArr[pos]);
                frame.pack();
                frame.setVisible(false);
            }

            return graphPanelArr;

        }





        public void addPanelToPDF(PDDocument document, JPanel panel)
        {

            if (panel == null) {
                System.out.println("Empty Panel");
                return;
            }


            try {

                // Get result images

                BufferedImage bimage = PanelPrinter.getImage(panel);

                if (bimage == null) {
                    System.out.println("Null BufferedImage");
                    return;
                }

                // Create PDF pages

                PDPage pdPage = new PDPage(new PDRectangle(bimage.getWidth(), bimage.getHeight()));

                // Add pages to document

                document.addPage(pdPage);

                // Setup streams

                PDPageContentStream stream = new PDPageContentStream(document, pdPage);

                // Get PDImages from BufferedImages

                PDImageXObject pdImage = LosslessFactory.createFromImage(document, bimage);

                // Draw PDImages on document

                stream.drawImage(pdImage, 0, 0, pdImage.getWidth(), pdImage.getHeight());

                // Close content streams

                stream.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


    }
}


