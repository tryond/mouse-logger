package sequenced_tracer_panel.distraction_panel;

import javax.swing.*;

public abstract class DistractionPanel extends JPanel {

    private boolean visible;

    public abstract void showNextDistraction();

    public boolean isDistractionVisible() {
        return visible;
    }

    public void setDistractionVisible(boolean visible) {
        this.visible = visible;
        repaint();
    }
}
