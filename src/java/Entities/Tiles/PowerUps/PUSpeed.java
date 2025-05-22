package Entities.Tiles.PowerUps;

import Entities.Entity;
import Entities.Mobs.Player;
import Graphics.Sprite;
import Main.Game;

public class PUSpeed extends PU {
    public PUSpeed(int x, int y, int level, Sprite sprite) {
        super(x, y, level, sprite);
    }

    @Override
    public boolean collide(Entity e) {
        if(e instanceof Player) {
            Utils.SoundPlayer.playSound("src/resources/sounds/getitem.wav");
            ((Player) e).addPowerup(this);
            remove();
            return true;
        }
        return false;
    }

    @Override
    public void setValues() {
        active = true;
        Game.addPlayerSpeed(0.1);
    }
}
