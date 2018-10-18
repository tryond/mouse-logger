package sequenced_tracer_panel.button_panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class SingleButtonPanel extends JPanel implements ActionListener {

    protected AbstractButton button;
    private ArrayList<SingleButtonListener> listeners;

    public SingleButtonPanel()
    {
        super();
        setLayout(null);

        setupButton();

        add(button);
        button.addActionListener(this);

        setButtonSize(new Dimension(0, 0));
        setButtonCenterLocation(new Point(0, 0));

        listeners = new ArrayList<SingleButtonListener>();
        repaint();
    }

    protected abstract void setupButton();

    public boolean isButtonVisible()
    {
        return button.isVisible();
    }

    public void setButtonVisible(boolean buttonVisible)
    {
        button.setVisible(buttonVisible);
        repaint();
    }

    public Dimension getButtonSize()
    {
        return button.getSize();
    }

    public void setButtonSize(Dimension size)
    {
        button.setSize(size);
        updateButtonBounds();
        repaint();
    }

    public Point getButtonLocation()
    {
        return button.getLocation();
    }

    public void setButtonCenterLocation(Point centerLocation)
    {
        Point location = new Point(
                centerLocation.x - (button.getWidth() / 2),
                centerLocation.y - (button.getHeight() / 2)
        );


        button.setLocation(location);
        updateButtonBounds();
        repaint();
    }

    private void updateButtonBounds()
    {
        button.setBounds(button.getLocation().x,
                button.getLocation().y,
                button.getWidth(),
                button.getHeight()
        );
    }

    public void addListener(SingleButtonListener listener)
    {
        listeners.add(listener);
    }

    public void removeListener(SingleButtonListener listener)
    {
        listeners.remove(listener);
    }

    public void actionPerformed(ActionEvent e)
    {
        for (SingleButtonListener listener : listeners) {
            listener.buttonPressed((AbstractButton)e.getSource());
        }
    }
}
