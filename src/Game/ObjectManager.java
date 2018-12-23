package Game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Ghost ship;
	ArrayList<Projectile> projs;
	ArrayList<BadCandy> aliens;
	ArrayList<GoodCandy> good;
	Long enemyTimer;
	Long goodTimer;
	int enemySpawnTime;
	int goodSpawnTime;
	int score;
	int badCandy;
	int goodCandy;

	ObjectManager(Ghost object) {
		ship = object;
		projs = new ArrayList<Projectile>();
		aliens = new ArrayList<BadCandy>();
		good = new ArrayList<GoodCandy>();
		enemyTimer = new Long(0);
		goodTimer = new Long(0);
		enemySpawnTime = 1000;
		goodSpawnTime = 5000;
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
		for (int i = 0; i < good.size(); i++) {
			good.get(i).update();
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
		for (int i = 0; i < good.size(); i++) {
			good.get(i).draw(g);
		}
	}

	public void addProjectile(Projectile proj) {
		projs.add(proj);
	}

	public void addAlien(BadCandy al) {
		aliens.add(al);
	}

	public void addGood(GoodCandy gc) {
		good.add(gc);
	}

	public void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addAlien(new BadCandy(new Random().nextInt(CandyGuard.width), 0, 50, 50));
			enemyTimer = System.currentTimeMillis();
		}
		if (System.currentTimeMillis() - goodTimer >= goodSpawnTime) {
			addGood(new GoodCandy(new Random().nextInt(CandyGuard.width), 0, 50, 50));
			goodTimer = System.currentTimeMillis();
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

				for (GoodCandy gcandy : good) {
					if (p.collisionBox.intersects(gcandy.collisionBox)) {
						gcandy.isAlive = false;
						p.isAlive = false;
						GamePanel.currentState = GamePanel.END_STATE;
					}
					if (gcandy.collisionBox.intersects(a.collisionBox)) {
						gcandy.isAlive = false;
					}
				}
			}
		}
		for (BadCandy b : aliens) {
			if (b.y + b.height >= 800) {
				badCandy++;
				b.isAlive = false;
			}
			if (badCandy >= 5) {
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
		for (int i = 0; i < good.size(); i++) {
			if (good.get(i).isAlive == false) {
				good.remove(i);
			}
		}
	}

	public int getScore() {
		return this.score;
	}
}