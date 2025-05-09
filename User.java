public class User {
    private int x;
    private int y;

    /**
     * Default Constructor.
     */
    public User() {
        x = 0;
        y = 0;
    }

    /**
     * Constructor.
     */
    public User(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Move user up.
     */
    public void moveUp(Board board) {
        if (x == 0 || board.getAt(x - 1, y) == null
                || !board.getAt(x - 1, y).getIsMovable()) {
            return;
        }
        x -= 1;
    }

    /**
     * Move user down.
     */
    public void moveDown(Board board) {
        if (x == Board.WIDTH - 1 || board.getAt(x + 1, y) == null
                || !board.getAt(x + 1, y).getIsMovable()) {
            return;
        }
        x += 1;
    }

    /**
     * Move user to left.
     */
    public void moveLeft(Board board) {
        if (y == 0 || board.getAt(x, y - 1) == null
                || !board.getAt(x, y - 1).getIsMovable()) {
            return;
        }
        y -= 1;
    }

    /**
     * Move user to right.
     */
    public void moveRight(Board board) {
        if (y == Board.HEIGHT - 1 || board.getAt(x, y + 1) == null
                || !board.getAt(x, y + 1).getIsMovable()) {
            return;
        }
        y += 1;
    }

    /**
     * Place bomb at position.
     */
    public void placeBomb(Board board) {
        board.destroyAt(x + 1, y);
        board.destroyAt(x - 1, y);
        board.destroyAt(x, y + 1);
        board.destroyAt(x, y - 1);
    }

    /**
     * Get coordinate x.
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Set coordinate x.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get coordinate y.
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Set coordinate y.
     */
    public void setY(int y) {
        this.y = y;
    }
}
