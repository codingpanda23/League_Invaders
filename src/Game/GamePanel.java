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
	
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	
	GamePanel(){
		timer = new Timer(1000/60, this);
		
	}
	
	public void startGame(){
		timer.start();
	}

///////////////////////////////////////////////////////////
	
	public void updateMenuState(){
		
	}
	public void updateGameState(){
		
	}
	public void updateEndState(){
		
	}
	
	public void drawMenuState(Graphics g){
		
	}
	public void drawGameState(Graphics g){
		
	}
	public void drawEndState(Graphics g){
	
}
	
///////////////////////////////////////////////////////////	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
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
		 
	}
}
