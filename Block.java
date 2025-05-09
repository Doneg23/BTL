public abstract class Block {
    private int x;
    private int y;
    private boolean isDestructible;
    private boolean isMovable;

    /**
     * Constructor.
     */
    public Block(int x, int y, boolean isDestructible, boolean isMovable) {
        this.x = x;
        this.y = y;
        this.isDestructible = isDestructible;
        this.isMovable = isMovable;
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

    /**
     * Check if block is destructible.
     * @return destructible
     */
    public boolean getIsDestructible() {
        return isDestructible;
    }

    /**
     * Set block destructible.
     */
    public void setIsDestructible(boolean destructible) {
        isDestructible = destructible;
    }

    /**
     * Check if user can move to block.
     * @return movable
     */
    public boolean getIsMovable() {
        return isMovable;
    }

    /**
     * Set block movable.
     * @param movable
     */
    public void setIsMovable(boolean movable) {
        isMovable = movable;
    }

    /**
     * Get block hashcode.
     * @return hashcode
     */
    public abstract int hashCode();
}
