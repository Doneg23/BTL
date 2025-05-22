package Entities.Mobs;

import Entities.Bombs.DirectionalExplosion;
import Entities.Entity;
import Entities.Message;
import Entities.Mobs.Enemies.Enemy;
import Graphics.Screen;
import Graphics.Sprite;
import Entities.Bombs.Bomb;
import Entities.Tiles.PowerUps.PU;
import Key.KeyInput;
import Level.Coordinates;
import Main.Board;
import Main.Game;
import Utils.SoundPlayer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player extends Mob {
    private final List<Bomb> bombs;
    protected KeyInput keyInput;
    protected int timeBetweenPutBombs = 0;
    public static List<PU> powerUps = new ArrayList<PU>();

    public Player(int x, int y, Board board) {
        super(x, y, board);
        this.bombs = board.getBombs();
        this.keyInput = board.getKeyInput();
        this.sprite = Sprite.player_right;
    }

    @Override
    public void update() {
        clearBombs();
        if(!isAlive) {
            afterKill();
            return;
        }
        if(timeBetweenPutBombs < -7500) timeBetweenPutBombs = 0; else timeBetweenPutBombs--;
        animate();
        calculateMove();
        detectPlaceBomb();
    }

    @Override
    public void render(Screen screen) {
        calculateXOffset();
        if(isAlive) chooseSprite();
        else sprite = Sprite.player_dead1;
        screen.renderEntity((int)x, (int)y - sprite.SIZE, this);
    }

    public void calculateXOffset() {
        int xScroll = Screen.calculateXOffset(board, this);
        Screen.setOffset(xScroll, 0);
    }

    private void detectPlaceBomb() {
        if(keyInput.space && Game.getBombRate() > 0 && timeBetweenPutBombs < 0) {
            int xt = Coordinates.pixelToTile(x + (double) sprite.getSize() / 2);
            int yt = Coordinates.pixelToTile( (y + (double) sprite.getSize() / 2) - sprite.getSize());
            placeBomb(xt,yt);
            Game.addBombRate(-1);
            timeBetweenPutBombs = 30;
        }
    }

    protected void placeBomb(int x, int y) {
        Bomb b = new Bomb(x, y, board);
        board.addBomb(b);
        SoundPlayer.playSound("src/resources/sounds/putbomb.wav");
    }

    private void clearBombs() {
        Iterator<Bomb> itb = bombs.iterator();
        Bomb b;
        while(itb.hasNext()) {
            b = itb.next();
            if(b.isRemoved())  {
                itb.remove();
                Game.addBombRate(1);
            }
        }

    }

    @Override
    public void kill() {
        if(!isAlive) return;
        isAlive = false;
        board.addLives(-1);
        Message msg = new Message("-1 LIVE", getXMessage(), getYMessage(), 2, Color.white, 18);
        board.addMessage(msg);
        SoundPlayer.playSound("src/resources/sounds/die.wav");

    }

    @Override
    protected void afterKill() {
        if(timeAfter > 0) --timeAfter;
        else {
            if(bombs.isEmpty()) {
                if(board.getLives() == 0) {
                    board.endGame();
                    SoundPlayer.playSound("src/resources/sounds/game_over.wav");
                } else {
                    board.restartLevel();
                }
            }
        }
    }
    @Override
    protected void calculateMove() {
        int xa = 0, ya = 0;
        if(keyInput.up) ya--;
        if(keyInput.down) ya++;
        if(keyInput.left) xa--;
        if(keyInput.right) xa++;
        if(xa != 0 || ya != 0)  {
            move(xa * Game.getPlayerSpeed(), ya * Game.getPlayerSpeed());
            isMoving = true;
        } else isMoving = false;
    }

    @Override
    public boolean canMove(double x0, double y0) {
        for (int c = 0; c < 4; c++) {
            double xt = ((x + x0) + c % 2 * 11) / Game.TILE_SIZE;
            double yt = ((y + y0) + c / 2 * 12 - 13) / Game.TILE_SIZE;
            Entity a = board.getEntity(xt, yt, this);
            if(!a.collide(this)) return false;
        }
        return true;
    }

    @Override
    public void move(double xa, double ya) {
        if(xa > 0) direction = 1;
        if(xa < 0) direction = 3;
        if(ya > 0) direction = 2;
        if(ya < 0) direction = 0;
        if(canMove(0, ya)) y += ya;
        if(canMove(xa, 0)) x += xa;
    }

    @Override
    public boolean collide(Entity e) {
        if(e instanceof DirectionalExplosion) {
            kill();
            SoundPlayer.playSound("src/resources/sounds/dead2.wav");
            return false;
        }
        if(e instanceof Enemy) {
            kill();
            SoundPlayer.playSound("src/resources/sounds/dead1.wav");
            return true;
        }
        return true;
    }

    public void addPowerup(PU p) {
        if(p.isRemoved()) return;
        powerUps.add(p);
        p.setValues();
    }

    public void clearUsedPowerUps() {
        PU p;
        for (int i = 0; i < powerUps.size(); i++) {
            p = powerUps.get(i);
            if(!p.isActive()) {
                powerUps.remove(i);
                SoundPlayer.playSound("src/resources/sounds/getitem.wav");
            }
        }
    }

    public void removePowerUps() {
        for (int i = 0; i < powerUps.size(); i++) {
            powerUps.remove(i);
        }
    }

    private void chooseSprite() {
        switch(direction) {
            case 0:
                sprite = Sprite.player_up;
                if(isMoving) sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animate, 20);
                break;
            case 1:
                sprite = Sprite.player_right;
                if(isMoving) sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 20);
                break;
            case 2:
                sprite = Sprite.player_down;
                if(isMoving) sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animate, 20);
                break;
            case 3:
                sprite = Sprite.player_left;
                if(isMoving) sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate, 20);
                break;
            default:
                sprite = Sprite.player_right;
                if(isMoving) sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 20);
                break;
        }
    }
}
