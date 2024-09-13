package page_object_model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class ConfirmOrderPage {

    private final WebDriver driver;

    private final By yesButton = By.xpath(".//div[@class = 'Order_Modal__YZ-d3']/div[@class = 'Order_Buttons__1xGrp']/button[2]");

    private final By orderHeader = By.xpath(".//div[@class = 'Order_ModalHeader__3FDaJ']");

    public ConfirmOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickYesButton(){
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(yesButton)).get(0).click();
    }

    public void isOderDone(){
        assertEquals("что то пошло не так на последнем этапе, скорее всего это баг гуглхрома", driver.findElement(orderHeader).getText(), "Заказ оформлен");
    }

}
