package stepsDef;

import helpers.ExtentReportBuilder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.carReservationPage;

import static helpers.CommonFunctions.navigateTo;
import static helpers.ConfigReader.getString;

public class carReservationStepsDef extends ExtentReportBuilder {
    @Given("toyota car model")
    public void chooseToyota()
    {
        buildTest(" car rent case");
        navigateTo(getString("url"));
        carReservationPage.navigateToToyotaPage();
    }
    @When("car selected")
    public void selectCar()
    {
        carReservationPage.selectCarToReserve();
    }
    @And("click reservation and go through checkout flow")
    public void checkout()
    {
        carReservationPage.carDetailsAndCheckout();
    }
    @And("validate prices in checkout page with car details page")
    public void validatePrice() {}
    @And("enter user information through checkout page")
    public void userInfo() {}
    @And("go to payment and enter Credit card number")
    public void paymentFakeData()
    {
        carReservationPage.fakeCardPayment();
    }
    @Then("car will be reserved successfully")
    public void carRentFinished()
    {
        flushReport();
    }
}
