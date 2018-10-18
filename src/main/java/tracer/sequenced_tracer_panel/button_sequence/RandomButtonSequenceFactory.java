package sequenced_tracer_panel.button_sequence;

import sequenced_tracer_panel.button_sequence.delay.RandomButtonDelay;
import sequenced_tracer_panel.button_sequence.position.RandomButtonPosition;
import sequenced_tracer_panel.button_sequence.size.RandomButtonSize;

import java.awt.*;
import java.util.Random;

public class RandomButtonSequenceFactory extends ButtonSequenceFactory {

    public RandomButtonSequenceFactory(int minButtonSize,
                                       int maxButtonSize,
                                       Dimension panelSize,
                                       int boundary,
                                       int minButtonDistance,
                                       int maxButtonDistance,
                                       long minButtonDelayMS,
                                       long maxButtonDelayMS) {

        RNG = new Random();

        buttonSizeStrategy = new RandomButtonSize(minButtonSize, maxButtonSize, RNG);
        buttonPositionStrategy = new RandomButtonPosition(panelSize, minButtonDistance, maxButtonDistance, boundary, RNG);
        buttonDelayStrategy = new RandomButtonDelay(minButtonDelayMS, maxButtonDelayMS, RNG);
    }
}
