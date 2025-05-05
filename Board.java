import java.util.ArrayList;

public class Board {
    public static final int WIDTH = 16;
    public static final int HEIGHT = 9;
    private ArrayList<Block> blocks = new ArrayList<>();

    /**
     * Constructor.
     */
    public Board() {
    }

    public void addBlock(Block block) {
        if (getAt(block.getX(), block.getY()) != null
                || !validate(block.getX(), block.getY())) {
            return;
        }
        blocks.add(block);
    }

    public boolean validate(int x, int y) {
        return x >= 1 && y >= 1 && x <= WIDTH && y <= HEIGHT;
    }

    public void destroyAt(int x, int y) {
        if (!validate(x, y) || !getAt(x, y).getIsDestructible()) {
            return;
        }
        if (getAt(x, y).hashCode() == 2) {
            Block block = new Path(x, y);
            blocks.remove(getAt(x, y));
            blocks.add(block);
        }
        if (getAt(x, y).hashCode() == 3) {
            Block block = new Rock(x, y);
            blocks.remove(getAt(x, y));
            blocks.add(block);
        }
    }

    public Block getAt(int x, int y) {
        if (!validate(x, y)) {
            return null;
        }
        for (Block block : blocks) {
            if (block.getX() == x
                    && block.getY() == y) {
                return block;
            }
        }
        return null;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }
}
