package Entities.Mobs.Enemies;

import Entities.Mobs.Enemies.AIs.SimpleAI;
import Graphics.Sprite;
import Main.Board;
import Main.Game;

public class Doll extends Enemy {
    public Doll(int x, int y, Board board) {
        super(x, y, board, Sprite.doll_dead, Game.getPlayerSpeed(), 400);
        sprite = Sprite.doll_right1;
        ai = new SimpleAI();
        direction = ai.calculateDirection();
    }

    @Override
    protected void chooseSprite() {
        switch(direction) {
            case 0:
            case 1:
                if(isMoving) sprite = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animate, 60);
                else sprite = Sprite.doll_left1;
                break;
            case 2:
            case 3:
                if(isMoving) sprite = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animate, 60);
                else sprite = Sprite.doll_left1;
                break;
        }
    }
}
