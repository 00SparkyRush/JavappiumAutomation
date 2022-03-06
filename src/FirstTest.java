import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

public class FirstTest extends CoreTestCase {
    private MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }


    @Test
    public void testSearch() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No stip, looks like appdoes not start",
                2);
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Coudn`t find wiki searh",
                2
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "can`t find an element",
                2
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@class='android.view.ViewGroup']//*[@text= 'Object-oriented programming language']"),
                "can`t find search result",
                5);
    }

    @Test
    public void testCancelSearch() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No stip, looks like appdoes not start",
                2);
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "couldn`t find search container",
                2);
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "can`t find an element"
        );
        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "can`t clear the field",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@class = 'android.widget.ImageButton']"),
                "couldn`t find back button",
                2
        );
        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@class = 'android.widget.ImageButton']"),
                "Element shouldn`t be there but was found",
                2
        );

    }

   /* @Test
    public void testCompareArticleTitle()
    {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No stip, looks like appdoes not start"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Coudn`t find wiki searh"
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "can`t find an element"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "No article with such name"
        );
        WebElement title = MainPageObject.waitForElementPresent(
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
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No stip, looks like appdoes not start"
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Coudn`t find wiki searh"
        );
        MainPageObject.assertElementHasText(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search Wikipedia",
                "An element has no text 'Search Wikipedia'"
        );
    }

    @Test
    public void testSearchMultipleArticesAndClear() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No skip, looks like app does not start"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Couldn`t find wiki searh"
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "can`t enter search query"
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//android.view.ViewGroup[2]"),
                "search returns only one result",
                10);
        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "can`t clear the field",
                5
        );
        MainPageObject.waitForElementNotPresent(
                By.xpath("//android.view.ViewGroup[1]"),
                "search field was not cleared",
                10
        );
    }

    @Test
    public void testConfirmMultipleArticlesByKeyword() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No skip, looks like app does not start"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Couldn`t find wiki searh"
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "can`t enter search query"
        );
        MainPageObject.waitForElementPresent(
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
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No stip, looks like appdoes not start"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Coudn`t find wiki searh"
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "can`t find an element"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "No article with such name"
        );
        WebElement title = MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Article didn`t open",
                10);
        MainPageObject.swipeUpToFindElement(
                By.xpath("//*[@resource-id = 'pcs-footer-container-legal']"),
                "element does not exist at that page",
                20
        );

    }

    @Test
    public void testSaveFirstArticleToMyList() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No stip, looks like appdoes not start"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Coudn`t find wiki searh"
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "can`t find an element"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "No article with such name"
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Article didn`t open",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark']"),
                "Coudn`t save an article"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Coudn`t return back to search"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton"),
                "Coudn`t return to main screen"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='Saved']"),
                "Coudn`t open saved articles"
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/item_reading_list_statistical_description"),
                "Coudn`t open saved articles list"
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Can`t find an article in saved list",
                5
        );
        MainPageObject.swipeElementLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "can`t delete an article"
        );
        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "article still present",
                5
        );
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        String search_querry = "Linkin park discography";
        String search_item_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No stip, looks like appdoes not start"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Coudn`t find wiki searh"
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_querry,
                "can`t find an element"
        );
        MainPageObject.waitForElementPresent(
                By.xpath(search_item_locator),
                "Can`t find anything by request " + search_querry,
                15
        );
        int amount_of_search_elements = MainPageObject.getAmountOfElements(By.xpath(search_item_locator));
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

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No skip, looks like app does not start"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Couldn`t find wiki searh"
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_querry,
                "can`t find an element"
        );
        MainPageObject.waitForElementPresent(
                By.xpath(empty_result_label),
                "Can`t find 'No results' by request " + search_querry,
                15
        );
        MainPageObject.assertElementNotPresent(
                By.xpath(search_item_locator),
                "Found some results by request " + search_querry);
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        String search_querry = "java";

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No stip, looks like appdoes not start"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Coudn`t find wiki searh"
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_querry,
                "can`t find an element"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "No article with such name by request " + search_querry,
                15
        );
        String title_before_rotatoin =MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "text",
                "can`t find article title",
                15
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String title_after_rotatoin = MainPageObject.waitForElementAndGetAttribute(
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
        String title_after_second_rotatoin = MainPageObject.waitForElementAndGetAttribute(
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

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No stip, looks like appdoes not start"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Coudn`t find wiki searh"
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_querry1,
                "can`t find an element"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath(article1_title_xpath_locator),
                "No article with such name"
        );
        MainPageObject.waitForElementPresent(
                By.xpath(article1_title_xpath_locator),
                "Article didn`t open",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.xpath(save_button_xpath_locator),
                "Coudn`t save an article"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath(navigate_up_button_xpath_locator),
                "Coudn`t return back to search"
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_querry2,
                "can`t find an element"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath(article2_title_xpath_locator),
                "No article with such name"
        );
        MainPageObject.waitForElementPresent(
                By.xpath(article2_title_xpath_locator),
                "Article didn`t open",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.xpath(save_button_xpath_locator),
                "Coudn`t save an article"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath(navigate_up_button_xpath_locator),
                "Coudn`t return back to search"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton"),
                "Coudn`t return to main screen"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='Saved']"),
                "Coudn`t open saved articles"
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/item_reading_list_statistical_description"),
                "Coudn`t open saved articles list"
        );
        MainPageObject.waitForElementPresent(
                By.xpath(article1_title_xpath_locator),
                "Can`t find an article in saved list",
                5
        );
        MainPageObject.swipeElementLeft(
                By.xpath(article1_title_xpath_locator),
                "can`t delete an article"
        );
        MainPageObject.waitForElementPresent(
                By.xpath(article2_title_xpath_locator),
                "Can`t find an article in saved list",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath(article2_title_xpath_locator),
                "can`t open saved article",
                5
        );
        MainPageObject.waitForElementPresent(
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

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No stip, looks like appdoes not start"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Coudn`t find wiki searh"
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_querry1,
                "can`t find an element"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath(article1_title_xpath_locator),
                "No article with such name"
        );
        MainPageObject.assertElementPresent(
                By.xpath(general_title_xpath_locator),
                "an article has no title"
        );
    }

    @Test
    public void testCheckSearchArticleInBackground()
    {
        String search_querry1 = "java";
        String article1_title_xpath_locator = "//android.widget.TextView[@text='Java (programming language)']";

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "No stip, looks like appdoes not start"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Coudn`t find wiki searh"
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_querry1,
                "can`t find an element"
        );
        MainPageObject.waitForElementPresent(
                By.xpath(article1_title_xpath_locator),
                "Can`t find an article in search list",
                5
        );
        driver.runAppInBackground(2);
        MainPageObject.waitForElementPresent(
                By.xpath(article1_title_xpath_locator),
                "Can`t find an article in search list afteer returning from background",
                5
        );
    }
}
