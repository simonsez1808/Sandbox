package euler;

import java.math.BigInteger;

/*2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

What is the sum of the digits of the number 2^1000?*/
public class Problem0016 {

	public static void main(String[] args) {
		BigInteger start = new BigInteger("2");
		BigInteger bi = new BigInteger("2");

		for (int i = 0; i < 999; i++) {
			bi = bi.multiply(start);
			//System.out.println("Power: " + (i + 1) + " - " + bi);
		}
		
		System.out.println(bi);
		
		int sum = 0;
		String bis = bi.toString();
		for (int i = 0, e = bis.length(); i < e; i++) {
			sum += Integer.parseInt(bis.substring(i, i + 1));
		}
		
		System.out.println("Sum: " + sum);
	}
	
	

}
