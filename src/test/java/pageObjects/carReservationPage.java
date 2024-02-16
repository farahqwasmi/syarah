package pageObjects;

import helpers.CommonFunctions;
import helpers.WebDriverHelper;
import org.openqa.selenium.By;

import static org.testng.AssertJUnit.assertEquals;

public class carReservationPage extends CommonFunctions {
    private final static By toyotaModel =By.xpath("//*[@href='/en/autos/toyota' or @href='/autos/toyota' ]");
    private final static By modelsTitle=By.cssSelector("section  h2");
    private final static By firstToyotaCar =By.cssSelector(" div.allCarsResult a:nth-child(1)"); // optimized css locator , helps on make new UI changes failure less than usual
    private final static By buyNowBtn =By.xpath("//span[contains(.,'احجزها الآن') or contains(.,'Book it now')]");
    private final static By cashOption =By.xpath("//*[@value=\"1\"]");
    private final static By nextButton =By.xpath("//button[contains(text(), 'Next ') or contains(.,'متابعة')]");
    private final static By userName =By.xpath("//input[@name='full_name']");
    private final static By phone =By.xpath("//input[@name='phone_number']");
    private final static By continueToPayment =By.xpath("//button[@type='submit']");
    private final static By cardHolderName =By.id("mysr-cc-name");
    private final static By cardNumber =By.id("mysr-cc-number");
    private final static By cvv =By.cssSelector("input.mysr-form-input.mysr-form-cardInfoElement.mysr-form-cardInfoHalfWidth.mysr-form-topLeftRadius0.mysr-form-topRightRadius0.mysr-form-bottomLeftRadius0");
    private final static By expireDate =By.cssSelector(" input.mysr-form-input.mysr-form-cardInfoElement.mysr-form-cardInfoHalfWidth.mysr-form-topLeftRadius0.mysr-form-topRightRadius0.mysr-form-bottomRightRadius0");

    private final static By submitCard =By.xpath(" //button[@class='mysr-form-button']");

    private final static By carPriceDetailsPage =By.xpath("(//strong[contains(.,'سعر الكاش') or contains(.,'Cash Price')]/following-sibling::strong)[2]");

    private final static By carPriceCheckoutPage =By.xpath("//span[contains(.,'قيمة السيارة') or contains(.,'Car Price')]/following-sibling::strong");


    /**
     * Navigates to the Toyota page.
     * This method scrolls to the models title, waits for the Toyota model to be present and clickable, then clicks on it.
     */
    public static void navigateToToyotaPage(){
      scrollTo(element(modelsTitle));
      explicitlyWaitForWebElement("PRESENCE" , toyotaModel);
      explicitlyWaitForWebElement("CLICKABLE" , toyotaModel);
        click(toyotaModel);
    }
    /**
     * Selects a car to reserve.
     * This method waits for the first Toyota car to be clickable and then clicks on it.
     */
    public static void selectCarToReserve(){
        implicitlyWaitBySeconds(20);
        scrollTo(element(firstToyotaCar));
        explicitlyWaitForWebElement("CLICKABLE" , firstToyotaCar);

        click(firstToyotaCar);

    }
    /**
     * Performs car details and checkout.
     * This method checks the car price details, clicks on 'Buy Now' button, verifies the checkout price,
     * selects the cash payment option, clicks 'Next', enters user details, and proceeds to payment.
     */
    public static void carDetailsAndCheckout(){


        explicitlyWaitForWebElement("VISIBLE" , carPriceDetailsPage);
        String strDetailsPrice = element(carPriceDetailsPage).getText();

        forceClick(buyNowBtn );
     explicitlyWaitForWebElement("VISIBLE" , carPriceCheckoutPage);
        String [] strCheckoutPriceArr = element(carPriceCheckoutPage).getText().split(" ");
        String strCheckoutPrice;
        String currentUrl = WebDriverHelper.getWebDriver().getCurrentUrl();
       if (currentUrl.contains("/en/"))
           strCheckoutPrice = strCheckoutPriceArr[0]+strCheckoutPriceArr[1];
          else
           strCheckoutPrice = strCheckoutPriceArr[0];

        assertEquals(strCheckoutPrice,strDetailsPrice);


        forceClick(cashOption);

        scrollTo(element(nextButton));
        explicitlyWaitForWebElement("CLICKABLE" , nextButton);
        click(nextButton);


        scrollTo(element(userName));
        explicitlyWaitForWebElement("PRESENCE" , userName);
        sendKeys(userName , "test user");
        scrollTo(element(phone));
        explicitlyWaitForWebElement("PRESENCE" , phone);
        sendKeys(phone , "0512345678");


        scrollTo(element(continueToPayment));
        implicitlyWaitBySeconds(5);

        explicitlyWaitForWebElement("CLICKABLE" , continueToPayment);
        retryClick(continueToPayment , 2);


    }
    /**
     * Performs fake card payment.
     * This method enters fake card details and submits the payment form.
     */
    public static void fakeCardPayment(){
        explicitlyWaitForWebElement("PRESENCE" , cardHolderName);
        sendKeys(cardHolderName , "test card holder");

        explicitlyWaitForWebElement("PRESENCE" , cardNumber);
        sendKeys(cardNumber , "4242424242424242");

        explicitlyWaitForWebElement("PRESENCE" , cvv);
        sendKeys(cvv , "100");

        explicitlyWaitForWebElement("PRESENCE" , expireDate);
        sendKeys(expireDate , "0226");


       forceClick(submitCard);


    }

}
