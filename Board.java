public class Board {
    public static final int WIDTH = 16;
    public static final int HEIGHT = 9;
    private Block[][] blocks = new Block[HEIGHT][WIDTH];

    /**
     * Constructor.
     */
    public Board() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                blocks[i][j] = new Path(i, j);
            }
        }
    }

    public void addBlock(Block block) {
        if (getAt(block.getX(), block.getY()) != null
                || !validate(block.getX(), block.getY())) {
            return;
        }
        blocks[block.getX()][block.getY()] = block;
    }

    public boolean validate(int x, int y) {
        return x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT;
    }

    public void destroyAt(int x, int y) {
        if (!validate(x, y) || !getAt(x, y).getIsDestructible()) {
            return;
        }
        if (getAt(x, y).hashCode() == 2) {
            blocks[x][y] = new Path(x, y);
        }
        if (getAt(x, y).hashCode() == 3) {
            blocks[x][y] = new Rock(x, y);
        }
    }

    public Block getAt(int x, int y) {
        if (!validate(x, y)) {
            return null;
        }
        return blocks[x][y];
    }

    public Block[][] getBlocks() {
        return blocks;
    }

    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    public void printBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            System.out.print("|");
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(blocks[i][j].hashCode() + "\t|");
            }
            System.out.print("\n");
        }
    }
}
