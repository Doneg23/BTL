package GUI;

import Exceptions.BBMException;
import Main.Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Game game;

    public GamePanel(Frame frame) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
        try {
            game = new Game(frame);
            add(game);
            game.setVisible(true);
        } catch (BBMException e) {
            e.printStackTrace();
            System.exit(0);
        }
        setVisible(true);
        setFocusable(true);
    }

    public void changeSize() {
        setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
        revalidate();
        repaint();
    }

    public Game getGame() {
        return game;
    }
}
