package auto.utility;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;


public class Init extends Driverbase {
    protected WebDriver driver = null;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){

        driver = getDriver();
        driver.get(Configfile.url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {

        if (driver != null)
            driver.close();

    }
}