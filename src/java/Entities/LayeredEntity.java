package Entities;

import Entities.Tiles.DestroyableTiles.DestroyableTile;
import Graphics.Screen;

import java.util.LinkedList;

public class LayeredEntity extends Entity {
    protected LinkedList<Entity> entities = new LinkedList<Entity>();

    public LayeredEntity(int x, int y, Entity ... entities) {
        this.x = x;
        this.y = y;
        for (int i = 0; i < entities.length; i++) {
            this.entities.add(entities[i]);
            if(i > 1) {
                if(entities[i] instanceof DestroyableTile)
                    ((DestroyableTile)entities[i]).addBelowSprite(entities[i-1].getSprite());
            }
        }
    }

    @Override
    public void update() {
        clearRemoved();
        getTopEntity().update();
    }

    @Override
    public void render(Screen screen) {
        getTopEntity().render(screen);
    }

    public Entity getTopEntity() {
        return entities.getLast();
    }

    private void clearRemoved() {
        Entity top = getTopEntity();
        if(top.isRemoved()) entities.removeLast();
    }

    public void addBeforeTop(Entity e) {
        entities.add(entities.size() - 1, e);
    }

    @Override
    public boolean collide(Entity e) {
        return getTopEntity().collide(e);
    }

}
