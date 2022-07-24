package auto.utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Services {

    protected WebDriver driver;

    public Services(WebDriver driver) {
        this.driver = driver;
    }

    public void clearAndType(WebElement txtElement, String inputText) {
        try {
            txtElement.clear();
            txtElement.sendKeys(inputText);
        } catch (Exception e) {

        }
    }

    public void click(WebElement webElement) {
        try {
            webElement.click();
        } catch (Exception E) {

        }
    }


    public String getText(WebElement webElement) {
        try {
            webElement.getText().trim();
        } catch (Exception E) {

        }
        return webElement.getText().trim();
    }

    public boolean isElementDisplayed(WebElement webElement) {
        try {
            if (webElement.isDisplayed()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
