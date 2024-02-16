package helpers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * Helper class for building and managing Extent Reports.
 */
public class ExtentReportBuilder {
    ExtentReports extent;
    ExtentSparkReporter spark;
    static ExtentTest test;

    /**
     * Initializes the Extent Reports and attaches the ExtentSparkReporter.
     */
    @BeforeTest
    public void extentReportCreator()
    {
        extent = new ExtentReports();
        spark = new ExtentSparkReporter("Spark.html");
        extent.attachReporter(spark);
    }

    /**
     * Builds a test in the Extent Report with the given caseName.
     *
     * @param caseName The name of the test case.
     */
    public void buildTest(String caseName)
    {
        if (extent != null)
            test = extent.createTest(caseName);
        else {
            extentReportCreator();
            test = extent.createTest(caseName);
        }
    }

    /**
     * Logs a passed status in the Extent Report.
     *
     * @param txt The log message.
     */
    public void logsPassed(String txt)
    {
        test.log(Status.PASS, txt);
    }

    /**
     * Logs a failed status in the Extent Report.
     *
     * @param txt The log message.
     */
    public void logFailed(String txt)
    {
        test.log(Status.FAIL, txt);
    }

    /**
     * Logs an informational status in the Extent Report.
     *
     * @param txt The log message.
     */
    public static void logInfo(String txt)
    {
        test.log(Status.INFO, txt);
    }

    /**
     * Flushes the Extent Report, ensuring all logs are written.
     */
    @AfterTest
    public void flushReport()
    {
        extent.flush();
    }
}