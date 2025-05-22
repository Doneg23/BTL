package Entities.Tiles.DestroyableTiles;

import Entities.Bombs.DirectionalExplosion;
import Entities.Entity;
import Entities.Mobs.Enemies.Kondoria;
import Graphics.Screen;
import Graphics.Sprite;
import Level.Coordinates;

public class Brick extends DestroyableTile{
    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Screen screen) {
        int x0 = Coordinates.tileToPixel(x);
        int y0 = Coordinates.tileToPixel(y);
        if(destroyed) {
            sprite = movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2);
            screen.renderEntityWithBelowSprite(x0, y0, this, belowSprite);
        }
        else screen.renderEntity( x0, y0, this);
    }

    @Override
    public boolean collide(Entity e) {
        if(e instanceof DirectionalExplosion) destroy();
        if(e instanceof Kondoria) return true;
        return false;
    }
}
