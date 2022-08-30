package framework.qe.factories.impl;

import com.fasterxml.jackson.databind.ObjectMapper;


import framework.qe.factories.IDriverConnectionFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * web driver android session setup
 */
public class AndroidAppFactory implements IDriverConnectionFactory {

    WebDriver driver;

    Map<String, ?> capabilitiesMap;
    ObjectMapper mapper = new ObjectMapper();
    String json, jsonFile;
    DesiredCapabilities desiredCapabilities;
    String url;

    public WebDriver getDriver() {

        switch (testMode()) {
            case local:
                jsonFile = System.getProperty(TEST_JSON_CAP) != null ? System.getProperty(TEST_JSON_CAP)
                        : "localAndroidApp.json";
                driver = initDriver(jsonFile);
                break;

            case grid:
                // TODO initialize the remote remote webdriver via grid
                break;

            case cloud:
                jsonFile = System.getProperty(TEST_JSON_CAP) != null ? System.getProperty(TEST_JSON_CAP)
                        : "bsAndroidApp.json";
                driver = initDriver(jsonFile);
                break;
        }
        return driver;
    }

    @SuppressWarnings("unchecked")
    private Map<String, ?> getCapabilities(String jsonCapabilities) {
        try {
            json = FileUtils.readFileToString(new File(jsonCapabilities), StandardCharsets.UTF_8);
            capabilitiesMap = mapper.readValue(json, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return capabilitiesMap;
    }


    private WebDriver initDriver(String jsonFile) {
        capabilitiesMap = getCapabilities(jsonFile);
        url = capabilitiesMap.get("url").toString();
        capabilitiesMap.remove("url");
        desiredCapabilities = new DesiredCapabilities(capabilitiesMap);
        if (testMode().name().equals("cloud")) {
            desiredCapabilities.setCapability("build", getCapabilities(jsonFile).get("build") + "_" + getDateTime());
        }


        try {
            driver = new AndroidDriver<MobileElement>(new URL(url), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }

    /**
     * Date and time
     *
     * @return
     */
    public String getDateTime() {
        // Create object of SimpleDateFormat class and decide the format
        DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        // get current date time with Date()
        Date date = new Date();
        // Now format the date
        String currentDate = dateFormat.format(date);
        String newCurrentDate = currentDate.replace('/', '-');
        return newCurrentDate;
    }
}
