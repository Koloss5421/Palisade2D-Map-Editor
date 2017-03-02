package com.jphardin;

import java.awt.Polygon;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class TileType {
	int tileSize = 32;
	int tileSetSize = 512;
	public Tile[] tiles = new Tile[256];
	BufferedImage img = null;
	
	public TileType() {
		try {
		img = ImageIO.read(getClass().getResource("/resources/tiles0000.png"));
		} catch(Exception e) {
			new Exception(e);
		}
		

		for (int i = 0; i < tiles.length; i++) {

			switch(i) {
			case 0:
				this.tiles[i] = new Tile(i, "Air", false, false, getImg(i));
				break;
			case 1:
				this.tiles[i] = new Tile(i, "Brick", true, false, getImg(i));
				break;
			case 2:
				this.tiles[i] = new Tile(i, "Purple", true, false, getImg(i));
				break;
			case 255:
				this.tiles[i] = new Tile(i, "NULL", true, false, getImg(i));
			}
			
		}
	}

	private BufferedImage getImg(int id) {

		BufferedImage subImage = null;

		int x = id * tileSize;
		int y = 0;
		while (x > tileSetSize) {
			y = y + tileSize;
			x = x - tileSetSize;
		} 
		if (id == 0) {
			subImage = img.getSubimage(0, 0, tileSize, tileSize);
			return subImage;
		}
		subImage = img.getSubimage(x, y, tileSize, tileSize);
		return subImage;
	}
	public BufferedImage getTextureOf(int id) {

		return tiles[id].getTexture();
	}
	public Tile getTile(int id) {
		return tiles[id];
	}
	public Polygon getHitBoxOf(int id, int x, int y, int tileSize) {
		if(tiles[id].getHitbox() == null) {
			x = x * tileSize;
			y = y * tileSize;
			int[] xPoints = {x, x + tileSize, x + tileSize, x};
			int[] yPoints = {y, y, y + tileSize, y + tileSize}; 
			Polygon hitbox = new Polygon(xPoints, yPoints, 4);
			return hitbox;
		}
		else {
			return tiles[id].getHitbox();
		}
	}
	public String getTileName(int id) {
		return tiles[id].getName();
	}
}