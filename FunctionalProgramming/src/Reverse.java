
public class Reverse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(reverse("fred"));

	}
	
	static String reverse(String arg) {
		System.out.println("Arg: " + arg);
		
	    if(arg.length() == 0) {
	        return arg;
	    }
	    else {
	    	System.out.println("Returning");
	        return reverse(arg.substring(1, arg.length())) + arg.substring(0, 1);
	    }
	    
	}

}
