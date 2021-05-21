package unsw.dungeon;

import java.util.ArrayList;

public class SwordState5 implements SwordState {

	@Override
	public void swingSword(Player player) {
		Dungeon d = player.getDungeon();
    	ArrayList <Enemy>  right = d.getEnemies(player.getX() + 1, player.getY());
    	ArrayList <Enemy>  left = d.getEnemies(player.getX() - 1, player.getY());
    	player.kill_enemies(right);
    	player.kill_enemies(left);
    	player.addSwordAttack(player.getX() + 1, player.getY());
    	player.addSwordAttack(player.getX() - 1, player.getY());
		player.setSword(new SwordState4());
		
	}
	
	@Override
	public boolean isSword() {
		return true;
	}
}