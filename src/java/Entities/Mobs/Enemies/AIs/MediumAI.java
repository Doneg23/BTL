package Entities.Mobs.Enemies.AIs;

import Entities.Mobs.Enemies.Enemy;
import Entities.Mobs.Player;

public class MediumAI extends AI {
    Player player;
    Enemy enemy;

    public MediumAI(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public int calculateDirection() {
        if(player == null)
            return random.nextInt(4);
        int vertical = random.nextInt(2);
        if(vertical == 1) {
            int v = calculateRowDirection();
            if(v != -1) return v;
            else return calculateColDirection();
        } else {
            int h = calculateColDirection();
            if(h != -1) return h;
            else return calculateRowDirection();
        }
    }

    protected int calculateColDirection() {
        if(player.getXTile() < enemy.getXTile()) return 3;
        else if(player.getXTile() > enemy.getXTile()) return 1;
        return -1;
    }

    protected int calculateRowDirection() {
        if(player.getYTile() < enemy.getYTile()) return 0;
        else if(player.getYTile() > enemy.getYTile()) return 2;
        return -1;
    }
}
