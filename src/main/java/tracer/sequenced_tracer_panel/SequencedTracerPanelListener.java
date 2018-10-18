package sequenced_tracer_panel;

import sequenced_tracer_panel.tracer_panel.Trace;

public interface SequencedTracerPanelListener {

    public void newSequence();

    public void updateSequence();

    public void sequenceOver();

    public void traceAdded(Trace trace);

}
