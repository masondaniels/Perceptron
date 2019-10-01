package com.github.masondaniels.perceptron;

import com.github.masondaniels.perceptron.ui.App;
import com.github.masondaniels.perceptron.ui.AppColors;
import com.github.masondaniels.perceptron.ui.specific.PerceptronChart;
import com.github.masondaniels.perceptron.ui.specific.PerceptronPoint;

public class Main {

	public static void main(String[] args) {

		/*
		 * Input data for perceptron. (9, 50), (130, 20), (110, 130), and (90, 100) are
		 * all points that correspond with their classifications ("expecteds"). I should
		 * really make this just one array with x, y, z instead of two arrays: x, y and
		 * another with z. Z is the expected value. If it is 1 it is positive. -1 is
		 * negative (though these classifications are arbitrary). Keep in mind that a
		 * perceptron can only seperate linear data. So, if you input something other
		 * than linear data, the app will never "draw" because it will get hung up on
		 * the isAccurate method. The isAccurate method simply returns if all points are
		 * classified correctly or not (only once they are all classified correctly does
		 * the app draw).
		 * 
		 * I have a working app (and have had since early 2019) where you can just left
		 * or right click to place points on a canvas but decided to remake it except
		 * with clean code and comments. You can't click on the canvas to place points
		 * in this version, you must place them by editing the inputs and expecteds
		 * arrays.
		 * 
		 * My goal was to go for code that isn't very ugly.
		 */

		double[][] inputs = { { 9, 50 }, { 130, 20 }, { 110, 130 }, { 90, 100 } };
		double[] expecteds = { 1, 1, -1, -1 };
		Perceptron perceptron = new Perceptron(inputs[0].length);
		while (!perceptron.isAccurate(inputs, expecteds)) {
			perceptron.train(inputs, expecteds, 0.1);
		}
		
		int width = 400;
		int height = 400;

		App app = new App(width, height);
		app.setTitle("Perceptron");

		/*
		 * Draw chart
		 */

		app.addAppObj(new PerceptronChart(0, 0, width, height, perceptron));

		/*
		 * Draw points
		 */

		for (int i = 0; i < inputs.length; i++) {
			app.addAppObj(new PerceptronPoint(inputs[i][0], inputs[i][1], 10,
					(expecteds[i] == 1) ? AppColors.POSITIVE_POINTS : AppColors.NEGATIVE_POINTS));
		}
	}

}
