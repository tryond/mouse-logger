//package sequenced_tracer_menu_bar;
//
//import org.apache.commons.io.FilenameUtils;
//import sequenced_tracer_menu_bar.star_panel.StarPanel;
//import sequenced_tracer_panel.SequencedTracerPanel;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.image.BufferedImage;
//import java.io.File;
//
//public class SequencedTracerPrintMenuBar extends SequencedTracerMenuBar {
//
//    private final JMenuItem PRINT_SEQUENCE_ITEM;
//    private final JMenuItem PRINT_STAR_ITEM;
//
//    public SequencedTracerPrintMenuBar(SequencedTracerPanel sequencedTracerPanel)
//    {
//        super(sequencedTracerPanel);
//
//        // Create file menu items
//
//        PRINT_SEQUENCE_ITEM = new JMenuItem("Print Sequence Trace");
//        PRINT_STAR_ITEM = new JMenuItem("Print Sequence Star");
//
//        // Assign methods to each file menu item
//
//        PRINT_SEQUENCE_ITEM.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                printSequenceTrace();
//            }
//        });
//
//        PRINT_STAR_ITEM.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                printSequenceStar();
//            }
//        });
//
//        // Add menu items to the file menu
//
//        FILE_MENU.add(PRINT_SEQUENCE_ITEM);
//        FILE_MENU.add(PRINT_STAR_ITEM);
//    }
//
//    // TODO
//    public void printSequenceTrace()
//    {
//        System.out.println("Print Sequence Trace");
//
//        File file;
//
//        JFileChooser c = new JFileChooser();
//
//        c.showSaveDialog(getParent());
//        file = c.getSelectedFile();
//        if (file == null) {
//            return;
//        }
//
//        if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("jpeg")) {
//            // filename is OK as-is
//        } else {
//            file = new File(file.toString() + ".jpeg");  // append .xml if "foo.jpg.xml" is OK
//            file = new File(file.getParentFile(), FilenameUtils.getBaseName(file.getName())+".jpeg"); // ALTERNATIVELY: remove the extension (if any) and replace it with ".xml"
//        }
//
//        try
//        {
//            BufferedImage image = new BufferedImage(SEQUENCED_TRACER_PANEL.getWidth() * 2,
//                    SEQUENCED_TRACER_PANEL.getHeight() * 2,
//                    BufferedImage.TYPE_INT_RGB);
//
//            Graphics2D graphics2D = image.createGraphics();
//            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//            graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
//
//            // TODO
//            graphics2D.scale(2.0, 2.0);
//
//
//            SEQUENCED_TRACER_PANEL.paint(graphics2D);
//
//            String s = SEQUENCED_TRACER_PANEL.getSequenceCode();
//            FontMetrics fm = graphics2D.getFontMetrics();
//            // int x = image.getWidth() - fm.stringWidth(s) - 5;
//            // int y = fm.getHeight();
//
//            int x = SEQUENCED_TRACER_PANEL.getWidth() - fm.stringWidth(s) - 5;
//            int y = SEQUENCED_TRACER_PANEL.getHeight() - 5;
//
//            graphics2D.drawString(s, x, y);
//
//            ImageIO.write(image,"jpeg", file);
//        }
//        catch(Exception exception)
//        {
//            //code
//        }
//
//    }
//
//    // TODO
//    public void printSequenceStar()
//    {
//        System.out.println("Print Sequence Star");
//
//        File file;
//
//        JFileChooser c = new JFileChooser();
//
//        c.showSaveDialog(getParent());
//        file = c.getSelectedFile();
//        if (file == null) {
//
//            System.out.println("HERE?");
//
//
//            return;
//        }
//
//        System.out.println("Passed the test");
//
//        if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("jpeg")) {
//            // filename is OK as-is
//        } else {
//            file = new File(file.toString() + ".jpeg");  // append .xml if "foo.jpg.xml" is OK
//            file = new File(file.getParentFile(), FilenameUtils.getBaseName(file.getName())+".jpeg"); // ALTERNATIVELY: remove the extension (if any) and replace it with ".xml"
//        }
//
//        try {
//            BufferedImage image = new BufferedImage(SEQUENCED_TRACER_PANEL.getWidth() * 2,
//                    SEQUENCED_TRACER_PANEL.getHeight() * 2,
//                    BufferedImage.TYPE_INT_RGB);
//
//            Graphics2D graphics2D = image.createGraphics();
//            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//            graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
//
//            // TODO
//            graphics2D.scale(2.0, 2.0);
//
//            // TODO
//            StarPanel starPanel = new StarPanel(SEQUENCED_TRACER_PANEL.getTracesByPositionAndRound());
//            starPanel.setPreferredSize(SEQUENCED_TRACER_PANEL.getPreferredSize());
//            starPanel.paint(graphics2D);
//
//            System.out.println("After painting star");
//
//            String s = SEQUENCED_TRACER_PANEL.getSequenceCode();
//            FontMetrics fm = graphics2D.getFontMetrics();
//            // int x = image.getWidth() - fm.stringWidth(s) - 5;
//            // int y = fm.getHeight();
//
//            int x = SEQUENCED_TRACER_PANEL.getWidth() - fm.stringWidth(s) - 5;
//            int y = SEQUENCED_TRACER_PANEL.getHeight() - 5;
//
//            graphics2D.drawString(s, x, y);
//
//            ImageIO.write(image, "jpeg", file);
//        }
//        catch(Exception exception)
//        {
//            // TODO
//            exception.printStackTrace();
//            return;
//        }
//    }
//
//
//}
