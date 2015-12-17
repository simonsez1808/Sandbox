package euler;

//The sum of the squares of the first ten natural numbers is,
//1^2 + 2^2 + ... + 102 = 385

//The square of the sum of the first ten natural numbers is,
//(1 + 2 + ... + 10)2 = 552 = 3025

//Hence the difference between the sum of the squares of the first
//ten natural numbers and the square of the sum is 3025 − 385 = 2640.
//Find the difference between the sum of the squares of the first one
//hundred natural numbers and the square of the sum.

public class Problem0006 {

	private static final int START_NUM = 1;
	private static final int END_NUM = 100;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long sumOfSquares = getSumOfSquares(START_NUM, END_NUM);
		long squareOfSum = getSquareOfSum(START_NUM, END_NUM);
		
		System.out.println("Sum of squares from " + START_NUM + " to "
				+ END_NUM + " is " + sumOfSquares);
		System.out.println("Square of sum " + squareOfSum);
		
		System.out.println("Differeince is " + Math.abs(sumOfSquares - squareOfSum));
	}
	


	static long getSumOfSquares(int startNum, int endNum) {
		long result = 0L;

		for (long i = startNum; i <= endNum; i++) {
			result += (long) Math.pow(i, 2);
		}
		
		return result;
	}

	static long getSquareOfSum(int startNum, int endNum) {
		long result = 0L;

		for (long i = startNum; i <= endNum; i++) {
			result += i;
		}

		return (long) Math.pow(result, 2);
	}

}
