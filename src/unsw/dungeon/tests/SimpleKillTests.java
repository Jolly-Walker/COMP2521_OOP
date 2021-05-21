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

class SimpleKillTests {
	
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
	void kill_right_test() {
		Sword sw = new Sword (1,0);
		dungeon.addEntity(sw);
		Entity e = dungeon.getEntity(1,0);
		assertNotNull (e);
		assertEquals (e instanceof Sword, true);
		player.moveRight();
		Entity e2 = dungeon.getEntity(1,0);
		assertNull(e2);
		Enemy en1 = new Enemy(1,1,dungeon);
		dungeon.addEntity(en1);
		assertNotNull (dungeon.getEntity(1,1));
		player.useSword();
		assertNull (dungeon.getEntity(1,1));
		
	}
	
	@Test
	void kill_left_test() {
		Sword sw = new Sword (1,0);
		dungeon.addEntity(sw);
		Entity e = dungeon.getEntity(1,0);
		assertNotNull (e);
		assertEquals (e instanceof Sword, true);
		player.moveRight();
		Entity e2 = dungeon.getEntity(1,0);
		assertNull(e2);
		player.moveDown();
		Enemy en1 = new Enemy(0,1,dungeon);
		dungeon.addEntity(en1);
		assertNotNull (dungeon.getEntity(0,1));
		player.useSword();
		assertNull (dungeon.getEntity(0,1));
		
	}
	
	@Test
	void kill_top_test() {
		Sword sw = new Sword (0,1);
		dungeon.addEntity(sw);
		Entity e = dungeon.getEntity(0,1);
		assertNotNull (e);
		assertEquals (e instanceof Sword, true);
		player.moveDown();
		Entity e2 = dungeon.getEntity(0,1);
		assertNull(e2);
		player.moveRight();
		Enemy en1 = new Enemy(2,0,dungeon);
		dungeon.addEntity(en1);
		assertNotNull (dungeon.getEntity(2,0));
		player.useSword();
		assertNull (dungeon.getEntity(2,0));
		
	}
	
	@Test
	void kill_bottom_test() {
		Sword sw = new Sword (0,1);
		dungeon.addEntity(sw);
		Entity e = dungeon.getEntity(0,1);
		assertNotNull (e);
		assertEquals (e instanceof Sword, true);
		player.moveDown();
		Entity e2 = dungeon.getEntity(0,1);
		assertNull(e2);
		Enemy en1 = new Enemy(0,2,dungeon);
		dungeon.addEntity(en1);
		assertNotNull (dungeon.getEntity(0,2));
		player.useSword();
		assertNull (dungeon.getEntity(0,2));
		
	}
	
//	@Test
//	void kill_player_test() {
//		assertNotNull(dungeon.getPlayer());
//		Sword sw = new Sword (0,1);
//		dungeon.addEntity(sw);
//		Entity e = dungeon.getEntity(0,1);
//		assertNotNull (e);
//		assertEquals (e instanceof Sword, true);
//		player.moveDown();
//		Entity e2 = dungeon.getEntity(0,1);
//		assertNull(e2);
//		
//		player.useSword();
//		player.useSword();
//		player.useSword();
//		player.useSword();
//		player.useSword();
//		player.useSword();
//
//		Enemy en1 = new Enemy(0,2,dungeon);
//		dungeon.addEntity(en1);
//		assertNotNull (dungeon.getEntity(0,2));
//		player.useSword();
//		assertNotNull (dungeon.getEntity(0,2));
//		en1.moveUp();
//		assertFalse(player.isAlived());
//	}
}
