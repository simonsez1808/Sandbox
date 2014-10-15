import java.util.Vector;

import lotus.domino.Database;
import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.NotesThread;
import lotus.domino.Session;
import lotus.domino.View;
import uk.co.pp.Credentials;

class Reindexer {

	static Session session;

	static {
		try {
			NotesThread.sinitThread();
			session = NotesFactory.createSession((String) null, (String) null,
					Credentials.PASSWORD);
		} catch (NotesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static final void reindexNotesApplication(String applicationServer,
			String applicationName) throws Exception {

		try {
			Database db = session.getDatabase(applicationServer,
					applicationName, false);

			if (db == null) {
				System.out.println("Sorry. Couldn't find that application");
				return;
			}
			
			System.out.println("OK, loaded up database named " + db
					+ " on server " + db.getServer());

			@SuppressWarnings("unchecked")
			Vector<View> allViews = db.getViews();

			System.out.println("Found " + allViews.size()
					+ " views to re-index");

			for (int i = 0; i < allViews.size(); i++) {
				View view = allViews.get(i);

				System.out.println("Starting to index view " + i + "("
						+ view.getName() + ") of " + allViews.size());
				System.out.println("View has " + view.getEntryCount()
						+ " view entries.");

				view.refresh();

				System.out.println("Completed indexing view " + view.getName());

			}

			System.out.println("* * * Completed indexing database "
					+ applicationServer + "//" + applicationName);

		} catch (Exception e) {

		} finally {
			NotesThread.stermThread();
		}
	}
}