package unsw.dungeon;

import java.util.ArrayList;

public class SwordState2 implements SwordState {

	@Override
	public void swingSword(Player player) {
		Dungeon d = player.getDungeon();
    	ArrayList <Enemy> top = d.getEnemies(player.getX(), player.getY() - 1);
    	ArrayList <Enemy>  bottom = d.getEnemies(player.getX(), player.getY() + 1);
    	ArrayList <Enemy>  right = d.getEnemies(player.getX() + 1, player.getY());
    	ArrayList <Enemy>  left = d.getEnemies(player.getX() - 1, player.getY());
    	ArrayList <Enemy>  top_right = d.getEnemies(player.getX() + 1, player.getY() - 1);
    	ArrayList <Enemy>  top_left = d.getEnemies(player.getX() - 1, player.getY() - 1);
    	ArrayList <Enemy>  bottom_right = d.getEnemies(player.getX() + 1, player.getY() + 1);
    	ArrayList <Enemy>  bottom_left = d.getEnemies(player.getX() - 1, player.getY() + 1);	
    	player.kill_enemies(top);
    	player.kill_enemies(bottom);
    	player.kill_enemies(right);
    	player.kill_enemies(left);
    	player.kill_enemies(top_right);
    	player.kill_enemies(top_left);
    	player.kill_enemies(bottom_right);
    	player.kill_enemies(bottom_left);
    	player.addSwordAttack(player.getX() + 1, player.getY());
    	player.addSwordAttack(player.getX() - 1, player.getY());
    	player.addSwordAttack(player.getX(), player.getY() - 1);
    	player.addSwordAttack(player.getX(), player.getY() + 1);
    	player.addSwordAttack(player.getX() + 1, player.getY() - 1);
    	player.addSwordAttack(player.getX() - 1, player.getY() - 1);
    	player.addSwordAttack(player.getX() + 1, player.getY() + 1);
    	player.addSwordAttack(player.getX() - 1, player.getY() + 1);
		player.setSword(new SwordState1());
	}
	
	@Override
	public boolean isSword() {
		return true;
	}

}
