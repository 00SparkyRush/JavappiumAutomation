package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String
            BACK_FROM_SEARCH_BUTTON = "//android.widget.ImageButton",
            SAVED_ARTICLES_BUTTON = "//android.widget.FrameLayout[@content-desc='Saved']";

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void goBackFromSearch()
    {
        this.waitForElementAndClick(
                By.xpath(BACK_FROM_SEARCH_BUTTON),
                "Coudn`t return to main screen"
        );
    }

    public void goToSavedArticles()
    {
        this.waitForElementAndClick(
                By.xpath(SAVED_ARTICLES_BUTTON  ),
                "Coudn`t open saved articles"
        );
    }
}
