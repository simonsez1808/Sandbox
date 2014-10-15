import java.util.Arrays;

import uk.co.pp.Credentials;
import lotus.domino.*;

public class ReindexNotesDatabase extends NotesThread {

	// Note you must have the Notes binary folder specified in the
	public static void main(String argv[]) {

		try {

			Reindexer.reindexNotesApplication("NDCOLORADO", "paraoct13\\pp_patientcomments.nsf");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//NotesThread.stermThread();
		}
	}


}


