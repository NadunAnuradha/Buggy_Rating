package auto.testcases;

import auto.pages.LoginPage;
import auto.utility.Init;
import org.testng.annotations.Test;

public class TestLogin extends Init{

    @Test
    public void testLoginSuccess() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLogin("nadunilam", "blackiR1!");
    }

    @Test
    public void testLoginWithInvalidUser() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLoginInvalidCredentials("abcdef", "abcdef");
    }

    @Test
    public void testLogOut() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLogout("nadunilam", "blackiR1!");
    }
}