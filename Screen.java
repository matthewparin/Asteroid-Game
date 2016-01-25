//********************************************************************
//  Screen.java
//  Assignment Four.
//  Matthew Parin
//  SI 543
//  XX November 2015
//
//  Expected Score: 
//
//  Resources: 
//
//  Notes for grading: 
//	
//********************************************************************

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Screen extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{

	private SpaceShip ship;
	private Star[] stars = new Star[100];
	private ArrayList<Asteroid> ast = new ArrayList<Asteroid>();
	private int NUM_ASTEROIDS = 15;
	private Timer asteroidTimer;
	private Timer gameTimer;
	private final int ASTEROID_DELAY = 200;
	private final int GAME_DELAY = 1000;
	private int countdown = 15;
	private int score = 0;
	private int lives = 3;
	private final int WIDTH = 600;
	private final int HEIGHT = 600;
	private int flashCrash = 0;
	private int flashHit = 0;
	private Color backgroundColor = Color.BLACK;

	//-----------------------------------------------------------------
	//  Constructor: Sets up the panel, including the timer for the animation.
	//-----------------------------------------------------------------
	public Screen()
	{

		for (int i = 0; i < stars.length;i++){ // this is the loop for the stars
			stars[i] = new Star(); // this is how you add stars to the array
		}

		for (int i = 0; i < NUM_ASTEROIDS;i++){ // this is the loop for the asteroids
			Asteroid temp = new Asteroid(); // this creates the variable for ast
			ast.add(temp); // this adds the asteroids to the array
		}
		
		ship = new SpaceShip();
		gameTimer = new Timer(GAME_DELAY, this);
		gameTimer.start();
		asteroidTimer = new Timer(ASTEROID_DELAY, this);
		asteroidTimer.start();
		addMouseListener(this);
		addMouseMotionListener(this);
		setPreferredSize (new Dimension(getWidth(), getHeight()));
		setBackground (backgroundColor);
	}

	//-----------------------------------------------------------------
	//  Draws the image in the current location.
	//-----------------------------------------------------------------
	public void paintComponent (Graphics page)
	{
		super.paintComponent (page);

		for (int i = 0; i < stars.length;i++) { // draws all the stars
			stars[i].drawStar(page);
		}

		for (int i = 0; i < NUM_ASTEROIDS;i++) { // draws all the asteroids
			ast.get(i).draw(page);
		}

		ship.draw(page,getWidth()); // draws the spaceship on the panel

		page.setColor(Color.WHITE);
		Font font = new Font("Serif", Font.PLAIN, 15);
		page.setFont(font);
		page.drawString(String.valueOf("Time Left\u003A 00\u003A" + countdown), 15, 20);
		page.drawString(String.valueOf("Score\u003A " + score), 15, 40);
		page.drawString(String.valueOf("Lives Left\u003A " + lives), 15, 60);

		if (flashCrash > 0) {
			Font crashFont = new Font("Serif", Font.PLAIN, 80);
			page.setFont(crashFont);
			page.drawString(String.valueOf("Crash!"), 15, 120);
			page.setFont(font);
			flashCrash--;
		}
		
		if (flashHit > 0) {
			Font hitFont = new Font("Serif", Font.PLAIN, 80);
			page.setFont(hitFont);
			page.drawString(String.valueOf("Hit!"), 15, 120);
			page.setFont(font);
			flashHit--;
		}
		
		if (lives == 2) {
			ship.setColor(Color.YELLOW);
		}
		
		if (lives == 1) {
			ship.setColor(Color.RED);
		}

		if (lives == 0 || lives == -1 || lives == -2){
			gameTimer.stop();
			asteroidTimer.stop();
			ship.setColor(Color.BLACK);
			page.drawString(String.valueOf("Game Over"), 250, 290);
		}

		if (lives < -2) {
			for (int i = 0; i < stars.length;i++) {
				stars[i].setColor(Color.RED);
			}

			Font sravanFont = new Font("Veranda", Font.BOLD, 40);
			page.setFont(sravanFont);
			page.drawString(String.valueOf("Stop playing, friend!"), 100, 300);
			page.setFont(font);
		}

		if (countdown == 0){
			gameTimer.stop();
			asteroidTimer.stop();
			page.drawString(String.valueOf("Game Over"), 250, 290);
		}

		if (lives > 0) {
			if (NUM_ASTEROIDS == 0) {
				gameTimer.stop();
				asteroidTimer.stop();
				Font winFont = new Font("Serif", Font.PLAIN, 40);
				page.setFont(winFont);
				page.drawString(String.valueOf("Congratulations. You Win!!!"), 70, 300);
				page.setFont(font);
			}
		}
	}

	//*****************************************************************
	//  Represents the action listener for the timer.
	//*****************************************************************
	public void actionPerformed (ActionEvent event)
	{
		if (event.getSource().equals(asteroidTimer)){
			Random r = new Random();
			for (Asteroid s: ast) {
				int x = s.getX() + r.nextInt(31) - 15; 
				int y = s.getY() + r.nextInt(31) - 15;
				s.move(x, y);
			} 
			repaint();
		}
		else if (event.getSource().equals(gameTimer)) {
			System.out.println("Update Timer");
			countdown--;
			if (countdown == 0) {
				gameTimer.stop();
			} 
			repaint();
		} 
	}

	public static void main (String[] args) {
		JFrame frame = new JFrame ("Listeners and Arrays");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		Screen panel = new Screen();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(true);
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ship.setShooting(true);
		asteroidCheck();
	}

	// **************************************************
	// Create an asteroidCheck() function that can be
	// referenced by the mouseListener components.
	// **************************************************
	private void asteroidCheck() { 
		int[] laserPosition = ship.getLaserPosition();
		int yPos = laserPosition[1];
		boolean explosion = false;
		Asteroid explodeAsteroid = null;

		for (Asteroid s: ast) {
			for(int xPos=laserPosition[0]; xPos<WIDTH; xPos++) {
				if(s.getAstBody().contains(xPos, yPos)) {
					explosion = true;
					explodeAsteroid = s;
					break;
				}
			}
			if(explosion)
				break;	
		}
		if(explosion) {
			ast.remove(explodeAsteroid);
			score++;
			NUM_ASTEROIDS--;
			flashHit = 10;
		}		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ship.setShooting(false);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		ship.move(e.getX() - 20, e.getY() - 15);
		ship.setShooting(true);
		repaint();
		boolean collision = false;
		Asteroid collisionAsteroid = null;
		for (Asteroid s: ast) {
			int[] xAsteroid = s.getAstBody().xpoints;
			int[] yAsteroid = s.getAstBody().ypoints;
			for(int i=0; i<2; i++)
				for(int j=0; j<2; j++)
					if (ship.getShipBody().contains(xAsteroid[i], yAsteroid[j])) {
						System.out.println("Crash");
						collision = true;
						collisionAsteroid = s;
					}
		}
		if(collision) {
			ast.remove(collisionAsteroid);
			NUM_ASTEROIDS--;
			lives--;
			flashCrash = 10;
		}
		asteroidCheck();
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		ship.move(e.getX() - 20, e.getY() - 15);
		repaint();
		boolean collision = false;
		Asteroid collisionAsteroid = null;
		for (Asteroid s: ast) {
			int[] xAsteroid = s.getAstBody().xpoints;
			int[] yAsteroid = s.getAstBody().ypoints;
			for(int i=0; i<2; i++)
				for(int j=0; j<2; j++)
					if (ship.getShipBody().contains(xAsteroid[i], yAsteroid[j])) {
						System.out.println("Crash");
						collision = true;
						collisionAsteroid = s;
					}
		}

		if(collision) {
			ast.remove(collisionAsteroid);
			NUM_ASTEROIDS--;
			lives--;
			flashCrash = 10;
		}
		repaint();	
	}
}
