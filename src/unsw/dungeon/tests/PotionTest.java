package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.ExitGoal;
import unsw.dungeon.Player;
import unsw.dungeon.Potion;
import unsw.dungeon.Wall;

class PotionTest {
	
	private Dungeon dungeon;
	private Player player;
	
	@BeforeEach
	void setUp() throws Exception {
		
		this.dungeon = new Dungeon (10,10);
		this.player = new Player (dungeon,0,2);
		this.dungeon.setGoal(new ExitGoal(this.player));
		this.dungeon.setPlayer(player);
	}

	@Test
	void potion_kill_test() {
		Potion p = new Potion (0,1);
		Enemy e = new Enemy (0,0,dungeon);
		Wall w1 = new Wall (1,0);
		Wall w2 = new Wall (1,1);
		Wall w3 = new Wall (1,2);
		
		dungeon.addEntity(p);
		dungeon.addEntity(e);
		dungeon.addEntity(w1);
		dungeon.addEntity(w2);
		dungeon.addEntity(w3);
		
		assertNotNull (dungeon.getEntity(0, 1));
		assertNotNull (dungeon.getEntity(0, 0));
		assertNotNull (dungeon.getEntity(1, 0));
		assertNotNull (dungeon.getEntity(1, 1));
		assertNotNull (dungeon.getEntity(1, 2));
		
		player.moveUp();
		assertNull (dungeon.getEntity(0, 1));
		
		player.moveUp();
		assertNull (dungeon.getEntity(0, 0));
	}
	

}
