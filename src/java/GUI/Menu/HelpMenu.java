package GUI.Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import GUI.Frame;
import GUI.InfoBox;

public class HelpMenu extends JMenu {
    public HelpMenu(Frame frame)  {
        super("Help");
        JMenuItem instructions = new JMenuItem("How to play");
        instructions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        instructions.addActionListener(new MenuActionListener(frame));
        add(instructions);
    }

    static class MenuActionListener implements ActionListener {
        public Frame frame;
        public MenuActionListener(Frame frame) {
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("How to play")) {
                new InfoBox(frame, "How to Play", "Movement: W,A,S,D or UP,DOWN, RIGHT, LEFT\nPut Bombs: SPACE, X" +
                        "\nKill all enemies and find the portal", JOptionPane.QUESTION_MESSAGE);
            }
        }
    }
}
