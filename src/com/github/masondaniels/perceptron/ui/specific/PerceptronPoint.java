package com.github.masondaniels.perceptron.ui.specific;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import com.github.masondaniels.perceptron.ui.AppColors;
import com.github.masondaniels.perceptron.ui.AppObject;

public class PerceptronPoint extends AppObject {

	private Color color;
	private boolean showText = false;
	private int pX;
	private int pY;

	public PerceptronPoint(double pX, double pY, int radius, Color color) {
		super((int) pX - radius, (int) pY - radius, radius, radius);
		this.pX = (int) pX;
		this.pY = (int) pY;
		this.color = color;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int) x, (int) y, width, height);
		if (showText) {
			g.setColor(AppColors.HOVER_TEXT);
			g.drawString("(" + pX + ", " + pY + ")", (int) mouseX, (int) mouseY);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (hitbox.contains(e.getPoint())) {
			showText = true;
		} else {
			showText = false;
		}
		super.mouseMoved(e);
	}

}
