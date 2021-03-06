package com.jphardin;

import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UITileSet {
	int tileSize = 32;
	int tileSetSize;
	public UITile[] tiles = new UITile[25];
	static BufferedImage img = null;
	
	public UITileSet() {
		
		try {
			img = ImageIO.read(getClass().getResource("resources/hudTiles.png"));
			tileSetSize = img.getWidth();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < tiles.length; i++) {
			
			switch(i) {
			case 0:
				this.tiles[i] = new UITile(i, "FileUp", true, getImg(i));
				break;
			case 1:
				this.tiles[i] = new UITile(i, "FileDown", true, getImg(i));
				break;
			case 2:
				this.tiles[i] = new UITile(i, "HelpUp", true, getImg(i));
				break;
			case 3:
				this.tiles[i] = new UITile(i, "HelpDown", true, getImg(i));
				break;
			case 4:
				this.tiles[i] = new UITile(i, "NewUp", true, getImg(i));
				break;
			case 5:
				this.tiles[i] = new UITile(i, "NewDown", true, getImg(i));
				break;
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
	public UITile getTile(int id) {
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
	public void setHitBox(int id, Polygon poly) {

		this.tiles[id].setHitBox(poly);
	}
	public void setTileX(int id, int x) {
		this.tiles[id].setX(x);
	}
	public void setTileY(int id, int y) {
		this.tiles[id].setY(y);
	}
	public void setTileWidth(int id, int width) {
		this.tiles[id].setWidth(width);
	}
	public Polygon getHitBox(int id) {

		return this.tiles[id].getHitbox();
	}

}