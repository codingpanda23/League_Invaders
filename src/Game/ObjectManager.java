package Game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class ObjectManager {
	Ghost ghost;
	ArrayList<Projectile> projs;
	ArrayList<BadCandy> bad;
	ArrayList<GoodCandy> good;
	Long enemyTimer;
	Long goodTimer;
	int enemySpawnTime;
	int goodSpawnTime;
	int gameTimer;
	int score;
	int badCandy;
	int goodCandy;
	int lives;

	ObjectManager(Ghost object) {
		ghost = object;
		projs = new ArrayList<Projectile>();
		bad = new ArrayList<BadCandy>();
		good = new ArrayList<GoodCandy>();
		enemyTimer = new Long(0);
		goodTimer = new Long(0);
		enemySpawnTime = 3000;
		goodSpawnTime = 2000;
		score = 0;
		lives = 3;
		}

	public void update() {
		ghost.update();
		for (int i = 0; i < projs.size(); i++) {
			projs.get(i).update();
		}
		for (int i = 0; i < bad.size(); i++) {
			bad.get(i).update();
		}
		for (int i = 0; i < good.size(); i++) {
			good.get(i).update();
		}
	}

	public void draw(Graphics g) {
		ghost.draw(g);
		for (int i = 0; i < projs.size(); i++) {
			projs.get(i).draw(g);
		}
		for (int i = 0; i < bad.size(); i++) {
			bad.get(i).draw(g);
		}
		for (int i = 0; i < good.size(); i++) {
			good.get(i).draw(g);
		}
	}

	public void addProjectile(Projectile proj) {
		projs.add(proj);
	}

	public void addAlien(BadCandy al) {
		bad.add(al);
	}

	public void addGood(GoodCandy gc) {
		good.add(gc);
	}

	public void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addAlien(new BadCandy(new Random().nextInt(CandyGuard.width - 100), 0, 50, 50));
			enemyTimer = System.currentTimeMillis();
		}
		if (System.currentTimeMillis() - goodTimer >= goodSpawnTime) {
			addGood(new GoodCandy(new Random().nextInt(CandyGuard.width - 100), 0, 50, 50));
			goodTimer = System.currentTimeMillis();
		}

	}

	//////////////////////////////////////////////////////////////////////////////////////////
	public void checkCollision() {
		for (BadCandy a : bad) {
			if (ghost.collisionBox.intersects(a.collisionBox)) {
				lives--;
				a.isAlive = false;
			}

			for (Projectile p : projs) {
				if (p.collisionBox.intersects(a.collisionBox)) {
					a.isAlive = false;
					p.isAlive = false;
					score++;
				}

			}

			if (a.y + a.height >= 800) {
				badCandy++;
				a.isAlive = false;
				lives--;
			}
		}
		for (GoodCandy gcandy : good) {
			
			if (ghost.collisionBox.intersects(gcandy.collisionBox)) {
				gcandy.isAlive = false;
				score++;
			}
			for (Projectile p : projs) {
				if (p.collisionBox.intersects(gcandy.collisionBox)) {
					gcandy.isAlive = false;
					p.isAlive = false;
					lives--;
				}
			}

		}
		if (lives == 0) {
			ghost.isAlive = false;
			GamePanel.currentState = GamePanel.END_STATE;
		}
		if (badCandy >= 3) {
			GamePanel.currentState = GamePanel.END_STATE;
		}

	}

	//////////////////////////////////////////////////////////////////////////////////////////
	public int lives() {
		return this.lives;
	}

	public void purgeObjects() {
		for (int i = 0; i < projs.size(); i++) {
			if (projs.get(i).isAlive == false) {
				projs.remove(i);
			}
		}
		for (int j = 0; j < bad.size(); j++) {
			if (bad.get(j).isAlive == false) {
				bad.remove(j);
			}
		}
		for (int k = 0; k < good.size(); k++) {
			if (good.get(k).isAlive == false) {
				good.remove(k);
			}
		}
	}

	public int getScore() {
		return this.score;
	}
}