import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class Account {

	public static void main(String[] args)
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		// TODO Auto-generated method stub
		WebClient webClient = new WebClient();

		HtmlPage htmlPage = webClient
				.getPage("https://www2.firstdirect.com/1/2/balances");
		// System.out.println(htmlPage.asXml());
		System.out.println(htmlPage.getAnchors());
		HtmlTextInput input = htmlPage.getElementByName("userid");
		 input.setText(Credentials.getUserName());
		System.out.println("Attempting with " + Credentials.getUserName());
		
		for (HtmlAnchor htmlAnchor : htmlPage.getAnchors()) {

			if (htmlAnchor.getAttribute("class").startsWith("fdLogonLink_")) {
				System.out.println("Found");
				System.out.println("* * * Got first page * * *");
				htmlPage = htmlAnchor.click();
			}
			
		}
		
		// Now we're at the password/memorable word page
		String pageAsXml = htmlPage.asXml();
		Pattern pattern = Pattern.compile("document.write\\('<p>.*?>(.*?)<.*?>.*?>(.*?)<.*?>.*?>(.*?)<.*?characters");
		Matcher matcher = pattern.matcher(pageAsXml);
		
		List<String> keys = new ArrayList<String>();
		if (matcher.find()) {
			System.out.println("Groups found = " + matcher.groupCount());

			for (int i = 1; i <= matcher.groupCount(); i++) {
				keys.add(matcher.group(i));
			}
		}
		
	System.out.println("Letters required by site " + Credentials.getLetters(keys));
	
	// Site always asks for 3 characters from the password.
	// Now find the input fields where we're supposed to enter these values.
	
	System.out.println(htmlPage.asXml());
	List<HtmlElement> passwordCharacterElements = getPasswordElements(htmlPage);
	
		HtmlForm htmlForm = htmlPage.getForms().get(0);
		//HtmlSubmitInput button = htmlForm.getInputByName(name);
		//htmlPage = button.click();

	}
	
	private static List<HtmlElement> getPasswordElements(HtmlPage htmlPage) {
		List<HtmlElement> passwordElements = new ArrayList<HtmlElement>();
		return null;
		//htmlPage.
	}

}
