package GUI.Menu;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import GUI.Frame;
import Main.Game;

public class OptionsMenu extends JMenu implements ChangeListener {
    Frame frame;

    public OptionsMenu(Frame frame) {
        super("Options");
        this.frame = frame;

        JMenuItem pause = new JMenuItem("Pause");
        pause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        pause.addActionListener(new MenuActionListener(frame));
        add(pause);

        JMenuItem resume = new JMenuItem("Resume");
        resume.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        resume.addActionListener(new MenuActionListener(frame));
        add(resume);

        addSeparator();

        add(new JLabel("Size: "));

        JSlider sizeRange = new JSlider(JSlider.HORIZONTAL, 1, 5, 3);

        sizeRange.setMajorTickSpacing(2);
        sizeRange.setMinorTickSpacing(1);
        sizeRange.setPaintTicks(true);
        sizeRange.setPaintLabels(true);
        sizeRange.addChangeListener(this);

        add(sizeRange);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int fps = (int)source.getValue();
            Game.SCALE = fps;
            System.out.println(Game.SCALE);
            frame.gamePanel.changeSize();
            frame.revalidate();
            frame.pack();
        }
    }

    static class MenuActionListener implements ActionListener {
        public Frame frame;
        public MenuActionListener(Frame frame) {
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Pause")) {
                frame.pauseGame();
            }
            if(e.getActionCommand().equals("Resume")) {
                frame.resumeGame();
            }
        }
    }
}

