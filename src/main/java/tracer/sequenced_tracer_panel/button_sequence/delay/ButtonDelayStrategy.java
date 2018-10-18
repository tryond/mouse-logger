package sequenced_tracer_panel.button_sequence.delay;

public interface ButtonDelayStrategy {

    public long[] generateButtonDelays(int numButtons);

}