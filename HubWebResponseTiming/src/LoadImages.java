import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class LoadImages {

	private static final boolean APPEND_TO_FILE = true;

	public static void main(String[] args)

	throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		long start = System.nanoTime();
		// TODO Auto-generated method stub
		final WebClient webClient = new WebClient();
		System.out.println("Web client timeout is set to "
				+ webClient.getOptions().getTimeout());
		webClient.getOptions().setJavaScriptEnabled(false);
		System.out.println("Loading main page");
		HtmlPage page0 = webClient
				.getPage("https://www.practiceplan.co.uk/home");
		System.out.println("Page loaded");
		DomNodeList dml = page0.getElementsByTagName("img");
		System.out.println(dml.getLength());

		Iterator<HtmlImage> it = dml.iterator();

		while (it.hasNext()) {
			HtmlImage image = it.next();
			System.out.println("Source: " + image.getAttribute("src"));
			BufferedImage loadedImage = ImageIO.read(new URL(
					"https://www.practiceplan.co.uk/web.nsf/"
							+ image.getAttribute("src")));
		}

		System.out.println("Completed " + (double) (System.nanoTime() - start)
				/ 1000000000);

		FileWriter output = new FileWriter(
				"c:/users/simonb/desktop/testwebtimer.txt", APPEND_TO_FILE);
		BufferedWriter writer = new BufferedWriter(output);
		Calendar cal = Calendar.getInstance();
		writer.write(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(cal
				.getTime())
				+ "|"
				+ String.valueOf((double) (System.nanoTime() - start) / 1000000000));
		writer.newLine();
		writer.flush();
		output.close();

	}

}
