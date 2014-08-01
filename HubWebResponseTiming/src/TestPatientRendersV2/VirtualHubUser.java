package TestPatientRendersV2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import credentials.Credentials;

class VirtualHubUser extends Thread{
	
	private final String OUTPUT_FILE_PATH = System
			.getProperty("user.home") + "/desktop/TestPatientRenderss.dat";
	private final boolean APPEND_TO_FILE = true;
	final Calendar testDate = Calendar.getInstance();
	final WebClient webClient = new WebClient();
	FileWriter output;
	BufferedWriter writer;
	long threadNumber;

	public VirtualHubUser(long threadNumber) throws IOException {
		output = new FileWriter(OUTPUT_FILE_PATH, APPEND_TO_FILE);
		this.threadNumber = threadNumber;
	}

	private final void outputToConsole(String text) {
		System.out.println("[" + threadNumber + "] " + text);
	}
	
	public void run() {
		outputToConsole("Web client timeout is set to "
				+ webClient.getOptions().getTimeout());
		webClient.getOptions().setJavaScriptEnabled(false);

		BufferedWriter writer = new BufferedWriter(output);
		
	
		try {
			outputToConsole("Loading login page");
			HtmlPage page = webClient
					.getPage("https://www.practiceplan.co.uk/web.nsf/wqo_page_login?openagent");
			outputToConsole("Login page loaded, now supplying credentials");
			HtmlForm form = page.getFormByName("frm_login");
			HtmlTextInput userName = form.getInputByName("Username");
			HtmlPasswordInput password = form.getInputByName("Password");

			userName.setText(Credentials.userName);
			password.setText(Credentials.password);

			HtmlButton newButton = (HtmlButton) page.createElement("button");
			newButton.setAttribute("type", "submit");

			// append the button to the form
			form.appendChild(newButton);

			outputToConsole("Clicking login button. This should load the password change request page.");
			HtmlPage page2 = newButton.click();
			outputToConsole("Login completed. Password change request page has been loaded.");

			outputToConsole("Page 2 response code: "
					+ page2.getWebResponse().getStatusCode());
			// TEST 1 STARTs, test 3 start
			long test1Start = System.nanoTime();
			long test3Start = System.nanoTime();

			// This is the "All Patients" view for the current dentist. Note
			// that we don't get the patients here because
			// it's an ajax call and we're not running js
			HtmlPage page3 = webClient
					.getPage("https://www.practiceplan.co.uk/web.nsf/wqo_page?openagent&id=285DC36A46B2843680257BF7002ED6F2");

			outputToConsole("Page 3 (All patients page) response code "
					+ page3.getWebResponse().getStatusCode());
			// outputToConsole(page3.getWebResponse().getContentAsString());
			outputToConsole("Test 1 (Load view render for dentist) took "
					+ Utils.getElapsedTimeInSecs(test1Start) + " secs");
			writer.write(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
					.format(testDate.getTime())
					+ "|Test1|"
					+ Utils.getElapsedTimeInSecs(test1Start));
			writer.newLine();
			// The Ajax call info is on the page, so we can parse it out with
			// rexexp
			// outputToConsole("******* PAGE 3 ********\n\n"
			// + page3.getWebResponse().getContentAsString());
			Pattern pattern = Pattern.compile("sAjaxSource\": \'(.*)\'");

			Matcher matcher = pattern.matcher(page3.getWebResponse()
					.getContentAsString());

			// Now we have the Ajax URL for the JSON for the entire data table
			// that shows individial patints and their plans
			TextPage page4 = null;

			if (matcher.find()) {
				outputToConsole(matcher.group(1));
				outputToConsole("Test 2 (get view render window data) is starting ");
				long test2Start = System.nanoTime();
				page4 = webClient.getPage(matcher.group(1));
				//outputToConsole(page4.getWebResponse().getContentAsString());
				outputToConsole("Test 2 (get view render window data) took "
						+ Utils.getElapsedTimeInSecs(test2Start) + " secs");
				writer.write(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
						.format(testDate.getTime())
						+ "|Test2|"
						+ Utils.getElapsedTimeInSecs(test2Start));
				writer.newLine();
				outputToConsole("Test 3 (test 1 + test2 ) took "
						+ Utils.getElapsedTimeInSecs(test3Start) + " secs");
				writer.write(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
						.format(testDate.getTime())
						+ "|Test3|"
						+ Utils.getElapsedTimeInSecs(test3Start));
				writer.newLine();
			} else {
				outputToConsole("No match found.");
			}

			pattern = Pattern.compile("\\[\"(.*?)\".*?(/web.nsf.*?)\"\\]");

			matcher = pattern.matcher(page4.getWebResponse()
					.getContentAsString());

			while (matcher.find()) {
				// outputToConsole(matcher.group(2));
				String url = "https://www.practicePlan.co.uk"
						+ matcher.group(2);
				outputToConsole("Test4 starting - retrieving a patient's details - patient name: "
								+ matcher.group(1));
				long test4Start = System.nanoTime();
				HtmlPage page5 = webClient.getPage(url);
				outputToConsole("Test4 (getting a patient's details) took "
						+ Utils.getElapsedTimeInSecs(test4Start) + " secs");
				writer.write(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
						.format(testDate.getTime())
						+ "|Test4|"
						+ Utils.getElapsedTimeInSecs(test4Start));
				writer.newLine();
			}

			writer.flush();
			writer.close();

			outputToConsole("Pausing for " + Constants.TIME_TO_WAIT_BETWEEN_PATIENT_RECORD_RETRIEVAL_SECS);
			Thread.sleep(Constants.TIME_TO_WAIT_BETWEEN_PATIENT_RECORD_RETRIEVAL_SECS * 1000);
			
		} catch (org.apache.http.conn.HttpHostConnectException e) {
			outputToConsole("Connection timed out - " + e.getMessage());
		} catch (ConnectException e) {
			outputToConsole("Connection timed out - " + e.getMessage());
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}