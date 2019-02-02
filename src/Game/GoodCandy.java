package Game;

import java.awt.Graphics;

public class GoodCandy extends GameObject {
	GoodCandy(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	public void update() {
		super.update();
		y++;
	}

	public void draw(Graphics g) {
		super.draw(g);
		g.drawImage(GamePanel.goodcandyImg, x, y, width, height, null);
	}
}
