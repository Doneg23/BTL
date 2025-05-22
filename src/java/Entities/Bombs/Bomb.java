package Entities.Bombs;

import Entities.AnimatedEntity;
import Entities.Entity;
import Entities.Mobs.Mob;
import Entities.Mobs.Player;
import Graphics.Screen;
import Graphics.Sprite;
import Level.Coordinates;
import Main.Board;
import Main.Game;

public class Bomb extends AnimatedEntity {
    protected double timeToExplode = 120; //2 seconds
    public int timeAfter = 20; //time to explosions disapear

    protected Board board;
    protected boolean allowedToPass = true;
    protected DirectionalExplosion[] explosions = null;
    protected boolean exploded = false;

    public Bomb(int x, int y,Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.sprite = Sprite.bomb;
    }

    @Override
    public void update() {
        if(timeToExplode > 0) timeToExplode--;
        else {
            if(!exploded) explosion();
            else updateExplosions();
            if(timeAfter > 0) timeAfter--;
            else remove();
        }
        animate();
    }

    @Override
    public void render(Screen screen) {
        if(exploded) {
            sprite =  Sprite.bomb_exploded2;
            renderExplosions(screen);
        } else sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 60);
        int xt = (int)x << 4;
        int yt = (int)y << 4;
        screen.renderEntity(xt, yt , this);
    }

    public void renderExplosions(Screen screen) {
        for (int i = 0; i < explosions.length; i++) {
            explosions[i].render(screen);
        }
    }

    public void updateExplosions() {
        for (int i = 0; i < explosions.length; i++) {
            explosions[i].update();
        }
    }

    public void explode() {
        timeToExplode = 0;
    }

    protected void explosion() {
        allowedToPass = true;
        exploded = true;
        Mob a = board.getMobAt(x, y);
        if(a != null)  a.kill();
        explosions = new DirectionalExplosion[4];
        for (int i = 0; i < explosions.length; i++) {
            explosions[i] = new DirectionalExplosion((int)x, (int)y, i, Game.getBombRadius(), board);
        }
    }

    public Explosion explosionAt(int x, int y) {
        if(!exploded) return null;
        for (int i = 0; i < explosions.length; i++) {
            if(explosions[i] == null) return null;
            Explosion e = explosions[i].explosionAt(x, y);
            if(e != null) return e;
        }
        return null;
    }

    public boolean isExploded() {
        return exploded;
    }


    @Override
    public boolean collide(Entity e) {
        if(e instanceof Player) {
            double diffX = e.getX() - Coordinates.tileToPixel(getX());
            double diffY = e.getY() - Coordinates.tileToPixel(getY());
            if(!(diffX >= -10 && diffX < 16 && diffY >= 1 && diffY <= 28)) allowedToPass = false;
            return allowedToPass;
        }
        if(e instanceof DirectionalExplosion) {
            explode();
            return true;
        }
        return false;
    }
}
