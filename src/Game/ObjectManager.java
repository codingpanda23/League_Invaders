package Game;
import java.awt.*;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Rocketship ship;
	ArrayList<Projectile> projs;
	ArrayList<Alien> aliens;
	Long enemyTimer;
	int enemySpawnTime;
	int score;
	int badCandy;

	ObjectManager(Rocketship object) {
		ship = object;
		projs = new ArrayList<Projectile>();
		aliens = new ArrayList<Alien>();
		enemyTimer = new Long(0);
		enemySpawnTime = 1000;
		score = 0;
	}

	public void update() {
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

	public void addProjectile(Projectile proj) {
		projs.add(proj);
	}

	public void addAlien(Alien al) {
		aliens.add(al);
	}

	public void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addAlien(new Alien(new Random().nextInt(LeagueInvaders.width), 0, 50, 50));
			enemyTimer = System.currentTimeMillis();
		}
	}

	public void checkCollision() {

		for (Alien a : aliens) {
			if (ship.collisionBox.intersects(a.collisionBox)) {
				ship.isAlive = false;
			}

			for (Projectile p : projs) {
				if (p.collisionBox.intersects(a.collisionBox)) {
					a.isAlive = false;
					p.isAlive = false;
					score++;
				}
			}
		}
		for (Alien b : aliens) {
			if (b.y+b.height>=800) {
				badCandy++;
				b.isAlive = false;
			}
			if (badCandy>=5) {
				GamePanel.currentState = GamePanel.END_STATE;
			}
		}
		
	
	}
	

	public void purgeObjects() {
		for (int i = 0; i < projs.size(); i++) {
			if (projs.get(i).isAlive == false) {
				projs.remove(i);
			}
		}
		for (int i = 0; i < aliens.size(); i++) {
			if (aliens.get(i).isAlive == false) {
				aliens.remove(i);
			}
		}
	}

	public int getScore() {
		return this.score;
	}
}