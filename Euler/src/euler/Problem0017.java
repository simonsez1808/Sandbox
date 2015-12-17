package euler;

/*If the numbers 1 to 5 are written out in words: one, two, three, four, five,
 * then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
 * If all the numbers from 1 to 1000 (one thousand) inclusive were written out
 * in words, how many letters would be used?
 * 
 *  NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two
 *  contains 23 letters and 115 (one hundred and fifteen) contains 20 letters.
 *  The use of "and" when writing out numbers is in compliance with British usage.
 */

public class Problem0017 {

	static String[] lowNumbers = { "", "one", "two", "three", "four", "five",
			"six", "seven", "eight", "nine", "ten", "eleven", "twelve",
			"thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
			"eighteen", "nineteen", "twenty" };

	static String[] hiNumbers = { "", "ten", "twenty", "thirty", "forty",
			"fifty", "sixty", "seventy", "eighty", "ninety" };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
int totalLetters = 0;
		for (int i = 1; i < 1001; i++) {
			String s = getWords(i);
			System.out.println(i + ": " + s);
			totalLetters += s.length();
		}
		
		System.out.println(totalLetters);

	}

	static String getWords(int number) {

		final String SEPARATOR = "";
		
		StringBuilder sb = new StringBuilder();

		int thousands = (int) Math.floor(number / 1000);
		if (thousands > 0) {
			sb.append(lowNumbers[thousands] + SEPARATOR + "thousand");
		}

		number = number - (1000 * thousands);

		int hundreds = (int) Math.floor(number / 100);

		if (hundreds > 0) {
			sb.append(lowNumbers[hundreds] + SEPARATOR + "hundred");
		}

		number = number - (100 * hundreds);
		
		if (number > 0 && number < 21) {
			sb.append((hundreds > 0 ? SEPARATOR + "and" + SEPARATOR : "") + lowNumbers[number]);
			number = 0;
		}
		
		int tens = (int) Math.floor(number / 10);
		
		if (tens > 0) {
			sb.append((hundreds > 0 ? SEPARATOR + "and" + SEPARATOR : "") + hiNumbers[tens]);
		}
		
		number = number - (10 * tens);
		
		if (number < 21) {
			sb.append((tens > 0 ? SEPARATOR : "") + lowNumbers[number]);
		}
		
		return sb.toString();
	}

}
