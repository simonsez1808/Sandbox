package euler;

import java.math.BigInteger;

//n! means n × (n - 1) × ... × 3 × 2 × 1

//For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
//and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.

//Find the sum of the digits in the number 100!

public class Problem0020 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String number = "100";
		System.out.println("Factorial(100): " + factorial(new BigInteger(number)));
		System.out.println("Sum of digits: " + getSumOfDigits(factorial(new BigInteger(number))));
	}
	
	private static long getSumOfDigits(BigInteger arg) {
		char[] chars = arg.toString().toCharArray();
		long total = 0;
		for (char i : chars) {
			total += Character.getNumericValue(i);
		}
		
		return total;
	}
	private static BigInteger factorial(BigInteger arg) {
		
		
		if (arg.equals(BigInteger.ONE)) {
			return BigInteger.ONE;
		}
		
		return arg.multiply(factorial(arg.subtract(new BigInteger("1"))));
	}

}
