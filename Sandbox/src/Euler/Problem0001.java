package Euler;

import java.util.ArrayList;

/* If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
 * The sum of these multiples is 23. Find the sum of all the multiples of 3 or 5 below 1000.
 */

public class Problem0001 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sum = 0;
	
		ArrayList<Integer> divisors = new ArrayList<Integer>();
		divisors.add(3);
		divisors.add(5);
		
		for (int i = 0; i < 1000; i++) {
			if (isDivisibleBy(divisors, i)) {
				sum += i;
			}
		}
		
		System.out.println("Total = " + sum);
	}
	
	private static boolean isDivisibleBy(ArrayList<Integer> divisors, int number) {
		for (int x : divisors) {
			if (number % x == 0) {
				return true;
			}
		}
		System.out.println(number + " is not divisible by 3 or 5");
		return false;
	}

}
