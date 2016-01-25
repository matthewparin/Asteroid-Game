//************************************************************************ 
//Class Name: Star 
//
//Author: Colleen van Lent
//
//Collaborators and/or supporting material: None
//
//Description of the class: This class creates and draws a 
//Star object.  It requires a Graphics parameter to draw.
//
//
//************************************************************************* 

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Star {
	private int xPos;
	private int yPos;
	private Color starColor;
	private final int DIM = 600;
	
	public Star(){
		Random r = new Random();
		xPos = r.nextInt(DIM);
		yPos = r.nextInt(DIM);
		starColor = Color.yellow;
	}
	
	public void drawStar (Graphics page) {
		int[] xArr1 = {xPos, xPos+5, xPos+10};
		int[] yArr1 = {yPos + 10, yPos, yPos+10};
		int[] xArr2 = {xPos, xPos+10, xPos+5};
		int[] yArr2 = {yPos +5, yPos+5, yPos+10};
		
		page.setColor(starColor);
		page.fillPolygon(xArr1, yArr1, 3);
		page.fillPolygon(xArr2, yArr2, 3);	
	}
	
	public void setColor(Color c) {
		starColor = c;
	}
}