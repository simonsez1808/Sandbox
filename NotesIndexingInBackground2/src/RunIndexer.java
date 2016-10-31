import java.util.Vector;

import lotus.domino.Database;
import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.NotesThread;
import lotus.domino.Session;
import lotus.domino.View;

public class RunIndexer {

	public static void main(String[] args) throws NotesException {
		// TODO Auto-generated method stub

		NotesThread.sinitThread();

		Session session = NotesFactory.createSession((String) null,
				(String) null, "3edcvfr$");

		System.out.println("Effective user name: " + session.getEffectiveUserName());

		Database database = session.getDatabase("NDCOLORADO", "TRAINING_THREE\\pp_patients.nsf");

		refreshDatabase(database);

	}

	static long refreshDatabase(Database database) throws NotesException {

		if (!database.isOpen()) {
			System.out.println ("Unable to open database ");
			return 0;
		}
		
		System.out.println("Database is open");
		
		@SuppressWarnings("unchecked")
		Vector<View> views = database.getViews();
		
		System.out.println("Starting refresh of database " + database.getServer() + "//" + database.getFilePath());
		long viewsProcessedCount = 0;
		long failedRefreshCount = 0;

		for (View view : views) {
			viewsProcessedCount++;
			System.out.println("Refreshing view " + view.getName() + " (" +
			String.format("%,d", view.getEntryCount()) + " entries), view " + viewsProcessedCount
					+ " of " + String.format("%,d",views.size()));

			try {
				view.refresh();
			}
			catch (Exception e) {
				System.out.println("Unable to refresh view " + view.getName());
				failedRefreshCount++;
			}
			
		}

		System.out.println("Completed refresh of database " + database.getServer() + "//" + database.getFilePath());
		return failedRefreshCount;
	}


}
