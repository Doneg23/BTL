package Level;

import Exceptions.LevelLoadingException;
import Main.Board;

public abstract class Level implements LevelInterface {
    protected int width, height, level;
    protected String[] lineTiles;
    protected Board board;

    public Level(String path, Board board) throws LevelLoadingException {
        loadLevel(path);
        this.board = board;
    }

    @Override
    public abstract void loadLevel(String path) throws LevelLoadingException;

    public abstract void createEntities();

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLevel() {
        return level;
    }

}
