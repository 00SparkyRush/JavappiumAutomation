import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest extends WebDriverHelper {



    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevicce");
        capabilities.setCapability("platformVersion", "8.0"); //AndroidOS version
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app",
                "C:/Users/kirill.minaev/Desktop/JavaAppiumAutomation/apks/org.wikipedia_50391_apps.evozi.com.apk"); //path to apk

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void firstTest()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No stip, looks like appdoes not start",
                2);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Coudn`t find wiki searh",
                2
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "can`t find an element",
                2
        );
        waitForElementPresent(
                By.xpath("//*[@class='android.view.ViewGroup']//*[@text= 'Object-oriented programming language']"),
                "can`t find search result",
                5);
    }

    @Test
    public  void testCancelSearch()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No stip, looks like appdoes not start",
                2);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "couldn`t find search container",
                2);
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "can`t find an element"
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "can`t clear the field",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@class = 'android.widget.ImageButton']"),
                "couldn`t find back button",
                2
        );
        waitForElementNotPresent(
                By.xpath("//*[@class = 'android.widget.ImageButton']"),
                "Element shouldn`t be there but was found",
                2
        );

    }

   /* @Test
    public void testCompareArticleTitle()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No stip, looks like appdoes not start"
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Coudn`t find wiki searh"
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "can`t find an element"
        );
        waitForElementAndClick(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "No article with such name"
        );
        WebElement title = waitForElementPresent(
                By.xpath("//android.view.View[@content-desc='Java (programming language)']"),
                "Article didn`t open",
                10);
        String article_title = title.getAttribute("content-desc");
        Assert.assertEquals(
                "could not find article title",
                "Java (programming language)",
                article_title
                );
    }*/

    @Test
    public void testSearchFieldText()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No stip, looks like appdoes not start"
        );
        waitForElementPresent(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Coudn`t find wiki searh"
        );
        assertElementHasText(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search Wikipedia",
                "An element has no text 'Search Wikipedia'"
        );
    }

    @Test
    public void testSearchMultipleArticesAndClear()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No skip, looks like app does not start"
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Couldn`t find wiki searh"
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "can`t enter search query"
        );
        waitForElementPresent(
                By.xpath("//android.view.ViewGroup[2]"),
                "search returns only one result",
                10);
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "can`t clear the field",
                5
        );
        waitForElementNotPresent(
                By.xpath("//android.view.ViewGroup[1]"),
                "search field was not cleared",
                10
        );
    }

    @Test
    public void testConfirmMultipleArticlesByKeyword()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No skip, looks like app does not start"
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Couldn`t find wiki searh"
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "can`t enter search query"
        );
        waitForElementPresent(
                By.xpath("//*[@class='android.view.ViewGroup']//*[@text = 'java']"),
                "can`t find search result",
                10);

        int elements_overall_amount = driver.findElements(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']")).size();
        int elements_with_keyword_amount = driver.findElements(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']//*[contains(@text, 'Java')]")).size();
       Assert.assertTrue("not every result contains corresponding keyword",elements_with_keyword_amount == elements_overall_amount);
    }

    @Test
    public void testSwipeArticle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No stip, looks like appdoes not start"
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Coudn`t find wiki searh"
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "can`t find an element"
        );
        waitForElementAndClick(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "No article with such name"
        );
        WebElement title = waitForElementPresent(
                By.xpath("//android.view.View[@content-desc='Java (programming language)']"),
                "Article didn`t open",
                10);
        swipeUpToFindElement(
                By.xpath("//*[@resource-id = 'pcs-footer-container-legal']"),
                "element does not exist at that page",
                20
        );

    }
}
