package sequenced_tracer_panel;

import sequenced_tracer_panel.button_panel.SingleButtonPanel;
import sequenced_tracer_panel.button_sequence.ButtonSequenceFactory;
import sequenced_tracer_panel.distraction_panel.DistractionPanel;
import sequenced_tracer_panel.results_panel.ResultsPanel;
import sequenced_tracer_panel.tracer_panel.TracerOverlayPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class DistractedSequencedTracerPanel extends SequencedTracerPanel {

    private final DistractionPanel distractionPanel;

    public DistractedSequencedTracerPanel(ButtonSequenceFactory buttonSequenceFactory, SingleButtonPanel singleButtonPanel, TracerOverlayPanel tracerOverlayPanel, ResultsPanel resultsPanel, DistractionPanel distractionPanel)
    {
        super(buttonSequenceFactory, singleButtonPanel, tracerOverlayPanel, resultsPanel);
        this.distractionPanel = distractionPanel;

        add(distractionPanel, Integer.valueOf(0));
    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);

        distractionPanel.setPreferredSize(preferredSize);

        distractionPanel.setBounds(0, 0, preferredSize.width, preferredSize.height);
    }

    @Override
    public void resetSequence() {
        distractionPanel.setDistractionVisible(false);
        super.resetSequence();
    }


    @Override
    protected void sequenceOver()
    {
        // Deactivate distraction panel

        distractionPanel.setDistractionVisible(false);

        // Call super method

        super.sequenceOver();
    }

    @Override
    public void buttonPressed(AbstractButton button) {
        distractionPanel.showNextDistraction();;
        super.buttonPressed(button);
    }

}
