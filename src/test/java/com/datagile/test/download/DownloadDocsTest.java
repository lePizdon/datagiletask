package com.datagile.test.download;

import com.datagile.test.download.pages.BrowserPage;
import com.datagile.test.download.pages.GazPage;
import com.datagile.test.util.ConfProperties;
import com.datagile.test.util.Log;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

public class DownloadDocsTest {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static ChromeOptions options;
    private static Logger logger;

    @BeforeTest
    public void initializeDriver() {
        options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Test");
        options.addArguments("profile-directory=Profile 2");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        logger = Log.getLogger(DownloadDocsTest.class);
    }

    @Test
    public void testDownload() {
        BrowserPage browserPage = new BrowserPage(driver);
        GazPage gazPage = new GazPage(driver);

        driver.get(ConfProperties.getProperty("yandex.landing.endpoint"));
        try {
            wait.until(ExpectedConditions.visibilityOf(browserPage.getSearchFrame()));
            Assert.assertTrue(browserPage.getSearchFrame().isDisplayed());
        } catch (TimeoutException e) {
            logger.error("timeout during explicit wait of iframe render");
        } catch (AssertionError e) {
            logger.error("frame is not displayed or have been moved");
        }
        logger.debug("frame found");
        driver.switchTo().frame(browserPage.getSearchFrame());

        browserPage.fillOutFormAndSubmit(ConfProperties.getProperty("request.text"), wait);
        logger.debug("form submitted");

        driver.switchTo().defaultContent();
        switchToTheNext(driver, true);


        advance(wait, browserPage.getSearchResult());

        switchToTheNext(driver, true);

        advance(wait, gazPage.getProducts());

        hardAdvance(wait, gazPage.getAnkey());

        switchToTheNext(driver, false);

        wait.until(ExpectedConditions.visibilityOf(gazPage.getInfo()));
        logger.info("info loaded");
        advance(wait, gazPage.getMaterials());
        driver.get(gazPage.getGuide().getAttribute("href"));
        try {
            Thread.sleep(Duration.ofSeconds(10));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void hardAdvance(WebDriverWait wait, WebElement target) {
        clickableWait(wait, target);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", target);
        logger.debug("hard js click {}", target.toString());
    }

    public static void advance(WebDriverWait wait, WebElement target) {
        clickableWait(wait, target);
        target.click();
        logger.debug("standard click {}", target.toString());
    }

    public static void clickableWait(WebDriverWait wait, WebElement target) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(target));
        } catch (TimeoutException e) {
            logger.error("timeout while explicit wait of clickable element {}", target.toString());
        }
    }

    public static void switchToTheNext(WebDriver driver, boolean ident) {
        String urlContext = driver.getCurrentUrl();
        String handleContext = driver.getWindowHandle();
        List<String> tabs = new LinkedList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.getLast());
        logger.debug("attempt to switch tabs");
        try {
            Assert.assertNotEquals(driver.getWindowHandle(), handleContext);
        } catch (AssertionError e) {
            if (ident) {
                logger.error("matched handles after tab switch, must be identical");
                throw e;
            } else {
                logger.debug("all is well");
            }
        }
        try {
            Assert.assertNotEquals(driver.getCurrentUrl(), urlContext);
        } catch (AssertionError e) {
            if (ident) {
                logger.error("matched url after advance and tab switch, must be identical");
                throw e;
            } else {
                logger.debug("all is well");
            }
        }
        logger.trace("successful switch");
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

}
