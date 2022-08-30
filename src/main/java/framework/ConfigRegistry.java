package framework;



import framework.qe.factories.IDriverConnectionFactory;
import framework.qe.factories.impl.AndroidAppFactory;
import framework.qe.factories.impl.IosFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigRegistry {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigRegistry.class);

	private final String TEST_PLATFORM = "test.platform";

	public enum TestPlatform {
		androidapp, iOSapp
	}

	IDriverConnectionFactory connectionFactory;

	TestPlatform testPlatform = null;

	public ConfigRegistry() {

		testPlatform = TestPlatform
				.valueOf(System.getProperty(TEST_PLATFORM) != null ? System.getProperty(TEST_PLATFORM)
						: TestPlatform.androidapp.toString());
	}

	/**
	 * This method is responsible to return a web driver instance
	 * 
	 * @param
	 * @return WebDriver
	 */
	public WebDriver getDriver() {
		LOGGER.info("Registering Web driver ...");
		connectionFactory = getDriverConnectionFactory();
		return connectionFactory.getDriver();
	}

	public IDriverConnectionFactory getDriverConnectionFactory() {
		switch (testPlatform) {
		case androidapp:
			connectionFactory = new AndroidAppFactory();
			break;
		case iOSapp:
			connectionFactory = new IosFactory();
			break;
		}
		return connectionFactory;
	}

	public TestPlatform getTestPlatform() {
		return testPlatform;
	}

}
