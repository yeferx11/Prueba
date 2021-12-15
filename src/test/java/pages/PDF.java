package pages;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import junit.framework.Assert;

public class PDF {
	
	WebDriver driver = null;
	
	@Before
	public void setUp() {

		driver = new ChromeDriver();
	}
	
	@Test
	public void verifyContentInPDf() {
		//specify the url of the pdf file
		String url ="https://www.banistmo.com/wps/wcm/connect/www.banistmo.com11237/32423bb1-895f-4008-b065-f67da5199b09/banistmo_tarifario_autos_correct_22marzo%2B%281%29.pdf?MOD=AJPERES&CVID=mq7MTAD";
		driver.get(url);
		try {
			String pdfContent = readPdfContent(url);
			Assert.assertTrue(pdfContent.contains("Tarifas de Préstamos de Autos"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	
	public static  String readPdfContent(String url) throws IOException {
		
		URL pdfUrl = new URL(url);
		InputStream in = pdfUrl.openStream();
		BufferedInputStream bf = new BufferedInputStream(in);
		PDDocument doc = PDDocument.load(bf);
		int numberOfPages = getPageCount(doc);
		System.out.println("The total number of pages "+numberOfPages);
		String content = new PDFTextStripper().getText(doc);
		doc.close();
	
	return content;
}
	
	public static int getPageCount(PDDocument doc) {
		int pageCount = doc.getNumberOfPages();
		return pageCount;
		
	}

}