package sequenced_tracer_panel.button_sequence;

import java.awt.*;
import java.util.Arrays;

/**
 * A composition of positions, sizes and delays
 *
 * Each indexed value represents a position, size, and delay for a component
 * to be displayed.
 *
 * There are 'positions' unique positions repeated across 'rounds' rounds
 */
public class ButtonSequence {

    final public int rounds;       // num of times positions are revisited
    final public int positions;    // num of unique points

    final public Point[] positionArr;
    final public Dimension[] sizeArr;
    final public long[] delayArr;

    final public String code;

    /**
     * Creates a sequence wherein each indexed value provides a position to place a
     * component, a size to assign said component, and an amount of time to delay
     * until displaying the component.
     *
     * @param positions number of unique positions
     * @param rounds number of times positions are repeated
     * @param positionArr array of positions
     * @param sizeArr array of sizes
     * @param delayArr array of delays
     * @param code encapsulates button sequence details for button factory
     */
    public ButtonSequence(int positions,
                          int rounds,
                          Point[] positionArr,
                          Dimension[] sizeArr,
                          long[] delayArr,
                          String code)
    {
        this.positions = positions;
        this.rounds = rounds;

        this.positionArr = positionArr;
        this.sizeArr = sizeArr;
        this.delayArr = delayArr;

        this.code = code;
    }

    @Override
    public String toString() {
        return "Buttons: " + positions + "\n" +
                "Rounds: " + rounds + "\n" +
                "Positions: " + Arrays.toString(positionArr) + "\n" +
                "Sizes: " + Arrays.toString(sizeArr) + "\n" +
                "Delays: " + Arrays.toString(delayArr) + "\n" +
                "Code: " + code;
    }
}
