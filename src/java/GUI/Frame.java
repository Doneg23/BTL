package GUI;

import Main.Game;
import GUI.Menu.Menu;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public GamePanel gamePanel;
    private JPanel containerPanel;
    private final InfoPanel infoPanel;
    private final Game game;

    public Frame() {
        setJMenuBar(new Menu(this));
        containerPanel = new JPanel(new BorderLayout());
        gamePanel = new GamePanel(this);
        infoPanel = new InfoPanel(gamePanel.getGame());

        containerPanel.add(infoPanel, BorderLayout.PAGE_START);
        containerPanel.add(gamePanel, BorderLayout.PAGE_END);

        game = gamePanel.getGame();
        add(containerPanel);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        game.start();
    }

    public void newGame() {
        game.getBoard().newGame();
    }

    public void changeLevel(int i) {
        game.getBoard().changeLevel(i);
    }

    public void pauseGame() {
        game.getBoard().gamePause();
    }

    public void resumeGame() {
        game.getBoard().gameResume();
    }

    public boolean isRunning() {
        return game.isRunning();
    }

    public void setTime(int time) {
        infoPanel.setTime(time);
    }

    public void setLives(int lives) {
        infoPanel.setLives(lives);
    }

    public void setPoints(int points) {
        infoPanel.setPoints(points);
    }


}
