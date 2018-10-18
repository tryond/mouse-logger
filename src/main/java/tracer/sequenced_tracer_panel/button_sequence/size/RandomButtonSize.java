package sequenced_tracer_panel.button_sequence.size;

import java.awt.*;
import java.util.Random;

public class RandomButtonSize implements ButtonSizeStrategy {

    private final int MIN_SIZE;
    private final int MAX_SIZE;

    private final Random RNG;

    public RandomButtonSize(int minSize, int maxSize, Random rng) {

        if (minSize > maxSize) {
            MAX_SIZE = minSize;
            MIN_SIZE = maxSize;
        }
        else {
            MIN_SIZE = minSize;
            MAX_SIZE = maxSize;
        }

        RNG = rng;
    }

    public Dimension[] generateButtonSizes(int numSizes) {

        Dimension[] buttonSizeArr = new Dimension[numSizes];

        int range = MAX_SIZE - MIN_SIZE;
        for (int i = 0; i < numSizes; ++i) {
            int size = RNG.nextInt(range + 1) + MIN_SIZE;
            buttonSizeArr[i] = new Dimension(size, size);
        }

        return buttonSizeArr;
    }
}
