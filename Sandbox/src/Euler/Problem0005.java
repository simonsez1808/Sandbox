package Euler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Question - 2520 is the smallest number that can be divided by each of the
 * numbers from 1 to 10 without any remainder. What is the smallest positive
 * number that is evenly divisible by all of the numbers from 1 to 20?
 * 
 * Answer - The smallest number is the one that is the product of all the prime
 * numbers under 20.
 */

public class Problem0005 {

	static final int TOP_NUMBER = 20;

	static List<Integer> allNumbers = getAllFactors(TOP_NUMBER);

	static List<Integer> getAllFactors(Integer topNumber) {
		List<Integer> factors = new ArrayList<Integer>();

		for (int i = 2; i <= topNumber; i++) {
			factors.add(i);
		}

		return factors;
	}

	static Map<Integer, Integer> getPrimeFactorsOf(Integer number) {

		List<Integer> listOfPrimes = getPrimeNumbersUpToAndIncluding(number);
		Map<Integer, Integer> primeFactors = new TreeMap<Integer, Integer>();

		while (number > 1) {

			for (Integer prime : listOfPrimes) {

				if (number % prime == 0) {
					number = number / prime;

					if (primeFactors.containsKey(prime)) {
						primeFactors.put(prime, primeFactors.get(prime) + 1);
					} else {
						primeFactors.put(prime, 1);
					}

				}

			}
		}

		return primeFactors;
	}

	static List<Integer> getPrimeNumbersUpToAndIncluding(Integer upperLimit) {
		List<Integer> primeNumbers = new ArrayList<Integer>();

		for (int i = 2; i <= upperLimit; i++) {

			if (isPrime(i)) {
				primeNumbers.add(i);
			}

		}
		return primeNumbers;
	}

	static boolean isPrime(long number) {
		int i;
		boolean primeFlag = true;
		
		for (i = 2; i < (number / 2); i++) {

			if (number % i == 0) {
				primeFlag = false;
				break;
			}

		}

		return primeFlag;
	}

	public static void main(String[] args) {

		Map<Integer, Integer> summaryPrimeFactors = new TreeMap<Integer, Integer>();

		System.out.println(getAllFactors(TOP_NUMBER));
		System.out.println(getPrimeNumbersUpToAndIncluding(TOP_NUMBER));
		System.out.println(getPrimeFactorsOf(TOP_NUMBER));

		for (int i : getAllFactors(TOP_NUMBER)) {
			
			for (Map.Entry<Integer, Integer> me : getPrimeFactorsOf(i).entrySet()) {
				
				if (summaryPrimeFactors.containsKey(me.getKey())) {
					if (me.getValue() > summaryPrimeFactors.get(me.getKey())) {
						summaryPrimeFactors.put(me.getKey(), me.getValue());
					}
				} else {
					summaryPrimeFactors.put(me.getKey(), me.getValue());
				}
			}

		}
		
		System.out.println("Summary prime factors: " + summaryPrimeFactors);

		Long finalNumber = 1L;
		
		for (Map.Entry<Integer, Integer> me : summaryPrimeFactors.entrySet()) {
			finalNumber *= (long) Math.pow(me.getKey(), me.getValue());
		}

		System.out.println("FINAL: " + finalNumber);

	}
}
