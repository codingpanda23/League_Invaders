package Game;

import java.awt.Color;
import java.awt.Graphics;

public class BadCandy extends GameObject{
	BadCandy(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		collisionBox.setBounds(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}
	public void update() {
		super.update();
		y++;
	}
	public void draw(Graphics g) {
		//super.draw(g);
		g.drawImage(GamePanel.candyImg, x, y, width, height, null);
	}
}