public class Brick extends Block {
    /**
     * Constructor.
     */
    public Brick(int x, int y) {
        super(x, y, true, false);
    }

    /**
     * Get brick hashcode.
     * @return 3
     */
    @Override
    public int hashCode() {
        return 3;
    }
}
