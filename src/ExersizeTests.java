import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;

public class ExersizeTests extends CoreTestCase {
    private lib.ui.MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearchFieldText() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        MainPageObject.skipInitialLanguageSelect();

        SearchPageObject.initSearchInput();
        String actual_input_text = SearchPageObject.getSearchInputText();

        Assert.assertEquals(
                "Search input text is incottect",
                "Search Wikipedia",
                actual_input_text
        );
    }

    @Test
    public void testSearchMultipleArticesAndClear() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        MainPageObject.skipInitialLanguageSelect();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitForAnySearchResult();
        int search_result = SearchPageObject.getTotalAmountOfArticlesInSearch();
        Assert.assertTrue("found only one article or less",search_result>1);
        SearchPageObject.clearSearchInput();
        int cleared_search_result = SearchPageObject.getTotalAmountOfArticlesInSearch();
        Assert.assertTrue("searsh result was not cleared",cleared_search_result==0);
    }

    @Test
    public void testConfirmMultipleArticlesByKeyword() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        MainPageObject.skipInitialLanguageSelect();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitForAnySearchResult();

        int elements_overall_amount = SearchPageObject.getTotalAmountOfArticlesInSearch();
        int elements_with_keyword_amount = SearchPageObject.getTotalAmountOfArticlesInSearchBysubstring("Java");
        Assert.assertTrue("not every result contains corresponding keyword", elements_with_keyword_amount == elements_overall_amount);
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
}
