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
	Long gameTimer;

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
	final static int END_STATE2 = 3;
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
		gameTimer = System.currentTimeMillis();

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
	
	public void updateEndState2(){
		
	}

	public void drawMenuState(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, CandyGuard.width, CandyGuard.height);
		g.drawImage(GamePanel.candypileImg, 100, 80, 300, 200, null);
		g.drawImage(GamePanel.candyImg, 440, 420, 50, 50, null);

		g.setColor(Color.ORANGE);
		g.setFont(titleFont);
		g.drawString("Candy Guard", 70, 70);

		g.setFont(instruction);
		g.drawString("You are the halloween candy guard.", 10, 300);
		g.drawString("Collect as many pieces of candy as", 10, 350);
		g.drawString("you can by touching them. Destroy", 10, 400);
		g.drawString("evil candies by shooting them", 10, 450);
		g.drawString("with the space key. If one reaches", 10, 500);
		g.drawString("the pile or touches you, you lose", 10, 550);
		g.drawString("a life. If you shoot a good candy,", 10, 600);
		g.drawString("you also lose a life. Move with", 10, 650);
		g.drawString("the arrow keys before time is up.", 5, 700);

		g.setFont(done);
		g.setColor(Color.YELLOW);
		g.drawString("Press ENTER To Start", 100, 750);
	}

		
	public void drawGameState(Graphics g) {
		g.drawImage(GamePanel.spookyImg, -5, -5, 550, 810, null);
		manage.draw(g);
		Long time = System.currentTimeMillis() - gameTimer;
		g.setColor(Color.BLACK);
		g.setFont(scorefont);
		g.drawString("Time Left:" + (60000-time)/1000, 10, 40);
		g.drawString("Lives Left:" + manage.lives(), 10, 80);
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
		g.drawString("You lost all of ", 120, 400);
		g.drawString("your lives.", 150, 450);
		g.drawString("You scored " + manage.getScore() + " points!", 90, 550);
		g.setFont(done);
		g.drawString("Hit ENTER to try again", 90, 700);
	}
	
	public void drawEndState2(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, CandyGuard.width, CandyGuard.height);
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString("Game", 190, 150);
		g.drawString("Won!", 200, 200);
		g.setFont(scorefont);
		g.drawString("You scored " + manage.getScore() + " points!", 80, 400);
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
		} else if (currentState == END_STATE2){
			updateEndState2();
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
		} else if (currentState == END_STATE2) {
			drawEndState2(g);
		}
	}

}
