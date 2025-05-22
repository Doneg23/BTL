package Entities.Tiles.PowerUps;

import Entities.Tiles.Tile;
import Graphics.Sprite;

public abstract class PU extends Tile {
    protected int duration = -1;
    protected boolean active = false;
    protected int level;

    public PU(int x, int y, int level, Sprite sprite) {
        super(x, y, sprite);
        this.level = level;
    }

    public abstract void setValues();

    public void removeLive() {
        if(duration > 0) duration--;
        if(duration == 0) active = false;
    }

    public int getDuration() {
        return duration;
    }

    public int getLevel() {
        return level;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
