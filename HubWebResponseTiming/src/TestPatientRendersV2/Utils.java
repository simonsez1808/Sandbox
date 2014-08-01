package TestPatientRendersV2;

class Utils {
	
	static double getElapsedTimeInSecs(long startTime) {
		return new Double(System.nanoTime() - startTime) / 1000000000;
	}
	
}