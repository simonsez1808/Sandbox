package euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * 
 * Find the sum of all the primes below two million.
 */
public class Problem0010 {

	public static void main(String[] args) {

		ListOfPrimes.add();

		System.out.println("Done. sum of primes < "
				+ Constants.MAXIMUM_PRIME_THRESHOLD + " = "
				+ ListOfPrimes.getSum());
	}

}

class Constants {
	protected static final long MAXIMUM_PRIME_THRESHOLD = 2000000L;
}

class ListOfPrimes {
	static List<Long> primes = new ArrayList<Long>(Arrays.asList(2L, 3L, 5L));

	static long getHighest() {
		return primes.get(primes.size() - 1);
	}

	static void add() {
		for (long i = getHighest(); i < Constants.MAXIMUM_PRIME_THRESHOLD; i++) {
			if (!isDivisibleByAnExistingPrime(i)) {
				//System.out.println(i);
				primes.add(i);
			}
		}
	}

	static long getSum() {
		long sum = 0;
		for (long element : primes) {
			sum += element;
		}
		return sum;
	}

	static boolean isDivisibleByAnExistingPrime(long candidate) {
		for (long divisor : primes) {
			if (candidate % divisor == 0) {
				return true;
			}
		}

		return false;
	}

}
