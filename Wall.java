public class Wall extends Block {
    /**
     * Constructor.
     */
    public Wall(int x, int y) {
        super(x, y, false, false);
    }

    /**
     * Get wall hashcode.
     * @return 0
     */
    @Override
    public int hashCode() {
        return 0;
    }
}
