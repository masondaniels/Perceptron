package com.github.masondaniels.perceptron.ui.specific;

import java.awt.Graphics;

import com.github.masondaniels.perceptron.Perceptron;
import com.github.masondaniels.perceptron.ui.AppColors;
import com.github.masondaniels.perceptron.ui.AppObject;

public class PerceptronChart extends AppObject {

	private Perceptron perceptron;

	public PerceptronChart(int x, int y, int width, int height, Perceptron perceptron) {
		super(x, y, width, height);
		this.perceptron = perceptron;
	}

	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (perceptron.calculate(i, j) == 1) {
					g.setColor(AppColors.PREDICTED_POSITIVE);
				} else {
					g.setColor(AppColors.PREDICTED_NEGATIVE);
				}
				g.drawRect(i, j, 1, 1);
			}
		}
	}
}