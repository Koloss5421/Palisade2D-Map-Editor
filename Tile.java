package com.jphardin;

import java.awt.Polygon;
import java.awt.image.BufferedImage;

public class Tile {
	
	int id;
	String name;
	boolean collidable;
	boolean gravity;
	BufferedImage texture;
	Polygon hitBox = null;
	
	public Tile(int id, String name, boolean collidable, boolean gravity, BufferedImage texture) {
		this.id = id;
		this.name = name;
		this.collidable = collidable;
		this.gravity = gravity;
		this.texture = texture;
		
	}
	public BufferedImage getTexture() {
		return this.texture;
	}
	public int getID() {
		return this.id;
	}
	public Polygon getHitbox() {
		return hitBox;
		
	}
	public boolean collides() {
		return collidable;
	}
	public String getName() {
		return name;
	}

}
