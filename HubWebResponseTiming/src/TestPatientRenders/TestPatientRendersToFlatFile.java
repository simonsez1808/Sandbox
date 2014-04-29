package TestPatientRenders;

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

public class TestPatientRendersToFlatFile {

	private static final long TIME_TO_WAIT_BETWEEN_PATIENT_RECORD_RETRIEVAL_SECS = 10;
	private static final long NUMBER_OF_ITERATIONS = 40;
	private static final String OUTPUT_FILE_PATH = System
			.getProperty("user.home") + "/desktop/TestPatientRenderss.dat";
	private static final boolean APPEND_TO_FILE = true;

	public static void main(String[] args) throws InterruptedException,
			IOException {

		for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
			System.out.println("Starting test number " + (i + 1) + " of " + NUMBER_OF_ITERATIONS);
			runTest();
		}

		System.out.println("* * * * * * * * TEST RUN COMPLETED * * * * * * * * ");
	}

	private static void runTest() throws IOException, InterruptedException {
		
		final Calendar testDate = Calendar.getInstance();
		final WebClient webClient = new WebClient();
		System.out.println("Web client timeout is set to "
				+ webClient.getOptions().getTimeout());
		webClient.getOptions().setJavaScriptEnabled(false);
		FileWriter output = new FileWriter(OUTPUT_FILE_PATH, APPEND_TO_FILE);
		BufferedWriter writer = new BufferedWriter(output);

		try {
			System.out.println("Loading login page");
			HtmlPage page = webClient
					.getPage("https://www.practiceplan.co.uk/web.nsf/wqo_page_login?openagent");
			System.out.println("Login page loaded, now supplying credentials");
			HtmlForm form = page.getFormByName("frm_login");
			HtmlTextInput userName = form.getInputByName("Username");
			HtmlPasswordInput password = form.getInputByName("Password");

			userName.setText(Credentials.userName);
			password.setText(Credentials.password);

			HtmlButton newButton = (HtmlButton) page.createElement("button");
			newButton.setAttribute("type", "submit");

			// append the button to the form
			form.appendChild(newButton);

			System.out
					.println("Clicking login button. This should load the password change request page.");
			HtmlPage page2 = newButton.click();
			System.out
					.println("Login completed. Password change request page has been loaded.");

			System.out.println("Page 2 response code: "
					+ page2.getWebResponse().getStatusCode());
			// TEST 1 STARTs, test 3 start
			long test1Start = System.nanoTime();
			long test3Start = System.nanoTime();

			// This is the "All Patients" view for the current dentist. Note
			// that we don't get the patients here because
			// it's an ajax call and we're not running js
			HtmlPage page3 = webClient
					.getPage("https://www.practiceplan.co.uk/web.nsf/wqo_page?openagent&id=285DC36A46B2843680257BF7002ED6F2");

			System.out.println("Page 3 (All patients page) response code "
					+ page3.getWebResponse().getStatusCode());
			// System.out.println(page3.getWebResponse().getContentAsString());
			System.out.println("Test 1 (Load view render for dentist) took "
					+ getElapsedTimeInSecs(test1Start));
			writer.write(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
					.format(testDate.getTime())
					+ "|Test1|"
					+ getElapsedTimeInSecs(test1Start));
			writer.newLine();
			// The Ajax call info is on the page, so we can parse it out with
			// rexexp
			// System.out.println("******* PAGE 3 ********\n\n"
			// + page3.getWebResponse().getContentAsString());
			Pattern pattern = Pattern.compile("sAjaxSource\": \'(.*)\'");

			Matcher matcher = pattern.matcher(page3.getWebResponse()
					.getContentAsString());

			// Now we have the Ajax URL for the JSON for the entire data table
			// that shows individial patints and their plans
			TextPage page4 = null;

			if (matcher.find()) {
				System.out.println(matcher.group(1));
				System.out
						.println("Test 2 (get view render window data) is starting ");
				long test2Start = System.nanoTime();
				page4 = webClient.getPage(matcher.group(1));
				System.out.println(page4.getWebResponse().getContentAsString());
				System.out.println("Test 2 (get view render window data) took "
						+ getElapsedTimeInSecs(test2Start));
				writer.write(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
						.format(testDate.getTime())
						+ "|Test2|"
						+ getElapsedTimeInSecs(test2Start));
				writer.newLine();
				System.out.println("Test 3 (test 1 + test2 ) took "
						+ getElapsedTimeInSecs(test3Start));
				writer.write(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
						.format(testDate.getTime())
						+ "|Test3|"
						+ getElapsedTimeInSecs(test3Start));
				writer.newLine();
			} else {
				System.out.println("No match found.");
			}

			pattern = Pattern.compile("\\[\"(.*?)\".*?(/web.nsf.*?)\"\\]");

			matcher = pattern.matcher(page4.getWebResponse()
					.getContentAsString());

			while (matcher.find()) {
				// System.out.println(matcher.group(2));
				String url = "https://www.practicePlan.co.uk"
						+ matcher.group(2);
				System.out
						.println("Test4 starting - retrieving a patient's details - patient name: "
								+ matcher.group(1));
				long test4Start = System.nanoTime();
				HtmlPage page5 = webClient.getPage(url);
				System.out.println("Test4 (getting a patient's details) took "
						+ getElapsedTimeInSecs(test4Start));
				writer.write(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
						.format(testDate.getTime())
						+ "|Test4|"
						+ getElapsedTimeInSecs(test4Start));
				writer.newLine();
			// System.out.println(page5.getWebResponse().getContentAsString());
			}

			writer.flush();
			writer.close();

			System.out.println("Pausing for " + TIME_TO_WAIT_BETWEEN_PATIENT_RECORD_RETRIEVAL_SECS);
			Thread.sleep(TIME_TO_WAIT_BETWEEN_PATIENT_RECORD_RETRIEVAL_SECS * 1000);
			

		} catch (ConnectException e) {
			System.out.println("Connection timed out - " + e.getMessage());
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static double getElapsedTimeInSecs(long startTime) {
		return new Double(System.nanoTime() - startTime) / 1000000000;
	}

}
