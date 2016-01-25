//********************************************************************
//  Asteroid.java
//  For assignment three, the one due Tuesday night.
//  Matthew Parin
//  SI 543
//  27 October 2015
//********************************************************************

import java.awt.*;
import java.util.Random;

public class Asteroid {

	Random r = new Random();
	private int xPos, yPos;
	private final int HEIGHT = r.nextInt(10)+15;
	private final int WIDTH = HEIGHT;
	private Color clr = Color.GRAY;
	private int deltaX = r.nextInt(16);
	private int deltaY = r.nextInt(16);
	private final int DIM = 590;
	Polygon asteroidBody ;
	

	//-----------------------------------------------------------------
	//  Methods
	//-----------------------------------------------------------------

	public int getDeltaX() {
		return deltaX;
	}

	public int getDeltaY() {
		return deltaY;
	}

	public Asteroid(){
		Random r = new Random();
		xPos = r.nextInt(DIM);
		yPos = r.nextInt(DIM);
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		int[] xAsteroid = {xPos, xPos, xPos+WIDTH, xPos+WIDTH};
		int[] yAsteroid = {yPos, yPos+HEIGHT, yPos+HEIGHT, yPos};
		int numPoints = 4;
		asteroidBody = new Polygon(xAsteroid, yAsteroid, numPoints);
		g.drawPolygon(asteroidBody);
		
		g.setColor(clr);
		g.fillOval(xPos, yPos, WIDTH, HEIGHT);
		g.setColor(Color.RED);
		g.drawOval(xPos, yPos, WIDTH, HEIGHT);

	}
	public void move(int x, int y){
		xPos =x;
		yPos =y;
	}
	
	public int getX(){
		return xPos;
	}

	public int getY(){
		return yPos;
	}
	
	public Polygon getAstBody(){
		return asteroidBody;
	}
}
