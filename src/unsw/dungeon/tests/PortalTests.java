package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.ExitGoal;
import unsw.dungeon.Player;
import unsw.dungeon.Portal;
import unsw.dungeon.Wall;

class PortalTests {

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
	void portal_up_test() {
		Portal p1 = new Portal(1, 1, 1);
		Portal p2 = new Portal (-1, 3, 4);
		dungeon.addEntity(p1);
		dungeon.addEntity(p2);
		
		assertNotNull (dungeon.getEntity(1, 1));
		assertNotNull (dungeon.getEntity(3, 4));
		
		player.moveDown();
		assertEquals (player.getX(), 3);
		assertEquals (player.getY(), 3);
		
	}
	
	@Test
	void portal_down_test() {
		Portal p1 = new Portal(1, 1, 1);
		Portal p2 = new Portal (-1, 3, 4);
		Wall w = new Wall (3,3);
		dungeon.addEntity(p1);
		dungeon.addEntity(p2);
		dungeon.addEntity(w);
		
		assertNotNull (dungeon.getEntity(1, 1));
		assertNotNull (dungeon.getEntity(3, 4));
		assertNotNull (dungeon.getEntity(3, 3));
		
		player.moveDown();
		assertEquals (player.getX(), 3);
		assertEquals (player.getY(), 5);
		
	}
	
	@Test
	void portal_right_test() {
		Portal p1 = new Portal(1, 1, 1);
		Portal p2 = new Portal (-1, 3, 4);
		Wall w1 = new Wall (3,3);
		Wall w2 = new Wall (3,5);
		dungeon.addEntity(p1);
		dungeon.addEntity(p2);
		dungeon.addEntity(w1);
		dungeon.addEntity(w2);
		
		assertNotNull (dungeon.getEntity(1, 1));
		assertNotNull (dungeon.getEntity(3, 4));
		assertNotNull (dungeon.getEntity(3, 3));
		assertNotNull (dungeon.getEntity(3, 5));
		
		player.moveDown();
		assertEquals (player.getX(), 4);
		assertEquals (player.getY(), 4);
		
	}
	
	@Test
	void portal_left_test() {
		Portal p1 = new Portal(1, 1, 1);
		Portal p2 = new Portal (-1, 3, 4);
		Wall w1 = new Wall (3,3);
		Wall w2 = new Wall (3,5);
		Wall w3 = new Wall (4,4);
		
		dungeon.addEntity(p1);
		dungeon.addEntity(p2);
		dungeon.addEntity(w1);
		dungeon.addEntity(w2);
		dungeon.addEntity(w3);
		
		assertNotNull (dungeon.getEntity(1, 1));
		assertNotNull (dungeon.getEntity(3, 4));
		assertNotNull (dungeon.getEntity(3, 3));
		assertNotNull (dungeon.getEntity(3, 5));
		assertNotNull (dungeon.getEntity(4, 4));
		
		player.moveDown();
		assertEquals (player.getX(), 2);
		assertEquals (player.getY(), 4);
		
	}
	
	@Test
	void portal_blocked_test() {
		Portal p1 = new Portal(1, 1, 1);
		Portal p2 = new Portal (-1, 3, 4);
		Wall w1 = new Wall (3,3);
		Wall w2 = new Wall (3,5);
		Wall w3 = new Wall (4,4);
		Wall w4 = new Wall (2,4);
		
		dungeon.addEntity(p1);
		dungeon.addEntity(p2);
		dungeon.addEntity(w1);
		dungeon.addEntity(w2);
		dungeon.addEntity(w3);
		dungeon.addEntity(w4);
		
		assertNotNull (dungeon.getEntity(1, 1));
		assertNotNull (dungeon.getEntity(3, 4));
		assertNotNull (dungeon.getEntity(3, 3));
		assertNotNull (dungeon.getEntity(3, 5));
		assertNotNull (dungeon.getEntity(4, 4));
		assertNotNull (dungeon.getEntity(2, 4));
		
		player.moveDown();
		assertEquals (player.getX(), 1);
		assertEquals (player.getY(), 0);
		
	}

}
