package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	boolean isAlive;
	Rectangle collisionBox;

	GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		isAlive = true;
		collisionBox = new Rectangle(x, y, width, height);
	}

	public void update() {
		collisionBox.setBounds(x, y, width, height);
	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(collisionBox.x+50, collisionBox.y+30, collisionBox.width, collisionBox.height);
	}
}
