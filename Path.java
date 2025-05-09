public class Path extends Block {
    /**
     * Constructor.
     */
    public Path(int x, int y) {
        super(x, y, false, true);
    }

    /**
     * Get path hashcode.
     * @return 1
     */
    @Override
    public int hashCode() {
        return 1;
    }
}
