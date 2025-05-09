public class Game {
    private Board board;
    private User user;
    private int exitX;
    private int exitY;

    /**
     * Constructor.
     */
    public Game(Board board) {
        board = new Board();
        user = new User();
        exitX = (int) (16 - Math.random() * 8);
        exitY = (int) (9 - Math.random() * 4.5);
    }

    /**
     * Get board.
     * @return board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Set board.
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Get user.
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set user.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Get coordinate x of the exit.
     * @return x
     */
    public int getExitX() {
        return exitX;
    }

    /**
     * Set coordinate x of the exit.
     */
    public void setExitX(int x) {
        exitX = x;
    }

    /**
     * Get coordinate y of the exit.
     * @return y
     */
    public int getExitY() {
        return exitY;
    }

    /**
     * Set coordinate y of the exit.
     */
    public void setExitY(int y) {
        exitY = y;
    }
}
