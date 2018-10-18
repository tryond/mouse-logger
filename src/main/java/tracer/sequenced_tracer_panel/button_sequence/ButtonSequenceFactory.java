package sequenced_tracer_panel.button_sequence;

import sequenced_tracer_panel.button_sequence.delay.ButtonDelayStrategy;
import sequenced_tracer_panel.button_sequence.position.ButtonPositionStrategy;
import sequenced_tracer_panel.button_sequence.size.ButtonSizeStrategy;

import java.util.Random;

public abstract class ButtonSequenceFactory {

    protected ButtonSizeStrategy buttonSizeStrategy;
    protected ButtonPositionStrategy buttonPositionStrategy;
    protected ButtonDelayStrategy buttonDelayStrategy;

    protected Random RNG;

    public ButtonSequence createRandomButtonSequence(int numButtons, int numRounds) {

        long seed = (long) RNG.nextInt((int)Math.pow(16, 4));

        return createRandomButtonSequence(numButtons, numRounds, seed);
    }

    public ButtonSequence createRandomButtonSequence(int numButtons, int numRounds, long seed) {

        RNG.setSeed(seed);

        String code = getCode(numButtons, numRounds, seed);

        return new ButtonSequence(
                numButtons,
                numRounds,
                buttonPositionStrategy.generateButtonPositions(numButtons),
                buttonSizeStrategy.generateButtonSizes(numButtons),
                buttonDelayStrategy.generateButtonDelays(numButtons),
                code
        );
    }

    public ButtonSequence createButtonSequenceFromCode(String code)
    {
        String[] seedValues = parseCode(code);


        int numButtons =  Integer.valueOf(seedValues[0]);
        int numRounds = Integer.valueOf(seedValues[1]);
        long seed = Long.parseLong(seedValues[2], 16);

        return createRandomButtonSequence(numButtons, numRounds, seed);
    }

    public String getCode(int numButtons, int numRounds, long seed)
    {
        return numButtons + "-" + numRounds + "-" + Long.toHexString(seed);
    }

    public String[] parseCode(String seedString)
    {
        return seedString.split("-");
    }
}
