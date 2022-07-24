package auto.pages;

import auto.utility.Services;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends Services {

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    final static String MSG_SUCCESS = "Nadun";
    final static String MSG_ERROR = "Invalid username/password";


    @FindBy(name = "login")
    private WebElement txtUserName;

    @FindBy(name = "password")
    private WebElement txtPassword;

    @FindBy(css = "button.btn.btn-success")
    private WebElement btnLogin;

    @FindBy(css = "span.nav-link.disabled")
    private WebElement actualsucssessMsg;

    @FindBy(css = "span.label.label-warning")
    private WebElement actualerrorMsg;

    @FindBy(linkText = "Logout")
    private WebElement btnLogout;

    public void verifyLogin(String username, String password) throws InterruptedException {

        clearAndType(txtUserName, username);
        clearAndType(txtPassword, password);
        click(btnLogin);
        if (isElementDisplayed(actualsucssessMsg))
        assertTrue(getText(actualsucssessMsg).contains(MSG_SUCCESS),
                "Actual '" + getText(actualsucssessMsg) + "' should be same as expected '" + MSG_SUCCESS + "'.");
        else
            assertTrue(false);
    }

    public void verifyLoginInvalidCredentials(String username, String password) {

        clearAndType(txtUserName, username);
        clearAndType(txtPassword, password);
        click(btnLogin);
        assertTrue(getText(actualerrorMsg).contains(MSG_ERROR),
                "Actual '" + getText(actualerrorMsg) + "' should be same as expected '" + MSG_ERROR + "'.");
    }

    public void verifyLogout(String username, String password) {

        clearAndType(txtUserName, username);
        clearAndType(txtPassword, password);
        click(btnLogin);
        click(btnLogout);
        if (isElementDisplayed(txtUserName))
            assertTrue(true, "Logout Successful");
    }
}
