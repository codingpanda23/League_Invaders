package Game;

import java.awt.Color;
import java.awt.Graphics;

public class Alien extends GameObject{
	Alien(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}
	public void update() {
		super.update();
		y++;
	}
	public void draw(Graphics g) {
		g.drawImage(GamePanel.candyImg, x, y, width, height, null);
	}
}
