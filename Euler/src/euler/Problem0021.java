package euler;

import java.util.ArrayList;
import java.util.List;

// Let d(n) be defined as the sum of proper divisors of n (numbers less than
// n which divide evenly into n).
// If d(a) = b and d(b) = a, where a <> b, then a and b are an amicable pair
// and each of a and b are called amicable numbers.

// For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22,
// 44, 55 and 110; therefore d(220) = 284. The proper divisors of 284 are
// 1, 2, 4, 71 and 142; so d(284) = 220.

// Evaluate the sum of all the amicable numbers under 10000.

public class Problem0021 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 1; i < 10; i++) {
			Number n = new Number(i);
			System.out.println(i + ": " + n.properDivisors + " Sum: " + n.sumOfProperDivisors);
		}
	}
	
	private static class Number {
		Integer value;
		List<Integer> properDivisors;
		Integer sumOfProperDivisors;
		Number(Integer arg) {
			value = new Integer(arg);
			properDivisors = getProperDivisors(arg);
			sumOfProperDivisors = getSumOfProperDivisors(arg);
		}
		
		private List<Integer> getProperDivisors(Integer arg) {
			List<Integer> listOfDivisors = new ArrayList<Integer>();
			for (int i = 1; i <= arg / 2; i++) {
				if (arg % i == 0) {
					listOfDivisors.add(i);
				}
			}

			return listOfDivisors;
		}
		
		private int getSumOfProperDivisors(Integer arg) {
			List<Integer> listOfDivisors = getProperDivisors(arg);
			int sum = 0;
			for(Integer i : listOfDivisors) {
				sum += i;
			}
			
			return sum;
		}
	}
}
