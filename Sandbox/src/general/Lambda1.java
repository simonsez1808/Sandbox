package general;

import java.util.Arrays;
import java.util.List;

public class Lambda1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] atp = {"Rafael Nadal", "Novak Djokovic", "Stanislas Wawrinka", "David Ferrer", "Roger Federer", "Andy Murray", "Tomas Berdych", "Juan Martin Del Potro"};
		List<String> players =  Arrays.asList(atp);
		       
		// Old looping
		for (String player : players) {
		     System.out.print(player + "; ");
		}
		       
		// Using lambda expression and functional operations
		System.out.println("***************************************************************");
		players.forEach((player) -> System.out.println(player + " **fred"));
		 
		// Using double colon operator in Java 8
		players.forEach(System.out::println);
	}

}
