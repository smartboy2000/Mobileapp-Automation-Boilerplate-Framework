package tests;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageactions.ICatalogPage;

public class CatalogTest extends TestSession {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogTest.class);
    private ICatalogPage iCatalogPage;


    @BeforeClass(alwaysRun = true)
    public void class_objects_setup() throws Exception {
        LOGGER.info("Running Catalog App page tests");
        iCatalogPage = initPlatformPage(iCatalogPage, "CatalogPageAction");

    }

    @Test(priority = 0)
    public void testCatalogButtons(){
        iCatalogPage.clickOnButtonTitle();
        iCatalogPage.clickOnDemoTitle();
        iCatalogPage.clickOnMaterialButton();
    }

    @Test(priority = 1)
    public void testCatalogCheckBox(){
        iCatalogPage.clickOnCrossIcon();
        iCatalogPage.clickOnBackButton();
        iCatalogPage.clickOnCheckBoxTitle();
        iCatalogPage.clickOnDemoTitle();
        iCatalogPage.clickOnEnabledCheckbox();
    }

    @Test(priority = 2)
    public void testCatalogAlerts(){
        iCatalogPage.clickOnCrossIcon();
        iCatalogPage.clickOnBackButton();
        iCatalogPage.clickOnDialogsTitle();
        iCatalogPage.clickOnDemoTitle();
        iCatalogPage.clickOnAlertButton();
        iCatalogPage.clickOnAcceptAlert();
    }

    @Test(priority = 3)
    public void testCatalogDropDown(){
        iCatalogPage.clickOnCrossIcon();
        iCatalogPage.clickOnBackButton();
        iCatalogPage.clickOnMenusTitle();
        iCatalogPage.clickOnDemoTitle();
        iCatalogPage.clickOnShowMenuButton();
        iCatalogPage.clickOnOneOption();
    }
    @Test(priority = 4)
    public void testCatalogRadioButton(){
        iCatalogPage.clickOnCrossIcon();
        iCatalogPage.clickOnBackButton();
        iCatalogPage.clickOnRadioButtonTitle();
        iCatalogPage.clickOnDemoTitle();
        iCatalogPage.clickOnEnabledRadioButton();
    }
}
