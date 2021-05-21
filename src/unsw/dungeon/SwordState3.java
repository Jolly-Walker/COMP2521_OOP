package unsw.dungeon;

import java.util.ArrayList;

public class SwordState3 implements SwordState {

	@Override
	public void swingSword(Player player) {
		Dungeon d = player.getDungeon();
    	ArrayList <Enemy> top = d.getEnemies(player.getX(), player.getY() - 1);
    	ArrayList <Enemy>  bottom = d.getEnemies(player.getX(), player.getY() + 1);
    	ArrayList <Enemy>  right = d.getEnemies(player.getX() + 1, player.getY());
    	ArrayList <Enemy>  left = d.getEnemies(player.getX() - 1, player.getY());
    	player.kill_enemies(top);
    	player.kill_enemies(bottom);
    	player.kill_enemies(right);
    	player.kill_enemies(left);
    	player.addSwordAttack(player.getX() + 1, player.getY());
    	player.addSwordAttack(player.getX() - 1, player.getY());
    	player.addSwordAttack(player.getX(), player.getY() - 1);
    	player.addSwordAttack(player.getX(), player.getY() + 1);
		player.setSword((new SwordState2()));
		
	}
	
	@Override
	public boolean isSword() {
		return true;
	}
}
