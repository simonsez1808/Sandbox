package euler;

/**
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which
 * a2 + b2 = c2
 * 
 * For example, 32 + 42 = 9 + 16 = 25 = 52.
 * 
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000. Find
 * the product abc.
 */
public class Problem0009 {

	public static void main(String[] args) {
		
		for (long a = 1; a < 1000; a++) {
			
			for (long b = 1; b < 1000; b++) {
				Triangle triangle = new Triangle(a, b);
				
				if (triangle.isPythagorean()
						&& triangle.getSumOfSides() == 1000) {
					System.out.println("Found " + triangle.getDescription());
				}
				
			}
			
		}

	}

}

class Triangle {

	private long a;
	private long b;
	private double c;

	Triangle(Long a, Long b) {
		this.a = a;
		this.b = b;
		c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}

	boolean isPythagorean() {
		return Math.floor(c) == c;
	}

	double getSumOfSides() {
		return (double) (a + b + c);
	}

	String getDescription() {
		return a + " " + b + " " + c;
	}
}
