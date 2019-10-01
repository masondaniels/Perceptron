package com.github.masondaniels.perceptron;

import java.util.Random;

public class Perceptron {

	/*
	 * Without comments, this perceptron class is 60 lines of code.
	 * 
	 * A perceptron is the base of machine learning. It's linear, easy to
	 * understand, and pretty fun to create.
	 *
	 * These comments may be inaccurate, it has been a while since I touched the
	 * textbook I used to create this class (Neural Networks and Deep Learning: A
	 * Textbook, by Charu C. Aggarwal).
	 */

	private double[] weights;
	private double bias;

	public Perceptron(int length) {

		/*
		 * The parameter "length" is the length of input nodes. There are 2 inputs in
		 * this application, x & y. This makes sense since we are making an input output
		 * machine. X & y are our inputs and the output (calculate method) is the
		 * classification (either negative or positive, but this is arbitrary, it could
		 * be any label you want).
		 */

		weights = new double[length];

		/*
		 * In a perceptron, there is a weight for each input. These weights are
		 * manipulated (trained) to get the correct output.
		 */

		Random random = new Random();
		for (int i = 0; i < weights.length; i++) {
			weights[i] = random.nextBoolean() ? -1 : 1;
		}

		bias = random.nextBoolean() ? -1 : 1;

		/*
		 * The bias solves a mathematical issue with mean-centered input where the
		 * expected value is not equal to 0. (Though this explaination could use some
		 * work). I do know through trial and error, however, that the bias gives the
		 * classifier another degree of movement to classify points.
		 * 
		 * Unfortunately the book I used to learn about the perceptron, the same
		 * textbook mentioned above, seems very wordy about the exact issue the bias
		 * resolves.
		 */
	}

	public double calculate(double... input) {

		/*
		 * The calculate method is able to classify inputs (for example, 2 inputs which
		 * correspond to x & y) but not input arrays (multiple x & y coordinates).
		 * 
		 * This is just a design I went with to make things less confusing and less
		 * error prone.
		 */

		double sum = 0;
		for (int i = 0; i < input.length; i++) {
			sum += (input[i] * weights[i]) + bias;
		}
		return sign(sum);

		/*
		 * This is the second most important part of the perceptron, the perceptron's
		 * summation equation followed by returning the classification using the
		 * activation function, sign.
		 * 
		 * This predicted value is acquired by summing the multiplied values of the
		 * inputs and weights (plus the bias) then putting the value through the
		 * activation function.
		 * 
		 */

	}

	/*
	 * The activation function. Sign, in this instance, is great for linear
	 * classification!
	 */

	private double sign(double sum) {
		return (sum >= 0) ? 1 : -1;
	}

	/*
	 * A method I threw together to tell my application when all points are
	 * classified correctly. It does exactly what you'd imagine, check if all points
	 * have no error.
	 */

	public boolean isAccurate(double[][] inputs, double[] expecteds) {
		for (int i = 0; i < inputs.length; i++) {
			double expected = expecteds[i];
			double[] input = inputs[i];
			if (getError(input, expected) != 0) {
				return false;
			}
		}
		return true;
	}

	public void train(double[][] inputs, double[] expecteds, double rate) {
		for (int i = 0; i < inputs.length; i++) {
			double expected = expecteds[i];
			double[] input = inputs[i];
			adjustWeights(getError(input, expected), input, rate);
		}
	}

	private void adjustWeights(double error, double[] inputs, double rate) {
		for (int i = 0; i < weights.length; i++) {
			weights[i] += error * inputs[i] * rate;
		}
		bias += error * rate;
	}

	/*
	 * There are three possible outcomes of the error method: 0, 2, or -2. When the
	 * error is zero, there is no error. If the error is either 2 or -2 the weights
	 * need to be updated in the negative direction (as seen in adjustWeights).
	 */

	private double getError(double input[], double expected) {
		return expected - calculate(input);
	}
}