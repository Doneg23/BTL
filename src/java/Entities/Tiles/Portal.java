package Entities.Tiles;

import Entities.Entity;
import Entities.Mobs.Player;
import Graphics.Sprite;
import Main.Board;

public class Portal extends Tile {
    protected Board board;

    public Portal(int x, int y, Board board, Sprite sprite) {
        super(x, y, sprite);
        this.board = board;
    }

    @Override
    public boolean collide(Entity e) {
        if(e instanceof Player) {
            if(!board.detectNoEnemies()) return false;
            if(e.getXTile() == getX() && e.getYTile() == getY()) {
                if(board.detectNoEnemies()) board.nextLevel();
            }
            return true;
        }
        return false;
    }
}
