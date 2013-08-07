package Euler;

/*By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
 * that the 6th prime is 13.
 * What is the 10 001st prime number?*/
public class Problem0007 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		long number = 2;
		long primeCount = 0;

		while (true) {
			
			if (isPrime(number)) {
				primeCount++;
				System.out.println("Got prime number " + primeCount
						+ " value is " + number);
				
				if (primeCount == 10001) {
					break;
				}
				
	
			}
			number ++;
		}

		System.out.println("Result = " + number);
	}

	static boolean isPrime(long number) {
		for (long i = 2; i <= number / 2; i++) {
			if (number % i == 0) {
				return false;
			}
		}

		return true;
	}

}
