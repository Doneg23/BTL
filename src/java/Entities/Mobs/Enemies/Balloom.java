package Entities.Mobs.Enemies;

import Entities.Mobs.Enemies.AIs.SimpleAI;
import Graphics.Sprite;
import Main.Board;
import Main.Game;

public class Balloom extends Enemy {
    public Balloom(int x, int y, Board board) {
        super(x, y, board, Sprite.balloom_dead, Game.getPlayerSpeed() / 2, 100);

        sprite = Sprite.balloom_left1;

        ai = new SimpleAI();
        direction = ai.calculateDirection();
    }

    @Override
    protected void chooseSprite() {
        switch(direction) {
            case 0:
            case 1:
                sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 60);
                break;
            case 2:
            case 3:
                sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 60);
                break;
        }
    }
}
