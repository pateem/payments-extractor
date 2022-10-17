package com.sap.data.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class SeleniumDriverFactory {


    public WebDriver getDriver() {
        String osName = System.getProperty("os.name").toLowerCase();
        String driverFileName = osName.contains("mac") ? "geckodriver" : "geckodriver.exe";
        URL firefoxURL = getClass().getClassLoader().getResource("driver" + File.separator + driverFileName);
        try {
            System.setProperty("webdriver.gecko.driver", Paths.get(firefoxURL.toURI()).toFile().getAbsolutePath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

}
