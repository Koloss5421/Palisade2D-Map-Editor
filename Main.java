package com.jphardin;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Main extends JPanel {
	
	final static int FPS = 25;
	final static int SKIP_TICKS = 1000 / FPS;
	static int sleep_time = 0;
	
	
	static boolean running;
	
	static JFrame frame;
	static String title = "Palisade2D Map Editor";
	static int windowWidth = 800;
	static int windowHeight = 600;
	
	static String mapName = "";
	static boolean mapLoaded = false;
	
	static int yOffset = 0;
	static int xOffset = 0;
	static int tileSize = 50;
	static TileType tileSet = new TileType();
	static UITileSet uiTileSet = new UITileSet();
	static TileMap tileMapper;
	
	static int selector = 0;
	
	static int cursor = Cursor.DEFAULT_CURSOR;
	
	public Main() {
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		
		long next_tick = System.currentTimeMillis();
		
		frame = new JFrame(title);
		Main main = new Main();	
		//Input input = new Input();
		KeyListener keyListener = new KeyListener();
		MouseInput mouseListener = new MouseInput();
		FileManager fileManager = new FileManager();
		
		frame.setSize(windowWidth, windowHeight);
		frame.setMinimumSize(new Dimension(windowWidth, windowHeight));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		frame.add(main);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addKeyListener(keyListener);
		frame.addMouseListener(mouseListener);
		frame.addMouseMotionListener(mouseListener);
		frame.setFocusable(true);
		
		

		running = true;
		
		while(running) {
			
			frame.repaint();
			KeyListener.updateInput();
			MouseInput.updateMouse();
			next_tick += SKIP_TICKS;
			sleep_time = (int) (next_tick - System.currentTimeMillis());
			
			if (sleep_time >= 0) {
				Thread.sleep(sleep_time);
			}
			//System.out.println("Memory usage: " + ( Runtime.getRuntime().freeMemory() / 1048576 ) + " Mb");
		}
		
	}
	//PaintMethod used with repaint()
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if(mapLoaded) {
			tileMapper.drawMap(g2, tileSize, yOffset, xOffset);
		}
		else {
			g2.setFont(new Font("Consolas", Font.BOLD, 20));
			g2.setColor(Color.red);
			g2.drawString("Map not loaded!", 10, windowHeight - 55);
			g2.setFont(new Font("Consolas", Font.PLAIN, 20));
			g2.setColor(Color.red);
			g2.drawString("Use 'File' to load map.", 10, windowHeight - 35);
		}
		UI.drawHud(g2);
		FileManager.draw(g2);
		
	}
	public static void setXOffset(int x) {
		xOffset = x;
	}
	public static void setYOffset(int y) {
		yOffset = y;
	}
	public static int getXOffset() {
		return xOffset;
	}
	public static int getYOffset() {
		return yOffset;
	}
	public static int getSelector() {
		return selector;
	}
	public static void setSelector(int i) {
		selector = i;
	}
	public static void setMapName(String newName) {
		mapName = newName;
		mapLoaded = true;
	}
	public static void saveMap() {
		//tileMapper.writeMap(level);
	}
	public static void setCursor(int newCursor) {
		frame.getContentPane().setCursor(Cursor.getPredefinedCursor(newCursor));
	}

}
