package unsw.dungeon.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.ExitGoal;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;

class MovementTests {
	
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
	void wall_blocked_test() {
		Wall wall01 = new Wall (0,1);
		Wall wall02 = new Wall (1,0);
		this.dungeon.addEntity(wall01);
		this.dungeon.addEntity(wall02);
		assertEquals (player.isBlocked(wall01.getX(), wall01.getY(), "down"), true);
		assertEquals (player.isBlocked(wall02.getX(), wall02.getY(), "right"), true);
	}

	@Test
	void boulder_moveDown_test() {
		Boulder b01 = new Boulder (dungeon,0,1);
		this.dungeon.addEntity(b01);
		assertEquals (player.isBlocked(b01.getX(), b01.getY(), "down"), false);
		player.moveDown();
		assertEquals (b01.getX(), 0);
		assertEquals (b01.getY(), 2);		
	}
	
	@Test
	void boulder_moveTop_test() {
		player.moveDown();
		player.moveDown();
		Boulder b01 = new Boulder (dungeon,0,1);
		this.dungeon.addEntity(b01);
		assertEquals (player.isBlocked(b01.getX(), b01.getY(), "up"), false);
		player.moveUp();
		assertEquals (b01.getX(), 0);
		assertEquals (b01.getY(), 0);
	}
	
	@Test
	void boulder_moveRight_test() {
		Boulder b01 = new Boulder (dungeon,1,0);
		this.dungeon.addEntity(b01);
		assertEquals (player.isBlocked(b01.getX(), b01.getY(), "right"), false);
		player.moveRight();
		assertEquals (b01.getX(), 2);
		assertEquals (b01.getY(), 0);
	}	
	
	@Test
	void boulder_moveLeft_test() {
		player.moveRight();
		player.moveRight();
		Boulder b01 = new Boulder (dungeon,1,0);
		this.dungeon.addEntity(b01);
		assertEquals (player.isBlocked(b01.getX(), b01.getY(), "left"), false);
		player.moveLeft();
		assertEquals (b01.getX(), 0);
		assertEquals (b01.getY(), 0);
	}	
	
	/**
	 *  To test the player is not allowed to move a boulder if that boulders is stick to another one.
	 *  To test the player can move boulder if there is not one sticked with it.
	 * */
	@Test
	void boulder_blocked_test() {
		Boulder b01 = new Boulder (dungeon,1,0);
		Boulder b02 = new Boulder (dungeon,2,0);
		Boulder b03 = new Boulder (dungeon,0,1);
		this.dungeon.addEntity(b01);
		this.dungeon.addEntity(b02);
		this.dungeon.addEntity(b03);
		assertEquals (player.isBlocked(b01.getX(), b01.getY(), "right"), true);
		assertEquals (player.getX(), 0);
		assertEquals (player.getY(), 0);
		assertEquals (b01.getX(), 1);
		assertEquals (b01.getY(), 0);
		
		assertEquals (player.isBlocked(b03.getX(), b03.getY(), "down"), false);
		player.moveDown();
		assertEquals (b03.getX(), 0);
		assertEquals (b03.getY(), 2);
		assertEquals (player.getX(), 0);
		assertEquals (player.getY(), 1);
	}
	
}
