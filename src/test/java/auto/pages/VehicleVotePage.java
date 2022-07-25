package auto.pages;


import auto.utility.Services;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertTrue;

public class VehicleVotePage extends Services {

    public VehicleVotePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    final static  String SUCCESS_MSG = "Thank you for your vote!";



    @FindBy(css = "button.btn.btn-success")
    private WebElement btnSuccess;

    @FindBy(id = "comment")
    private WebElement txtComment;

    @FindBy(xpath = "//div[3]/div/a/img")
    private WebElement btnRegisteredModels;

    @FindBy(linkText = "Buggy Rating")
    private WebElement btnBuggyRating;

    @FindBy(xpath = "//input")
    private WebElement textPageNumber;


    @FindBy(css = "p.card-text")
    private WebElement textSuccessMsg;

    @FindBy(css = "div.row > div.pull-xs-right")
    private WebElement getRecordCount;


    @FindBy(xpath = "//tbody/tr[1]/td[2]")
    private WebElement textRankOneMake;

    @FindBy(xpath = "//tbody/tr[1]/td[3]")
    private WebElement textRankOneModel;

    @FindBy(xpath = "//tbody/tr[1]/td[5]")
    private WebElement textRankOneCount;
    @FindBy(xpath = "//div[2]/div/div/h3")
    private WebElement textPModuleVotes;

    @FindBy(xpath ="//a[contains(text(),'»')]")
    private WebElement moveToNext;

    @FindBy(css ="img.img-fluid.center-block")
    private WebElement PopularMake;

    @FindBy(xpath ="//small")
    private WebElement PopularMakeVal;


    public void VerifyPopularMakeVotes() throws InterruptedException {
        String storePMakeVotes = PopularMakeVal.getText();
        PopularMake.click();

        int rowCount = driver.findElements(By.xpath("//table/tbody/tr")).size();
        int totalVotes=0;
        for (int i = 1; i <= rowCount; i++) {
            int Votes= Integer.parseInt(driver.findElement(By.xpath("//tbody/tr[" + i +"]/td[4]")).getText().trim());
            totalVotes=totalVotes+Votes;
        }
        assertTrue(storePMakeVotes.contains(Integer.toString(totalVotes)),
                "Actual '" + storePMakeVotes + "' should be same as expected '" + Integer.toString(totalVotes) + "'.");


    }

    public void VerifyVehicleVote() throws InterruptedException
    {
        btnRegisteredModels.click();

        int rowCount = driver.findElements(By.xpath("//table/tbody/tr")).size();

        String strArray[]= getRecordCount.getText().split("of");
        int pageCount = Integer.parseInt(strArray[1].trim());
        Boolean stop = false;

        for (int ii = 1; ii <= pageCount; ii++)
        {

            for (int i = 1; i <= rowCount; i++)
            {
                if (stop==true){break;}
                if ((ii>1)&&(stop==false)) {
                    // Navigating to the next page if all vehicles are already voted in the current page
                    clearAndType(textPageNumber, Integer.toString(ii));
                    Thread.sleep(3000);
                    textPageNumber.sendKeys(Keys.RETURN);
                    Thread.sleep(3000);
                }
                // Navigating to each vehicle and picking the vehicle which is not voted
                driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[7]/a")).click();
                if (isElementDisplayed(txtComment)) {
                    txtComment.sendKeys("TEST COMMENT" + java.time.LocalDate.now() + " " + java.time.LocalTime.now());
                    btnSuccess.click();
                    assertTrue(getText(textSuccessMsg).contains(SUCCESS_MSG),
                            "Actual '" + getText(textSuccessMsg) + "' should be same as expected '" + SUCCESS_MSG + "'.");
                    stop =true;
                    break;
                }
                driver.navigate().back();

            }

        }
    }

    public void VerifyPopularModel() throws InterruptedException {
        String storePModuleVotes = textPModuleVotes.getText();
        btnRegisteredModels.click();
        //assertVote
        assertTrue(storePModuleVotes.contains(textRankOneCount.getText()));
        //assertModel
        assertTrue(storePModuleVotes.contains(textRankOneModel.getText()));
        //assertMake
        assertTrue(storePModuleVotes.contains(textRankOneMake.getText()));

    }

}
