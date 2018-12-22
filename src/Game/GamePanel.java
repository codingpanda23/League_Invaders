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
	Font instruction;
	Font done;
	Ghost rocket;
	ObjectManager manage;
	

	public static BufferedImage candyImg;
	public static BufferedImage pandaImg;
	public static BufferedImage bulletImg;
	public static BufferedImage spookyImg;
	public static BufferedImage candypileImg;

	final static int MENU_STATE = 0;
	final static int GAME_STATE = 1;
	final static int END_STATE = 2;
	static int currentState = MENU_STATE;

	GamePanel() {
		timer = new Timer(1000 / 60, this);
		titleFont = new Font("Courier", Font.PLAIN, 60);
		font = new Font("TimesRoman", Font.PLAIN, 48);
		scorefont = new Font("Courier", Font.PLAIN, 30);
		instruction = new Font("Courier", Font.PLAIN, 23);
		done = new Font("Courier", Font.PLAIN, 25);
		rocket = new Ghost(180, 650, 50, 50);
		manage = new ObjectManager(rocket);
		

		try {
			candyImg = ImageIO.read(this.getClass().getResourceAsStream("candy.png"));
			pandaImg = ImageIO.read(this.getClass().getResourceAsStream("panda.png"));
			bulletImg = ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));
			spookyImg = ImageIO.read(this.getClass().getResourceAsStream("spooky.png"));
			candypileImg = ImageIO.read(this.getClass().getResourceAsStream("candypile.png"));
		} catch (IOException e) {
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
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, CandyGuard.width, CandyGuard.height);
		g.drawImage(GamePanel.candypileImg, 100, 170, 300, 200, null);

		g.setColor(Color.ORANGE);
		g.setFont(titleFont);
		g.drawString("Candy Guard", 50, 150);

		g.setFont(instruction);
		g.drawString("You are the halloween candy guard.", 10, 400);
		g.drawString("Destroy as many bad candies as you", 10, 450);
		g.drawString("can. Once 5 bad candies reach the", 10, 500);
		g.drawString("pile, the game ends. Don't let the", 10, 550);
		g.drawString("bad candies touch you. Use the", 40, 600);
		g.drawString("arrow keys to move and space", 50, 650);
		g.drawString("to shoot.", 200, 700);

		g.setColor(Color.YELLOW);
		g.drawString("Press ENTER To Start", 120, 750);
	}

	public void drawGameState(Graphics g) {
		g.drawImage(GamePanel.spookyImg, -5, -5, 550, 810, null);
		manage.draw(g);
	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, CandyGuard.width, CandyGuard.height);
		g.setColor(Color.RED);
		g.setFont(font);
		g.drawString("Game", 190, 150);
		g.drawString("Over", 200, 200);

		g.setColor(Color.RED);
		g.setFont(scorefont);
		g.drawString("You destroyed " + manage.getScore(), 120, 400);
		g.drawString("bad candies!", 150, 450);
		g.setFont(done);
		g.drawString("Hit ENTER to try again", 90, 700);
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
			if (currentState > END_STATE) {
				rocket = new Ghost(180, 650, 50, 50);
				manage = new ObjectManager(rocket);
				timer = new Timer(1000 / 60, this);
				currentState = MENU_STATE;
			}
			
		} 
		else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			manage.addProjectile(new Projectile(rocket.x + 70, rocket.y + 30, 10, 15));
		} else {
			if (!rocket.isMoving) {
				// movement keys
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					rocket.isMoving = true;
					rocket.direction = Ghost.RIGHT;
				} 
				else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					rocket.isMoving = true;
					rocket.direction = Ghost.LEFT;
				} 
				else if (e.getKeyCode() == KeyEvent.VK_UP) {
					rocket.isMoving = true;
					rocket.direction = Ghost.UP;
				} 
				else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					rocket.isMoving = true;
					rocket.direction = Ghost.DOWN;
				}
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent a) {
		// TODO Auto-generated method stub

		rocket.isMoving = false;

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
