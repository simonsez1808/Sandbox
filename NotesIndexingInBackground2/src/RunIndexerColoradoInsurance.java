import java.util.Vector;

import lotus.domino.Database;
import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.NotesThread;
import lotus.domino.Session;
import lotus.domino.View;

public class RunIndexerColoradoInsurance {

	public static void main(String[] args) throws NotesException {
		// TODO Auto-generated method stub
		
		NotesThread.sinitThread();

		Session session = NotesFactory.createSession((String) null,
				(String) null, "3edcvfr$");
		
		System.out.println(session.getEffectiveUserName());
		
		Database database = session.getDatabase("NDCOLORADO", "TRAINING_THREE\\pp_insurance.nsf");
		
		Indexer indexer = new Indexer();
		indexer.indexDb(database);
	}


}
