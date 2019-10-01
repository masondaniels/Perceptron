package com.github.masondaniels.perceptron.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

public class App extends JFrame implements Runnable {

	private ArrayList<AppObject> appObjs = new ArrayList<AppObject>();
	private static final long serialVersionUID = 1L;
	private Canvas canvas = new Canvas();

	public App(int initialWidth, int initialHeight) {
		Dimension dimension = new Dimension(initialWidth, initialHeight);
		setSize(dimension);
		canvas.setSize(dimension);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas.setBackground(Color.WHITE);
		add(canvas);
		pack();
		setResizable(false);
		setVisible(true);
		canvas.createBufferStrategy(3);
		setLocationRelativeTo(null);
		new Thread(this).start();
	}

	/*
	 * The drawing runs on a different thread, of course.
	 */

	@Override
	public void run() {
		try {
			while (true) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				BufferStrategy bs = canvas.getBufferStrategy();
				Graphics g = bs.getDrawGraphics();
				g.setColor(canvas.getBackground());
				g.clearRect(0, 0, getWidth(), getHeight());
				g.fillRect(0, 0, getWidth(), getHeight());
				draw(g);
				g.setColor(Color.WHITE);
				g.dispose();
				bs.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Draw objects here. AppObjs are basically the smallest units of things to draw
	 * -- sort of like sprites.
	 */

	private void draw(Graphics g) {
		synchronized (appObjs) {
			for (AppObject appObj : appObjs) {
				appObj.draw(g);
			}
		}
	}

	public void addAppObj(AppObject appObj) {
		synchronized (appObjs) {
			canvas.addMouseListener(appObj);
			canvas.addMouseMotionListener(appObj);
			appObjs.add(appObj);
		}
	}

	public void removeAppObj(AppObject appObj) {
		synchronized (appObjs) {
			canvas.removeMouseListener(appObj);
			canvas.removeMouseMotionListener(appObj);
			appObjs.remove(appObj);
		}
	}

}