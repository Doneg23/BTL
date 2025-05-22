package Entities.Bombs;

import Entities.Entity;
import Entities.Mobs.Mob;
import Graphics.Screen;
import Main.Board;

public class DirectionalExplosion extends Entity {
    protected Board board;
    protected int direction;
    private final int radius;
    protected int xOrigin, yOrigin;
    protected Explosion[] explosions;

    public DirectionalExplosion(int x, int y, int direction, int radius, Board board) {
        xOrigin = x;
        yOrigin = y;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.radius = radius;
        this.board = board;

        explosions = new Explosion[ calculatePermittedDistance() ];
        createExplosions();
    }

    private void createExplosions() {
        boolean last = false;
        int x0 = (int)x;
        int y0 = (int)y;
        for (int i = 0; i < explosions.length; i++) {
            last = i == explosions.length -1 ? true : false;
            switch (direction) {
                case 0: y0--; break;
                case 1: x0++; break;
                case 2: y0++; break;
                case 3: x0--; break;
            }
            explosions[i] = new Explosion(x0, y0, direction, last, board);
        }
    }

    private int calculatePermittedDistance() {
        int r = 0;
        int x0 = (int)x;
        int y0 = (int)y;
        while(r < radius) {
            if(direction == 0) y0--;
            if(direction == 1) x0++;
            if(direction == 2) y0++;
            if(direction == 3) x0--;
            Entity a = board.getEntity(x0, y0, null);
            if(a instanceof Mob) ++r;
            if(!a.collide(this)) break;
            ++r;
        }
        return r;
    }

    public Explosion explosionAt(int x0, int y0) {
        for (int i = 0; i < explosions.length; i++) {
            if(explosions[i].getX() == x0 && explosions[i].getY() == y0)
                return explosions[i];
        }
        return null;
    }

    @Override
    public void update() {}

    @Override
    public void render(Screen screen) {
        for (int i = 0; i < explosions.length; i++) {
            explosions[i].render(screen);
        }
    }

    @Override
    public boolean collide(Entity e) {
        return true;
    }
}
