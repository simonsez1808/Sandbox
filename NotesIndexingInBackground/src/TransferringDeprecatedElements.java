import uk.co.pp.Credentials;
import lotus.domino.Database;
import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.NotesThread;
import lotus.domino.Session;
import lotus.domino.NoteCollection;

public class TransferringDeprecatedElements {

	public static void main(String[] args) throws NotesException {
		// TODO Auto-generated method stub
		NotesThread.sinitThread();

		Database db;
		Session session = NotesFactory.createSession((String) null, (String) null,
				Credentials.PASSWORD);
		db = session.getDatabase("NDGEMINI", "2009SYSTEM1\\pp_admin.nsf");
		NoteCollection nc = db.createNoteCollection(false);
		nc.selectAllDesignElements(true);
		System.out.println("Found " + nc.getCount() + " elements.");
		System.out.println("Done");
	}

}
