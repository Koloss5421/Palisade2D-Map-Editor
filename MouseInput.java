package com.jphardin;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseInput extends MouseAdapter implements MouseListener  {
	
	
	static TileMap tileMapper = Main.tileMapper; // Links to tileMapper created in Main
	TileType tileSet = Main.tileSet; // Links to tileSet Created in Main
	Main main = new Main(); // Links to main as an object (Still not sure if I should do this)
	static boolean[] buttons = new boolean[5];
	static int mouseX = 0;
	static int mouseY = 0;
	
	public static void updateMouse() {
		
		boolean buttonPressed = false;
		// Checks if Mouse 1 is down and that the mouse is over the Close button
		if(buttons[0] && FileManager.checkCloseHover(new Point(mouseX, mouseY))) {
			FileManager.setShowFiles(false);
		}
		if(buttons[0] && UI.checkFileHover(new Point(mouseX,mouseY))) {
			UI.setFileButtonState(1);
			FileManager.setShowFiles(true);
		}
		else {
			UI.setFileButtonState(0);
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
	
	public void mousePressed(MouseEvent e) {
		buttons[e.getButton() - 1] = true;
		mouseX = e.getX();
		mouseY = e.getY() - 27;
		
	}
	public void mouseDragged(MouseEvent e){
		mouseX = e.getX();
		mouseY = e.getY() - 27;
	}
	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton() - 1] = false;
	}
	public void mouseMoved(MouseEvent e) {
		
		boolean hovering = false;
		mouseX = e.getX();
		mouseY = e.getY() - 27;
		Point point = new Point(mouseX,mouseY);
		
		if( FileManager.checkCloseHover(point) ) {
			hovering = true;
		}
		
		if ( UI.checkFileHover(point) ) {
			hovering = true;
		}
		if(hovering) {
			Main.setCursor(Cursor.HAND_CURSOR);
		}
		else {
			
			Main.setCursor(Cursor.DEFAULT_CURSOR);
		}
	}
}
