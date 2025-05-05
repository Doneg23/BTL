public class Brick extends Block {
    public Brick(int x, int y) {
        super(x, y, true, false);
    }

    public int hashCode() {
        return 3;
    }
}
