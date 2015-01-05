import java.io.IOException;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class VOSA {
	
	public static final String VOSA_URL = "http://www.tan.gov.uk/tanen/vosa_anonymousoperatorsearchresults_new.asp?radType=HGV&radUseMe=Town&txtCriteria=birkenhead";
public static void getVOSA() throws IOException {
		
		Document doc;
		
	// need http protocol
		Connection connection = Jsoup.connect(VOSA_URL);
		connection.timeout(30000);
			doc = connection.get();
	
	 
			// get all links
			Elements links = doc.select("a.top_link");
			for (Element link : links) {
	 
				// get the value from href attribute
				System.out.println("\nlink : " + link.attr("href"));
				System.out.println("text : " + link.text());
	 
			}
}
}