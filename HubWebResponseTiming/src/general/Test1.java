package general;
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

public class Test1 {

	private static final long TIME_TO_WAIT_BETWEEN_PATIENT_RECORD_RETRIEVAL_MS = 0;
	private static final String DRIVER_NAME = "org.sqlite.JDBC";
	private static final String DB_NAME = "webtesting.db";
	private static final String TABLE_NAME = "testdata";
	private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_NAME + "(TestDate TEXT, TestName TEXT, TestTime NUM)";
	private static final String DROP_TABLE = "DROP TABLE " + TABLE_NAME;
	private static final String CLEAR_TABLE = "DELETE FROM " + TABLE_NAME;

	public static void main(String[] args) throws InterruptedException,
			ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// register the driver

		Class.forName(DRIVER_NAME);

		Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
		Calendar testDate = Calendar.getInstance();
		Statement stmt = conn.createStatement();

		System.out.println(CREATE_TABLE);

		stmt.execute(CREATE_TABLE);
		// stmt.execute(CLEAR_TABLE);

		final WebClient webClient = new WebClient();
		System.out.println("Web client timeout is set to "
				+ webClient.getOptions().getTimeout());
		webClient.getOptions().setJavaScriptEnabled(false);

		try {
			Calendar start = Calendar.getInstance();
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

			// append the button to the formx
			form.appendChild(newButton);

			System.out.println("Clicking login button");
			HtmlPage page2 = newButton.click();
			System.out.println("Login completed. Now loading main page");
			// Logged in, carry on
			// System.out.println(page2.getWebResponse().getContentAsString());

			// TEST 1 STARTs, test 3 start
			long test1Start = System.nanoTime();
			long test3Start = System.nanoTime();

			// This is the "All Patients" view for the current dentist. Note
			// that we don't get the patiens here because
			// it's an ajax call and we're not running js
			HtmlPage page3 = webClient
					.getPage("https://www.practiceplan.co.uk/web.nsf/wqo_page?openagent&id=285DC36A46B2843680257BF7002ED6F2");

			System.out.println("Page 3 response code "
					+ page3.getWebResponse().getStatusCode());
			// System.out.println(page3.getWebResponse().getContentAsString());
			System.out.println("Test 1 (Load view render for dentist) took "
					+ getElapsedTimeInSecs(test1Start));
			stmt.execute("INSERT INTO "
					+ TABLE_NAME
					+ " VALUES('"
					+ new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
							.format(testDate.getTime()) + "', 'Test1', "
					+ getElapsedTimeInSecs(test1Start) + ")");
			// The Ajax call info is on the page, so we can parse it out with
			// rexexp
			Pattern pattern = Pattern.compile("sAjaxSource\": \'(.*)\'");

			Matcher matcher = pattern.matcher(page3.getWebResponse()
					.getContentAsString());

			// Now we have the Ajax URL for the JSON for the entire data table
			// that shows individial patints and their plans
			TextPage page4 = null;
			if (matcher.find()) {
				System.out.println(matcher.group(1));

				System.out.println("Test 2 has starting");
				long test2Start = System.nanoTime();

				page4 = webClient.getPage(matcher.group(1));
				System.out.println(page4.getWebResponse().getContentAsString());
				System.out.println("Test 2 (get view rendr window data) took "
						+ getElapsedTimeInSecs(test2Start));
				stmt.execute("INSERT INTO "
						+ TABLE_NAME
						+ " VALUES('"
						+ new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
								.format(testDate.getTime()) + "', 'Test2', "
						+ getElapsedTimeInSecs(test2Start) + ")");
				System.out.println("Test 3 (test 1 + test3) took "
						+ getElapsedTimeInSecs(test3Start));
				stmt.execute("INSERT INTO "
						+ TABLE_NAME
						+ " VALUES('"
						+ new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
								.format(testDate.getTime()) + "', 'Test3', "
						+ getElapsedTimeInSecs(test3Start) + ")");

			} else {
				System.out.println("No match found.%n");
			}

			pattern = Pattern.compile("\\[\"(.*?)\".*?(/web.nsf.*?)\"\\]");
			matcher = pattern.matcher(page4.getWebResponse()
					.getContentAsString());

			while (matcher.find()) {
				System.out.println(matcher.group(2));
				String url = "https://www.practicePlan.co.uk"
						+ matcher.group(2);
				long itemOpenNanoSecs = System.nanoTime();
				System.out
						.println("Test4 starting - retrieving a patient's details");
				long test4Start = System.nanoTime();
				HtmlPage page5 = webClient.getPage(url);
				System.out.println("Test4 took "
						+ getElapsedTimeInSecs(test4Start));
				stmt.execute("INSERT INTO "
						+ TABLE_NAME
						+ " VALUES('"
						+ new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
								.format(testDate.getTime()) + "', 'Test4', "
						+ getElapsedTimeInSecs(test4Start) + ")");
				Thread.sleep(TIME_TO_WAIT_BETWEEN_PATIENT_RECORD_RETRIEVAL_MS);
				System.out.println(page5.getWebResponse().getContentAsString());
			}

			System.out.println("Test completed.");

			ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME
					+ " WHERE TESTNAME = 'Test1'");

			long count = 0;
			double totalTime = 0;
			double minimumTime = Double.MAX_VALUE;
			double maximumTime = Double.MIN_VALUE;
			while (rs.next()) {
				System.out.println("Date: " + rs.getString("TestDate"));
				System.out.println("Name: " + rs.getString("TestName"));
				System.out.println("Time: " + rs.getDouble("TestTime"));
				count++;
				totalTime += rs.getDouble("TestTime");

				if (rs.getDouble("TestTime") < maximumTime) {
					minimumTime = rs.getDouble("TestTime");
				}

				if (rs.getDouble("TestTime") > minimumTime) {
					minimumTime = rs.getDouble("TestTime");
				}

			}

			System.out.println("Test1 count: " + count + " Average: "
					+ totalTime / count);

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
