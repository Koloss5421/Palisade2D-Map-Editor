package com.jphardin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;

import javax.imageio.ImageIO;

public class FileManager {
	
	static boolean showFiles = false;
	
	public BufferedImage closeButton;
	public Polygon closeButtonBox;
	
	public FileManager() {
		loadResources();
		int filesWidth = 600;
		int filesHeight = 500;
		int x = (Main.windowWidth / 2) - (filesWidth / 2);
		int y = (Main.windowHeight / 2) - (filesHeight / 2);
		x = x + filesWidth - closeButton.getWidth() - 5;
		y = y + 5;
		int[] xPoly = {x,x + closeButton.getWidth(),x + closeButton.getWidth(),x};
		int[] yPoly = {y, y, y + closeButton.getHeight(), y + closeButton.getHeight()};
		closeButtonBox = new Polygon(xPoly, yPoly, 4);
	}
	
	public void draw(Graphics2D g2) {
		int filesWidth = 600;
		int filesHeight = 500;
		int x = (Main.windowWidth / 2) - (filesWidth / 2);
		int y = (Main.windowHeight / 2) - (filesHeight / 2);
		UITileSet uiTileSet = Main.uiTileSet;
			drawDialog(g2, filesWidth, filesHeight, x, y);
			drawFileList(g2, filesWidth, filesHeight, x, y);
			g2.drawImage(uiTileSet.getTextureOf(newButtonState), x, y, width, width, null); // draw the button
	}
	private void loadResources() {
		try {
			closeButton = ImageIO.read(new File("resources/RedX.png"));
		} catch (Exception e) {
			new Exception(e);
		}
	}
	private File[] getFiles() {
		File dir = new File("resources/");
		FilenameFilter mapFilter = getFileFilter();
		File[] list = dir.listFiles(mapFilter);
		return list;
	}
	private FilenameFilter getFileFilter() {
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if(lowercaseName.endsWith(".map")) {
					return true;
				}
				else 
				{
					return false;
				}
			}
		};	
		return filter;
	}
	private void drawDialog(Graphics2D g2, int filesWidth, int filesHeight, int x, int y) {
		
		g2.setColor(Color.DARK_GRAY);
		g2.fillRect(x, y, filesWidth, filesHeight);
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(4));
		g2.drawRect(x, y, filesWidth, filesHeight);
		g2.drawImage(closeButton, x + filesWidth - closeButton.getWidth() - 5, y + 5, 32, 32, null);
	}
	private void drawFileList(Graphics2D g2, int filesWidth, int filesHeight, int x, int y) {
		File[] files = getFiles();
		for(int i = 0; i < files.length; i++) {
			g2.drawString(files[i].getName(), 0, 0 + i * 20);
		}
	}
	public void setShowFiles(boolean setter) {
		showFiles = setter;
		UI ui = Main.ui;
		ui.setFileManOpen(setter);
	}
	public void newFile(String fileName) {
		
	}
	public boolean checkCloseHover(Point mouseLoc) {
		if(closeButtonBox.contains(mouseLoc)) {
			if (showFiles) {
				return true;
			}
			else {
				return false;
			}
			
		}
		else {
			return false;
		}
		
	}
}
