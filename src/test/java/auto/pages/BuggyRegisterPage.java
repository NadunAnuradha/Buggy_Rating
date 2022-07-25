package auto.pages;

import auto.utility.Services;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;

public class BuggyRegisterPage extends Services {
    public BuggyRegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    final static String MSG_SUCCESS = "Registration is successful";

    @FindBy(name = "username")
    private WebElement txtUsername;

    @FindBy(name = "firstName")
    private WebElement txtFirstname;

    @FindBy(name = "lastName")
    private WebElement txtLastname;

    @FindBy(id = "password")
    private WebElement txtPassword;

    @FindBy(name = "confirmPassword")
    private WebElement txtConfirmPassword;

    @FindBy(xpath = "//div/div/form/button")
    private WebElement btnRegister;


    @FindBy(css = "a.btn.btn-success-outline")
    private WebElement btnRegisterHome;

    @FindBy(css = "div.result.alert.alert-success")
    private WebElement msgSuccess;


    public void VerifyBuggyRegister() throws InterruptedException {
        btnRegisterHome.click();
        //Please note the values are hardcoded for the moment
        clearAndType(txtUsername, "nadunilamNew");
        clearAndType(txtFirstname, "NEW");
        clearAndType(txtLastname, "NEWONE");
        clearAndType(txtPassword, "blackiR1!11");
        clearAndType(txtConfirmPassword, "blackiR1!11");
        btnRegister.click();
        if (isElementDisplayed(msgSuccess)) {
            assertTrue(getText(msgSuccess).contains(MSG_SUCCESS),
                    "Actual '" + getText(msgSuccess) + "' should be same as expected '" + MSG_SUCCESS + "'.");
        } else {
            assertTrue(false);
        }
    }
}
