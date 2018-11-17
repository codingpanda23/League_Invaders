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
	Rocketship rocket;
	Boolean boo;
	ObjectManager manage;
	
	public static BufferedImage candyImg;
    public static BufferedImage pandaImg;
    public static BufferedImage bulletImg;
    public static BufferedImage spookyImg;
	
	final static int MENU_STATE = 0;
	final static int GAME_STATE = 1;
	final static int END_STATE = 2;
	static int currentState = MENU_STATE;

	GamePanel() {
		timer = new Timer(1000 / 60, this);
		titleFont = new Font("Comic Sans", Font.PLAIN, 48);
		font = new Font("Comic Sans", Font.PLAIN, 48);
		scorefont = new Font("Comic Sans", Font.PLAIN, 30);
		instruction = new Font("Comic Sans", Font.PLAIN, 25);
		done = new Font("Comic Sans", Font.PLAIN, 25);
		rocket = new Rocketship(250, 700, 50, 50);
		boo = new Boolean(true);
		manage = new ObjectManager(rocket);

		try {
            candyImg = ImageIO.read(this.getClass().getResourceAsStream("candy.png"));
            pandaImg = ImageIO.read(this.getClass().getResourceAsStream("panda.png"));
            bulletImg = ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));
            spookyImg = ImageIO.read(this.getClass().getResourceAsStream("spooky.png"));
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
		g.drawString("Candy Guard", 100, 150);
		
		g.setFont(instruction);
		g.drawString("You are the candy guard.", 100, 500);
		g.drawString("Destroy the bad candies", 100, 550);
		g.drawString("before they reach the good", 100, 600);
		g.drawString("candy pile for halloween.", 100, 650);
		g.drawString("Don't let them touch you, though.", 70, 700);
	}

	public void drawGameState(Graphics g) {
		g.drawImage(GamePanel.spookyImg, 0, 0, LeagueInvaders.width, LeagueInvaders.height, null);
		manage.draw(g);
	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString("The evil", 120, 150);
		g.drawString("candies won!", 90, 200);
		
		g.setColor(Color.BLACK);
		g.setFont(scorefont);
		g.drawString("You destroyed "+ manage.getScore() + " bad candies!", 50, 400);
		g.setFont(done);
		g.drawString("Hit ENTER to try again", 125, 450);
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
