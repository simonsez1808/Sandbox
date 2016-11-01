import lotus.domino.Database;
import lotus.domino.Document;
import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.Session;

public class PPUtils {

	static Database thisDb;
	static Session session;

	static {
		try {
			session = NotesFactory.createSession((String) null, (String) null, Authentication.getInfo());
			thisDb = session.getCurrentDatabase();

		} catch (NotesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param sendTo
	 * @param subject
	 * @param body
	 * @throws NotesException
	 */
	public static void sendSimpleEmail(String sendTo, String subject, String body) throws NotesException {
		Document mailDoc = thisDb.createDocument();
		mailDoc.replaceItemValue("SendTo", sendTo.split(","));
		mailDoc.replaceItemValue("Subject", subject);
		mailDoc.replaceItemValue("body", body);
		mailDoc.send();
	}
}
