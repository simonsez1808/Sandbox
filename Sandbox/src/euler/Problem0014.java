package euler;

import java.util.ArrayList;
import java.util.List;

/*Longest Collatz sequence
 Problem 14
 The following iterative sequence is defined for the set of positive integers:

 n → n/2 (n is even)
 n → 3n + 1 (n is odd)

 Using the rule above and starting with 13, we generate the following sequence:

 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
 It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.

 Which starting number, under one million, produces the longest chain?

 NOTE: Once the chain starts the terms are allowed to go above one million.*/

class Problem0014 {

	public static void main(String[] args) {
	

		long startNumber = 13;
		long workingNumber = 13;
		int chainLength = 0;
		int maxChainLength = 0;
		long maxNumber = 0;

		for (startNumber = 1; startNumber < 1000000; startNumber++) {
			workingNumber = startNumber;
			chainLength = 0;
			List<Long> numberList = new ArrayList<Long>();
			
			while (workingNumber > 1) {
				// System.out.println(workingNumber);
				workingNumber = getCollatz(workingNumber);
				chainLength++;
				numberList.add(workingNumber);
			}

			if (chainLength > maxChainLength) {
				maxNumber = startNumber;
				maxChainLength = chainLength;
				System.out.println("Maximum start number = " + maxNumber
						+ " Maximum chain length = " + maxChainLength);
				System.out.println("Sequence: " + numberList);
			}
			
			
		}

	}

	static long getCollatz(long number) {
		if (number % 2 == 0) {
			return number / 2;
		} else {
			return (3 * number) + 1;
		}
	}

}
