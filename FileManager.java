package com.jphardin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileManager {
	
	static boolean showFiles = false;
	
	public static BufferedImage closeButton;
	public static Polygon closeButtonBox;
	
	public FileManager() {
		try {
			closeButton = ImageIO.read(new File("resources/RedX.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int windowWidth = Main.windowWidth;
		int windowHeight = Main.windowHeight;
		int filesWidth = 600;
		int filesHeight = 500;
		int x = (windowWidth / 2) - (filesWidth / 2);
		int y = (windowHeight / 2) - (filesHeight / 2);
		x = x + filesWidth - closeButton.getWidth() - 5;
		y = y + 5;
		int[] xPoly = {x,x + closeButton.getWidth(),x + closeButton.getWidth(),x};
		int[] yPoly = {y, y, y + closeButton.getHeight(), y + closeButton.getHeight()};
		closeButtonBox = new Polygon(xPoly, yPoly, 4);
	}
	
	
	public static void draw(Graphics2D g2) {
		
		int windowWidth = Main.windowWidth;
		int windowHeight = Main.windowHeight;
		
		if(showFiles && closeButton != null) {
			File dir = new File("/resources/");
			File[] list = dir.listFiles();
			int filesWidth = 600;
			int filesHeight = 500;
			int x = (windowWidth / 2) - (filesWidth / 2);
			int y = (windowHeight / 2) - (filesHeight / 2);
			
			g2.setColor(Color.DARK_GRAY);
			g2.fillRect(x, y, filesWidth, filesHeight);
			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke(4));
			g2.drawRect(x, y, filesWidth, filesHeight);
			g2.drawImage(closeButton, x + filesWidth - closeButton.getWidth() - 5, y + 5, 32, 32, null);
			
			
		}
	}
	public static void setShowFiles(boolean setter) {
		showFiles = setter;
	}
	public static boolean checkCloseHover(Point mouseLoc) {
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
