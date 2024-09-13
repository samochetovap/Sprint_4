package page_object_model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class UserInOrderDataPage {

    private final WebDriver driver;

    private final By userNameField = By.xpath(".//div[@class = 'Order_Form__17u6u']/div[1]/input");

    private final By userSurNameField = By.xpath(".//div[@class = 'Order_Form__17u6u']/div[2]/input");

    private final By userAddressField = By.xpath(".//div[@class = 'Order_Form__17u6u']/div[3]/input");

    private final By userMetroField = By.xpath(".//div[@class = 'Order_Form__17u6u']/div[4]/div/div/input");

    private final By userPhoneField = By.xpath(".//div[@class = 'Order_Form__17u6u']/div[5]/input");

    private final By nextButton = By.xpath(".//div[@class = 'Order_NextButton__1_rCA']/button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    private final By validates = By.xpath(".//*[contains(@class, 'Input_Error__1Tx5d')]");

    private final By metroStations = By.xpath(".//div/div/div[@class = 'select-search__select']/ul/li");


    public UserInOrderDataPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadUserInOrderData() {
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(driver -> (driver.findElement(userNameField).getAttribute("value") != null
        ));
    }

    public void setUserNameFieldValue(String value) {
        driver.findElement(userNameField).sendKeys(value);
    }

    public void setUserSurNameField(String value) {
        driver.findElement(userSurNameField).sendKeys(value);
    }

    public void setUserAddressField(String value) {
        driver.findElement(userAddressField).sendKeys(value);
    }

    public void selectRandomUserMetroIntoField() {
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(userMetroField)).click();
        List<WebElement> metroList = new WebDriverWait(driver, Duration.ofSeconds(20)).until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(metroStations));

        WebElement webElement = metroList.get(new Random().nextInt(metroList.size()));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        webElement.click();
    }

    public void setUserPhoneField(String value) {
        driver.findElement(userPhoneField).sendKeys(value);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
        validateFields();
    }

    private void validateFields() {
        assertTrue("Поля не прошли валидацию", driver.findElements(validates).isEmpty());
    }

}
