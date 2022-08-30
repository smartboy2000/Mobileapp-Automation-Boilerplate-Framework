package framework.utils;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class UtilsComponent {

	AppiumDriver<MobileElement> appiumDriver;

	@SuppressWarnings("unchecked")
	public UtilsComponent(WebDriver appiumDriver) {
		this.appiumDriver = (AppiumDriver<MobileElement>) appiumDriver;
	}

	public MobileElement waitForElementToBecomeVisible(MobileElement element, int waitInSeconds) {
		new WebDriverWait(appiumDriver, waitInSeconds).until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public MobileElement waitForElementToBeClickable(MobileElement element, int waitInSeconds) {
		new WebDriverWait(appiumDriver, waitInSeconds).until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}

	public void waitForElementToLoad() {
		appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public String getCurrentClassName() {
		return this.getClass().getSimpleName() + " : ";
	}

	public void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void scrollTillElement(String element){
		appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector())" +
				".scrollIntoView(new UiSelector().textContains(\"" + element + "\").instance(0));"));
		sleep(1000);
	}

}
