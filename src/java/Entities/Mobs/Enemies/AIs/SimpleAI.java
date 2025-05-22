package Entities.Mobs.Enemies.AIs;

public class SimpleAI extends AI {
    @Override
    public int calculateDirection() {
        return random.nextInt(4);
    }
}
