public class Utils {

	public static final String getDurationAsText(long startTimeMillisecs,
			long endTimeMillisecs) {
		long durationSecs = (endTimeMillisecs - startTimeMillisecs) / 1000;
		long minutes = durationSecs / 60;
		long secs = durationSecs % 60;
		
		return (minutes > 0 ? minutes + " min" + (minutes > 1 ? "s " : " ")
				: "") + secs + " seconds ";
	}
}
