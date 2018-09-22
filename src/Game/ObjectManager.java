package Game;

import java.awt.Graphics;

public class ObjectManager {
	Rocketship ship;
	Projectile[]proj = {};
	
	ObjectManager(Rocketship object){
		ship = object;
		
	}
	public void update(){
		ship.update();
	}
	public void draw(Graphics g) {
		ship.draw(g);
	}
	
}
