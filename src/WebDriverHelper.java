import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.apache.xerces.util.DOMEntityResolverWrapper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Dictionary;

public class WebDriverHelper {

    public AppiumDriver driver;

    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message+"\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 5);
    }


    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInseconds)
    {
        WebElement element = waitForElementPresent( by,  error_message,  timeoutInseconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndClick(By by, String error_message)
    {
        return waitForElementAndClick( by,  error_message, 5);
    }



    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInseconds)
    {
        WebElement element = waitForElementPresent( by,  error_message,  timeoutInseconds);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message)
    {
        return waitForElementAndSendKeys(by, value, error_message, 5);
    }


    public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message,timeoutInSeconds);
        element.clear();
        return element;
    }

    public void assertElementHasText(By by, String expected_text, String error_message)
    {
        WebElement element = waitForElementPresent(by, "An element is not present", 5);
        String actual_text = element.getAttribute("text");
        Assert.assertEquals(
                error_message,
                expected_text,
                actual_text
        );
    }

    public void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y =(int) (size.height * 0.8);
        int end_y =(int) (size.height * 0.2);

        action.press(x,start_y).waitAction(timeOfSwipe).moveTo(x,end_y).release().perform();
    }

    public void quickSwipeUp()
    {
        swipeUp(200);
    }

    public void swipeUpToFindElement(By by,String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0)
        {
            if (already_swiped > max_swipes)
            {
                waitForElementPresent(by,"Can`t find element by swiping up \n" + error_message, 0);
                return;
            }
            quickSwipeUp();
            already_swiped++;
        }
    }
}
