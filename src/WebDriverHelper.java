import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
}
