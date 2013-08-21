package euler;

import java.math.BigInteger;

/*Starting in the top left corner of a 2×2 grid, and only being able
 * to move to the right and down, there are exactly 6 routes to the bottom
 * right corner. 
 * 
 * How many such routes are there through a 20×20 grid?*/

public class Problem0015 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for (int i = 1; i < 21; i++) {
			Grid grid = new Grid(i);
			System.out.println(grid.getPaths());
		}
	}

	static class Grid {
		int n;

		Grid(int lengthOfSide) {
			n = lengthOfSide;
		}

		BigInteger getPaths() {
			System.out.println("Grid size " + n + " x " + n);
			BigInteger term1 = factorial(2 * n);
			System.out.println("Term1: " + term1);
			BigInteger term2 = factorial(n).multiply(factorial(n));
			System.out.println("Term2: " + term2);
			return term1.divide(term2);
		}

		static BigInteger factorial(int n) {

			if (n <= 2) {
				return new BigInteger(String.valueOf(n));
			} else {
				return (factorial(n - 1).multiply(new BigInteger(Integer.toString(n))));
			}
			
		}

	}

}
