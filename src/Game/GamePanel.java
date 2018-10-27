package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

///////////////////////////////////////////////////////////

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	Font titleFont;
	Font font;
	Font scorefont;
	Rocketship rocket;
	Boolean boo;
	ObjectManager manage;
	
	public static BufferedImage alienImg;
    public static BufferedImage pandaImg;
    public static BufferedImage bulletImg;
    public static BufferedImage spaceImg;
	
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;

	GamePanel() {
		timer = new Timer(1000 / 60, this);
		titleFont = new Font("Comic Sans", Font.PLAIN, 48);
		font = new Font("Comic Sans", Font.PLAIN, 48);
		scorefont = new Font("Comic Sans", Font.PLAIN, 30);
		rocket = new Rocketship(250, 700, 50, 50);
		boo = new Boolean(true);
		manage = new ObjectManager(rocket);

		try {
            alienImg = ImageIO.read(this.getClass().getResourceAsStream("alien.png"));
            pandaImg = ImageIO.read(this.getClass().getResourceAsStream("panda.png"));
            bulletImg = ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));
            spaceImg = ImageIO.read(this.getClass().getResourceAsStream("space.png"));
		} 	
		catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
    		}
	}

	public void startGame() {
		timer.start();
	}

	///////////////////////////////////////////////////////////


	public void updateMenuState() {

	}

	public void updateGameState() {
		manage.update();
		manage.manageEnemies();
		manage.purgeObjects();
		manage.checkCollision();
		if (!rocket.isAlive) {
			currentState = END_STATE;
		}
	}

	public void updateEndState() {

	}

	public void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		g.setColor(Color.YELLOW);
		g.setFont(titleFont);
		g.drawString("League Invaders", 65, 150);
	}

	public void drawGameState(Graphics g) {
		g.drawImage(GamePanel.spaceImg, 0, 0, LeagueInvaders.width, LeagueInvaders.height, null);
		manage.draw(g);
	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString("Game Over", 120, 150);
		
		g.setColor(Color.BLACK);
		g.setFont(scorefont);
		g.drawString("You scored "+ manage.getScore() + " points!", 100, 400);
	}

	///////////////////////////////////////////////////////////

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			currentState++;
			if (currentState >= END_STATE) {
				rocket = new Rocketship(250, 700, 50, 50);
				manage = new ObjectManager(rocket);
			}
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rocket.x+=rocket.speed;
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			rocket.x-=rocket.speed;
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			rocket.y-=rocket.speed;
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			rocket.y+=rocket.speed;
		}
		else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			manage.addProjectile(new Projectile(rocket.x+70, rocket.y+ 30, 10, 15));
		}
		if (currentState > END_STATE) {
			currentState = MENU_STATE;
		}
	}

	@Override
	public void keyReleased(KeyEvent a) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent a) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
			drawEndState(g);
		}
	}
}
