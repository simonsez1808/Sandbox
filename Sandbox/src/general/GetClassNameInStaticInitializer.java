package general;

public class GetClassNameInStaticInitializer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Object myClass = new Object() {};
		System.out.println(myClass.getClass().getEnclosingClass().getCanonicalName());
	}

}
