package Level;

import Exceptions.LevelLoadingException;

public interface LevelInterface {
    public void loadLevel(String path) throws LevelLoadingException;
}
