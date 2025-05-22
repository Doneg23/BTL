package GUI.Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import GUI.Frame;

public class GameMenu extends JMenu {
    public Frame frame;

    public GameMenu(Frame frame) {
        super("Game");
        this.frame = frame;
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newGame.addActionListener(new MenuActionListener(frame));
        add(newGame);
    }

    static class MenuActionListener implements ActionListener {
        public Frame frame;
        public MenuActionListener(Frame frame) {
            this.frame = frame;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("New Game")) {
                frame.newGame();
            }
        }
    }

}