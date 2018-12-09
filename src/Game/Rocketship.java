package Game;

import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject {
	int speed;
	
	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		speed = 20;
	}

	public void update() {
		super.update();
	}

	public void draw(Graphics g) {
		g.drawImage(GamePanel.pandaImg, x, y, width+100, height+100, null);

	}

}
