package pageactions.mobileapp;

import framework.utils.UtilsComponent;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import pageactions.ICatalogPage;
import pagefactory.mobileapp.CatalogPoFactory;

public class CatalogPageAction extends UtilsComponent implements ICatalogPage {

    AppiumDriver<MobileElement> appiumDriver;
    CatalogPoFactory catalogPoFactory;
    private String currentClassName;

    @SuppressWarnings("unchecked")
    public CatalogPageAction(WebDriver driver) {
        super(driver);
        appiumDriver = (AppiumDriver<MobileElement>) driver;
        catalogPoFactory = new CatalogPoFactory(appiumDriver);
        currentClassName = getCurrentClassName();
    }


    /**
     * Click on Button Title
     */
    public void clickOnButtonTitle() {
        Reporter.log(currentClassName + "Clicking On Button Title", true);
        waitForElementToBecomeVisible(catalogPoFactory.buttonTitle,10);
        waitForElementToBeClickable(catalogPoFactory.buttonTitle,10);
        catalogPoFactory.buttonTitle.click();
    }

    /**
     * Click on Demo Title
     */
    public void clickOnDemoTitle() {
        Reporter.log(currentClassName + "Clicking On Demo Title", true);
        waitForElementToBecomeVisible(catalogPoFactory.demoTitle,10);
        waitForElementToBeClickable(catalogPoFactory.demoTitle,10);
        catalogPoFactory.demoTitle.click();
    }

    /**
     * Click on Material Button
     */
    public void clickOnMaterialButton() {
        Reporter.log(currentClassName + "Clicking On Material Button", true);
        waitForElementToBecomeVisible(catalogPoFactory.materialButton,10);
        waitForElementToBeClickable(catalogPoFactory.materialButton,10);
        catalogPoFactory.materialButton.click();
    }

    /**
     * Click on Cross Icon
     */
    public void clickOnCrossIcon() {
        Reporter.log(currentClassName + "Clicking On Cross Icon", true);
        waitForElementToBecomeVisible(catalogPoFactory.crossIcon,10);
        waitForElementToBeClickable(catalogPoFactory.crossIcon,10);
        catalogPoFactory.crossIcon.click();
    }

    /**
     * Click on Back Button
     */
    public void clickOnBackButton() {
        Reporter.log(currentClassName + "Clicking On Back Button", true);
        waitForElementToBecomeVisible(catalogPoFactory.backButton,10);
        waitForElementToBeClickable(catalogPoFactory.backButton,10);
        catalogPoFactory.backButton.click();
    }

    /**
     * Click on Checkbox Title
     */
    public void clickOnCheckBoxTitle() {
        Reporter.log(currentClassName + "Clicking On Checkbox Title", true);
        waitForElementToBecomeVisible(catalogPoFactory.checkboxTitle,10);
        waitForElementToBeClickable(catalogPoFactory.checkboxTitle,10);
        catalogPoFactory.checkboxTitle.click();
    }

    /**
     * Click on Enabled CheckBox
     */
    public void clickOnEnabledCheckbox() {
        Reporter.log(currentClassName + "Clicking On Enabled CheckBox", true);
        waitForElementToBecomeVisible(catalogPoFactory.enabledCheckbox,10);
        waitForElementToBeClickable(catalogPoFactory.enabledCheckbox,10);
        catalogPoFactory.enabledCheckbox.click();
    }


    /**
     * Click on Dialogs Title
     */
    public void clickOnDialogsTitle() {
        Reporter.log(currentClassName + "Clicking On Dialogs Title", true);
        scrollTillElement("Dialogs");
        waitForElementToBecomeVisible(catalogPoFactory.dialogsTitle,10);
        waitForElementToBeClickable(catalogPoFactory.dialogsTitle,10);
        catalogPoFactory.dialogsTitle.click();
    }

    /**
     * Click on Alert Button
     */
    public void clickOnAlertButton() {
        Reporter.log(currentClassName + "Clicking On Alert Button", true);
        waitForElementToBecomeVisible(catalogPoFactory.alertButton,10);
        waitForElementToBeClickable(catalogPoFactory.alertButton,10);
        catalogPoFactory.alertButton.click();
    }

    /**
     * Click on Accept Alert
     */
    public void clickOnAcceptAlert() {
        Reporter.log(currentClassName + "Clicking On Accept Alert", true);
        waitForElementToBecomeVisible(catalogPoFactory.acceptAlert,10);
        waitForElementToBeClickable(catalogPoFactory.acceptAlert,10);
        catalogPoFactory.acceptAlert.click();
    }


    /**
     * Click on Menus Title
     */
    public void clickOnMenusTitle() {
        Reporter.log(currentClassName + "Clicking On Menus Title", true);
        scrollTillElement("Menus");
        waitForElementToBecomeVisible(catalogPoFactory.menusTitle,10);
        waitForElementToBeClickable(catalogPoFactory.menusTitle,10);
        catalogPoFactory.menusTitle.click();
    }

    /**
     * Click on Menus Button
     */
    public void clickOnShowMenuButton() {
        Reporter.log(currentClassName + "Clicking On Menus Button", true);
        waitForElementToBecomeVisible(catalogPoFactory.showMenuButton,10);
        waitForElementToBeClickable(catalogPoFactory.showMenuButton,10);
        catalogPoFactory.showMenuButton.click();
    }


    /**
     * Click on One Option
     */
    public void clickOnOneOption() {
        Reporter.log(currentClassName + "Clicking On One Option", true);
        waitForElementToBecomeVisible(catalogPoFactory.oneValue,10);
        waitForElementToBeClickable(catalogPoFactory.oneValue,10);
        catalogPoFactory.oneValue.click();
    }

    /**
     * Click on Radio Button Title
     */
    public void clickOnRadioButtonTitle() {
        Reporter.log(currentClassName + "Clicking On Radio Button Title", true);
        scrollTillElement("Radio Button");
        waitForElementToBecomeVisible(catalogPoFactory.radioButtonTitle,10);
        waitForElementToBeClickable(catalogPoFactory.radioButtonTitle,10);
        catalogPoFactory.radioButtonTitle.click();
    }

    /**
     * Click on Enabled Radio Button
     */
    public void clickOnEnabledRadioButton() {
        Reporter.log(currentClassName + "Clicking On Enabled Radio Button", true);
        waitForElementToBecomeVisible(catalogPoFactory.enabledRadioButton,10);
        waitForElementToBeClickable(catalogPoFactory.enabledRadioButton,10);
        catalogPoFactory.enabledRadioButton.click();
    }

}
