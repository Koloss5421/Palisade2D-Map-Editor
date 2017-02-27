package com.jphardin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

public class UI {
	static int windowWidth = Main.windowWidth;
	static int windowHeight = Main.windowHeight;
	static int tileSize = Main.tileSize;
	static UITileSet uiTileSet = Main.uiTileSet;
	static boolean drawGrid = false;
	static boolean showHitBoxes = false;
	static int fileButtonState = 0;
	
	public static void drawHud(Graphics2D g2) {
		TileType tileSet = Main.tileSet;
		int selector = Main.selector;
		
		int maxX = windowWidth / tileSize;
		int maxY = windowHeight / tileSize;
		
		if(drawGrid) {
			for (int y = 0; y < maxY; y++) {
				for (int x = 0; x < maxX; x++) {
					g2.setColor(Color.BLUE);
					g2.setStroke(new BasicStroke(2));
					g2.drawLine(x, y * tileSize, windowWidth, y * tileSize);
					g2.drawLine(x * tileSize, y , x * tileSize, windowHeight);
				}
			}
		}
		// Draw 'File' Button
		int x = 5;
		int y = 5;
		int width = (int)(tileSize * 1.5); 
		defineFileHitbox(x,y,width);
		g2.drawImage(uiTileSet.getTextureOf(fileButtonState), x, y, width, width, null); // draw the button
		// Draw Selector
		g2.setColor(Color.DARK_GRAY);
		width = 120;
		int fontHeight = 20;
		int selectX = windowWidth - (width + 15);
		int selectY = 0 + 10;
		g2.fillRect(selectX, selectY, width, width + fontHeight + 5);
		
		int offset = 3;
		g2.drawImage(tileSet.getTextureOf(selector), selectX + offset, selectY + offset, width - (offset * 2), width - (offset * 2), null);
		//g2.setColor(Color.RED);
		g2.drawRect(selectX + offset, selectY + offset, width - (offset * 2), width - (offset * 2));
		
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Consolas", Font.PLAIN, fontHeight));
		String tileName = selector + ":" + tileSet.getTileName(selector);
		int nameWidth = g2.getFontMetrics().stringWidth(tileName);
		g2.drawString(tileName, (selectX + width / 2) - (nameWidth / 2), selectY + width + fontHeight);
	}
	public static void defineFileHitbox(int x, int y, int width) {
		uiTileSet.setTileX(0, x); // set the X
		uiTileSet.setTileY(0, y); // set the Y
		uiTileSet.setTileX(1, x); // set the X
		uiTileSet.setTileY(1, y); // set the Y
		int[] xPoly = { x, x + width , x + width, x}; 
		int[] yPoly = { y, y, y + width - 32, y + width - 32};
		Polygon poly = new Polygon(xPoly, yPoly, 4);
		uiTileSet.setHitBox(0, poly);
		uiTileSet.setHitBox(1, poly);
	}
	public static boolean getDrawGrid() {
		return drawGrid;
	}
	public static void setDrawGrid(boolean bool) {
		drawGrid = bool;
	}
	public static boolean getShowHitBoxes() {
		return showHitBoxes;
	}
	public static void setShowHitBoxes(boolean bool) {
		showHitBoxes = bool;
	}
	public static void setFileButtonState(int state) {
		fileButtonState = state;
	}
	public static boolean checkFileHover(Point point) {
		Polygon hitBox = uiTileSet.getHitBox(fileButtonState);
		if( hitBox.contains(point) ) {
			return true;
		}
		else {
			return false;
		}
	}
}
