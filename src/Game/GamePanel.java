package Game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

///////////////////////////////////////////////////////////

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	Timer timer;
	GameObject gameObject;
	
	GamePanel(){
		timer = new Timer(1000/60, this);
		gameObject = new GameObject(1, 1, 1, 1);
	}
	
	public void startGame(){
		timer.start();
	}

///////////////////////////////////////////////////////////
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
		gameObject.update();
	}
	@Override
	public void keyPressed(KeyEvent a) {
		// TODO Auto-generated method stub
		System.out.println("Hi");
	}
	@Override
	public void keyReleased(KeyEvent a) {
		// TODO Auto-generated method stub
		System.out.println("hello");
	}
	@Override
	public void keyTyped(KeyEvent a) {
		// TODO Auto-generated method stub
		System.out.println("smiles! :D");
	}
	@Override
	public void paintComponent(Graphics g){
		 gameObject.draw(g);
	}
}
