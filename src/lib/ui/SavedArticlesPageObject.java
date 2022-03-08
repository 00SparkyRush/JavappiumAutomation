package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SavedArticlesPageObject extends MainPageObject {

    private static final String
            SAVED_ARTICLES_LIST_ELEMENT = "org.wikipedia:id/item_reading_list_statistical_description",
            ARTICLE_TITLE = "//*[@text='{TITLE}']";

    public SavedArticlesPageObject (AppiumDriver driver)
    {
        super(driver);
    }
    //TEMPLATE METHODS
    private static String getArticleTitle(String title_of_article)
    {
        return ARTICLE_TITLE.replace("{TITLE}", title_of_article);
    }

    //TEMPLATE METHODS

    public void openSavedArticlesList()
    {
        this.waitForElementAndClick(
                By.id(SAVED_ARTICLES_LIST_ELEMENT),
                "Coudn`t open saved articles list"
        );
    }
    public void waitForArticletoApearByTitle(String title)
    {
        String title_by_subsstring = getArticleTitle(title);
        this.waitForElementPresent(
                By.xpath(title_by_subsstring),
                "Can`t find an article in saved list",
                5
        );
    }
    public void waitForArticletoDissapearByTitle(String title)
    {
        String title_by_subsstring = getArticleTitle(title);
        this.waitForElementNotPresent(
                By.xpath(title_by_subsstring),
                "Can`t find an article in saved list",
                15
        );
    }
    public void deleteArticleByTitleWithSwipe(String title)
    {
        String title_by_subsstring = getArticleTitle(title);
        this.waitForArticletoApearByTitle(title);
        this.swipeElementLeft(
                By.xpath(title_by_subsstring),
                "can`t delete an article"
        );
        this.waitForArticletoDissapearByTitle(title);
    }
}
