//********************************************************************
//  SpaceShip.java         
//  Matthew Parin
//  SI 543
//  Description of the class: Represents a Spaceship with a particular
//  position, size, and color.
//********************************************************************

import java.awt.*;

public class SpaceShip {

	private int xPos, yPos;
	private boolean shooting;
	private Color clr;
	private final int HEIGHT = 30;
	private final int WIDTH = 40;
	private Polygon shipBody;

	//-----------------------------------------------------------------
	//  Constructors
	//-----------------------------------------------------------------
	public SpaceShip () {
		xPos = (int)(251*Math.random());
		yPos = (int)(251*Math.random());
		shooting = false;
		clr = Color.GREEN;
	}

	public SpaceShip(int x, int y) {
		xPos = x;
		yPos = y;
		shooting = false;
		clr = Color.GREEN;
	}

	//-----------------------------------------------------------------
	//  Draws this Spaceship in the specified graphics context.
	//-----------------------------------------------------------------
	public void draw(Graphics page, int w)
	{	
		//Code to draw and style a Spaceship
		page.setColor(Color.BLACK);
		int[] xShip = {xPos, xPos, xPos+WIDTH, xPos+WIDTH};
		int[] yShip = {yPos, yPos+HEIGHT, yPos+HEIGHT, yPos};
		int numPoints = 4;
		shipBody = new Polygon(xShip, yShip, numPoints);
		page.drawPolygon(shipBody);

		page.setColor(Color.ORANGE);
		page.fillArc(xPos-18, yPos+8, 20, 14, 360, 360);
		page.setColor(Color.DARK_GRAY);
		page.fillRoundRect(xPos-10, yPos+5, 20, 20, 10, 10);
		page.setColor(clr);
		page.fillOval(xPos, yPos, WIDTH, HEIGHT);
		page.setColor(Color.BLACK);
		page.fillRoundRect(xPos+30, yPos+9, 8, 8, 8, 8);
		page.fillRoundRect(xPos+21, yPos+9, 8, 8, 8, 8);
		page.fillRoundRect(xPos+11, yPos+9, 8, 8, 8, 8);
		page.fillRoundRect(xPos+2, yPos+9, 8, 8, 8, 8);
		page.setColor(Color.WHITE);
		page.drawLine(xPos+1, yPos+18, xPos+39, yPos+18);
		page.drawArc(xPos, yPos, WIDTH, HEIGHT, 360, 360);
		if (shooting == true) {
			page.setColor(Color.RED);
			page.drawLine(xPos+40, yPos+16, w, yPos+16);
		}	
	}


	//-----------------------------------------------------------------
	//  Methods
	//-----------------------------------------------------------------
	public void setShooting(boolean input) {
		shooting = input;
	}

	public Polygon getShipBody() {
		return shipBody;
	}

	public void setColor(Color c) {
		clr = c;
	}

	public void move(int x, int y) {
		xPos = x;
		yPos = y;
	}

	public int[] getLaserPosition() {
		return new int[]{xPos+40, yPos+16};
	}
}