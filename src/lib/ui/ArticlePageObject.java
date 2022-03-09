package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String
            TITLE = "//android.widget.TextView[1]",
            FOOTER_ELEMENT = "//*[@text = 'View article in browser']",
            ADD_TO_SAVED_LIST_ELEMENT = "org.wikipedia:id/article_menu_bookmark",
            BACK_BUTTON_ELEMENT = "//android.widget.ImageButton[@content-desc='Navigate up']";


    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(
                By.xpath(TITLE),
                "can`t open an article",
                10
        );
    }
    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "can`t find article end",
                20);
    }
    public void addArticleToSavedList()
    {
        this.waitForElementAndClick(
                By.id(ADD_TO_SAVED_LIST_ELEMENT),
                "Coudn`t save an article"
        );
    }

    public void goBackFromArticle()
    {
        this.waitForElementAndClick(
                By.xpath(BACK_BUTTON_ELEMENT),
                "Coudn`t return back to search"
        );
    }
    public void assertArticleHasTitle()
    {
        this.assertElementPresent(
                By.xpath(TITLE),
        "An article has no title");
    }
}
