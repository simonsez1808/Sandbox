import java.util.Date;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import lotus.domino.*;


public class Indexer {

	public Indexer() {
	}

	public void indexDb(Database database) throws NotesException {

		System.out.println(database.getFilePath());
		System.out.println(database.isOpen());

		// get all the views
		@SuppressWarnings("unchecked")
		Vector<View> views = database.getViews();

		int viewsProcessedCount = 0;

		for (View view : views) {

			System.out.println("Refreshing view " + view.getName() + "(" +
					view.getEntryCount() + " entries), view " + viewsProcessedCount + " of " + views.size());
			Date startTime = new Date();
			view.refresh();
			Date endTime = new Date();
			Long timeTaken = TimeUnit.MILLISECONDS.toSeconds(endTime.getTime() - startTime.getTime());
			System.out.println("Finished Refreshing view " + view.getName() + ", took " +
					timeTaken + " seconds.");
			viewsProcessedCount++;
		}
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Database " + database.getServer() + "//" + database.getFilePath() + " all Done. Total views processed: " + viewsProcessedCount);
		System.out.println("----------------------------------------------------------------------");
	}
}
