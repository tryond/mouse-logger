package sequenced_tracer_menu_bar;

import sequenced_tracer_panel.SequencedTracerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SequencedTracerMenuBar extends JMenuBar {

    protected final SequencedTracerPanel SEQUENCED_TRACER_PANEL;

    protected final JMenu FILE_MENU;
    private final JMenu NEW_MENU;

    private final JMenuItem RESET_ITEM;
    private final JMenuItem RANDOM_ITEM;
    private final JMenuItem GET_CODE_ITEM;
    private final JMenuItem ENTER_CODE_ITEM;

    public SequencedTracerMenuBar(SequencedTracerPanel sequencedTracerPanel)
    {
        super();

        this.SEQUENCED_TRACER_PANEL = sequencedTracerPanel;

        // Create file menu

        FILE_MENU = new JMenu("File");

        // Create file menu items

        RESET_ITEM = new JMenuItem("Reset");
        NEW_MENU = new JMenu("New");
        GET_CODE_ITEM = new JMenuItem("Get Code");

        // Create new menu items

        RANDOM_ITEM = new JMenuItem("Random");
        ENTER_CODE_ITEM = new JMenuItem("Enter Code");

        // Assign methods to each file menu item

        RANDOM_ITEM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newSequence();
            }
        });

        RESET_ITEM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetSequence();
            }
        });

        GET_CODE_ITEM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveSequence();
            }
        });

        ENTER_CODE_ITEM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadSequence();
            }
        });

        // Add menu items to the new menu

        NEW_MENU.add(RANDOM_ITEM);
        NEW_MENU.add(ENTER_CODE_ITEM);

        // Add menu items to the file menu

        FILE_MENU.add(RESET_ITEM);
        FILE_MENU.add(NEW_MENU);
        FILE_MENU.add(GET_CODE_ITEM);

        // Add the file menu to the menu bar

        add(FILE_MENU);
    }

    // TODO
    public void newSequence()
    {
        String buttons = JOptionPane.showInputDialog(getParent(), "Number of Buttons?", 5);

        if ((buttons == null) || (buttons.length() <= 0)) return;

        String rounds = JOptionPane.showInputDialog(getParent(), "Number of Rounds?", 3);

        if ((rounds == null) || (rounds.length() <= 0)) return;

        SEQUENCED_TRACER_PANEL.newSequence(Integer.valueOf(buttons), Integer.valueOf(rounds));
    }

    // TODO
    public void resetSequence()
    {
        SEQUENCED_TRACER_PANEL.resetSequence();
    }

    // TODO
    public void saveSequence()
    {
        JLabel code = new JLabel(SEQUENCED_TRACER_PANEL.getSequenceCode());

        JTextArea textArea = new JTextArea();
        textArea.setText(SEQUENCED_TRACER_PANEL.getSequenceCode());

        JOptionPane.showMessageDialog(getParent(), textArea, "Sequence Code", JOptionPane.INFORMATION_MESSAGE);
    }

    // TODO
    public void loadSequence()
    {
        String code = JOptionPane.showInputDialog(getParent(), "Enter Sequence Code", null);

        SEQUENCED_TRACER_PANEL.loadSequenceWithCode(code);
    }





}
