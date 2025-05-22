package Entities.Mobs.Enemies;

import Entities.Bombs.DirectionalExplosion;
import Entities.Entity;
import Entities.Message;
import Entities.Mobs.Enemies.AIs.AI;
import Entities.Mobs.Mob;
import Entities.Mobs.Player;
import Level.Coordinates;
import Main.Board;
import Graphics.Sprite;
import Graphics.Screen;
import Main.Game;
import Utils.SoundPlayer;

import java.awt.*;

public abstract class Enemy extends Mob {
    protected int points;
    protected double speed;
    protected AI ai;
    protected final double MAX_STEPS;
    protected final double rest;
    protected double steps;
    protected int finalAnimation = 30;
    protected Sprite deadSprite;

    public Enemy(int x, int y, Board board, Sprite dead, double speed, int points) {
        super(x, y, board);
        this.points = points;
        this.speed = speed;
        MAX_STEPS = Game.TILE_SIZE / speed;
        rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
        steps = MAX_STEPS;
        timeAfter = 20;
        deadSprite = dead;
    }

    @Override
    public void update() {
        animate();
        if(!isAlive) {
            afterKill();
            return;
        }
        if(isAlive) calculateMove();
    }

    @Override
    public void render(Screen screen) {
        if(isAlive) chooseSprite();
        else {
            if(timeAfter > 0) {
                sprite = deadSprite;
                animate = 0;
            } else sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animate, 60);
        }
        screen.renderEntity((int)x, (int)y - sprite.SIZE, this);
    }

    @Override
    public void calculateMove() {
        int xa = 0, ya = 0;
        if(steps <= 0){
            direction = ai.calculateDirection();
            steps = MAX_STEPS;
        }
        if(direction == 0) ya--;
        if(direction == 2) ya++;
        if(direction == 3) xa--;
        if(direction == 1) xa++;
        if(canMove(xa, ya)) {
            steps -= 1 + rest;
            move(xa * speed, ya * speed);
            isMoving = true;
        } else {
            steps = 0;
            isMoving = false;
        }
    }

    @Override
    public void move(double xa, double ya) {
        if(!isAlive) return;
        y += ya;
        x += xa;
    }

    @Override
    public boolean canMove(double x0, double y0) {
        double xr = x, yr = y - 16;
        if(direction == 0) { yr += sprite.getSize() -1 ; xr += (double) sprite.getSize() /2; }
        if(direction == 1) {yr += (double) sprite.getSize() /2; xr += 1;}
        if(direction == 2) { xr += (double) sprite.getSize() /2; yr += 1;}
        if(direction == 3) { xr += sprite.getSize() -1; yr += (double) sprite.getSize() /2;}
        int xx = Coordinates.pixelToTile(xr) +(int)x0;
        int yy = Coordinates.pixelToTile(yr) +(int)y0;
        Entity a = board.getEntity(xx, yy, this);
        return a.collide(this);
    }

    @Override
    public boolean collide(Entity e) {
        if(e instanceof DirectionalExplosion) {
            kill();
            return false;
        }
        if(e instanceof Player) {
            ((Player) e).kill();
            return false;
        }
        return true;
    }

    @Override
    public void kill() {
        if(!isAlive) return;
        isAlive = false;
        SoundPlayer.playSound("src/resources/sounds/clear.wav");
        board.addPoints(points);
        Message msg = new Message("+" + points, getXMessage(), getYMessage(), 2, Color.white, 14);
        board.addMessage(msg);
    }


    @Override
    protected void afterKill() {
        if(timeAfter > 0) --timeAfter;
        else {
            if(finalAnimation > 0) --finalAnimation;
            else remove();
        }
    }

    protected abstract void chooseSprite();
}
