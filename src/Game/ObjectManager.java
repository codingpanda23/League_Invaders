package Game;
import java.awt.*;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Ghost ship;
	ArrayList<Projectile> projs;
	ArrayList<BadCandy> aliens;
	Long enemyTimer;
	int enemySpawnTime;
	int score;
	int badCandy;

	ObjectManager(Ghost object) {
		ship = object;
		projs = new ArrayList<Projectile>();
		aliens = new ArrayList<BadCandy>();
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

	public void addAlien(BadCandy al) {
		aliens.add(al);
	}

	public void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addAlien(new BadCandy(new Random().nextInt(CandyGuard.width), 0, 50, 50));
			enemyTimer = System.currentTimeMillis();
		}
	}

	public void checkCollision() {

		for (BadCandy a : aliens) {
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
		for (BadCandy b : aliens) {
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