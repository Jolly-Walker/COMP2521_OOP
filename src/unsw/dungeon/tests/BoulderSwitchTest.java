package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.ExitGoal;
import unsw.dungeon.Player;
import unsw.dungeon.Switch;

class BoulderSwitchTest {
	
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
	void activate_test() {
		Switch s = new Switch(0,2);
		Boulder d = new Boulder (dungeon,0,1);
		
		dungeon.addEntity(s);
		dungeon.addEntity(d);
		
		assertNotNull (dungeon.switchGet(0, 2));
		assertNotNull (dungeon.switchGet(0, 1));
		assertFalse (s.getActivateStatus());
		player.moveDown();
		assertTrue (s.getActivateStatus());
		player.moveDown();
		assertFalse (s.getActivateStatus());
	}

}
