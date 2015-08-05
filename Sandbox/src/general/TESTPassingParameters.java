package general;

public class TESTPassingParameters {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuffer calling = new StringBuffer("fred");
		System.out.println(calling);
		method1(calling);
		System.out.println(calling);
	}

	public static void method1(StringBuffer parameter) {
		parameter.append("XXX");
	}

}
