package com.jphardin;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseInput extends MouseAdapter implements MouseListener  {
	
	
	TileMap tileMapper = Main.tileMapper; // Links to tileMapper created in Main
	TileType tileSet = Main.tileSet; // Links to tileSet Created in Main
	
	boolean[] buttons = new boolean[5];
	int mouseX = 0;
	int mouseY = 0;
	int currentButton = -1;
	
	public void updateMouse() {
		UI ui = Main.ui;
		FileManager fileManager = Main.fileManager;
		boolean buttonPressed = false;
		
		// Checks if Mouse 1 is down and that the mouse is over the Close button
		if(buttons[0] && fileManager.checkCloseHover(new Point(mouseX, mouseY))) {
			fileManager.setShowFiles(false);
			buttonPressed = true;
		}
		if(buttons[0] && currentButton != -1) {
			ui.setButtonState(currentButton, 1);
			fileManager.setShowFiles(true);
			buttonPressed = true;
		}
		else {
		
		}
		// Checks to see if a map is loaded so there aren't null errors
		if (Main.mapLoaded && !buttonPressed) {
			if(buttons[0]) {
				Point mouseLoc = new Point(mouseX, mouseY);
				tileMapper.hasTile(mouseLoc, Main.xOffset, Main.yOffset);
			}
			// Method Overloading to use Right-Click as a "Delete"
			if(buttons[2]) {
				Point mouseLoc = new Point(mouseX, mouseY);
				tileMapper.hasTile(mouseLoc, Main.xOffset, Main.yOffset, 0);
			}
		}
	}
	// Watches mouse movement to change Cursor to Hand Style when over a button
	public void mouseMoved(MouseEvent e) {
		FileManager fileManager = Main.fileManager;
		UI ui = Main.ui;
		boolean hovering = false;
		this.mouseX = e.getX();
		this.mouseY = e.getY() - 27;
		Point point = new Point(mouseX,mouseY);
		int buttonCheck = ui.checkButtonHover(point);
		
		if(fileManager.checkCloseHover(point) ) {
			hovering = true;
		}
		
		if (buttonCheck != -1) {
			hovering = true;
			currentButton = buttonCheck;
		}
		if(hovering) {
			Main.setCursor(Cursor.HAND_CURSOR);
		}
		else {
			currentButton = -1;
			Main.setCursor(Cursor.DEFAULT_CURSOR);
		}
	}
	public void mousePressed(MouseEvent e) {
		this.buttons[e.getButton() - 1] = true;
		this.mouseX = e.getX();
		this.mouseY = e.getY() - 27;
		
	}
	public void mouseDragged(MouseEvent e){
		this.mouseX = e.getX();
		this.mouseY = e.getY() - 27;
	}
	public void mouseReleased(MouseEvent e) {
		this.buttons[e.getButton() - 1] = false;
	}
}
