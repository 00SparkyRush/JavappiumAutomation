import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest extends WebDriverHelper {


    @Before
    public void setUp() throws Exception {
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
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
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
    public void testCancelSearch() {
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
    public void testSearchFieldText() {
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
    public void testSearchMultipleArticesAndClear() {
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
    public void testConfirmMultipleArticlesByKeyword() {
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
        Assert.assertTrue("not every result contains corresponding keyword", elements_with_keyword_amount == elements_overall_amount);
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

    @Test
    public void testSaveFirstArticleToMyList() {
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
        waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Article didn`t open",
                10
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark']"),
                "Coudn`t save an article"
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Coudn`t return back to search"
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton"),
                "Coudn`t return to main screen"
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='Saved']"),
                "Coudn`t open saved articles"
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/item_reading_list_statistical_description"),
                "Coudn`t open saved articles list"
        );
        waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Can`t find an article in saved list",
                5
        );
        swipeElementLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "can`t delete an article"
        );
        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "article still present",
                5
        );
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        String search_querry = "Linkin park discography";
        String search_item_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";

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
                search_querry,
                "can`t find an element"
        );
        waitForElementPresent(
                By.xpath(search_item_locator),
                "Can`t find anything by request " + search_querry,
                15
        );
        int amount_of_search_elements = getAmountOfElements(By.xpath(search_item_locator));
        Assert.assertTrue(
                "Too few results were found",
                amount_of_search_elements > 0
        );
    }

    @Test
    public void testAmountofEmtySearch() {
        String search_querry = "fsfghgfhrg";
        String search_item_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String empty_result_label = "//*[@text='No results']";

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
                search_querry,
                "can`t find an element"
        );
        waitForElementPresent(
                By.xpath(empty_result_label),
                "Can`t find 'No results' by request " + search_querry,
                15
        );
        assertElementNotPresent(
                By.xpath(search_item_locator),
                "Found some results by request " + search_querry);
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        String search_querry = "java";

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
                search_querry,
                "can`t find an element"
        );
        waitForElementAndClick(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "No article with such name by request " + search_querry,
                15
        );
        String title_before_rotatoin = waitForElementAndGetAttribute(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "text",
                "can`t find article title",
                15
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String title_after_rotatoin = waitForElementAndGetAttribute(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "text",
                "can`t find article title",
                15
        );
        Assert.assertEquals(
                "title changed after rotatoin",
                title_before_rotatoin,
                title_after_rotatoin
        );
        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_second_rotatoin = waitForElementAndGetAttribute(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "text",
                "can`t find article title",
                15
        );
        Assert.assertEquals(
                "title changed after rotatoin",
                title_before_rotatoin,
                title_after_second_rotatoin
        );
    }

    @Test
    public void testSaveArticlesToMyList() {
        String search_querry1 = "java";
        String search_querry2 = "appium";
        String article1_title_xpath_locator = "//android.widget.TextView[@text='Java (programming language)']";
        String article2_title_xpath_locator = "//android.widget.TextView[@text='Appium']";

        String save_button_xpath_locator = "//*[@resource-id='org.wikipedia:id/article_menu_bookmark']";
        String navigate_up_button_xpath_locator = "//android.widget.ImageButton[@content-desc='Navigate up']";

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
                search_querry1,
                "can`t find an element"
        );
        waitForElementAndClick(
                By.xpath(article1_title_xpath_locator),
                "No article with such name"
        );
        waitForElementPresent(
                By.xpath(article1_title_xpath_locator),
                "Article didn`t open",
                10
        );
        waitForElementAndClick(
                By.xpath(save_button_xpath_locator),
                "Coudn`t save an article"
        );
        waitForElementAndClick(
                By.xpath(navigate_up_button_xpath_locator),
                "Coudn`t return back to search"
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_querry2,
                "can`t find an element"
        );
        waitForElementAndClick(
                By.xpath(article2_title_xpath_locator),
                "No article with such name"
        );
        waitForElementPresent(
                By.xpath(article2_title_xpath_locator),
                "Article didn`t open",
                10
        );
        waitForElementAndClick(
                By.xpath(save_button_xpath_locator),
                "Coudn`t save an article"
        );
        waitForElementAndClick(
                By.xpath(navigate_up_button_xpath_locator),
                "Coudn`t return back to search"
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton"),
                "Coudn`t return to main screen"
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='Saved']"),
                "Coudn`t open saved articles"
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/item_reading_list_statistical_description"),
                "Coudn`t open saved articles list"
        );
        waitForElementPresent(
                By.xpath(article1_title_xpath_locator),
                "Can`t find an article in saved list",
                5
        );
        swipeElementLeft(
                By.xpath(article1_title_xpath_locator),
                "can`t delete an article"
        );
        waitForElementPresent(
                By.xpath(article2_title_xpath_locator),
                "Can`t find an article in saved list",
                5
        );
        waitForElementAndClick(
                By.xpath(article2_title_xpath_locator),
                "can`t open saved article",
                5
        );
        waitForElementPresent(
                By.xpath(article2_title_xpath_locator),
                "Can`t find an article in saved list",
                15
        );
    }

    @Test
    public void testAssertArticleHasTitle()
    {
        String search_querry1 = "java";
        String article1_title_xpath_locator = "//android.widget.TextView[@text='Java (programming language)']";
        String general_title_xpath_locator = "//android.view.View[1]";

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
                search_querry1,
                "can`t find an element"
        );
        waitForElementAndClick(
                By.xpath(article1_title_xpath_locator),
                "No article with such name"
        );
        assertElementPresent(
                By.xpath(general_title_xpath_locator),
                "an article has no title"
        );
    }
}
