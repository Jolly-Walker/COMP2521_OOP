package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Door;
import unsw.dungeon.DoorKey;
import unsw.dungeon.Dungeon;
import unsw.dungeon.ExitGoal;
import unsw.dungeon.Player;

class DoorKeytest {
	
	private Dungeon dungeon;
	private Player player;
	
	@BeforeEach
	void setUp() throws Exception {
		
		this.dungeon = new Dungeon (10,10);
		this.player = new Player (dungeon,0,0);
		this.dungeon.setGoal(new ExitGoal(this.player));
		this.dungeon.setPlayer(player);
	}

	@Test
	void unlock_test() {
		DoorKey k = new DoorKey (0,1,1);
		Door d = new Door (0,2,1);
		
		dungeon.addEntity(k);
		dungeon.addEntity(d);
		
		assertNotNull (dungeon.getEntity(0, 1));
		assertNotNull (dungeon.getEntity(0, 2));
//		assertTrue (d.isBlocked());
		
		player.moveDown();
		assertNull (dungeon.getEntity(0, 1));
		
		player.dropKey();
		assertNotNull (dungeon.getEntity(0, 1));
		
		player.moveDown();
//		assertTrue (d.isBlocked());
		assertEquals (player.getX(), 0);
		assertEquals (player.getY(), 1);
		
		player.moveRight();
		player.moveLeft();
		player.moveDown();
//		assertFalse (d.isBlocked());
		assertEquals (player.getX(), 0);
		assertEquals (player.getY(), 2);
		
	}

}
