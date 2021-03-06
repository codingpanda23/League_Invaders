package Game;

import java.awt.Color;
import java.awt.Graphics;

public class Ghost extends GameObject {
	static final int UP = 0;
	static final int DOWN = 1;
	static final int LEFT = 2;
	static final int RIGHT = 3;

	int speed;
	boolean isMoving;
	int direction;

	Ghost(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.width+=100;
		this.height+=100;
		speed = 10;
		isMoving = false;
		direction = 0;

	}

	public void update() {
		collisionBox.setBounds(x+45, y+35, width-90, height-90);
		
		if (isMoving) {

			if (direction == UP) {
				y -= speed;
			} else if (direction == DOWN) {
				y += speed;
			} else if (direction == LEFT) {
				x -= speed;
			} else if (direction == RIGHT) {
				x += speed;
			}
			if (x > CandyGuard.width - 60) {
				x = CandyGuard.width - 60;
			} else if (x < -100) {
				x = -100;
			}
			if (y < 0) {
				y = 0;
			} else if (y > CandyGuard.height - 60) {
				y = CandyGuard.height - 60;
			}
		}

	}

	public void draw(Graphics g) {
		g.drawImage(GamePanel.pandaImg, x, y, width, height, null);
		//super.draw(g);
	}

}
