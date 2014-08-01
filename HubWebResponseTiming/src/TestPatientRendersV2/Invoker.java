package TestPatientRendersV2;

import java.io.IOException;

public class Invoker {

	public static void main(String[] args) throws IOException {
		System.out.println("Starting testing . . .");
		
		for (int i = 0; i < 2; i++) {
			VirtualHubUser vhu = new VirtualHubUser(i);
			vhu.start();
		}

		System.out.println(". . . Completed");
	}

}
