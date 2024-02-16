package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Helper class containing common functions for Selenium WebDriver operations.
 * Extends the ExtentReportBuilder for logging purposes.
 */
public class CommonFunctions extends ExtentReportBuilder {

    /**
     * Finds a WebElement by the given By locator.
     *
     * @param by The By locator to identify the WebElement.
     * @return The WebElement found.
     */
    public static WebElement element(By by)
    {
        return WebDriverHelper.getWebDriver().findElement(by);
    }

    /**
     * Navigates to the specified URL.
     *
     * @param url The URL to navigate to.
     */
    public static void navigateTo(String url)
    {
        WebDriverHelper.getWebDriver().navigate().to(url);
        logInfo("Navigate to " + url);
    }

    /**
     * Types text into a WebElement identified by the given By locator.
     *
     * @param by   The By locator to identify the WebElement.
     * @param text The text to be typed into the WebElement.
     */
    public static void sendKeys(By by, String text)
    {
        forceClick(by);
        element(by).sendKeys(text);
        logInfo("Enter " + text);
    }

    /**
     * Clicks on a WebElement identified by the given By locator.
     *
     * @param by The By locator to identify the WebElement.
     */
    protected static void click(By by)
    {
        logInfo("Click on " + element(by).getText());
        element(by).click();
    }

    /**
     * Forcefully clicks on a WebElement identified by the given By locator using JavaScript.
     *
     * @param by The By locator to identify the WebElement.
     */
    public static void forceClick(By by)
    {
        JavascriptExecutor executor = (JavascriptExecutor) WebDriverHelper.getWebDriver();
        executor.executeScript("arguments[0].click();", element(by));
    }

    /**
     * Clicks on a WebElement identified by the given By locator with retry mechanism.
     *
     * @param by    The By locator to identify the WebElement.
     * @param retry The number of retry attempts.
     */
    public static void retryClick(By by, int retry)
    {
        for (int i = 0; i <= retry; i++) {
            try {
                element(by).click();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Explicitly waits for a WebElement based on the provided wait strategy.
     *
     * @param waitStrategy The wait strategy ("CLICKABLE", "PRESENCE", or "VISIBLE").
     * @param webElement   The By locator to identify the WebElement.
     */
    public static void explicitlyWaitForWebElement(String waitStrategy, By webElement)
    {
        WebElement element = null;
        waitStrategy = waitStrategy.toLowerCase();
        waitStrategy = waitStrategy.toUpperCase();
        switch (waitStrategy) {
            case "CLICKABLE":
                element = new WebDriverWait(WebDriverHelper.getWebDriver(), Duration.ofSeconds(40))
                        .until(ExpectedConditions.elementToBeClickable(webElement));
                break;
            case "PRESENCE":
                element = new WebDriverWait(WebDriverHelper.getWebDriver(), Duration.ofSeconds(40))
                        .until(ExpectedConditions.presenceOfElementLocated(webElement));
                break;
            case "VISIBLE":
                element = new WebDriverWait(WebDriverHelper.getWebDriver(), Duration.ofSeconds(40))
                        .until(ExpectedConditions.visibilityOf(element(webElement)));
                break;
        }
    }

    /**
     * Implicitly waits for a specified number of seconds.
     *
     * @param time The number of seconds to wait implicitly.
     */
    public static void implicitlyWaitBySeconds(int time)
    {
        WebDriverHelper.getWebDriver().manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    /**
     * Scrolls to a WebElement using JavaScript.
     *
     * @param element The WebElement to scroll to.
     */
    public static void scrollTo(WebElement element)
    {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverHelper.getWebDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }
}