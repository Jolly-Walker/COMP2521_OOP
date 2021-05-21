package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.ExitGoal;
import unsw.dungeon.Player;
import unsw.dungeon.Treasure;

class TreasureTest {

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
	void pick_up_test() {
		Treasure t = new Treasure(0,1);
		dungeon.addEntity(t);
		assertNotNull (dungeon.getEntity(0, 1));
		player.moveDown();
		assertNull (dungeon.getEntity(0, 1));
		
	}

}
