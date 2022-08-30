package listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.TestSession;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * This class is to take screenshot in case of failures
 * It saves screenshot in encoded format
 *
 */
public class ScreenshotListener extends TestListenerAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScreenshotListener.class);
	private static final String SCREENSHOT_PATH = "target" + File.separator + "screenshots";

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		LOGGER.info(SCREENSHOT_PATH + File.separator + iTestResult.getName() + ".png");
		String imageAsString = ((TakesScreenshot) TestSession.driver).getScreenshotAs(OutputType.BASE64);
		Reporter.log(imageAsString);
		saveScreenshotInFolder(imageAsString, iTestResult);
	}
	
	private void saveScreenshotInFolder(String imageAsString, ITestResult iTestResult) {
		try {
			byte[] image = Base64.getDecoder().decode(imageAsString);
			File screenshotFolder = new File(SCREENSHOT_PATH);
			if (!screenshotFolder.exists()) {
				new File(SCREENSHOT_PATH).mkdir();
			}
			FileOutputStream outputStream = new FileOutputStream(
					SCREENSHOT_PATH + File.separator + iTestResult.getName() + ".png");
			outputStream.write(image);
			outputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
