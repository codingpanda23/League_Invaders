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
	//Long gameTimer;

	public static BufferedImage candyImg;
	public static BufferedImage pandaImg;
	public static BufferedImage bulletImg;
	public static BufferedImage spookyImg;
	public static BufferedImage candypileImg;
	public static BufferedImage goodcandyImg;
	public static BufferedImage heartImg;

	final static int MENU_STATE = 0;
	final static int GAME_STATE = 1;
	final static int END_STATE = 2;
	static int currentState = MENU_STATE;

	GamePanel() {
		timer = new Timer(1000 / 60, this);
		titleFont = new Font("Comic Sans MS", Font.PLAIN, 60);
		font = new Font("TimesRoman", Font.PLAIN, 48);
		scorefont = new Font("Courier", Font.PLAIN, 30);
		instruction = new Font("Courier", Font.PLAIN, 22);
		done = new Font("Courier", Font.PLAIN, 25);
		rocket = new Ghost(180, 650, 50, 50);
		manage = new ObjectManager(rocket);
		//gameTimer = System.currentTimeMillis();

		try {
			candyImg = ImageIO.read(this.getClass().getResourceAsStream("candy.png"));
			pandaImg = ImageIO.read(this.getClass().getResourceAsStream("panda.png"));
			bulletImg = ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));
			spookyImg = ImageIO.read(this.getClass().getResourceAsStream("spooky.png"));
			candypileImg = ImageIO.read(this.getClass().getResourceAsStream("candypile.png"));
			goodcandyImg = ImageIO.read(this.getClass().getResourceAsStream("goodcandy.jpg"));
			heartImg = ImageIO.read(this.getClass().getResourceAsStream("heart.png"));
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
		g.drawImage(GamePanel.pandaImg, 70, 70, 170, 170, null);
		g.drawImage(GamePanel.candypileImg, 100, 80, 300, 200, null);
		g.drawImage(GamePanel.candyImg, 380, 365, 50, 50, null);
		g.drawImage(GamePanel.goodcandyImg, 400, 420, 50, 50, null);

		g.setColor(Color.ORANGE);
		g.setFont(titleFont);
		g.drawString("Candy Guard", 70, 70);

		g.setColor(Color.WHITE);
		g.setFont(instruction);
		g.drawString("Move in all directions with the", 10, 300);
		g.drawString("arrow keys. Use the space bar", 10, 350);
		g.drawString("to destroy evil candies -->", 10, 400);
		g.drawString("and touch the good candies -->", 10, 450);
		g.drawString("to collect them to earn points.", 10, 500);
		g.drawString("If 3 evil candies reach the pile,", 10, 550);
		g.drawString("you shoot a good candy, or you", 10, 600);
		g.drawString("touch an evil candy, you lose a", 10, 650);
		g.drawString("life. Aim well!", 5, 700);

		g.setFont(done);
		g.setColor(Color.BLACK);
		g.fillRect(80, 725, 345, 40);
		g.setColor(Color.YELLOW);
		g.drawString("Press ENTER To Start", 100, 750);
	}

		
	public void drawGameState(Graphics g) {
		g.drawImage(GamePanel.spookyImg, -5, -5, 550, 810, null);
		manage.draw(g);
		g.setColor(Color.BLACK);
		g.setFont(scorefont);
		//Long time = System.currentTimeMillis()-gameTimer;
		//g.drawString("Time Left:" + (60000-time)/1000, 10, 40);
		g.drawString("Lives Left:" + manage.lives(), 10, 40);
		g.drawString("Score:" + manage.getScore(), 300, 40);
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
		g.drawString("Nice Try!", 180, 400);
		g.drawString("Do better next time!", 90, 450);
		g.drawString("You scored " + manage.getScore() + " points!", 90, 550);
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
				//gameTimer = System.currentTimeMillis();
				
				currentState = MENU_STATE;
			}
			if (manage.lives == 0) {
				currentState = END_STATE;
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
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					rocket.isMoving = true;
					rocket.direction = Ghost.LEFT;
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					rocket.isMoving = true;
					rocket.direction = Ghost.UP;
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
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
