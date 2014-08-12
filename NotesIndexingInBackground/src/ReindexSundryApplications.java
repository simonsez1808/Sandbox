import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import uk.co.pp.Credentials;
import lotus.domino.*;

public class ReindexSundryApplications extends NotesThread {

	static Session session;
	static String applicationNames[][] = {
			{ "NDCOLORADO", "PARAOCT13\\pp_auddis.nsf" },
			{ "", "PARAOCT13\\pp_auddis.nsf" },
			{ "NDGEMINI", "2009System1\\pp_admin.nsf" },
			{ "", "PARAJAN14\\pp_contacts.nsf" },
			{ "NDCOLORADO", "PARAJAN14\\pp_contacts.nsf" },
			{ "", "PARAJAN14\\pp_patients.nsf" },
			{ "NDCOLORADO", "PARAJAN14\\pp_patients.nsf" },
			{ "", "PARAJAN14\\pp_contacts.nsf" },
			{ "NDCOLORADO", "PARAJAN14\\pp_contacts.nsf" } };

	static boolean isQuiet;

	// Note you must have the Notes binary folder specified in the build
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

			for (int i = 0; i < applicationNames.length; i++) {
				reindexNotesApplication(applicationNames[i][0],
						applicationNames[i][1]);
			}

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

			if (!isQuiet) {
				System.out.println("Starting to index view # " + (i + 1) + " of " + allViews.size() + " named "
						+ view.getName());
				// System.out.println("View has " + view.getEntryCount()
				// + " view entries.");

				long startIndex = System.currentTimeMillis();
				view.refresh();
				long endIndex = System.currentTimeMillis();

				long durationSecs = (endIndex - startIndex) / 1000;
				long minutes = durationSecs / 60;
				long secs = durationSecs % 60;
				
				if (!isQuiet) {
					System.out.println("Completed indexing view "
							+ view.getName() + ", time taken = " + minutes
							+ " mins " + secs + " seconds ");
				}

			}

		}
	}
}
