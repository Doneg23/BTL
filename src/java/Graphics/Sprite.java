package Graphics;

public class Sprite {
    public final int SIZE;
    private int x0, y0;
    public int[] pixels;
    protected int realWidth;
    protected int realHeight;
    private SpriteSheet sheet;

    public static Sprite grass = new Sprite(16, 6, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite brick = new Sprite(16, 7, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite wall = new Sprite(16, 5, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite portal = new Sprite(16, 4, 0, SpriteSheet.tiles, 14, 14);

    public static Sprite player_up = new Sprite(16, 0, 0, SpriteSheet.tiles, 12, 16);
    public static Sprite player_down = new Sprite(16, 2, 0, SpriteSheet.tiles, 12, 15);
    public static Sprite player_left = new Sprite(16, 3, 0, SpriteSheet.tiles, 10, 15);
    public static Sprite player_right = new Sprite(16, 1, 0, SpriteSheet.tiles, 10, 16);

    public static Sprite player_up_1 = new Sprite(16, 0, 1, SpriteSheet.tiles, 12, 16);
    public static Sprite player_up_2 = new Sprite(16, 0, 2, SpriteSheet.tiles, 12, 15);

    public static Sprite player_down_1 = new Sprite(16, 2, 1, SpriteSheet.tiles, 12, 15);
    public static Sprite player_down_2 = new Sprite(16, 2, 2, SpriteSheet.tiles, 12, 16);

    public static Sprite player_left_1 = new Sprite(16, 3, 1, SpriteSheet.tiles, 11, 16);
    public static Sprite player_left_2 = new Sprite(16, 3, 2, SpriteSheet.tiles, 12 ,16);

    public static Sprite player_right_1 = new Sprite(16, 1, 1, SpriteSheet.tiles, 11, 16);
    public static Sprite player_right_2 = new Sprite(16, 1, 2, SpriteSheet.tiles, 12, 16);

    public static Sprite player_dead1 = new Sprite(16, 4, 2, SpriteSheet.tiles, 14, 16);
    public static Sprite player_dead2 = new Sprite(16, 5, 2, SpriteSheet.tiles, 13, 15);
    public static Sprite player_dead3 = new Sprite(16, 6, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite balloom_left1 = new Sprite(16, 9, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite balloom_left2 = new Sprite(16, 9, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite balloom_left3 = new Sprite(16, 9, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite balloom_right1 = new Sprite(16, 10, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite balloom_right2 = new Sprite(16, 10, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite balloom_right3 = new Sprite(16, 10, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite balloom_dead = new Sprite(16, 9, 3, SpriteSheet.tiles, 16, 16);

    public static Sprite oneal_left1 = new Sprite(16, 11, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite oneal_left2 = new Sprite(16, 11, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite oneal_left3 = new Sprite(16, 11, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite oneal_right1 = new Sprite(16, 12, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite oneal_right2 = new Sprite(16, 12, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite oneal_right3 = new Sprite(16, 12, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite oneal_dead = new Sprite(16, 11, 3, SpriteSheet.tiles, 16, 16);

    public static Sprite doll_left1 = new Sprite(16, 13, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite doll_left2 = new Sprite(16, 13, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite doll_left3 = new Sprite(16, 13, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite doll_right1 = new Sprite(16, 14, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite doll_right2 = new Sprite(16, 14, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite doll_right3 = new Sprite(16, 14, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite doll_dead = new Sprite(16, 13, 3, SpriteSheet.tiles, 16, 16);

    public static Sprite minvo_left1 = new Sprite(16, 8, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite minvo_left2 = new Sprite(16, 8, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite minvo_left3 = new Sprite(16, 8, 7, SpriteSheet.tiles, 16, 16);

    public static Sprite minvo_right1 = new Sprite(16, 9, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite minvo_right2 = new Sprite(16, 9, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite minvo_right3 = new Sprite(16, 9, 7, SpriteSheet.tiles, 16, 16);

    public static Sprite minvo_dead = new Sprite(16, 8, 8, SpriteSheet.tiles, 16, 16);

    public static Sprite kondoria_left1 = new Sprite(16, 10, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite kondoria_left2 = new Sprite(16, 10, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite kondoria_left3 = new Sprite(16, 10, 7, SpriteSheet.tiles, 16, 16);

    public static Sprite kondoria_right1 = new Sprite(16, 11, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite kondoria_right2 = new Sprite(16, 11, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite kondoria_right3 = new Sprite(16, 11, 7, SpriteSheet.tiles, 16, 16);

    public static Sprite kondoria_dead = new Sprite(16, 10, 8, SpriteSheet.tiles, 16, 16);

    public static Sprite mob_dead1 = new Sprite(16, 15, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite mob_dead2 = new Sprite(16, 15, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite mob_dead3 = new Sprite(16, 15, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite bomb = new Sprite(16, 0, 3, SpriteSheet.tiles, 15, 15);
    public static Sprite bomb_1 = new Sprite(16, 1, 3, SpriteSheet.tiles, 13, 15);
    public static Sprite bomb_2 = new Sprite(16, 2, 3, SpriteSheet.tiles, 12, 14);

    public static Sprite bomb_exploded2 = new Sprite(16, 0, 6, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_vertical2 = new Sprite(16, 3, 5, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_horizontal2 = new Sprite(16, 1, 9, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_horizontal_left_last2 = new Sprite(16, 0, 9, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_horizontal_right_last2 = new Sprite(16, 2, 9, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_vertical_top_last2 = new Sprite(16, 3, 4, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_vertical_down_last2 = new Sprite(16, 3, 6, SpriteSheet.tiles, 16, 16);

    public static Sprite brick_exploded = new Sprite(16, 7, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite brick_exploded1 = new Sprite(16, 7, 2, SpriteSheet.tiles, 16, 16);
    public static Sprite brick_exploded2 = new Sprite(16, 7, 3, SpriteSheet.tiles, 16, 16);

    public static Sprite powerup_bombs = new Sprite(16, 0, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_flames = new Sprite(16, 1, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_speed = new Sprite(16, 2, 10, SpriteSheet.tiles, 16, 16);

    public Sprite(int size, int x, int y, SpriteSheet sheet, int rw, int rh) {
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        x0 = x * SIZE;
        y0 = y * SIZE;
        this.sheet = sheet;
        realWidth = rw;
        realHeight = rh;
        load();
    }

    public Sprite(int size, int color) {
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    private void setColor(int color) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = color;
        }
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet.pixels[(x + x0) + (y + y0) * sheet.SIZE];
            }
        }
    }

    public static Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, int animate, int time) {
        int calc = animate % time;
        int diff = time / 3;
        if(calc < diff) {
            return normal;
        }
        if(calc < diff * 2) {
            return x1;
        }
        return x2;
    }

    public static Sprite movingSprite(Sprite x1, Sprite x2, int animate, int time) {
        int diff = time / 2;
        return (animate % time > diff) ? x1 : x2;
    }

    public int getSize() {
        return SIZE;
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getPixel(int i) {
        return pixels[i];
    }

    public int getRealWidth() {
        return realWidth;
    }

    public int getRealHeight() {
        return realHeight;
    }
}
