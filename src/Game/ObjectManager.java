package Game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class ObjectManager {
	Ghost ship;
	ArrayList<Projectile> projs;
	ArrayList<BadCandy> aliens;
	ArrayList<GoodCandy> good;
	Long enemyTimer;
	Long goodTimer;
	Long otherTimer;
	int enemySpawnTime;
	int goodSpawnTime;
	int gameTimer;
	int score;
	int badCandy;
	int goodCandy;
	int lives;

	ObjectManager(Ghost object) {
		ship = object;
		projs = new ArrayList<Projectile>();
		aliens = new ArrayList<BadCandy>();
		good = new ArrayList<GoodCandy>();
		enemyTimer = new Long(0);
		goodTimer = new Long(0);
		enemySpawnTime = 3000;
		goodSpawnTime = 2000;
		score = 0;
		lives = 3;
		//ship.collisionBox.setSize(width, height);
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
			addAlien(new BadCandy(new Random().nextInt(CandyGuard.width-100), 0, 50, 50));
			enemyTimer = System.currentTimeMillis();
		}
		if (System.currentTimeMillis() - goodTimer >= goodSpawnTime) {
			addGood(new GoodCandy(new Random().nextInt(CandyGuard.width-100), 0, 50, 50));
			goodTimer = System.currentTimeMillis();
		}
		
	}
//////////////////////////////////////////////////////////////////////////////////////////
	public void checkCollision() {
		for (BadCandy a : aliens) {
			if (ship.collisionBox.intersects(a.collisionBox)) {
				lives--;
				a.isAlive = false;
			}

			for (Projectile p : projs) {
				if (p.collisionBox.intersects(a.collisionBox)) {
					a.isAlive = false;
					p.isAlive = false;
					score++;
				}

				for (GoodCandy gcandy : good) {
					if (p.collisionBox.intersects(gcandy.collisionBox)) {
						lives--;
						gcandy.isAlive = false;
						p.isAlive = false;
					}
					
				}
			}
		
		if (lives == 0) {
			ship.isAlive = false;
			GamePanel.currentState = GamePanel.END_STATE;
		}
		for (GoodCandy gcan : good) {
			if (ship.collisionBox.intersects(gcan.collisionBox)) {
				gcan.isAlive = false;
			}
		}
		}
		for (BadCandy b : aliens) {
			if (b.y + b.height >= 800) {
				badCandy++;
				b.isAlive = false;
				lives--;
			}
			if (badCandy >= 3) {
				GamePanel.currentState = GamePanel.END_STATE;
			}
		}
		
	}
//////////////////////////////////////////////////////////////////////////////////////////	
	public int lives(){
		return this.lives;
	}
	
	public void purgeObjects() {
		for (int i = 0; i < projs.size(); i++) {
			if (projs.get(i).isAlive == false) {
				projs.remove(i);
			}
		}
		for (int j = 0; j < aliens.size(); j++) {
			if (aliens.get(j).isAlive == false) {
				aliens.remove(j);
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