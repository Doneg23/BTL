package Main;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Entities.Entity;
import Entities.Message;
import Entities.Bombs.Bomb;
import Entities.Bombs.Explosion;
import Entities.Mobs.Mob;
import Entities.Mobs.Player;
import Entities.Tiles.PowerUps.PU;
import Graphics.Render;
import Graphics.Screen;
import Level.FileLevel;
import Level.Level;
import Key.KeyInput;
import Exceptions.LevelLoadingException;
import Utils.SoundPlayer;

public class Board implements Render {
    protected Level level;
    protected Game game;
    protected KeyInput keyInput;
    protected Screen screen;

    public Entity[] entities;
    public List<Mob> mobs = new ArrayList<Mob>();
    protected List<Bomb> bombs = new ArrayList<Bomb>();
    private final List<Message> messages = new ArrayList<Message>();
    private int screenToShow = -1; //1:endgame, 2:changelevel, 3:paused
    private int time = Game.TIME;
    private int points = Game.POINTS;
    private int lives = Game.LIVES;

    public Board(Game game, KeyInput keyInput, Screen screen) {
        this.game = game;
        this.keyInput = keyInput;
        this.screen = screen;
        changeLevel(1);
    }

    @Override
    public void update() {
        if(game.isPaused()) return;
        updateEntities();
        updateMobs();
        updateBombs();
        updateMessages();
        detectEndGame();
        for (int i = 0; i < mobs.size(); i++) {
            Mob a = mobs.get(i);
            if(((Entity)a).isRemoved()) mobs.remove(i);
        }
    }

    @Override
    public void render(Screen screen) {
        if(game.isPaused()) return;
        int x0 = Math.max(0, Screen.xOffset >> 4);
        int x1 = Math.min(level.getWidth(), (Screen.xOffset + screen.getWidth() + Game.TILE_SIZE) / Game.TILE_SIZE);
        int y0 = Math.max(0, Screen.yOffset >> 4);
        int y1 = Math.min(level.getHeight(), (Screen.yOffset + screen.getHeight()) / Game.TILE_SIZE);
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                entities[x + y * level.getWidth()].render(screen);
            }
        }
        renderBombs(screen);
        renderMobs(screen);
    }

    public void newGame() {
        resetProperties();
        changeLevel(1);

    }

    private void resetProperties() {
        points = Game.POINTS;
        lives = Game.LIVES;
        Player.powerUps.clear();
        Game.playerSpeed = 1.0;
        Game.bombRadius = 1;
        Game.bombRate = 1;
    }

    public void restartLevel() {
        SoundPlayer.playSound("src/resources/sounds/win.wav");
        changeLevel(level.getLevel());
    }

    public void nextLevel() {
        //SoundPlayer.playSound("src/resources/sounds/win.wav");
        changeLevel(level.getLevel() + 1);
    }

    public void changeLevel(int targetLevel) {
        time = Game.TIME;
        screenToShow = 2;
        game.resetScreenDelay();
        game.pause();
        mobs.clear();
        bombs.clear();
        messages.clear();
        try {
            level = new FileLevel("levels/Level" + targetLevel + ".txt", this);
            entities = new Entity[level.getHeight() * level.getWidth()];
            level.createEntities();
        } catch (LevelLoadingException e) {
            endGame();
        }
    }

    public boolean isPowerUpUsed(int x, int y, int level) {
        PU p;
        for (int i = 0; i < Player.powerUps.size(); i++) {
            p = Player.powerUps.get(i);
            if(p.getX() == x && p.getY() == y && level == p.getLevel()) return true;
        }
        return false;
    }

    protected void detectEndGame() {
        if(time <= 0) restartLevel();
    }

    public void endGame() {
        screenToShow = 1;
        game.resetScreenDelay();
        game.pause();
    }

    public boolean detectNoEnemies() {
        int total = 0;
        for (int i = 0; i < mobs.size(); i++) {
            if(!(mobs.get(i) instanceof Player)) ++total;
        }
        return total == 0;
    }

    public void gamePause() {
        game.resetScreenDelay();
        if(screenToShow <= 0) screenToShow = 3;
        game.pause();
    }

    public void gameResume() {
        game.resetScreenDelay();
        screenToShow = -1;
        game.run();
    }

    public void drawScreen(Graphics g) {
        switch (screenToShow) {
            case 1:
                screen.drawEndGame(g, points);
                break;
            case 2:
                screen.drawChangeLevel(g, level.getLevel());
                break;
            case 3:
                screen.drawPaused(g);
                break;
        }
    }

    public Entity getEntity(double x, double y, Mob m) {
        Entity res = null;
        res = getExplosionAt((int)x, (int)y);
        if( res != null) return res;
        res = getBombAt(x, y);
        if( res != null) return res;
        res = getMobAtExcluding((int)x, (int)y, m);
        if( res != null) return res;
        res = getEntityAt((int)x, (int)y);
        return res;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public Bomb getBombAt(double x, double y) {
        Iterator<Bomb> bs = bombs.iterator();
        Bomb b;
        while(bs.hasNext()) {
            b = bs.next();
            if(b.getX() == (int)x && b.getY() == (int)y) return b;
        }
        return null;
    }

    public Mob getMobAt(double x, double y) {
        Iterator<Mob> itr = mobs.iterator();
        Mob cur;
        while(itr.hasNext()) {
            cur = itr.next();
            if(cur.getXTile() == x && cur.getYTile() == y) return cur;
        }
        return null;
    }

    public Player getPlayer() {
        Iterator<Mob> itr = mobs.iterator();
        Mob cur;
        while(itr.hasNext()) {
            cur = itr.next();
            if(cur instanceof Player) return (Player) cur;
        }
        return null;
    }

    public Mob getMobAtExcluding(int x, int y, Mob a) {
        Iterator<Mob> itr = mobs.iterator();
        Mob cur;
        while(itr.hasNext()) {
            cur = itr.next();
            if(cur == a) {
                continue;
            }
            if(cur.getXTile() == x && cur.getYTile() == y) {
                return cur;
            }
        }

        return null;
    }

    public Explosion getExplosionAt(int x, int y) {
        Iterator<Bomb> bs = bombs.iterator();
        Bomb b;
        while(bs.hasNext()) {
            b = bs.next();

            Explosion e = b.explosionAt(x, y);
            if(e != null) {
                return e;
            }
        }

        return null;
    }

    public Entity getEntityAt(double x, double y) {
        return entities[(int)x + (int)y * level.getWidth()];
    }

    public void addEntity(int pos, Entity e) {
        entities[pos] = e;
    }

    public void addMob(Mob e) {
        mobs.add(e);
    }

    public void addBomb(Bomb e) {
        bombs.add(e);
    }

    public void addMessage(Message e) {
        messages.add(e);
    }

    protected void renderEntities(Screen screen) {
        for (Entity entity : entities) {
            entity.render(screen);
        }
    }

    protected void renderMobs(Screen screen) {
        Iterator<Mob> it = mobs.iterator();
        while(it.hasNext()) it.next().render(screen);
    }

    protected void renderBombs(Screen screen) {
        Iterator<Bomb> it = bombs.iterator();
        while(it.hasNext()) it.next().render(screen);
    }

    public void renderMessages(Graphics g) {
        Message m;
        for (Message message : messages) {
            m = message;
            g.setFont(new Font("Arial", Font.PLAIN, m.getSize()));
            g.setColor(m.getColor());
            g.drawString(m.getMessage(), (int) m.getX() - Screen.xOffset * Game.SCALE, (int) m.getY());
        }
    }

    protected void updateEntities() {
        if( game.isPaused() ) return;
        for (int i = 0; i < entities.length; i++) {
            entities[i].update();
        }
    }

    protected void updateMobs() {
        if( game.isPaused() ) return;
        Iterator<Mob> itr = mobs.iterator();

        while(itr.hasNext() && !game.isPaused())
            itr.next().update();
    }

    protected void updateBombs() {
        if( game.isPaused() ) return;
        Iterator<Bomb> it = bombs.iterator();
        while(it.hasNext()) it.next().update();
    }

    protected void updateMessages() {
        if( game.isPaused() ) return;
        Message m;
        int left = 0;
        for (int i = 0; i < messages.size(); i++) {
            m = messages.get(i);
            left = m.getDuration();
            if(left > 0) m.setDuration(--left);
            else messages.remove(i);
        }
    }

    public KeyInput getKeyInput() {
        return keyInput;
    }

    public Level getLevel() {
        return level;
    }

    public Game getGame() {
        return game;
    }

    public int getShow() {
        return screenToShow;
    }

    public void setShow(int i) {
        screenToShow = i;
    }

    public int getTime() {
        return time;
    }

    public int getLives() {
        return lives;
    }

    public int subtractTime() {
        if(game.isPaused())
            return this.time;
        else
            return this.time--;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void addLives(int lives) {
        this.lives += lives;
    }

    public int getWidth() {
        return level.getWidth();
    }

    public int getHeight() {
        return level.getHeight();
    }

}
