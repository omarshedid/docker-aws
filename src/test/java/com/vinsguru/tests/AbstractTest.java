package com.vinsguru.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import com.vinsguru.listeners.TestListener;
import com.vinsguru.util.Config;
import com.vinsguru.util.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Listeners(TestListener.class)
public abstract class AbstractTest {

    protected WebDriver driver;

    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }
    @BeforeTest
    public void setDriver(ITestContext ctx) throws MalformedURLException {
        System.out.println(Thread.currentThread().getId());
        if (Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))) driver = getRemoteDriver();
        else driver = getLocalDriver();
        ctx.setAttribute("driver",driver);
    }

    private WebDriver getLocalDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private RemoteWebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities;
        if (Constants.CHROME.equals(Config.get(Constants.BROWSER))) capabilities = new ChromeOptions();
        else capabilities = new FirefoxOptions();

        String url = String.format(Config.get(Constants.GRID_URL_FORMAT), Config.get(Constants.GRID_HUB_HOST));
        return new RemoteWebDriver(new URL(url), capabilities);
    }

    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }


}
