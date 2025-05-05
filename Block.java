public abstract class Block {
    private int x;
    private int y;
    private boolean isDestructible;
    private boolean isMovable;

    public Block(int x, int y, boolean isDestructible, boolean isMovable) {
        this.x = x;
        this.y = y;
        this.isDestructible = isDestructible;
        this.isMovable = isMovable;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean getIsDestructible() {
        return isDestructible;
    }

    public void setIsDestructible(boolean destructible) {
        isDestructible = destructible;
    }

    public boolean getIsMovable() {
        return isMovable;
    }

    public void setIsMovable(boolean movable) {
        isMovable = movable;
    }

    public abstract int hashCode();
}
