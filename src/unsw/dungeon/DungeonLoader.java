package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    private Dungeon dungeon;
    private int nEnemies;
    private int nTreasures;
    private int nSwitches; 

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
        this.dungeon = null;
        this.nEnemies = 0;
        this.nTreasures = 0;
        this.nSwitches = 0; 
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        this.dungeon = dungeon;
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "sword":
        	Sword sword = new Sword(x, y);
        	onLoad(sword);
        	entity = sword;
        	break;
        case "enemy":
        	Enemy enemy = new Enemy(x, y, dungeon);
        	onLoad(enemy);
        	entity = enemy;
        	nEnemies++;
          	break;
        case "boulder":
        	Boulder boulder = new Boulder(dungeon, x, y);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "switch":
        	Switch floorSwitch = new Switch(x,y);
        	onLoad(floorSwitch);
        	entity = floorSwitch;
        	nSwitches++;
        	break;
        case "invincibility":
        	Potion potion = new Potion(x, y);
        	onLoad(potion);
        	entity = potion;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x, y);
        	onLoad(treasure);
        	entity = treasure;
        	dungeon.setnTreasures(dungeon.getnTreasures());
        	nTreasures++;
        	break;
        case "exit":
        	Exit exit = new Exit(x,y);
        	onLoad(exit);
        	entity = exit;
        	break;
        case "portal":
        	int pid = json.getInt("pid");
        	Portal portal = new Portal(pid,x,y);
        	onLoad(portal);
        	entity = portal;
        	break;
        case "door":
        	int did = json.getInt("did");
        	Door door = new Door(x,y,did);
        	onLoad(door);
        	entity = door;
        	break;
        case "key":
        	int kid = json.getInt("kid");
        	DoorKey key = new DoorKey(x,y,kid);
        	onLoad(key);
        	entity = key;
        	break;
        }
        dungeon.addEntity(entity);
    }
    
    
    public void loadGoal() {
    	JSONObject mainGoal = json.getJSONObject("goal-condition");
    	String goalName = mainGoal.getString("goal");
    	switch (goalName) {
    	
    		case "AND":
    			CompositeGoal aGoal = new CompositeGoal("AND");
    			dungeon.setGoal(aGoal);
    			JSONArray aSubs = mainGoal.getJSONArray("subgoals");
    			setSubGoals(aSubs, aGoal);
    			break;
    		case "OR":
    			CompositeGoal oGoal = new CompositeGoal("OR");
    			dungeon.setGoal(oGoal);
    			JSONArray oSubs = mainGoal.getJSONArray("subgoals");
    			setSubGoals(oSubs, oGoal);
    			break;
    		case "enemies":
    			EnemyGoal eGoal = new EnemyGoal(nEnemies);
    			dungeon.addObserverToEntities(eGoal);
    			dungeon.setGoal(eGoal);
    			break;
    		case "treasure":
    			TreasureGoal tGoal = new TreasureGoal(nTreasures);
    			dungeon.addObserverToEntities(tGoal);
    			dungeon.setGoal(tGoal);
    			break;
    		case "boulders":
    			SwitchGoal sGoal = new SwitchGoal(nSwitches);
    			dungeon.addObserverToEntities(sGoal);
    			dungeon.setGoal(sGoal);
    			break;
    		case "exit":
    			ExitGoal exGoal = new ExitGoal(dungeon.getPlayer());
    			dungeon.setGoal(exGoal);
    			break;
    	}
    	dungeon.checkSwitches();
    	return;
    }
    
    

    private void setSubGoals(JSONArray subGoals, CompositeGoal goal) {
    	
    	 for (int i = 0; i < subGoals.length(); i++) {
             JSONObject o = subGoals.getJSONObject(i);
             String goalName = o.getString("goal");
             switch (goalName) {
	           case "AND":
	        	   CompositeGoal aGoal = new CompositeGoal("AND");
	        	   JSONArray aSubs = o.getJSONArray("subgoals");
	        	   setSubGoals(aSubs, aGoal);
	        	   goal.addGoal(aGoal);
	        	   break;
	     		case "OR":
	     			CompositeGoal oGoal = new CompositeGoal("OR");
	     			JSONArray oSubs = o.getJSONArray("subgoals");
	     			setSubGoals(oSubs, oGoal);
	     			goal.addGoal(oGoal);
	     			break;
	     		case "enemies":
	     			EnemyGoal eGoal = new EnemyGoal(nEnemies);
	     			dungeon.addObserverToEntities(eGoal);
	     			goal.addGoal(eGoal);
	     			break;
	     		case "treasure":
	     			TreasureGoal tGoal = new TreasureGoal(nTreasures);
	     			dungeon.addObserverToEntities(tGoal);
	     			goal.addGoal(tGoal);
	     			break;
	     		case "boulders":
	     			SwitchGoal sGoal = new SwitchGoal(nSwitches);
	     			dungeon.addObserverToEntities(sGoal);
	     			goal.addGoal(sGoal);
	     			break;
	     		case "exit":
	     			ExitGoal exGoal = new ExitGoal(dungeon.getPlayer());
	     			goal.addGoal(exGoal);
	     			break;
             }
         }
		
	}

	public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);
    
    public abstract void onLoad(Sword sword);
    
    public abstract void onLoad(Enemy enemy);
    
    public abstract void onLoad(Switch floorSwitch);
    
    public abstract void onLoad(Potion potion);
    
    public abstract void onLoad(Treasure treasure);
    
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(Portal portal);
    
    public abstract void onLoad(Door door);
    
    public abstract void onLoad(DoorKey key);
    // TODO Create additional abstract methods for the other entities

}
