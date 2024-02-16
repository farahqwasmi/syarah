package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * The webDriverHelper class provides methods to manage the WebDriver instance,
 * including starting and quitting the ChromeDriver.
 */
public class WebDriverHelper {

    // Static ChromeDriver instance
    static ChromeDriver driver = null;

    // Static block to start the WebDriver when the class is loaded
    static {
        startWebDriver();
    }

    /**
     * Gets the current WebDriver instance.
     *
     * @return The WebDriver instance.
     */
    public static WebDriver getWebDriver() {
        return driver;
    }

    /**
     * Starts the ChromeDriver with specific configurations.
     */
    public static void startWebDriver() {
        // Chrome options to configure the ChromeDriver
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-web-security");
        chromeOptions.addArguments("--test-type");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--remote-allow-origins=*");

        // Setup WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Create a new ChromeDriver with the specified options
        driver = new ChromeDriver(chromeOptions);

        // Maximize the browser window
        driver.manage().window().maximize();
    }

    /**
     * Quits the current WebDriver instance.
     */
    public static void quitDriver() {
        driver.quit();
    }
}