import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.NotesThread;
import lotus.domino.Session;

public class RunIndexer {

	public static void main(String[] args) throws NotesException {
		// TODO Auto-generated method stub
		
		NotesThread.sinitThread();
		Session session;
		session = NotesFactory.createSession();
		
		System.out.println(session.getEffectiveUserName());
		System.out.println("Done");
		
	}

}
