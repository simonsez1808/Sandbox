import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import uk.co.pp.Credentials;
import lotus.domino.Database;
import lotus.domino.Document;
import lotus.domino.Item;
import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.NotesThread;
import lotus.domino.Session;
import lotus.domino.NoteCollection;
import lotus.domino.View;

/**
 * @author simonb
 * 
 *         The main reason we want to do this is because we want to move
 *         deprecated elements into the
 */
public class TransferringDeprecatedElements {

	public static void main(String[] args) throws NotesException {
		// TODO Auto-generated method stub
		NotesThread.sinitThread();

		Database db;
		Session session = NotesFactory.createSession((String) null,
				(String) null, Credentials.PASSWORD);
		db = session
				.getDatabase("NDGEMINI", "2009SYSTEM1\\pp_admin.nsf", false);
		NoteCollection nc = db.createNoteCollection(false);
		nc.selectAllDesignElements(true);
		nc.buildCollection();
		System.out.println("Found " + nc.getCount() + " elements.");
		System.out.println("Done");

		String noteId = nc.getFirstNoteID();

		while (noteId.length() > 0) {
			Document doc = db.getDocumentByID(noteId);
			if (doc.getItemValueString("$TITLE").contains("_DPRC_")) {
				System.out.println(doc.getItemValueString("$TITLE"));
			}
			noteId = nc.getNextNoteID(noteId);
		}
	}

	@SuppressWarnings("unchecked")
	static String displayItems(Document doc) throws NotesException {
		Map<String, String> map = new HashMap<String, String>();

		for (Item i : (Vector<Item>) doc.getItems()) {
			map.put(i.getName(), i.getValueString());
		}

		return map.toString();
	}
}
