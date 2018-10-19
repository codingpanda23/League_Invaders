package Game;

import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject{
	int speed;
	
	
	Projectile(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		speed = 10;
		
	}
	public void update() {
		super.update();
		y-=speed;
		if (y < 0) {
			isAlive = false;
		}
		if (y > 0) {
			isAlive = true;
		}
	}

	public void draw(Graphics g) {
		g.drawImage(GamePanel.bulletImg, x, y, width, height, null);
	}
	
	
}
