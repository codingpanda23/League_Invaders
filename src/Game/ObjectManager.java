package Game;

import java.awt.Graphics;

public class ObjectManager {
	Rocketship ship;
	
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
