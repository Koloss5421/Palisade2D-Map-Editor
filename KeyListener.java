package com.jphardin;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
	
	boolean[] keys = new boolean[256];
	int keyTimer = 0;
	int delay = 5;
	public void updateInput() {
		
		TileType tileSet = Main.tileSet;
		
		if(keys[KeyEvent.VK_LEFT] && keyTimer >= delay) {
			int xOffset = Main.getXOffset() - 1;
			Main.setXOffset(xOffset);
			keyTimer = 0;
		}
		if(keys[KeyEvent.VK_UP] && keyTimer >= delay) {
			int yOffset = Main.getYOffset() - 1;
			Main.setYOffset(yOffset);
			keyTimer = 0;
		}
		if(keys[KeyEvent.VK_RIGHT] && keyTimer >= delay) {
			int xOffset = Main.getXOffset() + 1;
			Main.setXOffset(xOffset);
			keyTimer = 0;
		}
		if(keys[KeyEvent.VK_DOWN] && keyTimer >= delay) {
			int yOffset = Main.getYOffset() + 1;
			Main.setYOffset(yOffset);
			keyTimer = 0;
		}
		if(keys[KeyEvent.VK_COMMA] && keyTimer >= delay) {
			int selector = Main.getSelector();
			int next = selector - 1;
			if(selector != 0 && tileSet.getTile(next) != null) {
				Main.setSelector(next);
			}
			else {
				for (int i = 255; i > 0; i--) {
					if(tileSet.getTile(i) != null && i < selector) {
						Main.setSelector(i);
						break;
					}
				}
			}
			keyTimer = 0;
		}
		if(keys[KeyEvent.VK_PERIOD] && keyTimer >= delay) {
			int selector = Main.getSelector();
			int next = selector + 1;
			if(selector != 255 && tileSet.getTile(next) != null) {
				Main.setSelector(next);
			}
			else {
				for (int i = selector; i < 256; i++) {
					if(tileSet.getTile(i) != null && i > selector) {
						Main.setSelector(i);
						break;
					}
				}
			}
			keyTimer = 0;
		}
		if(keys[KeyEvent.VK_CONTROL] && keys[KeyEvent.VK_S] && keyTimer >= delay) {
			Main.saveMap();
		}
		if(keys[KeyEvent.VK_C] && keyTimer >= delay) {
			Main.ui.setShowHitBoxes( !Main.ui.getShowHitBoxes() );
		}
		if(keys[KeyEvent.VK_G] && keyTimer >= delay) {
			Main.ui.setDrawGrid(!Main.ui.getDrawGrid());
		}
		keyTimer++;
	}
	public void keyPressed(KeyEvent event) {
		keys[event.getKeyCode()] = true;
	}
	public void keyReleased(KeyEvent event) {
		keys[event.getKeyCode()] = false;
	}
}

