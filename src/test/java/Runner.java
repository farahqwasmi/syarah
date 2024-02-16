
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;

import static helpers.webDriverHelper.quitDriver;

@CucumberOptions(

        features = "src/main/resources/features", glue = "stepsDef", tags = "@Test"

)

public class Runner extends AbstractTestNGCucumberTests {


    @AfterClass
    public void driverKiller(){
        quitDriver();    }
}