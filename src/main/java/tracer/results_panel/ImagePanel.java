package results_panel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    private BufferedImage image;

    public ImagePanel(BufferedImage image)
    {
        super();
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        super.paintComponent(g);

        double scaleFactor = Math.min(1d, getScaleFactorToFit(new Dimension(image.getWidth(), image.getHeight()), getSize()));

        int scaleWidth = (int) Math.round(image.getWidth() * scaleFactor);
        int scaleHeight = (int) Math.round(image.getHeight() * scaleFactor);

        Image scaled = image.getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);

        int width = getWidth() - 1;
        int height = getHeight() - 1;

        int x = (width - scaled.getWidth(this)) / 2;
        int y = (height - scaled.getHeight(this)) / 2;

        g.drawImage(scaled, x, y, this);


    }

    public static double getScaleFactorToFit(Dimension original, Dimension toFit) {

        double dScale = 1d;

        if (original != null && toFit != null) {

            double dScaleWidth = getScaleFactor(original.width, toFit.width);
            double dScaleHeight = getScaleFactor(original.height, toFit.height);

            dScale = Math.min(dScaleHeight, dScaleWidth);

        }

        return dScale;

    }

    public static double getScaleFactor(int iMasterSize, int iTargetSize) {

        double dScale = 1;
        if (iMasterSize > iTargetSize) {

            dScale = (double) iTargetSize / (double) iMasterSize;

        } else {

            dScale = (double) iTargetSize / (double) iMasterSize;

        }

        return dScale;

    }

}
