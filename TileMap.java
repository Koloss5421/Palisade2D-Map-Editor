package com.jphardin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class TileMap {
	// will hold the tilemap of the current level selected in constructor
	int[][] tileMap;
	TileType tileSet;
	int mainTileSize;
	
	public TileMap(String mapName, TileType tileType, int tileSize) {
		tileSet = tileType;
		mainTileSize = tileSize;

		// In Case map file doesn't exist - crash with error.
		try {
		Scanner getFile = new Scanner(getClass().getResourceAsStream("/resources/" + mapName + ".map"));
		
		// both will be used to initialize the tileMap array
		int countY = 0;
		int countX = 0;
		// count lines for tileMap Y Max and count max number of x tiles (I know some of this will end up being done twice BUT)
		// I wanted the level#.map files to be dynamic in size;
		while(getFile.hasNextLine()) {
			String line = getFile.nextLine();
			String[] xCount = line.split(",");
			if (xCount.length > countX) {
				countX = xCount.length;
			}
			countY++;
		}
		//getFile.close();
		getFile = new Scanner(new FileInputStream("resources/" + mapName + ".map"));
		//Set tileMap size to countY and countX
		tileMap = new int[countY][countX];
			for(int y = 0; y < countY; y++) {
				
				String line = getFile.nextLine();
				String[] tiles = line.split(",");
				int[] intArray = new int[tiles.length];
				for (int i = 0; i < tiles.length; i++) {
					intArray[i] = Integer.parseInt(tiles[i]);
				}
				tileMap[y] = Arrays.copyOf(intArray, intArray.length);

			}
		
		//getFile.close();
		} catch(Exception e) {
			new Exception(e);
		}
	}
	public int getTile(int x, int y) {
		return this.tileMap[y][x];
	}
	
	public int[][] getTileMapArray() {
		return this.tileMap;
	}
	public void drawMap(Graphics2D g2, int tileSize, int yOffset, int xOffset) {
		for(int y = 0; y < tileMap.length; y++) {
			for(int x = 0; x < tileMap[y].length; x++) {
				
				g2.drawImage(tileSet.getTextureOf(tileMap[y][x]), (x - xOffset) * tileSize, (y - yOffset) * tileSize, tileSize, tileSize, null);
				// DRAW TILEMAT BOUNDING BOX
				drawBoundingBox(g2, tileSize, yOffset, xOffset, x, y);
				if (Main.ui.getShowHitBoxes()) {
					drawCollision(g2, x, y, tileSize, xOffset, yOffset);
				}
			}
		}
		
		
	}
	private void drawBoundingBox(Graphics2D g2, int tileSize, int yOffset, int xOffset, int x, int y) {
		g2.setColor(Color.GREEN);
		g2.setStroke(new BasicStroke(2));
		g2.drawLine((0 - xOffset) * tileSize, (0 - yOffset) * tileSize, (0 - xOffset) * tileSize, (tileMap.length - yOffset) * tileSize);
		g2.drawLine((0 - xOffset) * tileSize, (0 - yOffset) * tileSize, (tileMap[y].length - xOffset) * tileSize, (0 - yOffset) * tileSize);
		g2.drawLine((tileMap[y].length - xOffset) * tileSize, (0 - yOffset) * tileSize, (tileMap[y].length - xOffset) * tileSize, (tileMap.length - yOffset) * tileSize);
		g2.drawLine((tileMap[y].length - xOffset) * tileSize, (tileMap.length - yOffset) * tileSize, (0 - xOffset) * tileSize, (tileMap.length - yOffset) * tileSize);
	}
	private void drawCollision(Graphics2D g2, int x, int y, int tileSize, int xOffset, int yOffset) {
		if (tileSet.getTile(tileMap[y][x]).collides() && Main.ui.getShowHitBoxes()) {
			g2.setColor(Color.red);
			g2.setStroke(new BasicStroke(1));
			g2.drawPolygon(tileSet.getHitBoxOf(tileMap[y][x], (x - xOffset), (y - yOffset), tileSize));
		}
	}
	public void setTile(int x, int y, int id) {
		tileMap[y][x] = id;
	}
	
	public void hasTile(Point mouseLoc, int xOffset, int yOffset) {
		
		for (int y = 0; y < tileMap.length; y++) {
			for (int x = 0; x < tileMap[y].length; x++) {
				Polygon currentTile = tileSet.getHitBoxOf(tileMap[y][x], (x - xOffset), (y - yOffset), mainTileSize);
				
				if (currentTile.contains(mouseLoc)) {
					tileMap[y][x] = Main.getSelector();
				}
			}
		}

	}
	public void hasTile(Point mouseLoc, int xOffset, int yOffset, int newID) {
		
		for (int y = 0; y < tileMap.length; y++) {
			for (int x = 0; x < tileMap[y].length; x++) {
				Polygon currentTile = tileSet.getHitBoxOf(tileMap[y][x], (x - xOffset), (y - yOffset), mainTileSize);
				
				if (currentTile.contains(mouseLoc)) {
					tileMap[y][x] = newID;
				}
			}
		}

	}
	public void writeMap(int level) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("resources/level" + level + ".map"));
			
			for (int y = 0; y < tileMap.length; y++) {
				String currentLine = "";
				for (int x = 0; x < tileMap[y].length; x++) {
					if (x != tileMap[y].length) {
						currentLine = currentLine + tileMap[y][x] + ",";
					}
					else
					{
						currentLine = currentLine + tileMap[y][x];
					}
					
				}
				writer.write(currentLine);
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("**Level " + level + " saved!");
		
	}


}
