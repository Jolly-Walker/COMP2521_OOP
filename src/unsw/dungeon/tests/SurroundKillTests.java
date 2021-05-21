package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;
import unsw.dungeon.ExitGoal;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;

class SurroundKillTests {
	
	private Dungeon dungeon;
	private Player player;
	
	@BeforeEach
	void setUp() throws Exception {
		
		this.dungeon = new Dungeon (10,10);
		this.player = new Player (dungeon,1,0);
		this.dungeon.setGoal(new ExitGoal(this.player));
		this.dungeon.setPlayer(player);
	}

	@Test
	void surround_kil_test() {
		Sword sw = new Sword (1,1);
		dungeon.addEntity(sw);
		Entity e = dungeon.getEntity(1,1);
		assertNotNull (e);
		assertEquals (e instanceof Sword, true);
		player.moveDown();
	
		Enemy en1 = new Enemy(0,0,dungeon);
		Enemy en2 = new Enemy(1,0,dungeon);
		Enemy en3 = new Enemy(2,0,dungeon);
		Enemy en4 = new Enemy(0,1,dungeon);
		Enemy en5 = new Enemy(0,2,dungeon);
		Enemy en6 = new Enemy(2,1,dungeon);
		Enemy en7 = new Enemy(2,2,dungeon);
		Enemy en8 = new Enemy(1,2,dungeon);
		
		dungeon.addEntity(en1);
		dungeon.addEntity(en2);
		dungeon.addEntity(en3);
		dungeon.addEntity(en4);
		dungeon.addEntity(en5);
		dungeon.addEntity(en6);
		dungeon.addEntity(en7);
		dungeon.addEntity(en8);	
		
		assertNotNull (dungeon.getEntity(0,0));
		assertNotNull (dungeon.getEntity(1,0));
		assertNotNull (dungeon.getEntity(1,0));
		assertNotNull (dungeon.getEntity(0,1));
		assertNotNull (dungeon.getEntity(0,2));
		assertNotNull (dungeon.getEntity(2,1));
		assertNotNull (dungeon.getEntity(2,2));
		assertNotNull (dungeon.getEntity(1,2));
		
		player.useSword();
		assertNull (dungeon.getEntity(0,0));
		assertNull (dungeon.getEntity(1,0));
		assertNull (dungeon.getEntity(1,0));
		assertNull (dungeon.getEntity(0,1));
		assertNull (dungeon.getEntity(0,2));
		assertNull (dungeon.getEntity(2,1));
		assertNull (dungeon.getEntity(2,2));
		assertNull (dungeon.getEntity(1,2));		
		
	}

}
