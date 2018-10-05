package Game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Rocketship ship;
	ArrayList<Projectile> projs;
	ArrayList<Alien> aliens;
	Long enemyTimer;
	int enemySpawnTime;
	
	
	ObjectManager(Rocketship object){
		ship = object;
		projs= new ArrayList<Projectile>();
		aliens = new ArrayList<Alien>();
		enemyTimer = new Long(0);
		
		
	}
	public void update(){
		ship.update();
		for (int i = 0; i < projs.size(); i++) {
			projs.get(i).update();
		}
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
		}
		
	}
	public void draw(Graphics g) {
		ship.draw(g);
		for (int i = 0; i < projs.size(); i++) {
			projs.get(i).draw(g);
		}
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
	}
	public void addProjectile(Projectile proj){
		projs.add(proj);
	}
	public void addAlien(Alien al) {
		aliens.add(al);
	}
	public void manageEnemies(){
        if(System.currentTimeMillis() - enemyTimer >= enemySpawnTime){
                addAlien(new Alien(new Random().nextInt(LeagueInvaders.width), 0, 50, 50));
                enemyTimer = System.currentTimeMillis();
        }
	}
	public void purgeObjects() {
		for (int i = 0; i < projs.size(); i++) {
			projs.remove(projs);
		}
		for (int i = 0; i < aliens.size(); i++) {
			aliens.remove(aliens);
		}
	}
	
}
