package sequenced_tracer_panel.distraction_panel;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

public class RandomShapesDepthPanel extends RandomShapesPanel {

    public RandomShapesDepthPanel(int numShapes, int minShapeSize, int maxShapeSize) {
        super(numShapes, minShapeSize, maxShapeSize);
    }

    @Override
    protected void drawShapes(Graphics2D g2d) {

        Arrays.sort(randomShapes, new Comparator<Shape>() {
            public int compare(Shape o1, Shape o2) {
                // Intentional: Reverse order for this demo

                int area1 = o1.getBounds().width * o1.getBounds().height;
                int area2 = o2.getBounds().width * o1.getBounds().width;

                return area1 - area2;
            }
        });
        double shapeArea;
        double minArea = (double) MIN_SHAPE_SIZE * MIN_SHAPE_SIZE;
        double maxArea = (double) MAX_SHAPE_SIZE * MAX_SHAPE_SIZE;

        for (int i = 0; i < NUM_SHAPES; ++i)
        {
            shapeArea = (double)(randomShapes[i].getBounds().width * randomShapes[i].getBounds().height);
            g2d.setColor(getRelativeColor(shapeArea, minArea, maxArea, randomColor));
            g2d.fill(randomShapes[i]);
        }
    }

    private Color getRelativeColor(double shapeArea, double minArea, double maxArea, Color color)
    {
        // Get HSB values of current color

        float[] hsbVals = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsbVals);

        // Get shape size relative to range of sizes

        float relativeSize = (float)((shapeArea - minArea) / (maxArea - minArea));

        float hue = hsbVals[0];
        float saturation = hsbVals[1];

        float multiplier = 1.2f;
        float lowerCap = .3f;

        float brightness = (relativeSize + lowerCap) * multiplier;

        if (brightness > 1.0f)
            brightness = 1.0f;

        Color retColor = new Color(Color.HSBtoRGB(hue, saturation, brightness));

        return retColor;
    }

}
