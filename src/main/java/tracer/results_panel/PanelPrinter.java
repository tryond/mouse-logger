//package results_panel;
//
//import org.apache.commons.io.FilenameUtils;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//
//public class PanelPrinter {
//
//    private static final double SCALE = 1.5;
//
//    public static BufferedImage getImage(JPanel panel)
//    {
//        BufferedImage image = null;
//
//        try
//        {
//            image = new BufferedImage((int)(panel.getWidth() * SCALE),
//                    (int)(panel.getHeight() * SCALE),
//                    BufferedImage.TYPE_INT_RGB);
//
//            Graphics2D graphics2D = image.createGraphics();
//            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//            graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
//
//            graphics2D.scale(SCALE, SCALE);
//
//            panel.paint(graphics2D);
//        }
//        catch(Exception exception)
//        {
//            //code
//
//            System.out.println(exception.getMessage());
//
//        }
//
//        return image;
//    }
//
//    public static void print(JPanel panel)
//    {
//        System.out.println("Print Sequence Trace");
//
//        File file;
//
//        JFileChooser c = new JFileChooser();
//        c.showSaveDialog(null);
//
//        file = c.getSelectedFile();
//        if (file == null) {
//            return;
//        }
//
//        if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("jpeg")) {
//            // filename is OK as-is
//        }
//        else {
//            file = new File(file.toString() + ".jpeg");  // append .xml if "foo.jpg.xml" is OK
//            file = new File(file.getParentFile(), FilenameUtils.getBaseName(file.getName())+".jpeg"); // ALTERNATIVELY: remove the extension (if any) and replace it with ".xml"
//        }
//
//        BufferedImage image = getImage(panel);
//
//        try {
//            ImageIO.write(image, "jpeg", file);
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//}
