package Game;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LeagueInvaders {
	class Game {
		JFrame frame;
	}

	public static void main(String[] args) {

	}

	void setup() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.add(frame);
		frame.setVisible(true);
		frame.setSize(500, 800);

	}
}
