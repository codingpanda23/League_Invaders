package Game;

import java.awt.Graphics;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	Boolean isAlive;
	
	GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		isAlive = new Boolean(true);
	}

	public void update() {

	}

	public void draw(Graphics g) {

	}
}
