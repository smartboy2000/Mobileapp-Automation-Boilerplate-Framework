package framework.qe.factories;

import org.openqa.selenium.WebDriver;

import java.util.Map;

public interface IDriverConnectionFactory {

	String TEST_MODE = "test.mode";
	String TEST_JSON_CAP = "test.jsonCap";

	enum TestMode {
		local, grid, cloud
	}

	default TestMode testMode() {
		return TestMode.valueOf(
				System.getProperty(TEST_MODE) != null ? System.getProperty(TEST_MODE) : TestMode.local.toString());
	}

	default WebDriver getDriver(Map<String, Object> configurationKeyDetails) {
		return null;
	}

	default WebDriver getDriver() {
		return null;
	}

//    default <T extends RemoteWebDriver> T getDriver() {return null;}
}
