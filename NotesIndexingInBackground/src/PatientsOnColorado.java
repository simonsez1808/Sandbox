import java.util.Vector;

import lotus.domino.*;

public class PatientsOnColorado extends NotesThread {

	// Note you must have the Notes binary folder specified in the
	public static void main(String argv[]) {

		try {
			System.out.println("Starting...");
			NotesThread.sinitThread();
			System.out.println("Next");

			// Get hold of Colorado
			System.out.println("Creating session with automatic logon.");
			Session session = NotesFactory.createSession((String) null,
					(String) null, Credentials.PASSWORD);
			System.out.println("stage 2");
			System.out.println(session.getCommonUserName());

			Database db = session.getDatabase("NDCOLORADO",
					"PARAOCT13\\pp_patients.nsf", false);
			System.out.println("OK, loaded up database named " + db
					+ " on server " + db.getServer());
			Vector<View> allViews = db.getViews();
			System.out.println("Found " + allViews.size()
					+ " views to re-index");

			for (int i = 0; i < allViews.size(); i++) {
				View view = allViews.get(i);
				System.out.println("Starting to index view " + i + "("
						+ view.getName() + ") of " + allViews.size());
				view.refresh();
				System.out.println("Completed indexing view " + view.getName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			NotesThread.stermThread();
		}
	}
}
