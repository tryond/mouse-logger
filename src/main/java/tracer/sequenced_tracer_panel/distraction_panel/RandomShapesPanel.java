package sequenced_tracer_panel.distraction_panel;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class RandomShapesPanel extends DistractionPanel {

    protected final int NUM_SHAPES;
    protected final int MIN_SHAPE_SIZE;
    protected final int MAX_SHAPE_SIZE;

    protected final Random RNG;
    protected Color randomColor;
    protected Shape[] randomShapes;

    public RandomShapesPanel(int numShapes, int minShapeSize, int maxShapeSize) {

        super();

        // Set instance variables

        this.NUM_SHAPES = numShapes;
        this.MIN_SHAPE_SIZE = minShapeSize;
        this.MAX_SHAPE_SIZE = maxShapeSize;

        RNG = new Random();
        randomShapes = new Shape[NUM_SHAPES];
        randomColor = getRandomColor();

        // Display first shapes

//        showNextDistraction();
    }

    public void showNextDistraction() {

        // Generate new color and shapes

        randomColor = getRandomColor();
        for (int i = 0; i < NUM_SHAPES; ++i) {
            randomShapes[i] = getRandomShape();
        }

        // Set the sequenced_tracer_panel.distraction_panel to be visible

        setDistractionVisible(true);
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        // If the sequenced_tracer_panel.distraction_panel is set to visible, draw shapes

        if (isDistractionVisible()) {
            Graphics2D g2d = (Graphics2D) g.create();
            drawShapes(g2d);
            g2d.dispose();
        }
    }

    protected void drawShapes(Graphics2D g2d) {
        g2d.setColor(randomColor);

        for (int i = 0; i < NUM_SHAPES; ++i) {
            g2d.fill(randomShapes[i]);
        }
    }

    private Shape getRandomShape() {
        if (RNG.nextBoolean()) {
            return getRandomCircle();
        }
        else {
            return getRandomSquare();
        }
    }

    private Color getRandomColor() {
        return Color.getHSBColor(RNG.nextFloat(), 0.9f, 0.9f);
    }

    private Shape getRandomCircle() {

        int diameter = RNG.nextInt(MAX_SHAPE_SIZE - MIN_SHAPE_SIZE) + MIN_SHAPE_SIZE;
        int radius = diameter / 2;

        Point randomPosition = getRandomPosition();

        int centeredX = randomPosition.x - radius;
        int centeredY = randomPosition.y - radius;

        return new Ellipse2D.Double(centeredX, centeredY, radius, radius);
    }

    private Shape getRandomSquare() {

        int sideLength = RNG.nextInt(MAX_SHAPE_SIZE - MIN_SHAPE_SIZE) + MIN_SHAPE_SIZE;
        Point randomPosition = getRandomPosition();

        int centeredX = randomPosition.x - (sideLength / 2);
        int centeredY = randomPosition.y - (sideLength / 2);

        return new Rectangle(centeredX, centeredY, sideLength, sideLength);
    }

    private Point getRandomPosition() {
        int x, y;
        x = getWidth() <= 0 ? 0 : RNG.nextInt(getWidth());
        y = getHeight() <= 0 ? 0 : RNG.nextInt(getHeight());
        return new Point(x, y);
    }

}
