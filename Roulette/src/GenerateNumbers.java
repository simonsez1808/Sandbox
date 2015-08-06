// It's important that we make the changes as subtle as possible. Therefore, I've chosen to
// use a pattern-matching approach based on the colour. If you get a certain
// sequence of colours (and this can be as complicated as you like, then the
// patterniser will guarantee that the next colour is predictable.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

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
private static final long MAX_BUFFER_LENGTH = 128;
	Queue<Number> nudgedNumbers;
	Map<String, String> patterns;
	StringBuilder buffer;

	Patterniser() {
		patterns.put("RBRB", "B");
		patterns.put("BRBR", "R");
		nudgedNumbers = new LinkedList<Number>();
	}

	//if the last n characters of the buffer match, then create a new 
	// random and save the last one to spit out later
	Number process(Number number){
		
		// If the buffer's full, then take off the first char
		if (buffer.length() == MAX_BUFFER_LENGTH) {
			buffer.deleteCharAt(0);
		}
		
		// Save the colour found
		buffer.append(number.getColour());
		
		// Now see if we have a match with the tail of the buffer
		for (Map.Entry<String, String> x : patterns.entrySet()) {
			if (buffer.toString().endsWith(x.getKey())) {
				// Match. Add the number to the queue
				
			}
		}
		
	}
	void pop(){}
	void push(){}

}

class Number {
	String number;

	Number(String number) {
		this.number = number;
	}

	String getColour() {
		Integer number;
		number = Integer.parseInt(this.number);

		if (number == 0) {
			return "G";
		}

		if (number % 2 == 0) {
			return "B";
		}

		return "R";
	}
}
