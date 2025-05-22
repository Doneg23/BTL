package Level;

import Entities.LayeredEntity;
import Entities.Mobs.Enemies.*;
import Entities.Mobs.Player;
import Entities.Tiles.DestroyableTiles.Brick;
import Entities.Tiles.Grass;
import Entities.Tiles.Portal;
import Entities.Tiles.PowerUps.PUBomb;
import Entities.Tiles.PowerUps.PUFlame;
import Entities.Tiles.PowerUps.PUSpeed;
import Entities.Tiles.Wall;
import Exceptions.LevelLoadingException;
import Graphics.Screen;
import Graphics.Sprite;
import Main.Board;
import Main.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;

public class FileLevel extends Level {
    public FileLevel(String path, Board board) throws LevelLoadingException {
        super(path, board);
    }

    @Override
    public void loadLevel(String path) throws LevelLoadingException {
        try {
            URL absPath = FileLevel.class.getResource("/" + path);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(absPath.openStream()));
            String data = in.readLine();
            StringTokenizer tokens = new StringTokenizer(data);
            level = Integer.parseInt(tokens.nextToken());
            height = Integer.parseInt(tokens.nextToken());
            width = Integer.parseInt(tokens.nextToken());
            lineTiles = new String[height];
            for(int i = 0; i < height; ++i) {
                lineTiles[i] = in.readLine().substring(0, width);
            }
            in.close();
        } catch (IOException e) {
            throw new LevelLoadingException("Error loading level " + path, e);
        }
    }

    @Override
    public void createEntities() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                addLevelEntity(lineTiles[y].charAt(x), x, y);
            }
        }
    }

    public void addLevelEntity(char c, int x, int y) {
        int pos = x + y * getWidth();
        switch(c) {
            case '#':
                board.addEntity(pos, new Wall(x, y, Sprite.wall));
                break;
            case 'b':
                LayeredEntity layer = new LayeredEntity(x, y,
                        new Grass(x ,y, Sprite.grass),
                        new Brick(x ,y, Sprite.brick));
                if(!board.isPowerUpUsed(x, y, level)) {
                    layer.addBeforeTop(new PUBomb(x, y, level, Sprite.powerup_bombs));
                }
                board.addEntity(pos, layer);
                break;
            case 's':
                layer = new LayeredEntity(x, y,
                        new Grass(x ,y, Sprite.grass),
                        new Brick(x ,y, Sprite.brick));
                if(!board.isPowerUpUsed(x, y, level)) {
                    layer.addBeforeTop(new PUSpeed(x, y, level, Sprite.powerup_speed));
                }
                board.addEntity(pos, layer);
                break;
            case 'f':
                layer = new LayeredEntity(x, y,
                        new Grass(x ,y, Sprite.grass),
                        new Brick(x ,y, Sprite.brick));
                if(!board.isPowerUpUsed(x, y, level)) {
                    layer.addBeforeTop(new PUFlame(x, y, level, Sprite.powerup_flames));
                }
                board.addEntity(pos, layer);
                break;
            case '*':
                board.addEntity(pos, new LayeredEntity(x, y,
                        new Grass(x ,y, Sprite.grass),
                        new Brick(x ,y, Sprite.brick)) );
                break;
            case 'x':
                board.addEntity(pos, new LayeredEntity(x, y,
                        new Grass(x ,y, Sprite.grass),
                        new Portal(x ,y, board, Sprite.portal),
                        new Brick(x ,y, Sprite.brick)) );
                break;
            case ' ':
                board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                break;
            case 'p':
                board.addMob( new Player(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILE_SIZE, board) );
                Screen.setOffset(0, 0);
                board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                break;
            case '1':
                board.addMob( new Balloom(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILE_SIZE, board));
                board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                break;
            case '2':
                board.addMob( new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILE_SIZE, board));
                board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                break;
            case '3':
                board.addMob( new Doll(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILE_SIZE, board));
                board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                break;
            case '4':
                board.addMob( new Minvo(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILE_SIZE, board));
                board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                break;
            case '5':
                board.addMob( new Kondoria(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILE_SIZE, board));
                board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                break;
            default:
                board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                break;
        }
    }

}