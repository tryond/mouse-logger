package sequenced_tracer_panel.button_sequence.delay;

import java.util.Random;

public class RandomButtonDelay implements ButtonDelayStrategy {

    final long MIN_DELAY;
    final long MAX_DELAY;

    final Random RNG;

    public RandomButtonDelay(long minDelay, long maxDelay, Random rng) {
        // minimum delay should be no less than zero and no greater than 5 seconds
        if (minDelay < 0) {
            MIN_DELAY = 0L;
        }
        else if (minDelay > 5000) {
            MIN_DELAY = 5000L;
        }
        else {
            MIN_DELAY = minDelay;
        }

        // maximum delay should be no less than MIN_DELAY
        MAX_DELAY = maxDelay < MIN_DELAY ? MIN_DELAY : maxDelay;

        this.RNG = rng;
    }

    public long[] generateButtonDelays(int numDelays)
    {
        long[] buttonDelayArr = new long[numDelays];

        for (int i = 0; i < numDelays; ++i) {
            buttonDelayArr[i] = getNextDelay();
        }

        return buttonDelayArr;
    }

    private long getNextDelay()
    {
        int range = (int)(MAX_DELAY - MIN_DELAY);
        return (long)RNG.nextInt(range + 1) + MIN_DELAY;
    }
}
