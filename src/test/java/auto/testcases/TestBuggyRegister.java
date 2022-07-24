package auto.testcases;

import auto.pages.BuggyRegisterPage;
import auto.utility.Init;
import org.testng.annotations.Test;

public class TestBuggyRegister extends Init {


    @Test
    public void testBuggyRegister() throws InterruptedException {
        BuggyRegisterPage buggyRegisterPage = new BuggyRegisterPage(driver);
        buggyRegisterPage.VerifyBuggyRegister();

    }
}
