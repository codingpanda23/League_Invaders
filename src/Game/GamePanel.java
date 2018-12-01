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
    public static BufferedImage candypileImg;
	
	final static int MENU_STATE = 0;
	final static int GAME_STATE = 1;
	final static int END_STATE = 2;
	static int currentState = MENU_STATE;

	GamePanel() {
		timer = new Timer(1000 / 60, this);
		titleFont = new Font("Comic Sans", Font.PLAIN, 60);
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
            candypileImg = ImageIO.read(this.getClass().getResourceAsStream("candypile.png"));
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
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		g.drawImage(GamePanel.candypileImg, 100, 200, 300, 200, null);
		
		g.setColor(Color.ORANGE);
		g.setFont(titleFont);
		g.drawString("Candy Guard", 80, 150);
		
		g.setFont(instruction);
		g.drawString("You are the halloween candy guard.", 40, 450);
		g.drawString("Destroy as many bad candies", 80, 500);
		g.drawString("as you can. Once 5 bad candies reach", 30, 550);
		g.drawString("the pile, the game ends. Don't let the", 30, 600);
		g.drawString("bad candies touch you. Use the arrow", 30, 650);
		g.drawString("keys to move and space to shoot.", 70, 700);
		
		g.setColor(Color.YELLOW);
		g.drawString("Good Luck!", 180, 750);
	}

	public void drawGameState(Graphics g) {
		g.drawImage(GamePanel.spookyImg, -5, -5, 550, 810, null);
		manage.draw(g);
	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		g.setColor(Color.RED);
		g.setFont(font);
		g.drawString("Game", 190, 150);
		g.drawString("Over", 200, 200);
		
		g.setColor(Color.RED);
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
			if (rocket.x>500) {
				rocket.x=500;
			}
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
