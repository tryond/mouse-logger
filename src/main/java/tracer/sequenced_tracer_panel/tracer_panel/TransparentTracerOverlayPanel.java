package sequenced_tracer_panel.tracer_panel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

public class TransparentTracerOverlayPanel extends TracerOverlayPanel {


    public TransparentTracerOverlayPanel(int timeoutMS, int bufferZonePx, JPanel tracedPanel) {
        super(timeoutMS, bufferZonePx, tracedPanel);
        setOpaque(false);
    }
}
