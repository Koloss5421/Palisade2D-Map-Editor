package com.jphardin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

public class UI {
	int windowWidth = Main.windowWidth;
	int windowHeight = Main.windowHeight;
	int tileSize = Main.tileSize;
	UITileSet uiTileSet = Main.uiTileSet;
	static boolean drawGrid = false;
	static boolean fileManOpen = false;
	boolean showHitBoxes = false;
	int fileButtonState = 0;
	int helpButtonState = 2;
	int newButtonState = 4;
	int buttonCount = 0;
	int[] buttons;
	

	
	
	public void drawHud(Graphics2D g2) {
		
		if(drawGrid) {
			drawGrid(g2);
		}
		
		drawFileButton(g2);// Draw 'File' Button
		drawHelpButton(g2);
		if(fileManOpen) {
			drawNewButton(g2);
			defineButtons();
		}
		defineButtons();
		drawSelector(g2);// Draw Selector

	}
	// DRAWING FUNCTIONS
	private void drawFileButton(Graphics2D g2) {
		int x = 5;
		int y = 5;
		int width = (int)(tileSize * 1.5); 
		defineButtonHitbox(x,y,width, 0, 1);
		g2.drawImage(uiTileSet.getTextureOf(fileButtonState), x, y, width, width, null); // draw the button
		buttonCount += 2;
	}
	private void drawHelpButton(Graphics2D g2) {
		int x = 690;
		int y = 160;
		int width = (int)(tileSize * 1.5); 
		defineButtonHitbox(x,y,width, 2, 3);
		g2.drawImage(uiTileSet.getTextureOf(helpButtonState), x, y, width, width, null); // draw the button
		buttonCount += 2;
	}
	private void drawNewButton(Graphics2D g2) {
		int x = 400;
		int y = 400;
		int width = (int)(tileSize * 1.5); 
		defineButtonHitbox(x,y,width, 2, 3);
		g2.drawImage(uiTileSet.getTextureOf(newButtonState), x, y, width, width, null); // draw the button
		buttonCount += 2;
	}
	private void defineButtons() {
		buttons = new int[buttonCount];
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = i;
		}
		buttonCount = 0;
	}
	private void drawSelector(Graphics2D g2) {
		TileType tileSet = Main.tileSet;
		int selector = Main.selector;
		int width = 120;
		int fontHeight = 20;
		int selectX = windowWidth - (width + 15);
		int selectY = 0 + 10;
		int offset = 3;
		
		g2.setColor(Color.DARK_GRAY);
		g2.fillRect(selectX, selectY, width, width + fontHeight + 5); // Draws Background Rectangle
		// Draws Currently selected texture for preview
		g2.drawImage(tileSet.getTextureOf(selector), selectX + offset, selectY + offset, width - (offset * 2), width - (offset * 2), null);
		// Draws currently selected Name and ID
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Consolas", Font.PLAIN, fontHeight));
		String tileName = selector + ":" + tileSet.getTileName(selector);
		int nameWidth = g2.getFontMetrics().stringWidth(tileName);
		g2.drawString(tileName, (selectX + width / 2) - (nameWidth / 2), selectY + width + fontHeight);

	}
	private void drawGrid(Graphics2D g2) {
		int maxX = windowWidth / tileSize;
		int maxY = windowHeight / tileSize;
		if (Main.mapLoaded) {
			for (int y = 0; y < maxY; y++) {
				for (int x = 0; x < maxX; x++) {
					g2.setColor(Color.BLUE);
					g2.setStroke(new BasicStroke(2));
					g2.drawLine(x, y * tileSize, windowWidth, y * tileSize);
					g2.drawLine(x * tileSize, y , x * tileSize, windowHeight);
				}
			}
		}
	}
	public void defineButtonHitbox(int x, int y, int width, int tileUp, int tileDown) {
		uiTileSet.setTileX(tileUp, x); // set the X
		uiTileSet.setTileY(tileUp, y); // set the Y
		uiTileSet.setTileX(tileDown, x); // set the X
		uiTileSet.setTileY(tileDown, y); // set the Y
		int[] xPoly = { x, x + width , x + width, x}; 
		int[] yPoly = { y, y, y + width - 32, y + width - 32};
		Polygon poly = new Polygon(xPoly, yPoly, 4);
		uiTileSet.setHitBox(tileUp, poly);
		uiTileSet.setHitBox(tileDown, poly);
		//buttons[tileUp] = tileUp;
		//buttons[tileDown] = tileDown;
	}
	public boolean getDrawGrid() {
		return drawGrid;
	}
	public void setDrawGrid(boolean bool) {
		drawGrid = bool;
	}
	public void setFileManOpen(boolean bool) {
		fileManOpen = bool;
	}
	public boolean getShowHitBoxes() {
		return showHitBoxes;
	}
	public void setShowHitBoxes(boolean bool) {
		this.showHitBoxes = bool;
	}
	public void setButtonState(int button, int change) {
		if(button == fileButtonState) {
			fileButtonState += change;
		}
		if(button == helpButtonState) {
			helpButtonState += change;
		}
	}
	public int checkButtonHover(Point point) {
		if(buttons != null) {
			//System.out.println("BUTTONS LENGTH=" + buttons.length);
			for(int i = 0; i < buttons.length; i++) {
					Polygon hitBox = uiTileSet.getHitBox(i);
					if (hitBox.contains(point)) {
						return i;
					}
				
			}
		}
		else 
		{
			defineButtons();
		}
		return -1;
		/*
		Polygon hitBox = uiTileSet.getHitBox(fileButtonState);
		if( hitBox.contains(point) ) {
			return true;
		}
		else {
			return false;
		}*/
	}
}
