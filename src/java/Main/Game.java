package Main;

import Exceptions.BBMException;
import Key.KeyInput;
import Graphics.Screen;
import GUI.Frame;
import Utils.SoundPlayer;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas {
    public static final int TILE_SIZE = 16;
    public static final int WIDTH = TILE_SIZE * 31 / 2;
    public static final int HEIGHT = 13 * TILE_SIZE;
    public static int SCALE = 3;
    public static final String TITLE = "Bomberman";

    public static final int TIME = 200;
    public static final int POINTS = 0;
    public static final int LIVES = 3;

    protected static int bombRate = 1;
    protected static int bombRadius = 1;
    protected static double playerSpeed = 1.0;
    protected int screenDelay = 3;

    private final KeyInput keyInput;
    private boolean isRunning = false;
    private boolean isPaused = true;
    private final Board board;
    private final Screen screen;
    private final Frame frame;
    private final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private final int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public Game(Frame frame) throws BBMException {
        this.frame = frame;
        frame.setTitle(TITLE);
        screen = new Screen(WIDTH, HEIGHT);
        keyInput = new KeyInput();
        board = new Board(this, keyInput, screen);
        addKeyListener(keyInput);
        SoundPlayer.loopSound("src/resources/sounds/audiogame.wav");
    }

    private void renderGame() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }
        screen.clear();
        board.render(screen);
        for (int i = 0; i < pixels.length; i++) { //create the image to be rendered
            pixels[i] = screen.pixels[i];
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        board.renderMessages(g);
        g.dispose();
        bs.show();
    }

    private void renderScreen() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        Graphics g = bs.getDrawGraphics();

        board.drawScreen(g);

        g.dispose();
        bs.show();
    }

    private void update() {
        keyInput.update();
        board.update();
    }

    public void start() {
        isRunning = true;

        long  lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0; //nanosecond, 60 frames per second
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                update();
                updates++;
                delta--;
            }

            if(isPaused) {
                if(screenDelay <= 0) {
                    board.setShow(-1);
                    isPaused = false;
                }
                renderScreen();
            } else {
                renderGame();
            }


            frames++;
            if(System.currentTimeMillis() - timer > 1000) { //once per second
                frame.setTime(board.subtractTime());
                frame.setPoints(board.getPoints());
                frame.setLives(board.getLives());
                timer += 1000;
                frame.setTitle(TITLE + " | " + updates + " rate, " + frames + " fps");
                updates = 0;
                frames = 0;
                if(board.getShow() == 2) --screenDelay;
            }
        }
    }

    public static double getPlayerSpeed() {
        return playerSpeed;
    }

    public static int getBombRate() {
        return bombRate;
    }

    public static int getBombRadius() {
        return bombRadius;
    }

    public static void addPlayerSpeed(double i) {
        playerSpeed += i;
    }

    public static void addBombRadius(int i) {
        bombRadius += i;
    }

    public static void addBombRate(int i) {
        bombRate += i;
    }

    public int getScreenDelay() {
        return screenDelay;
    }

    public void decreaseScreenDelay() {
        screenDelay--;
    }

    public void resetScreenDelay() {
        screenDelay = 3;
    }

    public KeyInput getKeyInput() {
        return keyInput;
    }

    public Board getBoard() {
        return board;
    }

    public void run() {
        isRunning = true;
        isPaused = false;
    }

    public void stop() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void pause() {
        isPaused = true;
    }

}