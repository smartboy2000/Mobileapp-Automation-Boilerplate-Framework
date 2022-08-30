package pagefactory.mobileapp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class CatalogPoFactory {

    public CatalogPoFactory(AppiumDriver<MobileElement> appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Duration.ofSeconds(10)), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Buttons']")
    public MobileElement buttonTitle;

    @AndroidFindBy(id = "io.material.catalog:id/cat_demo_landing_row_title")
    public MobileElement demoTitle;

    @AndroidFindBy(id = "io.material.catalog:id/material_button")
    public MobileElement materialButton;

    @AndroidFindBy(accessibility = "Close demo")
    public MobileElement crossIcon;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Checkbox']")
    public MobileElement checkboxTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Dialogs']")
    public MobileElement dialogsTitle;

    @AndroidFindBy(xpath = "//android.widget.CheckBox[@text='Enabled'][2]")
    public MobileElement enabledCheckbox;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
    public MobileElement backButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='AppCompat AlertDialog']")
    public MobileElement alertButton;

    @AndroidFindBy(id = "android:id/button1")
    public MobileElement acceptAlert;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Menus']")
    public MobileElement menusTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Radio Button']")
    public MobileElement radioButtonTitle;

    @AndroidFindBy(id = "io.material.catalog:id/menu_button")
    public MobileElement showMenuButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='One']")
    public MobileElement oneValue;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Enabled'][2]")
    public MobileElement enabledRadioButton;
}
