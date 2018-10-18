package sequenced_tracer_panel.button_panel;

import javax.swing.*;

public class TransparentSingleButtonPanel extends SingleButtonPanel {

    public TransparentSingleButtonPanel()
    {
        super();
        setOpaque(false);
    }

    protected void setupButton() {
        button = new JButton();
    }
}
