package euler;

/**
 * A palindromic number reads the same both ways. The largest palindrome made
 * from the product of two 2-digit numbers is 9009 = 91 * 99. Find the largest
 * palindrome made from the product of two 3-digit numbers
 */

public class Problem0004 {

	static long highestPalindrome = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (long i = 100; i < 999; i++) {
			for (long j = 100; j < 1000; j++) {
				long k = i * j;
				if (isPalindrome(k)) {
					if (k > highestPalindrome) {
						highestPalindrome = k;
					}
				}
			}
		}
		
		System.out.println("Highest palindrome = " + highestPalindrome);
		
	}

	static boolean isPalindrome(Long number) {
		return isPalindrome(number.toString());
	}

	static boolean isPalindrome(String number) {
		// System.out.println(Math.floor(number.length() / 2));
		for (int i = 0; i < Math.floor(number.length() / 2); i++) {
			if (number.charAt(i) != number.charAt(number.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}

}
