package framework.qe.factories.impl;

import com.fasterxml.jackson.databind.ObjectMapper;


import framework.qe.factories.IDriverConnectionFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * web driver ios session
 */
public class IosFactory implements IDriverConnectionFactory {
    WebDriver driver;

    Map<String,?> capabilitiesMap;
    ObjectMapper mapper = new ObjectMapper();
    String json, jsonFile;
    DesiredCapabilities desiredCapabilities;
    String url;



    public WebDriver getDriver() {

        switch(testMode()) {
            case local:
                jsonFile = System.getProperty(TEST_JSON_CAP)!=null ?
                        System.getProperty(TEST_JSON_CAP) : "localIOSApp.json";
                driver = initDriver(jsonFile);
                break;

            case grid:
                // TODO initialize the remote remote webdriver via grid
                break;

            case cloud:
                jsonFile = System.getProperty(TEST_JSON_CAP)!=null ?
                        System.getProperty(TEST_JSON_CAP) : "bsIOSApp.json";
                driver = initDriver(jsonFile);
                break;
        }
        return  driver;
    }

    @SuppressWarnings("unchecked")
	private Map<String,?>  getCapabilities(String jsonCapabilities) {
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
        try {
            driver = new IOSDriver<MobileElement>(new URL(url),desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }
}
