package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "org.wikipedia:id/search_src_text",
            SEARCH_CANCEL_BUTTON = "//*[@class = 'android.widget.ImageButton']",
            SEARCH_RESULT = "//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@class='android.view.ViewGroup']//*[@text= '{SUBSTRING}']",
            SERARH_RESULT_CONTAINING_SUBSTRING_TPL = "//*[@class='android.view.ViewGroup']//*[contains(@text, '{SUBSTRING}')]";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
    //TEMPLATE METHODS
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getResultSearchElementContainintSubstring(String substring)
    {
        return SERARH_RESULT_CONTAINING_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    //TEMPLATE METHODS

    public String getSearchInputText()
    {
        return this.waitForElementPresent(
                By.id(SEARCH_INPUT),
                "can`t find and search init element",
                5).
                getAttribute("text");
    }

    public void initSearchInput()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_INIT_ELEMENT),
                "can`t find and search init element",
                5
        );
        this.waitForElementAndClick(
                By.xpath(SEARCH_INIT_ELEMENT),
                "can`t find and click search init element"
        );
    }
    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(
                By.id(SEARCH_INPUT),
                search_line,
                "Can`t find and type into search input"
        );
    }
    public void waitForAnySearchResult()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT),
                "search result is empty",
                15
        );
    }
    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(
                By.xpath(search_result_xpath),
                "Can`t find search result with subsctring "+substring,
                5
        );
    }
    public void waitForSearchResultContainsText(String substring)
    {
        String search_result_xpath = getResultSearchElementContainintSubstring(substring);
        this.waitForElementPresent(
                By.xpath(search_result_xpath),
                "Can`t find search result with subsctring "+substring,
                5
        );
    }
    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                By.xpath(search_result_xpath),
                "Can`t find and click search result with subsctring " + substring,
                10
        );
    }
    public void waitFroCancelButtonToAppear()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_CANCEL_BUTTON),
                "Can`t find search cancel button"
        );
    }

    public void waitFroCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(
                By.xpath(SEARCH_CANCEL_BUTTON),
                "Search cancel button is still present",
                5
        );
    }

    public void clickCancelButton()
    {
        this.waitForElementAndClick(
                By.xpath(SEARCH_CANCEL_BUTTON),
                "can`t click by cancel button",
                5
        );
    }

    public int getTotalAmountOfArticlesInSearch()
    {
        return driver.findElements(By.xpath(SEARCH_RESULT)).size();
    }

    public int getTotalAmountOfArticlesInSearchBysubstring(String substring)
    {
        String search_results_by_substring = getResultSearchElementContainintSubstring(substring);
        return driver.findElements(By.xpath(search_results_by_substring)).size();
    }
    public void clearSearchInput()
    {
        waitForElementAndClear(
                By.id(SEARCH_INPUT),
                "can`t clear search input field",
                5
        );
    }
}
