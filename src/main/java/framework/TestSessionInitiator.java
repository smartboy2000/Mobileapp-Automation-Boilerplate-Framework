package framework;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class TestSessionInitiator {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestSessionInitiator.class);
	private ConfigRegistry configRegistry;
	private WebDriver driver;

	/**
	 * This method is to initiate Web Driver
	 */
	public TestSessionInitiator() {
		LOGGER.info("Starting Test Session....");
		configRegistry = new ConfigRegistry();
		driver = configRegistry.getDriver();
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	public void quitDriverSession() {
		((AppiumDriver<MobileElement>) driver).closeApp();
		((AppiumDriver<MobileElement>) driver).quit();
	}

	public ConfigRegistry getConfigRegistry() {
		return configRegistry;
	}
}
