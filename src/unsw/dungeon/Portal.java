package unsw.dungeon;

import javafx.scene.media.AudioClip;

public class Portal extends Entity {
	private int pid;
	private AudioClip portalSound; 
	
    public Portal(int pid ,int x, int y) {
        super(x, y);
        this.pid = pid;
        this.portalSound = new AudioClip(getClass().getResource("sounds/portal.mp3").toString());
    }
    
    /**
     * Teleports player
     * @param Dungeon d
     */
    public void teleport (Dungeon d) {
    	Player p = d.getPlayer();
    	Entity link_p = d.searchPortalEntity(this);
    	if (link_p instanceof Portal) {
    		String direction = check_surrounding(d, (Portal) link_p);
    		if (direction == "null") return;
    		else {
    			portalSound.play();
	    		p.x().set(link_p.getX());
	    		p.y().set(link_p.getY());
	    		switch (direction) {
	    		  case "top":
	    		    p.moveUp();
	    		    break;  
	    		  case "bottom":
	  	    		p.moveDown();
	    		    break;
	    		  case "right":
	    			p.moveRight();
	    		    break;
	    		  case "left":
	    			p.moveLeft(); 
	    		    break;
	    		}
    		}
    	}
    }
  
	public int getPid() {
		return pid;
	}   
    
	/**
	 * 
	 * @param Dungeon d - the dungeon
	 * @param Portal link - the corresponding portal to this portal
	 * @return returns a string which indicates the position of player entitiy when teleported
	 */
	public String check_surrounding(Dungeon d, Portal link) {
		Entity top = d.getEntity(link.getX(), link.getY() - 1);
		Entity bottom = d.getEntity(link.getX(), link.getY() + 1);
		Entity right = d.getEntity(link.getX() + 1, link.getY());
		Entity left = d.getEntity(link.getX() - 1, link.getY());
		
		if (top == null && link.getY()-1 > 0) return "top";
		else if (bottom == null && link.getY()+1 < d.getHeight()) return "bottom";
		else if (right == null && link.getX()+1 < d.getWidth()) return "right";
		else if (left == null && link.getX()-1 > 0) return "left";
		else return "null";
		
	}
	
	public boolean isBlocked (int x, int y, Dungeon d) {
		teleport (d);
		return true;
	}
}
