package Entities.Mobs.Enemies;

import Entities.Mobs.Enemies.AIs.MediumAI;
import Graphics.Sprite;
import Main.Board;
import Main.Game;

public class Minvo extends Enemy {
    public Minvo(int x, int y, Board board) {
        super(x, y, board, Sprite.minvo_dead, Game.getPlayerSpeed() * 2, 800);
        sprite = Sprite.minvo_right1;
        ai = new MediumAI(board.getPlayer(), this);
        direction  = ai.calculateDirection();
    }

    @Override
    protected void chooseSprite() {
        switch(direction) {
            case 0:
            case 1:
                if(isMoving) sprite = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animate, 60);
                else sprite = Sprite.minvo_left1;
                break;
            case 2:
            case 3:
                if(isMoving) sprite = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animate, 60);
                else sprite = Sprite.minvo_left1;
                break;
        }
    }
}
