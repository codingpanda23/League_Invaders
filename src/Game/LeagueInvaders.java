package Game;

import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame frame;
	final static int width = 500;
	final static int height = 800;
	GamePanel gamePanel;
	
	public LeagueInvaders(){
		frame = new JFrame();
		gamePanel = new GamePanel();
	}

	public static void main(String[] args) {
		LeagueInvaders object = new LeagueInvaders();
		object.setup();
	}

	public void setup() {
		frame.getContentPane().setPreferredSize(new Dimension(width, height));
		frame.pack();
		frame.add(gamePanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePanel.startGame();
		frame.addKeyListener(gamePanel);
	}
}
