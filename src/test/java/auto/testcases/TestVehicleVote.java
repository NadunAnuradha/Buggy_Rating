package auto.testcases;

import auto.pages.VehicleVotePage;
import org.testng.annotations.Test;
import auto.utility.Init;
import auto.pages.LoginPage;

public class TestVehicleVote extends Init{


    @Test
    public void testVehicleVote() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLogin("nadunilam", "blackiR1!");
        VehicleVotePage VehicleVote = new VehicleVotePage(driver);
        VehicleVote.VerifyVehicleVote();
    }

    @Test
    public void testPopularModel() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLogin("nadunilam1", "blackiR1!!");
        VehicleVotePage VehicleVote = new VehicleVotePage(driver);
        VehicleVote.VerifyPopularModel();
    }
}
