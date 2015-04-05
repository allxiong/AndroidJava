package com.example.asynch;

/**
 * 
 * @param radicand : the number for which the square root is desired
 * @param guess : the initial guess of the square root
 * @param precision : the number of decimals desired
 * @return square root calculated to desired precision using Newton's method.
 */
public class NewtonSqrt {
	static double sqrt(double radicand, double guess, int precision) {
		
		double scale = Math.pow(10, precision);
		//perform calculation
		double candidate = 0.5f * (guess + radicand/guess);
		/*
		 * compare the result and the guess to the desired precision.
		 * Note the round function returns a long int, we need to 
		 * scale the floating point result to a long to compare.
		 * If not equal, set guess to the result, and recurse: 
		 */
		if( ((double)Math.round(candidate * scale)/scale) != 
			((double)Math.round(guess * scale)/scale) ) {
			guess = candidate;
			candidate = sqrt(radicand, guess, precision);
		}
		/*
		 * this will not execute until the candidate and the guess are
		 * equal (to the desired precision). 
		 */
		
		return (double)Math.round(candidate * scale)/scale;
	}
}

