public class Rock extends Block {
    /**
     * Constructor.
     */
    public Rock(int x, int y) {
        super(x, y, true, false);
    }

    /**
     * Get rock hashcode.
     * @return 2
     */
    @Override
    public int hashCode() {
        return 2;
    }
}
