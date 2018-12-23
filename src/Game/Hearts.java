package Game;

import java.awt.Graphics;

public class Hearts extends GameObject {
	Hearts(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	public void update() {
		super.update();
		y++;
	}

	public void draw(Graphics g) {
		g.drawImage(GamePanel.heartImg, x, y, width, height, null);
	}
}
