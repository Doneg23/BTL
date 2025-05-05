public class Rock extends Block {
    public Rock(int x, int y) {
        super(x, y, true, false);
    }

    public int hashCode() {
        return 2;
    }
}
