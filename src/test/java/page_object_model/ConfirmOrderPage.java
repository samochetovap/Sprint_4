package page_object_model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConfirmOrderPage {

    private final WebDriver driver;

    private final By yesButton = By.xpath(".//div[@class = 'Order_Modal__YZ-d3']/div[@class = 'Order_Buttons__1xGrp']/button[2]");

    private final By lookAtStatusButton = By.xpath(".//div[@class = 'Order_NextButton__1_rCA']/button[@class ='Button_Button__ra12g Button_Middle__1CSJM']");

    public ConfirmOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickYesButton(){
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(yesButton)).get(0).click();
    }

    public void isOderDone(){
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(
                ExpectedConditions.visibilityOfElementLocated(lookAtStatusButton)).isDisplayed();
    }

}
