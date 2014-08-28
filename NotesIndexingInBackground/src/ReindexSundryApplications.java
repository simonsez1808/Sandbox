import java.util.Arrays;
import java.util.Vector;

import lotus.domino.Database;
import lotus.domino.NotesFactory;
import lotus.domino.NotesThread;
import lotus.domino.Session;
import lotus.domino.View;
import uk.co.pp.Credentials;

public class ReindexSundryApplications extends NotesThread {

	//
	
	static Session session;
	static final String LOCAL_SERVER = "";

	static String applicationNames[][] = {

			// Auddis
			//{ "NDCOLORADO", "PARAOCT13\\pp_auddis.nsf" },
			//{ LOCAL_SERVER, "PARAOCT13\\pp_auddis.nsf" },
			// PP_Admin
			//{ "NDGEMINI", "2009System1\\pp_admin.nsf" },
			//{ "NDDSVRM", "2009System1\\pp_admin.nsf" },
			// Contacts
			//{ LOCAL_SERVER, "PARAJAN14\\pp_contacts.nsf" },
			//{ "NDCOLORADO", "PARAJAN14\\pp_contacts.nsf" },
			//{ LOCAL_SERVER, "PARAOCT13\\pp_contacts.nsf" },
			//{ "NDCOLORADO", "PARAOCT13\\pp_contacts.nsf" },
			//{ "NDGEMINI", "2009SYSTEM1\\pp_contacts.nsf" },
			//{ "NDDSVRM", "2009SYSTEM1\\pp_contacts.nsf" },
			// Patients
			//{ LOCAL_SERVER, "PARAJAN14\\pp_patients.nsf" },
			//{ "NDCOLORADO", "PARAJAN14\\pp_patients.nsf" },
			//{ LOCAL_SERVER, "PARAOCT13\\pp_patients.nsf" },
			//{ "NDCOLORADO", "PARAOCT13\\pp_patients.nsf" },
			//{ "NDGEMINI", "2009SYSTEM1\\pp_patients.nsf" },
			//{ "NDDSVRM", "2009SYSTEM1\\pp_patients.nsf" },
		{ "NDGemini", "2009SYSTEM1\\Medenta_Contacts.nsf"},
		};

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

			System.out.println("Creating session with automatic logon.");
			session = NotesFactory.createSession((String) null, (String) null,
					Credentials.PASSWORD);
			System.out.println("stage 2");
			System.out.println(session.getCommonUserName());

			for (int i = 0; i < applicationNames.length; i++) {

				try {
					reindexNotesApplication(applicationNames[i][0],
							applicationNames[i][1]);
				} catch (Exception e) {
					System.out.println("ERROR: " + e.getMessage());
				}

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
				System.out.println("Starting to index view # " + (i + 1)
						+ " of " + allViews.size() + " named " + "\""
						+ view.getName() + "\"");

				long startTimeMillisecs = System.currentTimeMillis();
				view.refresh();

				if (!isQuiet) {
					System.out.println("Completed indexing view "
							+ view.getName()
							+ ", time taken = "
							+ Utils.getDurationAsText(startTimeMillisecs,
									System.currentTimeMillis()));
				}

			}

		}
	}
}
