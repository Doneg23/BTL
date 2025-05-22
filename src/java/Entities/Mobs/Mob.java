package Entities.Mobs;

import Entities.AnimatedEntity;
import Graphics.Screen;
import Main.Board;
import Main.Game;

public abstract class Mob extends AnimatedEntity {
    protected Board board;
    protected int direction = -1;
    protected boolean isAlive = true;
    protected boolean isMoving = false;
    public int timeAfter = 80;

    public Mob(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
    }

    @Override
    public abstract void update();

    @Override
    public abstract void render(Screen screen);

    protected abstract void calculateMove();

    protected abstract void move(double xa, double ya);

    public abstract void kill();

    protected abstract void afterKill();

    protected abstract boolean canMove(double x, double y);

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public int getDirection() {
        return direction;
    }

    protected double getXMessage() {
        return (x * Game.SCALE) + ((double) sprite.SIZE / 2 * Game.SCALE);
    }

    protected double getYMessage() {
        return (y* Game.SCALE) - ((double) sprite.SIZE / 2 * Game.SCALE);
    }

}
