package auto.pages;


import auto.utility.Services;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class VehicleVotePage extends Services {

    public VehicleVotePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    final static  String SUCCESS_MSG = "Thank you for your vote!";
    final static  String null_MSG = "You entered: null";


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



    public void VerifyVehicleVote() throws InterruptedException
    {
        btnRegisteredModels.click();

        int rowCount = driver.findElements(By.xpath("//table/tbody/tr")).size();

        String strArray[]= getRecordCount.getText().split("of");
        int pageCount = Integer.parseInt(strArray[1].trim());

            for (int i = 1; i <= rowCount; i++)
            {
                driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[7]/a")).click();
                if (isElementDisplayed(txtComment))
                {
                    txtComment.sendKeys("TEST COMMENT" + java.time.LocalDate.now() + " " + java.time.LocalTime.now());
                    btnSuccess.click();
                    assertTrue(getText(textSuccessMsg).contains(SUCCESS_MSG),
                            "Actual '" + getText(textSuccessMsg) + "' should be same as expected '" + SUCCESS_MSG + "'.");

                    break;
                }
                driver.navigate().back();


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
