import java.util.Arrays;
import java.util.Vector;

import lotus.domino.*;

public class PatientsOnColorado extends NotesThread {

	static Session session;
	static String applicationNames[] = { "NDCOLORADO||PARAOCT13\\" };
	static boolean isQuiet;

	// Note you must have the Notes binary folder specified in the
	public static void main(String argv[]) {

		try {

			if (Arrays.asList(argv).contains("-help")) {
				System.out.println("Command line arguments: ");
				System.out.println("-help: 	This print");
				System.out.println("-quiet: Do not log progress to console");
			}
			
			isQuiet = (Arrays.asList(argv).contains("-quiet"));

			NotesThread.sinitThread();

			// Get hold of Colorado
			System.out.println("Creating session with automatic logon.");
			session = NotesFactory.createSession((String) null, (String) null,
					Credentials.PASSWORD);
			System.out.println("stage 2");
			System.out.println(session.getCommonUserName());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			NotesThread.stermThread();
		}
	}

	public static final void reindexNotesApplication(String applicationServer,
			String applicationName) throws Exception {

		Database db = session.getDatabase(applicationServer, applicationName,
				false);

		if (!isQuiet) {
			System.out.println("OK, loaded up database named " + db
					+ " on server " + db.getServer());
		}

		@SuppressWarnings("unchecked")
		Vector<View> allViews = db.getViews();

		if (!isQuiet) {
			System.out.println("Found " + allViews.size()
					+ " views to re-index");
		}

		for (int i = 0; i < allViews.size(); i++) {
			View view = allViews.get(i);

			if (isQuiet) {
				System.out.println("Starting to index view " + i + "("
						+ view.getName() + ") of " + allViews.size());
				System.out.println("View has " + view.getEntryCount()
						+ " view entries.");
			}

			view.refresh();
			
			if (!isQuiet) {
				System.out.println("Completed indexing view " + view.getName());
			}
			
		}

	}
}
