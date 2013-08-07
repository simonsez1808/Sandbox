package Euler;

import java.util.ArrayList;
import java.util.List;

/**
 * The prime factors of 13195 are 5, 7, 13 and 29. What is the largest prime
 * factor of the number 600851475143 ?
 */
public class Problem0003 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Long> factors = new ArrayList<Long>();

		Long quotient = 600851475143L;

		while (quotient > 1) {
			//System.out.println("Quotient = " + quotient); 
			for (Long divisor = 2L; divisor <= quotient; divisor++) {
				if (quotient % divisor == 0) {
					factors.add(divisor);
					System.out.println("quotient before " + quotient + " factors " + factors);
					quotient = quotient / divisor;
					System.out.println("quotient after " + quotient);
					break;
				}
			}
		}
		
		System.out.println(factors);
	}

}
