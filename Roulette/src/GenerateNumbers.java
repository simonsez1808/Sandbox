import java.util.Arrays;
import java.util.Random;

import static java.lang.System.out;

public class GenerateNumbers {

	static Random random = new Random();
	static final int NUMBER_OF_SLOTS = 38;
	static final int REPEAT_LIMIT = 2;
	static String[] buffer = new String[REPEAT_LIMIT];

	public static void main(String[] args) {
		Arrays.fill(buffer, "");
		for (int i = 0; i < 5; i++) {
			out.println(getRouletteNumber());
		}

	}

	static String getRouletteNumber() {
		final String[] numbers = { "0", "00", "1", "2", "3", "4", "5", "6",
				"7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
				"28", "29", "30", "31", "32", "33", "34", "35", "36" };
		String newNumber;

		do {
			newNumber = numbers[random.nextInt(NUMBER_OF_SLOTS)];
		} while (hasExceededRepeatLimit(newNumber));

		return newNumber;
	}

	static boolean hasExceededRepeatLimit(String number) {

		out.println(Arrays.toString(buffer));

		if (buffer[0].equals(number) && buffer[1].equals(number)) {
			out.println("Ooops");
			return true;
		}

		out.println("Good");
		for (int i = 0; i < buffer.length - 1; i++) {
			buffer[i] = buffer[i + 1];
		}
		buffer[buffer.length - 1] = number;
		return false;
	}

}

class Patterniser {

	Patterniser() {
	}
}
