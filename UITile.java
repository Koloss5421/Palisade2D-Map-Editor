package com.jphardin;

import java.awt.Polygon;
import java.awt.image.BufferedImage;

public class UITile {
	
	private int id;
	private String name;
	private boolean button;
	private BufferedImage texture;
	private Polygon hitBox = null;
	private int x;
	private int y;
	private int width;
	
	public UITile(int id, String name, boolean button, BufferedImage texture) {
		this.id = id;
		this.name = name;
		this.button = button;
		this.texture = texture;
		
	}
	public BufferedImage getTexture() {
		return this.texture;
	}
	public int getID() {
		return this.id;
	}
	public Polygon getHitbox() {
		return this.hitBox;
	}
	public void setHitBox(Polygon poly) {
		this.hitBox = poly;
	}
	public boolean button() {
		return this.button;
	}
	public String getName() {
		return this.name;
	}
	public void setX(int x2) {
		x = x2;
	}
	public void setY(int y2) {
		y = y2;
	}
	public void setWidth(int width2) {
		width = width2;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int getWidth() {
		return this.width;
	}

}
