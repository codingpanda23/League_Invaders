package Game;

import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
	Rocketship ship;
	ArrayList<Projectile> proj; 
	
	ObjectManager(Rocketship object){
		ship = object;
		proj= new ArrayList<Projectile>();
	}
	public void update(){
		ship.update();
		for (int i = 0; i < proj.size(); i++) {
			proj.get(i).update();
		}
	}
	public void draw(Graphics g) {
		ship.draw(g);
		for (int i = 0; i < proj.size(); i++) {
			proj.get(i).draw(g);
		}
	}
	public void addProjectile(Projectile proj){
		
	}
	
}
